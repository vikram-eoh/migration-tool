package org.mtn.model.deployment;

import java.util.ArrayList;
import java.util.List;

public class Container {
    private String image;
    private String name;
    private Resources resources;
    private LivenessProbe livenessProbe;
    private ReadinessProbe readinessProbe;
    private List<Env> env = new ArrayList<Env>();
    private List<String> command;
    private List<String> args;
    private String imagePullPolicy;
    private List<Port> ports;

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Resources getResources() {
        return resources;
    }

    public void setResources(Resources resources) {
        this.resources = resources;
    }

    public LivenessProbe getLivenessProbe() {
        return livenessProbe;
    }

    public void setLivenessProbe(LivenessProbe livenessProbe) {
        this.livenessProbe = livenessProbe;
    }

    public ReadinessProbe getReadinessProbe() {
        return readinessProbe;
    }

    public void setReadinessProbe(ReadinessProbe readinessProbe) {
        this.readinessProbe = readinessProbe;
    }

    public List<Env> getEnv() {
        return env;
    }

    public void setEnv(List<Env> env) {
        this.env = env;
    }

    public String getImagePullPolicy() {
        return imagePullPolicy;
    }

    public void setImagePullPolicy(String imagePullPolicy) {
        this.imagePullPolicy = imagePullPolicy;
    }

    public List<Port> getPorts() {
        return ports;
    }

    public void setPorts(List<Port> ports) {
        this.ports = ports;
    }

    public List<String> getCommand() {
        return command;
    }

    public void setCommand(List<String> command) {
        this.command = command;
    }

    public List<String> getArgs() {
        return args;
    }

    public void setArgs(List<String> args) {
        this.args = args;
    }
}
