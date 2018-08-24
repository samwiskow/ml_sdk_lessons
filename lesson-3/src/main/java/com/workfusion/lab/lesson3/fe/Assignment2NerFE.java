/*
 * Copyright (C) WorkFusion 2018. All rights reserved.
 */
package com.workfusion.lab.lesson3.fe;

import java.util.Collection;
import java.util.Collections;

import com.workfusion.vds.sdk.api.nlp.fe.Feature;
import com.workfusion.vds.sdk.api.nlp.fe.FeatureExtractor;
import com.workfusion.vds.sdk.api.nlp.model.Document;
import com.workfusion.vds.sdk.api.nlp.model.Element;
import com.workfusion.vds.sdk.api.nlp.model.NamedEntity;

/**
 * Assignment 2
 */
public class Assignment2NerFE<T extends Element> implements FeatureExtractor<T> {

    /**
     * The {@link NamedEntity} type to use.
     */
    public final static String NER_TYPE = "state";

    /**
     * Name of {@link Feature} the feature extractor produces.
     */
    private static final String FEATURE_NAME = "stateFeature";

    @Override
    public Collection<Feature> extract(Document document, T element) {

        //TODO: PUT YOUR CODE HERE

        return Collections.emptyList();
    }

}