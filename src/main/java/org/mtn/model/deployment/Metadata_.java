package org.mtn.model.deployment;

public class Metadata_ {
    private String creationTimestamp;
    private Labels_ labels;

    public Labels_ getLabels() {
        return labels;
    }

    public void setLabels(Labels_ labels) {
        this.labels = labels;
    }

    public String getCreationTimestamp() {
        return creationTimestamp;
    }

    public void setCreationTimestamp(String creationTimestamp) {
        this.creationTimestamp = creationTimestamp;
    }
}
