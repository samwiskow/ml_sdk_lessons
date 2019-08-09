/*
 * Copyright (C) WorkFusion 2018. All rights reserved.
 */
package com.workfusion.lab.lesson3.fe;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.workfusion.vds.sdk.api.nlp.annotation.OnDocumentComplete;
import com.workfusion.vds.sdk.api.nlp.annotation.OnDocumentStart;
import com.workfusion.vds.sdk.api.nlp.fe.Feature;
import com.workfusion.vds.sdk.api.nlp.fe.FeatureExtractor;
import com.workfusion.vds.sdk.api.nlp.model.Document;
import com.workfusion.vds.sdk.api.nlp.model.Element;
import com.workfusion.vds.sdk.api.nlp.model.Line;
import com.workfusion.vds.sdk.api.nlp.model.Sentence;
import com.workfusion.vds.sdk.api.nlp.model.Token;

/**
 * Assignment 5
 */
public class Assignment5FirstInSentenceFE<T extends Element> implements FeatureExtractor<T> {

    /**
     * Name of {@link Feature} the feature extractor produces.
     */
    private static final String FEATURE_NAME = "firstInSentenceFeature";
    private Map<Token, Integer> textOccurrence = new HashMap<>();
    //private int totalFocusElementNumber;

    /**
     * The method is called once in the beginning of document processing.
     * @param document  a document is being processed.
     * @param focusElement  a type of {@link Element} the feature extractor will accept in the {@code extract()} method.
     */
    @OnDocumentStart
    public void documentStart(Document document, Class<T> focusElement) {

    	// Find all focus elements (by default, focusElement is Token)
        Collection<T> elements = document.findAll(focusElement);

        textOccurrence = new HashMap<>();
        
        // Calculate the number of text occurrences in the Document
        for (T element : elements) {
            
            List<Sentence> sentences = document.findCovering(Sentence.class, element);
        	java.util.Optional<Sentence> s = sentences.stream().findFirst();

        	List<Token> tokens = document.findCovered(Token.class, s.get());
        	java.util.Optional<Token> token = tokens.stream().findFirst();
            
            textOccurrence.put(token.get(), 1);
        }

    }

    @Override
    public Collection<Feature> extract(Document document, T element) {

    	List<Feature> result = new ArrayList<>();
    	
    	if (textOccurrence.get(element) != null)
    		result.add(new Feature(FEATURE_NAME, 1.0));
    	
        return result;
    }

    /**
     * The method is called once in the end of document processing. The main purpose of the method is to release all resources allocated
     * while the feature extractor was working.
     */
    @OnDocumentComplete
    public void documentComplete() {

    	textOccurrence.clear();

    }

}