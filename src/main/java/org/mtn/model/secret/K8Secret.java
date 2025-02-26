package org.mtn.model.secret;

import lombok.Data;

@Data
public class K8Secret {
    private String apiVersion;
    private String kind;
    private Metadata metadata;
    private Spec spec;

}













