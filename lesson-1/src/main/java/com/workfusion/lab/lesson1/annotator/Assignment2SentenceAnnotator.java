package com.workfusion.lab.lesson1.annotator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;

import com.workfusion.vds.sdk.api.nlp.annotator.Annotator;
import com.workfusion.vds.sdk.api.nlp.model.Document;
import com.workfusion.vds.sdk.api.nlp.model.Sentence;

/**
 * Assignment 2
 */
public class Assignment2SentenceAnnotator implements Annotator<Document> {

    /**
     * Regexp pattern to use.
     */
    public static final String SENTENCE_REGEXP = "[.!?]";

    @Override
    public void process(Document document) {
        int index = 0;
        String text = document.getText();
        Pattern pattern = Pattern.compile(SENTENCE_REGEXP);
        Matcher matcher = pattern.matcher(text);
        while (matcher.find()) {
            // No empty leading substring included for zero-width match at the beginning of the input char sequence.
            if (index == 0 && index == matcher.start() && matcher.start() == matcher.end()) {
                continue;
            }

            String match = text.substring(index, matcher.start());
            if (StringUtils.isNotBlank(match)) {
                Sentence.Descriptor descriptor = Sentence.descriptor()
                        .setBegin(index)
                        .setEnd(matcher.start());
                document.add(descriptor);
            }

            index = matcher.end();
        }

        if (index != text.length()) {
            Sentence.Descriptor descriptor = Sentence.descriptor()
                    .setBegin(index)
                    .setEnd(text.length());
            document.add(descriptor);
        }
    }

}
