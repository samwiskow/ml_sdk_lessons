/*
 * Copyright (C) WorkFusion 2018. All rights reserved.
 */
package com.workfusion.lab.lesson4.processing;

import com.workfusion.vds.sdk.api.nlp.model.Field;
import com.workfusion.vds.sdk.api.nlp.model.IeDocument;
import com.workfusion.vds.sdk.api.nlp.processing.Processor;

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

        //TODO: PUT YOUR CODE HERE

    }

}