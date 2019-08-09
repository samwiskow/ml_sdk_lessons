/*
 * Copyright (C) WorkFusion 2018. All rights reserved.
 */
package com.workfusion.lab.lesson10.config;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import com.workfusion.lab.lesson10.fe.*;
import com.workfusion.lab.lesson10.processing.ExpandPostProcessor;
import com.workfusion.vds.nlp.hypermodel.ie.generic.config.GenericIeHypermodelConfiguration;
import com.workfusion.vds.sdk.api.hpo.Dimensions;
import com.workfusion.vds.sdk.api.hpo.HpoConfiguration;
import com.workfusion.vds.sdk.api.hpo.ParameterSpace;
import com.workfusion.vds.sdk.api.hypermodel.annotation.Import;
import com.workfusion.vds.sdk.api.hypermodel.annotation.ModelConfiguration;
import com.workfusion.vds.sdk.api.hypermodel.annotation.Named;
import com.workfusion.vds.sdk.api.hypermodel.ConfigurationContext;
import com.workfusion.vds.sdk.api.hypermodel.annotation.Filter;
import com.workfusion.vds.sdk.api.nlp.configuration.IeConfigurationContext;
import com.workfusion.vds.sdk.api.nlp.fe.FeatureExtractor;
import com.workfusion.vds.sdk.api.nlp.model.Element;
import com.workfusion.vds.sdk.api.nlp.model.Field;
import com.workfusion.vds.sdk.api.nlp.model.IeDocument;
import com.workfusion.vds.sdk.api.nlp.processing.Processor;

/**
 * The model configuration class.
 */
@ModelConfiguration
@Import(configurations = {
	       @Import.Configuration(GenericIeHypermodelConfiguration.class)
	},
	resources = {
			@Import.Resource(value = "/invoice_number/parameters.json", condition = @Filter(expression = "field.code == 'invoice_number'"))
	}
)

public class Assignment2ModelConfiguration {

    /**
     * Name of {@link Field} representing an invoice number.
     */
    public final static String FIELD_INVOICE_NUMBER = "invoice_number";

    /**
     * Name of {@link Field} representing a product.
     */
    public final static String FIELD_PRODUCT = "product";

    @Named("fe1")
    public List<FeatureExtractor<Element>> getFeatureExtractors(IeConfigurationContext context) {
        List<FeatureExtractor<Element>> featuresExtractors = new ArrayList<>(); 	
        featuresExtractors.add(new RowIndexFE<Element>());
        
        return featuresExtractors;
    }
    
    @Named("fe2")
    public List<FeatureExtractor<Element>> getFeatureExtractors2(IeConfigurationContext context) {
        List<FeatureExtractor<Element>> featuresExtractors = new ArrayList<>(); 	
        featuresExtractors.add(new TableNumberFE<Element>());

        return featuresExtractors;
    }
    
    @Named("fe3")
    public List<FeatureExtractor<Element>> getFeatureExtractors3(IeConfigurationContext context) {
        List<FeatureExtractor<Element>> featuresExtractors = new ArrayList<>(); 	
        featuresExtractors.add(new ColumnIndexFE<Element>());

        return featuresExtractors;
    }

    @Named("parameterSpace")
    public ParameterSpace configure(IeConfigurationContext context) {
        ParameterSpace.Builder builder = new ParameterSpace.Builder();

        switch(context.getField().getCode()) {
	        case FIELD_INVOICE_NUMBER :{
	        	builder.add(Dimensions.required("fe3"));
	        	break;
	        }
	        case FIELD_PRODUCT :{
	        	builder.add(Dimensions.selectOne("fe1","fe2"));
	        	break;
	        }
        }

        return builder.build();
    }
    
    @Named("basePostProcessors")
    public List<Processor<IeDocument>> getProcessors() {
    	List<Processor<IeDocument>> processors = new ArrayList<Processor<IeDocument>>();
        processors.add(new ExpandPostProcessor());
        return processors;
    }
    
    @Named("hpoConfiguration")
    public HpoConfiguration hpoConfiguration(ConfigurationContext context) {
        return new HpoConfiguration.Builder()
                .timeLimit(600, TimeUnit.SECONDS)
                .maxExperimentsWithSameScore(5)
                .build();
    }

}