package com.workfusion.lab.lesson8.model;

import com.workfusion.lab.lesson8.config.Assignment1ModelConfiguration;
import com.workfusion.vds.nlp.hypermodel.ie.generic.GenericIeHypermodel;
import com.workfusion.vds.sdk.api.hypermodel.ModelType;
import com.workfusion.vds.sdk.api.hypermodel.annotation.HypermodelConfiguration;
import com.workfusion.vds.sdk.api.hypermodel.annotation.ModelDescription;

/**
 * The model class. Define here you model details like code, version etc.
 */
@ModelDescription(
        code = "wf-lab-ml-sdk-lesson-8-model",
        title = "WF Lab ML-SDK Lesson 8 Model (1.0)",
        description = "WF Lab ML-SDK Lesson 8 Model (1.0)",
        version = "1.0",
        type = ModelType.IE
)
@HypermodelConfiguration(Assignment1ModelConfiguration.class)
public class Assignment1Model extends GenericIeHypermodel {

    public Assignment1Model() throws Exception {
    }
}
