/*
 * Copyright (C) WorkFusion 2018. All rights reserved.
 */
package com.workfusion.lab.lesson9.process;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Optional;

import com.workfusion.vds.sdk.api.nlp.model.Field;
import com.workfusion.vds.sdk.api.nlp.model.IeDocument;
import com.workfusion.vds.sdk.api.nlp.processing.Processor;
import com.workfusion.vds.sdk.nlp.component.processing.normalization.OcrDateNormalizer;
import com.workfusion.vds.sdk.nlp.component.processing.DataValueNormalizationProcessor;
import com.workfusion.vds.sdk.nlp.component.processing.normalization.TextToDateNormalizer;

/**
 * Assignment 1
 */
public class Assignment1DatePostProcessor implements Processor<IeDocument> {

    /**
     * Name of {@link Field} representing a date.
     */
    public static final String FIELD_NAME = "date";

    /**
     * A format to which a date needs to be converted in the output.
     */
    private static final String OUTPUT_DATE_FORMAT = "MM/dd/yy";

    @Override
    public void process(IeDocument document) {

    	DateTimeFormatter inputDateFormatter = DateTimeFormatter.ofPattern("MMMM dd, yyyy");
        DateTimeFormatter outputDateFormatter = DateTimeFormatter.ofPattern(OUTPUT_DATE_FORMAT);
        
    	TextToDateNormalizer normalizer = new TextToDateNormalizer();
    	String dateString = normalizer.normalize("12/12/12");
    	
    	Optional<Field> fieldOptional = document.findField("date");
        if (fieldOptional.isPresent()) {
            Field field = fieldOptional.get();
            LocalDate date = LocalDate.parse(field.getValue(), inputDateFormatter);
            field.setValue((date).format(outputDateFormatter));
        }

    }

}