package com.workfusion.lab.lesson9.process;

import com.workfusion.vds.sdk.api.nlp.model.IeDocument;
import com.workfusion.vds.sdk.api.nlp.processing.Processor;
import com.workfusion.vds.sdk.nlp.component.processing.normalization.OcrDateNormalizer;

public class AnswerTypeDatePostProcessor implements Processor<IeDocument> {
    protected String normalize(String s) {
        OcrDateNormalizer dateNormalizer = new OcrDateNormalizer("dd/MM/yyyy");
        return dateNormalizer.normalize(s);
    }

	@Override
	public void process(IeDocument document) {
		// TODO Auto-generated method stub
		
	}
}