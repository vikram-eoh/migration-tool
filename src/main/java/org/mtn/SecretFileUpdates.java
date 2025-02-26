package org.mtn;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import com.fasterxml.jackson.dataformat.yaml.YAMLGenerator;
import org.mtn.model.secret.Data;
import org.mtn.model.secret.K8Secret;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class SecretFileUpdates {

    public static void updateSecretFile(Path secretFilePath) throws IOException {
        YAMLFactory yamlFactory = new YAMLFactory().enable(YAMLGenerator.Feature.MINIMIZE_QUOTES).disable(YAMLGenerator.Feature.WRITE_DOC_START_MARKER);
        ObjectMapper mapper = new ObjectMapper(yamlFactory);
        mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        mapper.findAndRegisterModules();
        File secretFilePathFile = secretFilePath.toFile();
        K8Secret k8Secret = mapper.readValue(secretFilePathFile, K8Secret.class);
        List<Data> data = k8Secret.getSpec().data;
        data.forEach(data1 -> {
            data1.remoteRef.key = "secret/" + data1.remoteRef.key.replace("secret/data/", "").replace("/", "--").replaceAll("\\.", "-").replaceAll("_", "-") + "--"
                    + data1.remoteRef.property.replaceAll("\\.", "-").replaceAll("_", "-");
            data1.remoteRef.property = null;
        });
        String outputContent = mapper.writeValueAsString(k8Secret);
        Files.writeString(secretFilePath, outputContent);

    }
}
