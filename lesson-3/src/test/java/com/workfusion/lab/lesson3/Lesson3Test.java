package com.workfusion.lab.lesson3;

import java.util.Map;
import java.util.Set;

import org.junit.Test;

import com.workfusion.lab.lesson3.fe.Assignment1KeywordFE;
import com.workfusion.lab.lesson3.fe.Assignment2NerFE;
import com.workfusion.lab.lesson3.fe.Assignment3TableFE;
import com.workfusion.lab.lesson3.fe.Assignment4FirstInSentenceFE;
import com.workfusion.lab.lesson3.fe.Assignment5FirstInSentenceFE;
import com.workfusion.lab.utils.LessonTestBase;
import com.workfusion.vds.sdk.api.nlp.annotation.OnDocumentComplete;
import com.workfusion.vds.sdk.api.nlp.annotation.OnDocumentStart;
import com.workfusion.vds.sdk.api.nlp.fe.Feature;
import com.workfusion.vds.sdk.api.nlp.fe.FeatureExtractor;
import com.workfusion.vds.sdk.api.nlp.model.Cell;
import com.workfusion.vds.sdk.api.nlp.model.Content;
import com.workfusion.vds.sdk.api.nlp.model.Document;
import com.workfusion.vds.sdk.api.nlp.model.Element;
import com.workfusion.vds.sdk.api.nlp.model.IeDocument;
import com.workfusion.vds.sdk.api.nlp.model.NamedEntity;
import com.workfusion.vds.sdk.api.nlp.model.Sentence;
import com.workfusion.vds.sdk.api.nlp.model.Table;
import com.workfusion.vds.sdk.api.nlp.model.Token;
import com.workfusion.vds.sdk.nlp.component.annotator.EntityBoundaryAnnotator;
import com.workfusion.vds.sdk.nlp.component.annotator.ner.AhoCorasickDictionaryNerAnnotator;
import com.workfusion.vds.sdk.nlp.component.annotator.tokenizer.MatcherTokenAnnotator;
import com.workfusion.vds.sdk.nlp.component.annotator.tokenizer.SplitterTokenAnnotator;
import com.workfusion.vds.sdk.nlp.component.dictionary.CsvDictionaryKeywordProvider;

public class Lesson3Test extends LessonTestBase {

    /**
     * <p><b>Assignment 1</b></p>
     * <p>
     *     Provide a custom implementation of {@link FeatureExtractor} that adds a {@link Feature} with name="stateFeature" for Tokens,
     *     whose text equals to the following worlds: Missouri, Nevada, Alaska, Hawaii, Texas, Maryland, Vermont, Kentucky,
     *     Massachusetts, Pennsylvania, Virginia. As a template, use {@link Assignment1KeywordFE}
     * </p>
     * <p>Tips:</p>
     * <ul>
     *     <li>Use {@link Element#getText()} method to get Token text.</li>
     *     <li>Use {@code new Feature(FEATURE_NAME, 1.0)} to create a {@link Feature} for tokens that contain required word.
     *     Consider that generic type T in feature extractor is a Token.</li>
     * </ul>
     */
    @Test
    public void assignment1() throws Exception {
        // Creates ML-SDK Document to process
        IeDocument document = getDocument("documents/lesson_3_assignment_1.txt");

        // Process annotators
        processAnnotators(document,
                new MatcherTokenAnnotator("\\w+") // Add OTTB annotator that provides word based Token
        );

        // Process FEs
        Map<Element, Set<Feature>> providedElementFeatures = processFeatures(document,
                new Assignment1KeywordFE() //Assignment FE to check
        );

        // Checks the provided Features with the assignment 1 pattern
        checkElementFeatures(providedElementFeatures, "lesson_3_assignment_1_check.json");
    }


    /**
     * <p><b>Assignment 2</b></p>
     * <p>
     *     You are provided with a pre-made Document containing a set of {@link NamedEntity} elements with NER_TYPE = "state". Each of those
     *     elements represents a state of the USA (Missouri, Nevada, New Mexico, etc.) and covers one or two Tokens. Your task is to create
     *     a Feature Extractor that searches for the mentioned Named Entity element and returns a feature with FEATURE_NAME = "stateFeature".
     *     As a template, use {@link Assignment2NerFE}
     * </p>
     * <p>Tips:</p>
     * <ul>
     *     <li>Consider using {@link Document#findCovering(Class searchType, Element focusElement)} to search for a covering element.</li>
     * </ul>
     */
    @Test
    public void assignment2() throws Exception {
        // Creates ML-SDK Document to process
        IeDocument document = getDocument("documents/lesson_3_assignment_2.txt");

        // Process annotators
        processAnnotators(document,
                new MatcherTokenAnnotator("\\w+"), // OTTB annotator that provides word based Token
                new EntityBoundaryAnnotator(), // OOTB annotator to provide EntityBoundary elements
                new AhoCorasickDictionaryNerAnnotator(Assignment2NerFE.NER_TYPE,
                        new CsvDictionaryKeywordProvider(
                                getConfigurationContext().getResource("classpath:dictionary/states.csv"))
                                .setCsvColumnsToCollect(0)) //OOTB annotator that provides "state" NamedEntity
        );

        // Process FEs list
        Map<Element, Set<Feature>> providedElementFeatures = processFeatures(document,
                new Assignment2NerFE() //Assignment FE to check
        );

        // Checks the provided Features with the assignment 2 pattern
        checkElementFeatures(providedElementFeatures, "lesson_3_assignment_2_check.json");
    }

