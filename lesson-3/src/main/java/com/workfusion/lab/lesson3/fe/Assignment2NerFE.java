/*
 * Copyright (C) WorkFusion 2018. All rights reserved.
 */
package com.workfusion.lab.lesson3.fe;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import com.workfusion.ml.statistics.domain.Token;
import com.workfusion.vds.sdk.api.nlp.fe.Feature;
import com.workfusion.vds.sdk.api.nlp.fe.FeatureExtractor;
import com.workfusion.vds.sdk.api.nlp.model.Document;
import com.workfusion.vds.sdk.api.nlp.model.Element;
import com.workfusion.vds.sdk.api.nlp.model.Line;
import com.workfusion.vds.sdk.api.nlp.model.NamedEntity;
import com.workfusion.vds.sdk.api.nlp.model.Sentence;

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
    	
    	List<Feature> features = new ArrayList<>();

    	List<NamedEntity> coveredNe = document.findCovering(NamedEntity.class, element); 
    	
        for(NamedEntity n:coveredNe) {
        	if(n.getType() == NER_TYPE)
        		features.add(new Feature(FEATURE_NAME, 1.0));
        }
        return features;
    }

}