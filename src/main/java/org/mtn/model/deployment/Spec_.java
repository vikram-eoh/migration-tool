package org.mtn.model.deployment;

import java.util.ArrayList;
import java.util.List;

public class Spec_ {
    private List<Container> containers = new ArrayList<Container>();
    private String restartPolicy;

    public List<Container> getContainers() {
        return containers;
    }

    public void setContainers(List<Container> containers) {
        this.containers = containers;
    }

    public String getRestartPolicy() {
        return restartPolicy;
    }

    public void setRestartPolicy(String restartPolicy) {
        this.restartPolicy = restartPolicy;
    }
}
