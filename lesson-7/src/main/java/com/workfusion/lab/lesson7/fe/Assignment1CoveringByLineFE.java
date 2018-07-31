/*
 * Copyright (C) WorkFusion 2018. All rights reserved.
 */
package com.workfusion.lab.lesson7.fe;

import java.util.Collection;
import java.util.Collections;

import com.workfusion.vds.sdk.api.nlp.fe.Feature;
import com.workfusion.vds.sdk.api.nlp.fe.FeatureExtractor;
import com.workfusion.vds.sdk.api.nlp.fe.annotation.FeatureName;
import com.workfusion.vds.sdk.api.nlp.model.Document;
import com.workfusion.vds.sdk.api.nlp.model.Element;

/**
 * Assignment 1
 */
@FeatureName(Assignment1CoveringByLineFE.FEATURE_NAME)
public class Assignment1CoveringByLineFE<T extends Element> implements FeatureExtractor<T> {

    /**
     * Name of {@link Feature} the feature extractor produces.
     */
    public final static String FEATURE_NAME = "covering_by_line";

    @Override
    public Collection<Feature> extract(Document document, T element) {

        // TODO:  PUT YOU CODE HERE

        return Collections.emptyList();
    }

}