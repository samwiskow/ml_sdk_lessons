/*
 * Copyright (C) WorkFusion 2018. All rights reserved.
 */
package com.workfusion.lab.lesson10.config;

import com.workfusion.vds.sdk.api.hpo.ParameterSpace;
import com.workfusion.vds.sdk.api.hypermodel.annotation.ModelConfiguration;
import com.workfusion.vds.sdk.api.hypermodel.annotation.Named;
import com.workfusion.vds.sdk.api.nlp.configuration.IeConfigurationContext;
import com.workfusion.vds.sdk.api.nlp.model.Field;

/**
 * The model configuration class.
 */
@ModelConfiguration
// TODO:  PUT YOU CODE HERE
public class Assignment2ModelConfiguration {

    /**
     * Name of {@link Field} representing an invoice number.
     */
    public final static String FIELD_INVOICE_NUMBER = "invoice_number";

    /**
     * Name of {@link Field} representing a product.
     */
    public final static String FIELD_PRODUCT = "product";

    @Named("parameterSpace")
    public ParameterSpace configure(IeConfigurationContext context) {
        ParameterSpace.Builder builder = new ParameterSpace.Builder();

        // TODO:  PUT YOU CODE HERE

        return builder.build();
    }

}