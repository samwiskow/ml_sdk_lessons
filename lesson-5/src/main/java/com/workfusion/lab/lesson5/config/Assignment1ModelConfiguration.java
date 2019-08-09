/*
 * Copyright (C) WorkFusion 2018. All rights reserved.
 */
package com.workfusion.lab.lesson5.config;

import java.util.ArrayList;
import java.util.List;

import com.workfusion.lab.lesson5.annotator.Assignment1SentenceAnnotator;
import com.workfusion.lab.lesson5.annotator.Assignment1TokenAnnotator;
import com.workfusion.vds.sdk.api.hypermodel.annotation.ModelConfiguration;
import com.workfusion.vds.sdk.api.hypermodel.annotation.Named;
import com.workfusion.vds.sdk.api.nlp.annotator.Annotator;
import com.workfusion.vds.sdk.api.nlp.configuration.IeConfigurationContext;
import com.workfusion.vds.sdk.api.nlp.fe.FeatureExtractor;
import com.workfusion.vds.sdk.api.nlp.model.Document;

/**
 * Assignment 1
 */
@ModelConfiguration
public class Assignment1ModelConfiguration {

	@Named("annotators")
    public List<Annotator<Document>> getAnnotators(IeConfigurationContext context) {
        //TODO configure annotators here.
        List<Annotator<Document>> annotators = new ArrayList<>();
        annotators.add(new Assignment1SentenceAnnotator());
        annotators.add(new Assignment1TokenAnnotator());
        return annotators;
    }

}