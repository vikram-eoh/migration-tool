package org.mtn.model.deployment;

public class Template {
    private Metadata_ metadata;
    private Spec_ spec;

    public Metadata_ getMetadata() {
        return metadata;
    }

    public void setMetadata(Metadata_ metadata) {
        this.metadata = metadata;
    }

    public Spec_ getSpec() {
        return spec;
    }

    public void setSpec(Spec_ spec) {
        this.spec = spec;
    }
}