    /**
     * <p><b>Assignment 3</b></p>
     * <p>
     *     Create a Feature Extractor that generates features for {@link Token} elements located inside {@link Table} elements. For every
     *     Token that is covered by a {@link Cell} you need to provide two Features:
     *     <ol>
     *         <li>Index of the Row where the Token is located ({@code name="rowIndex", value=<row index>})</li>
     *         <li>Index of the Column where the Token is located ({@code name="columnIndex", value=<column index>})</li>
     *     </ol>
     * </p>
     * <p>Tips:</p>
     * <ul>
     *     <li>Consider using {@link Document#findCovering(Class searchClass, Element focusElement)} to search for a covering element.</li>
     *     <li>Use {@link Cell#getRowIndex()} to get an index of a row and {@link Cell#getColumnIndex()} to get an index of a column.</li>
     * </ul>
     */
    @Test
    public void assignment3() throws Exception {
        // Creates ML-SDK Document to process
        IeDocument document = getDocument("documents/lesson_3_assignment_3.html");

        // Process annotators
        processAnnotators(document,
                new MatcherTokenAnnotator("\\w+") // OTTB annotator that provides word based Tokens
        );

        // Process FEs list
        Map<Element, Set<Feature>> providedElementFeatures = processFeatures(document,
                new Assignment3TableFE() //Assignment FE to check
        );

        // Checks the provided Features with the assignment 2 pattern
        checkElementFeatures(providedElementFeatures, "lesson_3_assignment_3_check.json");
    }


    /**
     * <p><b>Assignment 4</b></p>
     * <p>
     *     Create a Feature Extractor that produces a feature with FEATURE_NAME = "firstInSentenceFeature" if a Token is the first Token in
     *     a {@link Sentence}. As a template, use {@link Assignment4FirstInSentenceFE}.
     * </p>
     * <p>Tips:</p>
     * <ul>
     *     <li>Use {@link Document#findCovering(Class searchClass, Element focusElement)} to search for covering elements
     *     and {@link Document#findCovered(Class searchClass, Element focusElement)} for covered elements.</li>
     *     <li></li>
     * </ul>
     */
    @Test
    public void assignment4() throws Exception {
        // Creates ML-SDK Document to process
        IeDocument document = getDocument("documents/lesson_3_assignment_4.txt");

        // Process annotators
        processAnnotators(document,
                new MatcherTokenAnnotator("\\w+"), //OTTB annotator that provides word based Tokens
                new SplitterTokenAnnotator(Sentence.class, Content.class, "\\n") // OTTB annotator that provides new line based Sentence
        );

        // Process FEs
        Map<Element, Set<Feature>> providedElementFeatures = processFeatures(document,
                new Assignment4FirstInSentenceFE() //Assignment FE to check
        );

        // Checks the provided Features with the assignments pattern
        checkElementFeatures(providedElementFeatures, "lesson_3_assignment_4_check.json");
    }

    /**
     * <p><b>Assignment 5</b></p>
     * <p>
     *     Improve the {@link Assignment4FirstInSentenceFE} Feature Extractor by reducing the time of the execute() method work.
     *     Requirements to this Feature Extractor remain the same as in Assignment 4, however, the following additional restrictions
     *     are added:
     *     <ul>
     *         <li>In a method annotated with {@link OnDocumentStart}, you need to cache all the Tokens that are first in their Sentences.</li>
     *         <li>In a method annotated with {@link OnDocumentComplete}, you need to release all the resources allocated by the Feature Extractor.</li>
     *         <li>Do not call any Document methods in the extract() method.</li>
     *         <li>Use the prepared cache to produce features.</li>
     *     </ul>
     * </p>
     * <p>Note:</p>
     * <p>
     *     You are still allowed to use Document methods in <b>other methods</b> of your Feature Extractor.
     * </p>
     */
    @Test
    public void assignment5() throws Exception {
        // Creates ML-SDK Document to process
        IeDocument document = getDocument("documents/lesson_3_assignment_5.txt");

        // Process annotators
        processAnnotators(document,
                new MatcherTokenAnnotator("\\w+"), // OTTB annotator that provides word based Tokens
                new SplitterTokenAnnotator(Sentence.class, Content.class, "\\n") // OTTB annotator that provides new line based Sentence
        );

        // Process FEs list
        Map<Element, Set<Feature>> providedElementFeatures = processFeatures(document, getNotAccessibleDocument(),
                new Assignment5FirstInSentenceFE()
        );

        // Checks the provided Features with the assignment 2 pattern
        checkElementFeatures(providedElementFeatures, "lesson_3_assignment_5_check.json");
    }
}
