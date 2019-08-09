/*
 * Copyright (C) WorkFusion 2018. All rights reserved.
 */
package com.workfusion.lab.lesson9.run;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

import com.workfusion.lab.lesson9.model.Assignment1Model;
import com.workfusion.vds.sdk.run.ModelRunner;
import com.workfusion.vds.sdk.run.config.LocalExecutionConfiguration;

/**
 * Runner class for local model execution.
 * Could be used for model tuning and post-processing development.
 * Paths to input documents, trained model and output folders are required for the launch.
 */
public class Assignment1ModelExecutionRunner {

    /**
     * Model directory path to use.
     */
    private final static String MODEL_DIR_PATH = "results_assignment1/training/output/model";

    /**
     * Input directory path to use.
     */
    private final static String INPUT_DIR_PATH = "data/test";

    /**
     * Output directory path to use.
     */
    public final static String OUTPUT_DIR_PATH = "results_assignment1/extract";

    public static void main(String[] args) throws Exception {
        System.setProperty("WORKFLOW_LOG_FOLDER", "./logs/");

        //TODO Configure input/output
        Path trainedModelPath = Paths.get(MODEL_DIR_PATH);
        Path inputDirPath = Paths.get(INPUT_DIR_PATH);
        Path outputDirPath = Paths.get(OUTPUT_DIR_PATH);

        Map<String, Object> parameters = new HashMap<>();

        LocalExecutionConfiguration configuration = LocalExecutionConfiguration.builder()
                .inputDir(inputDirPath)
                .outputDir(outputDirPath)
                .trainedModelDir(trainedModelPath)
                .parameters(parameters)
                .build();

        ModelRunner.run(Assignment1Model.class, configuration);
    }

}