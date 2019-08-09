/*
 * Copyright (C) WorkFusion 2018. All rights reserved.
 */
package com.workfusion.lab.lesson9.config;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.workfusion.lab.lesson9.fe.IsNerPresentFE;
import com.workfusion.lab.lesson9.fe.KeywordsPreviousLineFE;
import com.workfusion.lab.lesson9.process.AnswerTypeDatePostProcessor;
import com.workfusion.lab.lesson9.process.Assignment1DatePostProcessor;
import com.workfusion.vds.sdk.api.hypermodel.annotation.ModelConfiguration;
import com.workfusion.vds.sdk.api.hypermodel.annotation.Named;
import com.workfusion.vds.sdk.api.nlp.annotator.Annotator;
import com.workfusion.vds.sdk.api.nlp.configuration.FieldType;
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
public class Assignment1ModelConfiguration {

    /**
     * Regex pattern to use for matching {@link Token} elements.
     */
    private final static String TOKEN_REGEX = "[\\w@.,$%’-]+";

    /**
     * Name of {@link Field} representing an invoice number.
     */
    public final static String FIELD_INVOICE_NUMBER = "invoice_number";

    /**
     * Regex pattern to match an invoice number.
     */
    private final static String INVOICE_NUMBER_REGEX = "\\d{10}";

    /**
     * Name of {@link Field} representing a date.
     */
    public final static String FIELD_DATE = "date";

    /**
     * Regex pattern to match a date.
     */
    private static final String DATE_REGEX = "(Jan(uary)?|Feb(ruary)?|Mar(ch)?|Apr(il)?|May|Jun(e)?|Jul(y)?|Aug(ust)?|Sep(tember)?|Oct(ober)?|Nov(ember)?|Dec(ember)?)\\s+\\d{1,2},\\s+\\d{4}";

    @Named("annotators")
    public List<Annotator<Document>> getAnnotators(IeConfigurationContext context) {
        List<Annotator<Document>> annotators = new ArrayList<>();

        annotators.add(new MatcherTokenAnnotator(TOKEN_REGEX));
        annotators.add(new EntityBoundaryAnnotator());
        
        switch (context.getField().getCode()) {
	        case FIELD_INVOICE_NUMBER: {
	            annotators.add(BaseRegexNerAnnotator.getJavaPatternRegexNerAnnotator(FIELD_INVOICE_NUMBER, INVOICE_NUMBER_REGEX));
	            break;
	        }
	        case FIELD_DATE: {
	            annotators.add(BaseRegexNerAnnotator.getJavaPatternRegexNerAnnotator(FIELD_DATE, DATE_REGEX));
	            break;
	        }
        }

        return annotators;
    }

    @Named("featureExtractors")
    public List<FeatureExtractor<Element>> getFeatureExtractors(IeConfigurationContext context) {
        List<FeatureExtractor<Element>> featuresExtractors = new ArrayList<>(); 	
        
        switch (context.getField().getCode()) {
        case FIELD_INVOICE_NUMBER: {
            featuresExtractors.add(new IsNerPresentFE<Element>(FIELD_INVOICE_NUMBER));
            break;
        }
        case FIELD_DATE: {
            featuresExtractors.add(new IsNerPresentFE<Element>(FIELD_DATE));
            break;
        }
    }

        return featuresExtractors;
    }

    @Named("processors")
    public List<Processor<IeDocument>> getProcessors() {
    	List<Processor<IeDocument>> processors = new ArrayList<Processor<IeDocument>>();
        processors.add(new Assignment1DatePostProcessor());
        return processors;
    }

}