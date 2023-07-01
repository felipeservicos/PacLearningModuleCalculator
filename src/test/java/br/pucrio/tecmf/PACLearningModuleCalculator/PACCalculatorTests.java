package br.pucrio.tecmf.PACLearningModuleCalculator;

import br.pucrio.tecmf.PACLearningModuleCalculator.model.CalculatorModelBuilder;
import br.pucrio.tecmf.PACLearningModuleCalculator.model.MachineLearningModelEnum;
import br.pucrio.tecmf.PACLearningModuleCalculator.model.SpecsModel;
import br.pucrio.tecmf.PACLearningModuleCalculator.service.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class PACCalculatorTests {


    private final Integer features=15;
    private final Integer neurons=10;
    private final Integer layers=8;
    private final Double accuracy=0.99;
    private final Double reliability=0.99;
    private final Integer range=20;
    private final Integer treeHeight=6;

    @Test
    public void mustCalculateLinearRegressionVCDim() {
        CalculatorModelBuilder calculatorModelBuilder = new CalculatorModelBuilder()
                .features(features)
                .accuracy(accuracy)
                .reliability(reliability);
        IPACLearningCalculator calculatorLinearRegression = new LinearRegressionCalculatorService(calculatorModelBuilder.getFeatures());


        SpecsModel linearRegressionModel = new SpecsModel(MachineLearningModelEnum.LINEAR_REGRESSION, calculatorLinearRegression.estimateVCDim(),
                calculatorLinearRegression.calculateMinimalSample(calculatorModelBuilder.getAccuracy(), calculatorModelBuilder.getReliability()));

        Assertions.assertEquals(linearRegressionModel.getVCDim(),features+1);

    }

    @Test
    public void mustCalculateRandomForestVCDim() {

        CalculatorModelBuilder calculatorModelBuilder = new CalculatorModelBuilder()
                .features(features)
                .treeHeight(treeHeight)
                .accuracy(accuracy)
                .reliability(reliability);

        IPACLearningCalculator calculatorRandomForest = new RandomForestCalculatorService(calculatorModelBuilder.getFeatures(),
                calculatorModelBuilder.getTreeHeight());
        SpecsModel randomForestModel = new SpecsModel(MachineLearningModelEnum.RANDOM_FOREST, calculatorRandomForest.estimateVCDim(),
                calculatorRandomForest.calculateMinimalSample(calculatorModelBuilder.getAccuracy(), calculatorModelBuilder.getReliability()));

        Assertions.assertEquals(randomForestModel.getVCDim(),(int) Math.pow(2, features)*treeHeight);
    }

    @Test
    public void mustCalculateSVMVCDim() {

        CalculatorModelBuilder calculatorModelBuilder = new CalculatorModelBuilder()
                .features(features)
                .treeHeight(treeHeight)
                .accuracy(accuracy)
                .reliability(reliability);

        IPACLearningCalculator calculatorSVM = new SVMCalculatorService(calculatorModelBuilder.getFeatures());
        SpecsModel svmModel = new SpecsModel(MachineLearningModelEnum.SVM, calculatorSVM.estimateVCDim(),
                calculatorSVM.calculateMinimalSample(calculatorModelBuilder.getAccuracy(), calculatorModelBuilder.getReliability()));

        Assertions.assertEquals(svmModel.getVCDim(),(int) Math.pow(2, features));

    }

    @Test
    public void mustCalculateNeuralNetworkVCDim() {

        CalculatorModelBuilder calculatorModelBuilder = new CalculatorModelBuilder()
                .features(features)
                .neurons(neurons)
                .layers(layers)
                .accuracy(accuracy)
                .reliability(reliability);

        IPACLearningCalculator calculatorNeuralNetwork = new NeuralNetworkCalculatorService(calculatorModelBuilder.getFeatures(),
                calculatorModelBuilder.getNeurons(), calculatorModelBuilder.getLayers());
        SpecsModel neuralNetworkModel = new SpecsModel(MachineLearningModelEnum.NEURAL_NETWORK, calculatorNeuralNetwork.estimateVCDim(),
                calculatorNeuralNetwork.calculateMinimalSample(calculatorModelBuilder.getAccuracy(), calculatorModelBuilder.getReliability()));

        Assertions.assertEquals(neuralNetworkModel.getVCDim(),(this.features * this.neurons * this.layers) + 2);
    }

    @Test
    public void mustCalculateLinearRegressionMinimalSample() {


        CalculatorModelBuilder calculatorModelBuilder = new CalculatorModelBuilder()
                .features(features)
                .accuracy(accuracy)
                .reliability(reliability);
        IPACLearningCalculator calculatorLinearRegression = new LinearRegressionCalculatorService(calculatorModelBuilder.getFeatures());


        SpecsModel linearRegressionModel = new SpecsModel(MachineLearningModelEnum.LINEAR_REGRESSION, calculatorLinearRegression.estimateVCDim(),
                calculatorLinearRegression.calculateMinimalSample(calculatorModelBuilder.getAccuracy(), calculatorModelBuilder.getReliability()));


        Assertions.assertEquals(linearRegressionModel.getMinimalSample(),(int) (1/accuracy*(linearRegressionModel.getVCDim()+Math.log(1/reliability))));

    }

    @Test
    public void mustCalculateRandomForestMinimalSample() {


        CalculatorModelBuilder calculatorModelBuilder = new CalculatorModelBuilder()
                .features(features)
                .treeHeight(treeHeight)
                .accuracy(accuracy)
                .reliability(reliability);

        IPACLearningCalculator calculatorRandomForest = new RandomForestCalculatorService(calculatorModelBuilder.getFeatures(),
                calculatorModelBuilder.getTreeHeight());
        SpecsModel randomForestModel = new SpecsModel(MachineLearningModelEnum.RANDOM_FOREST, calculatorRandomForest.estimateVCDim(),
                calculatorRandomForest.calculateMinimalSample(calculatorModelBuilder.getAccuracy(), calculatorModelBuilder.getReliability()));

        Assertions.assertEquals(randomForestModel.getMinimalSample(),(int) (1/accuracy*(randomForestModel.getVCDim()+Math.log(1/reliability))));

    }

    @Test
    public void mustCalculateSVMMinimalSample() {

        CalculatorModelBuilder calculatorModelBuilder = new CalculatorModelBuilder()
                .features(features)
                .treeHeight(treeHeight)
                .accuracy(accuracy)
                .reliability(reliability);

        IPACLearningCalculator calculatorSVM = new SVMCalculatorService(calculatorModelBuilder.getFeatures());
        SpecsModel svmModel = new SpecsModel(MachineLearningModelEnum.SVM, calculatorSVM.estimateVCDim(),
                calculatorSVM.calculateMinimalSample(calculatorModelBuilder.getAccuracy(), calculatorModelBuilder.getReliability()));

        Assertions.assertEquals(svmModel.getMinimalSample(),(int) (1/accuracy*(svmModel.getVCDim()+Math.log(1/reliability))));


    }

    @Test
    public void mustCalculateNeuralNetworkMinimalSample() {

        CalculatorModelBuilder calculatorModelBuilder = new CalculatorModelBuilder()
                .features(features)
                .neurons(neurons)
                .layers(layers)
                .accuracy(accuracy)
                .reliability(reliability);

        IPACLearningCalculator calculatorNeuralNetwork = new NeuralNetworkCalculatorService(calculatorModelBuilder.getFeatures(),
                calculatorModelBuilder.getNeurons(), calculatorModelBuilder.getLayers());
        SpecsModel neuralNetworkModel = new SpecsModel(MachineLearningModelEnum.NEURAL_NETWORK, calculatorNeuralNetwork.estimateVCDim(),
                calculatorNeuralNetwork.calculateMinimalSample(calculatorModelBuilder.getAccuracy(), calculatorModelBuilder.getReliability()));

        Assertions.assertEquals(neuralNetworkModel.getMinimalSample(),(int) (1/accuracy*(neuralNetworkModel.getVCDim()+Math.log(1/reliability))));

    }


}
