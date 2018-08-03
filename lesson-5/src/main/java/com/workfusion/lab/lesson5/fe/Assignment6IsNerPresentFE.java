package com.workfusion.lab.lesson5.fe;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.workfusion.vds.sdk.api.nlp.fe.Feature;
import com.workfusion.vds.sdk.api.nlp.fe.FeatureExtractor;
import com.workfusion.vds.sdk.api.nlp.model.Document;
import com.workfusion.vds.sdk.api.nlp.model.Element;
import com.workfusion.vds.sdk.api.nlp.model.NamedEntity;

import static com.workfusion.lab.lesson5.config.Assignment5ModelConfiguration.NER_TYPE;

public class Assignment6IsNerPresentFE<T extends Element> implements FeatureExtractor<T> {

    /**
     * The feature name to use.
     */
    public static final String FEATURE_NAME = "isNer";
    public static final String EMAIL_EXT = ".com";

    @Override
    public Collection<Feature> extract(Document document, T element) {
        List<Feature> result = new ArrayList<>();

        List<NamedEntity> namedEntity = document.findCovering(NamedEntity.class, element);
        for (NamedEntity ner : namedEntity) {
            if (ner.getType().equalsIgnoreCase(NER_TYPE) && element.getText().contains(EMAIL_EXT)) {
                result.add(new Feature(FEATURE_NAME, 1));
            }
        }
        return result;
    }
}