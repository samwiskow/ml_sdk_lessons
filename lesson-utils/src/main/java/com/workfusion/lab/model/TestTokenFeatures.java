package com.workfusion.lab.model;

import java.util.Set;

import com.workfusion.vds.sdk.api.nlp.fe.Feature;

public class TestTokenFeatures {

    private TestElement element;
    private Set<Feature> features;

    public TestElement getElement() {
        return element;
    }

    public void setElement(TestElement element) {
        this.element = element;
    }

    public Set<Feature> getFeatures() {
        return features;
    }

    public void setFeatures(Set<Feature> features) {
        this.features = features;
    }

}
