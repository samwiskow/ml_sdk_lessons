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

/**
 * Assignment 3
 */
public class Assignment3TableFE<T extends Element> implements FeatureExtractor<T> {

    /**
     * The "row index" feature name.
     */
    public static final String ROW_INDEX_FEATURE_NAME = "rowIndex";

    /**
     * The "cell index" feature name.
     */
    public static final String COLUMN_INDEX_FEATURE_NAME = "columnIndex";

    @Override
    public Collection<Feature> extract(Document document, T element) {

        //TODO: PUT YOUR CODE HERE

        return Collections.emptyList();
    }

}