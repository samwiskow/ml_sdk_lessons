/*
 * Copyright (C) WorkFusion 2018. All rights reserved.
 */
package com.workfusion.lab.lesson4.processing;

import java.util.Collection;

import com.workfusion.vds.sdk.api.nlp.model.Field;
import com.workfusion.vds.sdk.api.nlp.model.IeDocument;
import com.workfusion.vds.sdk.api.nlp.processing.Processor;
import com.workfusion.vds.sdk.nlp.component.processing.normalization.OcrAmountNormalizer;

/**
 * Assignment 5
 */
public class Assignment5PricePostProcessor implements Processor<IeDocument> {

    /**
     * Name of {@link Field} representing a price.
     */
    public static final String FIELD_NAME = "price";

    @Override
    public void process(IeDocument document) {

	Collection<Field> fields = document.findFields(FIELD_NAME);
    	
    	for(Field amountField : fields)
    	{
            String value = amountField.getText();
            String correctedValue = value
                    .replaceAll("G|b", "6")
                    .replaceAll("B", "8")
                    .replaceAll("I|l|i|\\|", "1")
                    .replaceAll("O", "0");
            OcrAmountNormalizer amountNormalizer = new OcrAmountNormalizer();
            correctedValue = amountNormalizer.normalize(correctedValue) + " USD";
            amountField.setValue(correctedValue);
            
    	}

    }

}