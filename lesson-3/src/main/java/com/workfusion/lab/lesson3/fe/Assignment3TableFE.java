/*
 * Copyright (C) WorkFusion 2018. All rights reserved.
 */
package com.workfusion.lab.lesson3.fe;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import com.workfusion.vds.sdk.api.nlp.fe.Feature;
import com.workfusion.vds.sdk.api.nlp.fe.FeatureExtractor;
import com.workfusion.vds.sdk.api.nlp.model.Cell;
import com.workfusion.vds.sdk.api.nlp.model.Document;
import com.workfusion.vds.sdk.api.nlp.model.Element;
import com.workfusion.vds.sdk.api.nlp.model.NamedEntity;
import com.workfusion.vds.sdk.api.nlp.model.Table;

/**
 * Assignment 3
 */
public class Assignment3TableFE<T extends Element> implements FeatureExtractor<T> {

    /**
     * The "row index" feature name.
     */
    private static final String ROW_INDEX_FEATURE_NAME = "rowIndex";

    /**
     * The "cell index" feature name.
     */
    private static final String COLUMN_INDEX_FEATURE_NAME = "columnIndex";

    @Override
    public Collection<Feature> extract(Document document, T element) {

    	List<Feature> features = new ArrayList<>();
    	List<Cell> cells = document.findCovering(Cell.class, element);

        for(Cell c:cells) {
	        	features.add(new Feature(ROW_INDEX_FEATURE_NAME, c.getRowIndex()));
	    		features.add(new Feature(COLUMN_INDEX_FEATURE_NAME, c.getColumnIndex()));
        }
        return features;
    }

}