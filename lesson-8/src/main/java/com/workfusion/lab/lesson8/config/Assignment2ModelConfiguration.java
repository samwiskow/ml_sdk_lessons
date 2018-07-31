/*
 * Copyright (C) WorkFusion 2018. All rights reserved.
 */
package com.workfusion.lab.lesson8.config;

import java.util.Collections;
import java.util.List;

import com.workfusion.vds.sdk.api.hypermodel.annotation.ModelConfiguration;
import com.workfusion.vds.sdk.api.hypermodel.annotation.Named;
import com.workfusion.vds.sdk.api.nlp.annotator.Annotator;
import com.workfusion.vds.sdk.api.nlp.configuration.IeConfigurationContext;
import com.workfusion.vds.sdk.api.nlp.fe.FeatureExtractor;
import com.workfusion.vds.sdk.api.nlp.model.Document;
import com.workfusion.vds.sdk.api.nlp.model.Element;
import com.workfusion.vds.sdk.api.nlp.model.Field;
import com.workfusion.vds.sdk.api.nlp.model.NamedEntity;
import com.workfusion.vds.sdk.api.nlp.model.Token;

/**
 * The model configuration class.
 * Here you can configure set of Feature Extractors, Annotators.
 */
@ModelConfiguration
public class Assignment2ModelConfiguration {

    /**
     * Regex pattern to use for matching {@link Token} elements.
     */
    public final static String TOKEN_REGEX = "([$\\$\\s:#_;'])";

    /**
     * Name of {@link Field} representing a client_address.
     */
    public final static String FIELD_STATE = "client_address_state";

    /**
     * Regex pattern to match a state.
     */
    private final static String STATE_REGEX = "[A-Z]{2}";

    /**
     * Type for {@link NamedEntity} to use for state NER.
     */
    private static final String NER_TYPE_STATE = "state";

    /**
     * Keyword that needs be found in previous line
     */
    private static final String KEYWORD_SIMILARITY = "address";

    @Named("annotators")
    public List<Annotator<Document>> getAnnotators(IeConfigurationContext context) {

        // TODO:  PUT YOU CODE HERE

        return Collections.emptyList();
    }

    @Named("featureExtractors")
    public List<FeatureExtractor<Element>> getFeatureExtractors(IeConfigurationContext context) {

        // TODO:  PUT YOU CODE HERE

        return Collections.emptyList();
    }

}