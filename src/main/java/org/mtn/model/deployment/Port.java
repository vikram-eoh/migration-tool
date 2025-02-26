package org.mtn.model.deployment;

public class Port {
    private Integer containerPort;
    private String protocol;
    public Integer getContainerPort() {
        return containerPort;
    }
    public void setContainerPort(Integer containerPort) {
        this.containerPort = containerPort;
    }
    public String getProtocol() {
        return protocol;
    }
    public void setProtocol(String protocol) {
        this.protocol = protocol;
    }
}