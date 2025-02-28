package org.mtn;

import org.mtn.model.secret.ConfigData;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class AksMigrationApplication {

    //Dev configs
    private final static String devOldDockerBasePath = "harbor.preprod.westeurope.api.mtn.com";
    private final static String devNewDockerBasePath = "crdplmaddevne21.azurecr.io";
    private final static String devOldHostName = "dev.preprod.westeurope.api.mtn.com";
    private final static String devNewHostName = "dev.northeurope.api.mtn.com";


    //UAT configs
    private final static String uatOldDockerBasePath = "harbor.preprod.westeurope.api.mtn.com";
    private final static String uatNewDockerBasePath = "crdplmaduatne21.azurecr.io";
    private final static String uatOldHostName = "preprod.preprod.westeurope.api.mtn.com";
    private final static String uatNewHostName = "uat.northeurope.api.mtn.com";


    //PROD Configs - Need to define

    private final static String dev = "DEV";
    private final static String uat = "UAT";


    private final static String currentEnv = uat;


    public static void main(String[] args) throws IOException {
        Map<String, ConfigData> configDataMap = new HashMap<>();
        ConfigData devConfigData = new ConfigData(devOldDockerBasePath, devNewDockerBasePath, devOldHostName, devNewHostName);
        configDataMap.put(dev, devConfigData);
        ConfigData uatConfigData = new ConfigData(uatOldDockerBasePath, uatNewDockerBasePath, uatOldHostName, uatNewHostName);
        configDataMap.put(uat, uatConfigData);

        ConfigData currentConfigData = configDataMap.get(currentEnv);

        //Define the absolute file path of resource/data
        String dataFile = "C:\\Users\\vikramsingh02\\Vikram\\Vikram\\Vikram\\Projects\\madapi\\AKSMigration\\src\\main\\resources\\data";

        List<String> fileNames = readFileNames(dataFile);

        List<Path> allFiles = fileNames.stream().map(FindAllFilesInFolder::getAllFiles).flatMap(Collection::stream).toList();
        allFiles.forEach(path -> replaceStringInFile(path, currentConfigData));
        allFiles.forEach(AksMigrationApplication::updateSecretFile);
        //This to add the MonogDB ATLAS secrets to deployment file
        //allFiles.forEach(AksMigrationApplication::updateDeploymentFile);
    }

    private static List<String> readFileNames(String dataFile) throws IOException {
        try (Stream<String> stream = Files.lines(Paths.get(dataFile))) {
            return stream.collect(Collectors.toList());
        }
    }

    public static void replaceStringInFile(Path filePath, ConfigData configData) {
        try {
            System.out.println("Changing host name and docker path: " + filePath.toAbsolutePath());
            Charset charset = StandardCharsets.UTF_8;
            String content = Files.readString(filePath, charset);
            content = content.replaceAll(configData.getOldDockerBasePath(), configData.getNewDockerBasePath());
            content = content.replaceAll(configData.getOldHostName(), configData.getNewHostName());
            content = content.replaceAll(configData.getOldHostName(), configData.getNewHostName());
            Files.writeString(filePath, content, charset);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(filePath);
        }
    }

    private static void updateSecretFile(Path filePath) {
        try {
            System.out.println("Changing secret file: " + filePath.toAbsolutePath());
            Charset charset = StandardCharsets.UTF_8;
            String content = Files.readString(filePath, charset);
            if (content.contains("kind: ExternalSecret")) {
                SecretFileUpdates.updateSecretFile(filePath);
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(filePath);
        }

    }

    private static void updateDeploymentFile(Path filePath) {
        try {
            System.out.println("Changing secret file: " + filePath.toAbsolutePath());
            Charset charset = StandardCharsets.UTF_8;
            String content = Files.readString(filePath, charset);
            if (content.contains("kind: Deployment")) {
                DeploymentFileByReplace.updateFile(filePath);
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(filePath);
        }

    }
}