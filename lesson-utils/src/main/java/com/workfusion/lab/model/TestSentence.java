package com.workfusion.lab.model;

import org.apache.commons.lang3.StringUtils;

import com.google.common.base.MoreObjects;
import com.workfusion.vds.sdk.api.nlp.model.Sentence;

public class TestSentence extends TestElement {
    @Override
    public String toString() {
        int maxPrintedTextSize = 100;
        return MoreObjects.toStringHelper(Sentence.class)
                .add("begin", begin)
                .add("end", end)
                .add("text (first " + maxPrintedTextSize + " symbols)", StringUtils.abbreviate(text, maxPrintedTextSize))
                .toString();
    }
}
