package com.workfusion.lab.model;

import com.google.common.base.MoreObjects;
import com.workfusion.vds.sdk.api.nlp.model.Sentence;

public class TestSentence extends TestElement {
    @Override
    public String toString() {
        return MoreObjects.toStringHelper(Sentence.class)
                .add("begin", begin)
                .add("end", end)
                .add("text", text)
                .toString();
    }
}
