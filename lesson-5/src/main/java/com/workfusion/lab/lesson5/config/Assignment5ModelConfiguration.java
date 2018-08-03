package com.workfusion.lab.lesson5.config;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import com.workfusion.vds.sdk.api.hypermodel.annotation.ModelConfiguration;
import com.workfusion.vds.sdk.api.hypermodel.annotation.Named;
import com.workfusion.vds.sdk.api.nlp.annotator.Annotator;
import com.workfusion.vds.sdk.api.nlp.configuration.IeConfigurationContext;
import com.workfusion.vds.sdk.api.nlp.fe.Feature;
import com.workfusion.vds.sdk.api.nlp.fe.FeatureExtractor;
import com.workfusion.vds.sdk.api.nlp.model.Document;
import com.workfusion.vds.sdk.api.nlp.model.Element;
import com.workfusion.vds.sdk.api.nlp.model.NamedEntity;
import com.workfusion.vds.sdk.api.nlp.model.Token;
import com.workfusion.vds.sdk.nlp.component.annotator.EntityBoundaryAnnotator;
import com.workfusion.vds.sdk.nlp.component.annotator.tokenizer.MatcherTokenAnnotator;

import static com.workfusion.lab.lesson5.config.Assignment5ModelConfiguration.NER_TYPE;
import static com.workfusion.vds.sdk.nlp.component.annotator.ner.BaseRegexNerAnnotator.getJavaPatternRegexNerAnnotator;

/**
 * Assignment 5
 */
@ModelConfiguration
public class Assignment5ModelConfiguration {

    /**
     * Regex pattern to use for matching {@link Token} elements.
     */
    private final static String TOKEN_REGEX = "[@\\w\\.]+";

    /**
     * Regex to match an email.
     */
    private final static String EMAIL_REGEX = "[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,4}";

    /**
     * Type for {@link NamedEntity} to use.
     */
    public final static String NER_TYPE = "email";

    @Named("annotators")
    public List<Annotator<Document>> getAnnotators(IeConfigurationContext context) {
        return Arrays.asList(
                new MatcherTokenAnnotator(TOKEN_REGEX),
                new EntityBoundaryAnnotator(),
                getJavaPatternRegexNerAnnotator(NER_TYPE, EMAIL_REGEX)
        );
    }

    @Named("featureExtractors")
    public List<FeatureExtractor<Element>> getFeatureExtractors(IeConfigurationContext context) {
        return Arrays.asList(
                new IsNerPresentFE<Element>()
        );
    }

}

/**
 * The feature extractor to be used in the configuration. It searches for {@link NamedEntity} elements with {@code type = NER_TYPE}
 * that cover {@link Token} elements and creates features with {@code name = FEATURE_NAME} for those tokens.
 */
class IsNerPresentFE<T extends Element> implements FeatureExtractor<T> {

    /**
     * Name of {@link Feature} the feature extractor produces.
     */
    public static final String FEATURE_NAME = "isNer";

    @Override
    public Collection<Feature> extract(Document document, T element) {
        List<Feature> result = new ArrayList<>();
        List<NamedEntity> namedEntity = document.findCovering(NamedEntity.class, element);
        for (NamedEntity ner : namedEntity) {
            if (ner.getType().equalsIgnoreCase(NER_TYPE)) {
                result.add(new Feature(FEATURE_NAME, 1));
            }
        }
        return result;
    }
}