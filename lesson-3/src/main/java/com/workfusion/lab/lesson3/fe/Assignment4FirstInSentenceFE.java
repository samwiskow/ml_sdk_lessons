/*
 * Copyright (C) WorkFusion 2018. All rights reserved.
 */
package com.workfusion.lab.lesson3.fe;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Optional;


import com.workfusion.vds.sdk.api.nlp.fe.Feature;
import com.workfusion.vds.sdk.api.nlp.fe.FeatureExtractor;
import com.workfusion.vds.sdk.api.nlp.model.Document;
import com.workfusion.vds.sdk.api.nlp.model.Element;
import com.workfusion.vds.sdk.api.nlp.model.Sentence;
import com.workfusion.vds.sdk.api.nlp.model.Token;


/**
 * Assignment 4
 */
public class Assignment4FirstInSentenceFE<T extends Element> implements FeatureExtractor<T> {

    /**
     * Name of {@link Feature} the feature extractor produces.
     */
    private static final String FEATURE_NAME = "firstInSentenceFeature";

    @Override
    public Collection<Feature> extract(Document document, T element) {
    	
    	List<Feature> features = new ArrayList();
    	
    	List<Sentence> sentences = document.findCovering(Sentence.class, element);
    	java.util.Optional<Sentence> s = sentences.stream().findFirst();

    	List<Token> tokens = document.findCovered(Token.class, s.get());
    	java.util.Optional<Token> token = tokens.stream().findFirst();

    	if (token.get().equals(element))
    		features.add(new Feature(FEATURE_NAME, 1.0));

    	return features;
    	
    }

}