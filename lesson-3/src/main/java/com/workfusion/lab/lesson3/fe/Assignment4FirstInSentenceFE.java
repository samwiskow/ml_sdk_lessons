package com.workfusion.lab.lesson3.fe;

import java.util.Collection;
import java.util.Collections;

import com.workfusion.vds.sdk.api.nlp.fe.Feature;
import com.workfusion.vds.sdk.api.nlp.fe.FeatureExtractor;
import com.workfusion.vds.sdk.api.nlp.model.Document;
import com.workfusion.vds.sdk.api.nlp.model.Element;

/**
 * Assignment 4
 */
public class Assignment4FirstInSentenceFE<T extends Element> implements FeatureExtractor<T> {

    /**
     * Name of {@link Feature} the feature extractor produces.
     */
    public static final String FEATURE_NAME = "firstInSentenceFeature";

    @Override
    public Collection<Feature> extract(Document document, T element) {

        //TODO: PUT YOUR CODE HERE

        return Collections.emptyList();
    }
}
