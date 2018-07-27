package com.workfusion.lab.lesson5.annotator;

import com.workfusion.vds.sdk.api.nlp.annotator.Annotator;
import com.workfusion.vds.sdk.api.nlp.model.Document;
import com.workfusion.vds.sdk.api.nlp.model.Sentence;

/**
 * Assignment 1
 */
public class Assignment1SentenceAnnotator implements Annotator<Document> {

    /**
     * Regex pattern to use for splitting a document into {@link Sentence} elements.
     */
    public static final String SENTENCE_REGEXP = "[.!?]";

    @Override
    public void process(Document document) {

        //TODO: PUT YOUR CODE HERE

    }

}
