package com.workfusion.lab.lesson9.fe;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.workfusion.vds.sdk.api.nlp.fe.Feature;
import com.workfusion.vds.sdk.api.nlp.fe.FeatureExtractor;
import com.workfusion.vds.sdk.api.nlp.fe.annotation.FeatureName;
import com.workfusion.vds.sdk.api.nlp.model.Cell;
import com.workfusion.vds.sdk.api.nlp.model.Document;
import com.workfusion.vds.sdk.api.nlp.model.Element;

/**
 * Assignment  4
 */
@FeatureName(IsInColumnNamedFE.FEATURE_NAME)
public class IsInColumnNamedFE<T extends Element> implements FeatureExtractor<T> {

    /**
     * Name of {@link Feature} the feature extractor produces.
     */
    public static final String FEATURE_NAME = "column_index";
    
    private String keyword;

    public IsInColumnNamedFE(String keyword) {
        this.keyword = keyword.toLowerCase();

    }

    @Override
    public Collection<Feature> extract(Document document, T element) {
        List<Feature> result = new ArrayList<>();
        
        List<Cell> lines = document.findPrevious(Cell.class, element, 9);
        
        for(Cell l : lines) {
        	//if(l.getText().toLowerCase().contains(keyword))
        		result.add(new Feature(FEATURE_NAME, l.getColumnIndex()));
        }

        return result;
    }

}
