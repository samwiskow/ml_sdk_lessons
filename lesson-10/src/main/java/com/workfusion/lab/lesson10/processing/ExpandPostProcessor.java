package com.workfusion.lab.lesson10.processing;

import com.workfusion.vds.sdk.api.nlp.model.Field;
import com.workfusion.vds.sdk.api.nlp.model.IeDocument;
import com.workfusion.vds.sdk.api.nlp.processing.Processor;

public class ExpandPostProcessor implements Processor<IeDocument> {

    /**
     * Name of {@link Field} representing a field to process.
     */
    public static final String FIELD_NAME = "somefield";

    @Override
    public void process(IeDocument document) {
        document.findFields()
                .forEach(f -> {
                    if (f.getValue() != null) {
                        String value = f.getValue();
                        f.setValue(value.replace("O", "0"));
                    }
                });
    }

}
