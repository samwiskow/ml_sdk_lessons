package com.workfusion.lab.model;

import com.workfusion.vds.sdk.api.nlp.model.Element;
import com.workfusion.vds.sdk.api.nlp.model.Field;
import com.workfusion.vds.sdk.api.nlp.model.NamedEntity;
import com.workfusion.vds.sdk.api.nlp.model.Sentence;
import com.workfusion.vds.sdk.api.nlp.model.Token;

public class TestElementFactory {

    public static TestElement createElement(Element element) {
        if (element instanceof Token) {
            return createElement((Token)element);
        } else if (element instanceof NamedEntity) {
            return createElement((NamedEntity)element);
        } else if (element instanceof Field) {
            return createElement((Field)element);
        } else if (element instanceof Sentence) {
            return createElement((Sentence)element);
        } else {
            throw new AssertionError("Unknown element type!");
        }

    }

    public static TestElement createElement(Token token) {
        TestElement testElement = new TestElement();
        testElement.setBegin(token.getBegin());
        testElement.setEnd(token.getEnd());
        testElement.setText(token.getText());
        return testElement;
    }

    public static TestElement createElement(Sentence sentence) {
        TestSentence testElement = new TestSentence();
        testElement.setBegin(sentence.getBegin());
        testElement.setEnd(sentence.getEnd());
        testElement.setText(sentence.getText());
        return testElement;
    }

    public static TestElement createElement(NamedEntity namedEntity) {
        TestNamedEntity testElement = new TestNamedEntity();
        testElement.setBegin(namedEntity.getBegin());
        testElement.setEnd(namedEntity.getEnd());
        testElement.setText(namedEntity.getText());
        testElement.setType(namedEntity.getType());
        return testElement;
    }

    public static TestElement createElement(Field field) {
        TestField testField = new TestField();
        testField.setBegin(field.getBegin());
        testField.setEnd(field.getEnd());
        testField.setText(field.getText());
        testField.setScore(field.getScore());
        testField.setValue(field.getValue());
        testField.setName(field.getName());
        return testField;
    }

}
