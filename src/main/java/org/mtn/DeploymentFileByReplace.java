package org.mtn;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class DeploymentFileByReplace {

    public static void updateFile(Path filePath) {
        try {
            String searchText = "name: config-server-username";
            List<String> lines = Files.readAllLines(filePath);
            List<String> modifiedLines = new ArrayList<>();
            for (int i = 0; i < lines.size(); i++) {
                String line = lines.get(i);
                if (line.contains(searchText)) {
                    addNewDataBaseEnvVarialbes(modifiedLines, lines, i);
                }
                modifiedLines.add(line);
            }
            Files.write(filePath, modifiedLines);
            System.out.printf("The file %s has been updated successfully.%n", filePath.toAbsolutePath());
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private static void addNewDataBaseEnvVarialbes(List<String> modifiedLines, List<String> lines, int i) {
        List<String> databaseUsername = getEnv(lines, i, "PREPROD_MONGODB_ATLAS_USERNAME", "mongodb-atlas-secrets", "username");
        List<String> databaseAppName = getEnv(lines, i, "PREPROD_MONGODB_ATLAS_APPNAME", "mongodb-atlas-secrets", "appname");
        List<String> databasePassword = getEnv(lines, i, "PREPROD_MONGODB_ATLAS_PASSWORD", "mongodb-atlas-secrets", "password");
        List<String> databaseUrl = getEnv(lines, i, "PREPROD_MONGODB_ATLAS_URL", "mongodb-atlas-secrets", "url");
        modifiedLines.addAll(databaseAppName);
        modifiedLines.addAll(databaseUsername);
        modifiedLines.addAll(databasePassword);
        modifiedLines.addAll(databaseUrl);
    }

    private static List<String> getEnv(List<String> lines, int i, String envVariableName, String secretRefName, String secretRefKey) {
        String line0 = lines.get(i).replace("config-server-username", envVariableName);
        String line1 = lines.get(i + 1);
        String line2 = lines.get(i + 2);
        String line3 = lines.get(i + 3).replace("madapi-config-server-v2-secrets", secretRefName);
        String line4 = lines.get(i + 4).replace("username", secretRefKey);
        List<String> newLines = new ArrayList<>();
        newLines.add(line0);
        newLines.add(line1);
        newLines.add(line2);
        newLines.add(line3);
        newLines.add(line4);
        return newLines;

    }
}
