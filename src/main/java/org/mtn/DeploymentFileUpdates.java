package org.mtn;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import com.fasterxml.jackson.dataformat.yaml.YAMLGenerator;
import org.mtn.model.deployment.Deployment;
import org.mtn.model.deployment.Env;
import org.mtn.model.deployment.SecretKeyRef;
import org.mtn.model.deployment.ValueFrom;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

//Note: I tried this way as well but in this way we encounter with lot of diffrent yaml tags and to keep all these intact is difficult, so used the other way of doing this: @DeploymentFileByReplace
public class DeploymentFileUpdates {
    public static void updateFile(Path secretfilepath) throws IOException {
        YAMLFactory yamlFactory = new YAMLFactory().enable(YAMLGenerator.Feature.MINIMIZE_QUOTES).disable(YAMLGenerator.Feature.WRITE_DOC_START_MARKER);
        ObjectMapper mapper = new ObjectMapper(yamlFactory);
        mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        mapper.findAndRegisterModules();
        File secretFilePathFile = secretfilepath.toFile();
        Deployment deployment = mapper.readValue(secretFilePathFile, Deployment.class);
        List<Env> envList = deployment.getSpec().getTemplate().getSpec().getContainers().get(0).getEnv();
        addNewDataBaseEnvVarialbes(envList);
        String outputContent = mapper.writeValueAsString(deployment);
        Files.writeString(secretfilepath, outputContent);

    }

    private static void addNewDataBaseEnvVarialbes(List<Env> envList) {
        Env databaseUsername = getEnv("PREPROD_MONGODB_ATLAS_USERNAME", "mongodb-atlas-secrets", "username");
        Env databaseAppName = getEnv("PREPROD_MONGODB_ATLAS_APPNAME", "mongodb-atlas-secrets", "appname");
        Env databasePassword = getEnv("PREPROD_MONGODB_ATLAS_PASSWORD", "mongodb-atlas-secrets", "password");
        Env databaseUrl = getEnv("PREPROD_MONGODB_ATLAS_URL", "mongodb-atlas-secrets", "url");
        envList.add(databaseAppName);
        envList.add(databaseUsername);
        envList.add(databasePassword);
        envList.add(databaseUrl);
    }

    private static Env getEnv(String envVariableName, String secretRefName, String secretRefKey) {
        Env env = new Env();
        env.setName(envVariableName);
        ValueFrom valueFrom = new ValueFrom();
        SecretKeyRef secretKeyRef = new SecretKeyRef();
        secretKeyRef.setName(secretRefName);
        secretKeyRef.setKey(secretRefKey);
        valueFrom.setSecretKeyRef(secretKeyRef);
        env.setValueFrom(valueFrom);
        return env;
    }
}
