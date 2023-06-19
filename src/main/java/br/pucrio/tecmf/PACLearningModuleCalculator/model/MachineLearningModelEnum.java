package br.pucrio.tecmf.PACLearningModuleCalculator.model;

public enum MachineLearningModelEnum {
    LINEAR_REGRESSION("Linear Regression"),
    RANDOM_FOREST("Random Forest"),
    SVM("Support Vector Machine"),
    NEURAL_NETWORK("Neural Network");

    private String name;

    MachineLearningModelEnum(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
