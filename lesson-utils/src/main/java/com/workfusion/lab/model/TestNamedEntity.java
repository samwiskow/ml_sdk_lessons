package com.workfusion.lab.model;

import org.apache.commons.lang3.StringUtils;

import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;
import com.workfusion.vds.sdk.api.nlp.model.NamedEntity;

public class TestNamedEntity extends TestElement {

    private String type;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(super.hashCode(), type);
    }

    @Override
    public boolean equals(Object obj) {
        TestNamedEntity other = (TestNamedEntity) obj;
        return super.equals(obj)
                && Objects.equal(this.type, other.type);
    }

    @Override
    public String toString() {
        int maxPrintedTextSize = 100;
        return MoreObjects.toStringHelper(NamedEntity.class)
                .add("begin", begin)
                .add("end", end)
                .add("text (first " + maxPrintedTextSize + " symbols)", StringUtils.abbreviate(text, maxPrintedTextSize))
                .add("type", type)
                .toString();
    }


}
