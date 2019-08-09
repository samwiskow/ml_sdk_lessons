/*
 * Copyright (C) WorkFusion 2018. All rights reserved.
 */
package com.workfusion.lab.lesson2.annotator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.workfusion.vds.sdk.api.nlp.annotator.Annotator;
import com.workfusion.vds.sdk.api.nlp.model.Document;
import com.workfusion.vds.sdk.api.nlp.model.NamedEntity;

/**
 * Assignment 2
 */
public class Assignment2EmailNerAnnotator implements Annotator<Document> {

    /**
     * Regex to match an email.
     */
    private static final String EMAIL_REGEXP = "\\b[\\w.%-]+@[-.\\w]+\\.[A-Za-z]{2,4}\\b";

    /**
     * Type for {@link NamedEntity} to use.
     */
    private static final String NER_TYPE = "email";

    @Override
    public void process(Document document) {

    	Pattern pattern = Pattern.compile(EMAIL_REGEXP);
        Matcher matcher = pattern.matcher(document.getText());
        int index = 0;
        while (matcher.find()) {
            document.add(NamedEntity.descriptor()
            .setBegin(matcher.start())
            .setEnd(matcher.end())
            .setType(NER_TYPE));
        }

    }

}