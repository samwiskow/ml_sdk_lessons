/*
 * Copyright (C) WorkFusion 2018. All rights reserved.
 */
package com.workfusion.lab.lesson4.processing;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import com.workfusion.vds.nlp.similarity.impl.JaroWinkler;
import com.workfusion.vds.sdk.api.nlp.model.Field;
import com.workfusion.vds.sdk.api.nlp.model.IeDocument;
import com.workfusion.vds.sdk.api.nlp.processing.Processor;
import com.workfusion.vds.sdk.nlp.component.processing.normalization.OcrAmountNormalizer;

/**
 * Assignment 6
 */
public class Assignment6SimilarityPostProcessor implements Processor<IeDocument> {

    /**
     * Name of {@link Field} representing a product type.
     */
    public static final String FIELD_NAME = "product_type";

    /**
     * Threshold to use in string similarity comparison.
     */
    private static final double SIMILARITY_THRESHOLD = 0.8;

    /**
     * Words list to use.
     */
    private static final List<String> WORDS = Arrays.asList(
            "Aerodynamic",
            "Bench",
            "Bronze",
            "Chair",
            "Computer",
            "Durable",
            "Duty",
            "Enormous",
            "Ergonomic",
            "Hat",
            "Heavy",
            "Iron",
            "Keyboard",
            "Knife",
            "Lamp",
            "Lightweight",
            "Linen",
            "Paper",
            "Rubber",
            "Wool"
    );

    @Override
    public void process(IeDocument document) {

    	JaroWinkler jw = new JaroWinkler();
    	Collection<Field> fields = document.findFields(FIELD_NAME);
    	
    	for(Field f : fields)
    	{
            String value = f.getValue();
            WORDS.forEach(word ->{
            	Double temp = jw.similarity(word, f.getValue());
            	if(temp >= SIMILARITY_THRESHOLD) {
            		f.setValue(word);
            		return;
            	}
            });
            
    	}

    }

}