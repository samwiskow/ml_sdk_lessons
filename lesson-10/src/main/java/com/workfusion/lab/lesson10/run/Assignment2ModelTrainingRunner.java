/*
 * Copyright (C) WorkFusion 2018. All rights reserved.
 */
package com.workfusion.lab.lesson10.run;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.workfusion.lab.lesson10.model.Assignment2Model;
import com.workfusion.vds.sdk.api.nlp.configuration.FieldInfo;
import com.workfusion.vds.sdk.api.nlp.configuration.FieldType;
import com.workfusion.vds.sdk.run.ModelRunner;
import com.workfusion.vds.sdk.run.config.LocalTrainingConfiguration;

public class Assignment2ModelTrainingRunner {

    /**
     * Input directory path to use.
     */
    private final static String INPUT_DIR_PATH = "data/train";

    /**
     * Output directory path to use.
     */
    public final static String OUTPUT_DIR_PATH = "results_assignment2";

    /**
     * Field names to use.
     */
    public final static String FIELD_INVOICE_NUMBER = "invoice_number";
    public final static String FIELD_PRODUCT = "product";

    public static void main(String[] args) throws Exception {
        System.setProperty("WORKFLOW_LOG_FOLDER", "./logs/");

        //TODO Configure input/output
        Path inputDirPath = Paths.get(INPUT_DIR_PATH);
        Path outputDirPath = Paths.get(OUTPUT_DIR_PATH	);

        //TODO Configure fields according to your use-case
        List<FieldInfo> fields = new ArrayList<>();
        fields.add(new FieldInfo.Builder("invoice_number")
                .type(FieldType.INVOICE_NUMBER)
                .build());
        fields.add(new FieldInfo.Builder("product")
                .type(FieldType.FREE_TEXT)
                .multiValue(true)
                .build());

        //TODO add parameters if needed.
        Map<String, Object> parameters = new HashMap<>();


        LocalTrainingConfiguration configuration = LocalTrainingConfiguration.builder()
                .inputDir(inputDirPath)
                .outputDir(outputDirPath)
                .fields(fields)
                .parameters(parameters)
                .build();

        ModelRunner.run(Assignment2Model.class, configuration);
    }

}