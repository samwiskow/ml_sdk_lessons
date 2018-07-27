package com.workfusion.lab.lesson8.processing;

import com.workfusion.vds.sdk.api.nlp.model.IeDocument;
import com.workfusion.vds.sdk.api.nlp.processing.ProcessingException;
import com.workfusion.vds.sdk.api.nlp.processing.Processor;

public class TotalAmountPostProcessor implements Processor<IeDocument> {

    private static final String REGEX_TOTAL_AMOUNT_WRONG_CHARS  = "[,($]";

    @Override
    public void process(IeDocument document) {

        // TODO:  PUT YOU CODE HERE
    }
}
