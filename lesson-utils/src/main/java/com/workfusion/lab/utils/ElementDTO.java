package com.workfusion.lab.utils;

import java.util.Set;

import com.google.common.base.Objects;
import com.workfusion.vds.sdk.api.nlp.fe.Feature;

public class ElementDTO {

    private int begin;
    private int end;
    private String text;
    private Set<Feature> features;

    public int getBegin() {
        return begin;
    }

    public int getEnd() {
        return end;
    }

    public String getText() {
        return text;
    }

    public ElementDTO setBegin(int begin) {
        this.begin = begin;
        return this;
    }

    public ElementDTO setEnd(int end) {
        this.end = end;
        return this;
    }

    public ElementDTO setText(String text) {
        this.text = text;
        return this;
    }


    public Set<Feature> getFeatures() {
        return features;
    }


    public ElementDTO setFeatures(Set<Feature> features) {
        this.features = features;
        return this;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(begin, end, text);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }

        ElementDTO other = (ElementDTO) obj;
        return Objects.equal(this.begin, other.begin)
                && Objects.equal(this.text, other.text)
                && Objects.equal(this.end, other.end);
    }

}
