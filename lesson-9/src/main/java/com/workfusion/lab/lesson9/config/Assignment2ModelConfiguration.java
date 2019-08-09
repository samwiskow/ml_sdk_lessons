/*
 * Copyright (C) WorkFusion 2018. All rights reserved.
 */
package com.workfusion.lab.lesson9.config;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.hibernate.mapping.Collection;

import com.workfusion.lab.lesson9.fe.*;
import com.workfusion.lab.lesson9.process.Assignment1DatePostProcessor;
import com.workfusion.lab.lesson9.process.Assignment7ExpandPostProcessor;
import com.workfusion.lab.lesson9.process.TotalAmountPostProcessor;
import com.workfusion.vds.sdk.api.hypermodel.annotation.ModelConfiguration;
import com.workfusion.vds.sdk.api.hypermodel.annotation.Named;
import com.workfusion.vds.sdk.api.nlp.annotator.Annotator;
import com.workfusion.vds.sdk.api.nlp.configuration.IeConfigurationContext;
import com.workfusion.vds.sdk.api.nlp.fe.FeatureExtractor;
import com.workfusion.vds.sdk.api.nlp.model.Document;
import com.workfusion.vds.sdk.api.nlp.model.Element;
import com.workfusion.vds.sdk.api.nlp.model.Field;
import com.workfusion.vds.sdk.api.nlp.model.IeDocument;
import com.workfusion.vds.sdk.api.nlp.model.Token;
import com.workfusion.vds.sdk.api.nlp.processing.Processor;
import com.workfusion.vds.sdk.nlp.component.annotator.EntityBoundaryAnnotator;
import com.workfusion.vds.sdk.nlp.component.annotator.ner.BaseRegexNerAnnotator;
import com.workfusion.vds.sdk.nlp.component.annotator.tokenizer.MatcherTokenAnnotator;
import com.workfusion.vds.sdk.nlp.component.annotator.tokenizer.SplitterTokenAnnotator;

/**
 * The model configuration class.
 * Here you can configure set of Feature Extractors, Annotators and Post-Processors.
 * Also you can import configuration with set of predefined components or your own configuration
 */
@ModelConfiguration
public class Assignment2ModelConfiguration {

    /**
     * Regex pattern to use for matching {@link Token} elements.
     */
    private final static String TOKEN_REGEX = "[\\w@.,$%’-]+";

    /**
     * Name of {@link Field} representing a client name.
     */
    public final static String FIELD_CLIENT_NAME = "client_name";

    /**
     * Name of {@link Field} representing a price.
     */
    public final static String FIELD_PRICE = "price";

    /**
     * Name of {@link Field} representing a product.
     */
    public final static String FIELD_PRODUCT = "product";

    /**
     * Regex pattern to match a price.
     */
    private final static String PRICE_REGEX = "(\\$[0-9]{1,3}[.][0-9]{2})";

    /**
     * Regex pattern to match a client name.
     */
    private static final String CLIENT_NAME_REGEX = "([A-Z][a-z ]{3,11}){3,4}";

    @Named("annotators")
    public List<Annotator<Document>> getAnnotators(IeConfigurationContext context) {
        List<Annotator<Document>> annotators = new ArrayList<>();

        annotators.add(new MatcherTokenAnnotator(TOKEN_REGEX));
        annotators.add(new EntityBoundaryAnnotator());
        switch (context.getField().getCode()) {
	        case FIELD_CLIENT_NAME: {
	            annotators.add(BaseRegexNerAnnotator.getJavaPatternRegexNerAnnotator(FIELD_CLIENT_NAME, CLIENT_NAME_REGEX));
	            break;
	        }
	        case FIELD_PRICE: {
	            annotators.add(BaseRegexNerAnnotator.getJavaPatternRegexNerAnnotator(FIELD_PRICE, PRICE_REGEX));
	            break;
	        }
        }

        return annotators;
    }

    @Named("featureExtractors")
    public List<FeatureExtractor<Element>> getFeatureExtractors(IeConfigurationContext context) {
        List<FeatureExtractor<Element>> featuresExtractors = new ArrayList<>();

        Set<String> columnNames = new HashSet<>();
        
        switch (context.getField().getCode()) {
	        case FIELD_CLIENT_NAME: {
	            featuresExtractors.add(new IsNerPresentFE<Element>(FIELD_CLIENT_NAME));
	            featuresExtractors.add(new SimilarityKeysInPrevLineFE<Element>("BILL TO"));
	            break;
	        }
	        case FIELD_PRICE: {
	        	columnNames.add("price");
	            featuresExtractors.add(new MatchFullColumnOrRowWithSpecifiedHeaderFeatureExtractror<Element>(FIELD_PRICE, columnNames, true));
	            break;
	        }
	        case FIELD_PRODUCT: {
	        	columnNames.add("product");
	            featuresExtractors.add(new MatchFullColumnOrRowWithSpecifiedHeaderFeatureExtractror<Element>(FIELD_PRODUCT, columnNames, true));
	            break;
	        }
        }


        return featuresExtractors;
    }

    @Named("processors")
    public List<Processor<IeDocument>> getProcessors() {

    	List<Processor<IeDocument>> processors = new ArrayList<Processor<IeDocument>>();
        processors.add(new TotalAmountPostProcessor());
        processors.add(new Assignment7ExpandPostProcessor());
        return processors;
    }

}