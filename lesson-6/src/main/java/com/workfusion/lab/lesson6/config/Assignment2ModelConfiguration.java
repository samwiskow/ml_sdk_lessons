/*
 * Copyright (C) WorkFusion 2018. All rights reserved.
 */
package com.workfusion.lab.lesson6.config;

import java.util.Collection;
import java.util.Collections;

import com.workfusion.vds.sdk.api.hypermodel.annotation.ModelConfiguration;
import com.workfusion.vds.sdk.api.nlp.fe.Feature;
import com.workfusion.vds.sdk.api.nlp.fe.FeatureExtractor;
import com.workfusion.vds.sdk.api.nlp.model.Document;
import com.workfusion.vds.sdk.api.nlp.model.Element;

/**
 * Assignment 2
 */
@ModelConfiguration
public class Assignment2ModelConfiguration {

    // TODO:  PUT YOU CODE HERE

    public static class IsAllLettersUpperCase<T extends Element> implements FeatureExtractor<T> {

        private static final String FEATURE_NAME = "IsUpperCaseLettersOnly";

        @Override
        public Collection<Feature> extract(Document document, T element) {
            String text = element.getText();
            if (text.matches("[A-Z]+")) {
                // if text contains only upper case letters, return a feature
                return Collections.singletonList(new Feature(FEATURE_NAME, 1));
            }
            // otherwise return empty list
            return Collections.emptyList();
        }

    }

    public static class IsOnlyNumberInTokenFE<T extends Element> implements FeatureExtractor<T> {

        private static final String FEATURE_NAME = "IsNumbersOnly";

        @Override
        public Collection<Feature> extract(Document document, T element) {
            String text = element.getText();
            if (text.matches("\\d+")) {
                // if text contains only digits, return a feature
                return Collections.singletonList(new Feature(FEATURE_NAME, 1));
            }
            // otherwise return empty list
            return Collections.emptyList();
        }
    }

}