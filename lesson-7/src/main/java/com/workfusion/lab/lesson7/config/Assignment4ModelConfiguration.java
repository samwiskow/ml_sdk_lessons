/*
 * Copyright (C) WorkFusion 2018. All rights reserved.
 */
package com.workfusion.lab.lesson7.config;

import java.util.ArrayList;
import java.util.List;

import com.workfusion.lab.lesson7.fe.Assignment3IsCoveredByNerFE;
import com.workfusion.lab.lesson7.fe.Assignment4ColumnIndexFE;
import com.workfusion.lab.lesson7.fe.Assignment4NextTokenIsNumberFE;
import com.workfusion.vds.sdk.api.hypermodel.annotation.ModelConfiguration;
import com.workfusion.vds.sdk.api.hypermodel.annotation.Named;
import com.workfusion.vds.sdk.api.nlp.annotator.Annotator;
import com.workfusion.vds.sdk.api.nlp.configuration.IeConfigurationContext;
import com.workfusion.vds.sdk.api.nlp.fe.FeatureExtractor;
import com.workfusion.vds.sdk.api.nlp.model.Document;
import com.workfusion.vds.sdk.api.nlp.model.Element;
import com.workfusion.vds.sdk.api.nlp.model.Field;
import com.workfusion.vds.sdk.api.nlp.model.Token;
import com.workfusion.vds.sdk.nlp.component.annotator.EntityBoundaryAnnotator;
import com.workfusion.vds.sdk.nlp.component.annotator.ner.BaseRegexNerAnnotator;
import com.workfusion.vds.sdk.nlp.component.annotator.tokenizer.MatcherTokenAnnotator;

/**
 * The model configuration class.
 * Here you can configure set of Feature Extractors, Annotators.
 */
@ModelConfiguration
public class Assignment4ModelConfiguration {

    /**
     * Regex pattern to use for matching {@link Token} elements.
     */
    public final static String TOKEN_REGEX = "[\\w@.,$%’-]+";

    /**
     * Name of {@link Field} representing a price.
     */
    public final static String FIELD_PRICE = "price";

    /**
     * Regex pattern to match a price.
     */
    public final static String PRICE_REGEX = "(\\$[0-9]{1,3}[.][0-9]{2})";

    // TODO:  PUT YOU CODE HERE

}