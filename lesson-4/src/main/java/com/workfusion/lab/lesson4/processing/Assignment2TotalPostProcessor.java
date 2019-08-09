/*
 * Copyright (C) WorkFusion 2018. All rights reserved.
 */
package com.workfusion.lab.lesson4.processing;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import com.workfusion.vds.sdk.api.nlp.model.Field;
import com.workfusion.vds.sdk.api.nlp.model.IeDocument;
import com.workfusion.vds.sdk.api.nlp.processing.Processor;
import com.workfusion.vds.sdk.nlp.component.processing.normalization.OcrAmountNormalizer;

/**
 * Assignment 2
 */
public class Assignment2TotalPostProcessor implements Processor<IeDocument> {

    /**
     * Name of {@link Field} representing a total.
     */
    public static final String FIELD_NAME = "total";
    
    private static final List<String> Codes = Arrays.asList(
            "USD"
    );

    @Override
    public void process(IeDocument document) {

    	Collection<Field> fields = document.findFields(FIELD_NAME);
    	
    	for(Field amountField : fields)
    	{
            String value = amountField.getValue();
            OcrAmountNormalizer amountNormalizer = new OcrAmountNormalizer();
            amountField.setValue(amountNormalizer.normalize(value) + " USD");
    	}
        

    }

}