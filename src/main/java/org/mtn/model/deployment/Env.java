package org.mtn.model.deployment;

public class Env {
    private String name;
    private String value;
    private ValueFrom valueFrom;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public ValueFrom getValueFrom() {
        return valueFrom;
    }

    public void setValueFrom(ValueFrom valueFrom) {
        this.valueFrom = valueFrom;
    }
}
