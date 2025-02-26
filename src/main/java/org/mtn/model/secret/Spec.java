package org.mtn.model.secret;

import java.util.List;

public class Spec{
    public String backendType;
    public List<Data> data;
    public String refreshInterval;
    public SecretStoreRef secretStoreRef;
    public Target target;
}