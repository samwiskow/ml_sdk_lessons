/*
 * Copyright (C) WorkFusion 2018. All rights reserved.
 */
package com.workfusion.lab.lesson8.fe;

import java.util.Collection;
import java.util.Collections;

import com.workfusion.vds.sdk.api.nlp.annotation.DependsOn;
import com.workfusion.vds.sdk.api.nlp.fe.Feature;
import com.workfusion.vds.sdk.api.nlp.fe.FeatureExtractor;
import com.workfusion.vds.sdk.api.nlp.fe.annotation.FeatureName;
import com.workfusion.vds.sdk.api.nlp.fe.annotation.Index;
import com.workfusion.vds.sdk.api.nlp.fe.annotation.IndexType;
import com.workfusion.vds.sdk.api.nlp.fe.annotation.Indexes;
import com.workfusion.vds.sdk.api.nlp.model.Document;
import com.workfusion.vds.sdk.api.nlp.model.Element;
import com.workfusion.vds.sdk.api.nlp.model.NamedEntity;
import com.workfusion.vds.sdk.api.nlp.model.Token;

/**
 * Determines if focus annotation is NER and the last NER in the document
 */
public class NerAbsolutePositionFE<T extends Element> implements FeatureExtractor<T> {

    /**
     * Name of {@link Feature} the feature extractor produces.
     */
    public static final String FEATURE_NAME = "lastnerFeature";

    /**
     * Determines if focus annotation is NER and the last NER in the document
     * @param document the Document containing the focus
     * @param element the focus being checked for it position in  the covering annotation
     * @return "lastnerFeature" if focus annotation is NER and the last NEM in the document, nothing otherwise
     */
    @Override
    public Collection<Feature> extract(Document document, T element) {

        // TODO:  PUT YOU CODE HERE

        return Collections.emptyList();
    }

}