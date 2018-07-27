package com.workfusion.lab.lesson9;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Map;

import org.junit.Test;

import com.workfusion.lab.lesson9.config.Assignment1ModelConfiguration;
import com.workfusion.lab.lesson9.config.Assignment2ModelConfiguration;
import com.workfusion.lab.lesson9.run.Assignment1ModelExecutionRunner;
import com.workfusion.lab.lesson9.run.Assignment1ModelTrainingRunner;
import com.workfusion.lab.lesson9.run.Assignment2ModelExecutionRunner;
import com.workfusion.lab.lesson9.run.Assignment2ModelTrainingRunner;
import com.workfusion.lab.utils.LessonTestBase;

import static com.workfusion.lab.lesson9.run.Assignment1ModelTrainingRunner.OUTPUT_DIR_PATH;

public class Lesson9Test extends LessonTestBase {

    /**
     * Assignment 1:
     * Provide annotators, FEs and post-processor(for 'date' field) configuration for fields:
     * - "invoice_number".
     * - "date"
     * Use solutions from the previous lessons.
     * The configuration must be the fields content depended (revise the Lesson 5).
     * Define field configuration and provided paths in the provided Assignment1ModelExecutionRunner.
     * <p>
     * The test runs the model training runner, and check the avg-evaluation-results.txt.
     * You need to provide the annotators/FE configuration that gives:
     * - "invoice_number": P>0.9 and R>0.6
     * - "date": P>0.9 and R>0.6
     * <p>
     * Then test runs the model execution runner, and check the per-field-statistics.csv.
     * - "invoice_number": P>0.9 and R>0.6
     * - "date": P>0.9 and R>0.6
     */
    @Test
    public void assignment1() throws Exception {

        // Obtains training statistics
        executeRunner(Assignment1ModelTrainingRunner.class);
        Map<String, FieldStatistic> trainingStatistics = getTrainingFieldStatistics(Assignment1ModelTrainingRunner.OUTPUT_DIR_PATH);

        // Check the field statistics
        checkFieldStatistics(trainingStatistics, Assignment1ModelConfiguration.FIELD_INVOICE_NUMBER, 0.9, 0.6);
        checkFieldStatistics(trainingStatistics, Assignment1ModelConfiguration.FIELD_DATE, 0.9, 0.6);

        executeRunner(Assignment1ModelExecutionRunner.class);
        Map<String, FieldStatistic> executionStatistics = getExecutionFieldStatistics(Assignment1ModelTrainingRunner.OUTPUT_DIR_PATH + "/extract");

        // Check the field statistics
        checkFieldStatistics(executionStatistics, Assignment1ModelConfiguration.FIELD_INVOICE_NUMBER, 0.9, 0.6);
        checkFieldStatistics(executionStatistics, Assignment1ModelConfiguration.FIELD_DATE, 0.9, 0.6);

    }

    /**
     * Assignment 2:
     * Check the provided data and provide the configuration for custom model
     * The test runs the model training runner, and check the avg-evaluation-results.txt.
     * You need to provide the annotators/FE/processors configuration that gives:
     * for all fields: P>0.9 and R>0.6
     * <p>
     * Then test runs the model execution runner, and check the per-field-statistics.csv.
     * for all fields: P>0.9 and R>0.6
     * List of fields:
     * - "price"
     * - "product"
     * - "client_name"
     */
    @Test
    public void assignment2() throws Exception {

        // Obtains training statistics
        executeRunner(Assignment2ModelTrainingRunner.class);
        Map<String, FieldStatistic> trainingStatistics = getTrainingFieldStatistics(Assignment2ModelTrainingRunner.OUTPUT_DIR_PATH);

        // Check the field statistics
        checkFieldStatistics(trainingStatistics, Assignment2ModelConfiguration.FIELD_PRICE, 0.9, 0.6);
        checkFieldStatistics(trainingStatistics, Assignment2ModelConfiguration.FIELD_PRODUCT, 0.9, 0.6);
        checkFieldStatistics(trainingStatistics, Assignment2ModelConfiguration.FIELD_CLIENT_NAME, 0.9, 0.6);

        executeRunner(Assignment2ModelExecutionRunner.class);
        Map<String, FieldStatistic> executionStatistics = getExecutionFieldStatistics(Assignment2ModelTrainingRunner.OUTPUT_DIR_PATH + "/extract");

        // Check the field statistics
        checkFieldStatistics(executionStatistics, Assignment2ModelConfiguration.FIELD_PRICE, 0.9, 0.6);
        checkFieldStatistics(executionStatistics, Assignment2ModelConfiguration.FIELD_PRODUCT, 0.9, 0.6);
        checkFieldStatistics(executionStatistics, Assignment2ModelConfiguration.FIELD_CLIENT_NAME, 0.9, 0.6);
    }

}
