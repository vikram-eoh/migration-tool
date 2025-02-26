package org.mtn.model.secret;

public class ConfigData {
    private String oldDockerBasePath;
    private String newDockerBasePath;
    private String oldHostName;
    private String newHostName;

    public ConfigData(String oldDockerBasePath, String newDockerBasePath, String oldHostName, String newHostName) {
        this.oldDockerBasePath = oldDockerBasePath;
        this.newDockerBasePath = newDockerBasePath;
        this.oldHostName = oldHostName;
        this.newHostName = newHostName;
    }

    public String getOldDockerBasePath() {
        return oldDockerBasePath;
    }

    public String getNewDockerBasePath() {
        return newDockerBasePath;
    }

    public String getOldHostName() {
        return oldHostName;
    }

    public String getNewHostName() {
        return newHostName;
    }
}
