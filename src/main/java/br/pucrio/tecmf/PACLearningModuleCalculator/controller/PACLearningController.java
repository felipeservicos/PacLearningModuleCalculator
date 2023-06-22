package br.pucrio.tecmf.PACLearningModuleCalculator.controller;

import br.pucrio.tecmf.PACLearningModuleCalculator.model.CalculatorModelBuilder;
import br.pucrio.tecmf.PACLearningModuleCalculator.model.MachineLearningModelEnum;
import br.pucrio.tecmf.PACLearningModuleCalculator.model.SpecsModel;
import br.pucrio.tecmf.PACLearningModuleCalculator.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
public class PACLearningController {


    @CrossOrigin
    @PostMapping("/runSimulations")
    ResponseEntity< List<SpecsModel>> calculate(Optional<Integer> features, Optional<Integer> neurons,
                                                 Optional<Integer> layers, Optional<Integer> accuracy,
                                                 Optional<Integer> reliability, Optional<Integer> range) {

        CalculatorModelBuilder calculatorModelBuilder = new CalculatorModelBuilder()
                .features(features.orElse(0))
                .neurons(neurons.orElse(0))
                .layers(layers.orElse(0))
                .accuracy(accuracy.orElse(0))
                .reliability(reliability.orElse(0))
                .range(range.orElse(0));


        List<SpecsModel> specsModelsList = new ArrayList<>();


       // //Calculate for all implementations of PACLearningCalculator and after, add to the list of models and return it

       //Linear Regression
        IPACLearningCalculator calculatorLinearRegression = new LinearRegressionCalculatorService(calculatorModelBuilder.getFeatures());

        Integer vcs=calculatorLinearRegression.estimateVCDim();

        Integer numero=calculatorLinearRegression.calculateMinimalSample(calculatorModelBuilder.getAccuracy(), calculatorModelBuilder.getReliability());


       SpecsModel linearRegressionModel= new SpecsModel(MachineLearningModelEnum.LINEAR_REGRESSION, calculatorLinearRegression.estimateVCDim(),
               calculatorLinearRegression.calculateMinimalSample(calculatorModelBuilder.getAccuracy(), calculatorModelBuilder.getReliability()));

        specsModelsList.add(linearRegressionModel);

        //Neural Network
        IPACLearningCalculator calculatorNeuralNetwork= new NeuralNetworkCalculatorService(calculatorModelBuilder.getFeatures(),
                calculatorModelBuilder.getNeurons(), calculatorModelBuilder.getLayers());
        SpecsModel neuralNetworkModel= new SpecsModel(MachineLearningModelEnum.NEURAL_NETWORK, calculatorNeuralNetwork.estimateVCDim(),
                calculatorNeuralNetwork.calculateMinimalSample(calculatorModelBuilder.getAccuracy(), calculatorModelBuilder.getReliability()));

        specsModelsList.add(neuralNetworkModel);

        //Random Forest
        IPACLearningCalculator calculatorRandomForest = new RandomForestCalculatorService(calculatorModelBuilder.getFeatures());
        SpecsModel randomForestModel= new SpecsModel(MachineLearningModelEnum.RANDOM_FOREST, calculatorRandomForest.estimateVCDim(),
                calculatorRandomForest.calculateMinimalSample(calculatorModelBuilder.getAccuracy(), calculatorModelBuilder.getReliability()));
        specsModelsList.add(randomForestModel);

        //SVM
        IPACLearningCalculator calculatorSVM = new SVMCalculatorService(calculatorModelBuilder.getFeatures());
        SpecsModel svmModel= new SpecsModel(MachineLearningModelEnum.SVM, calculatorSVM.estimateVCDim(),
                calculatorSVM.calculateMinimalSample(calculatorModelBuilder.getAccuracy(), calculatorModelBuilder.getReliability()));
        specsModelsList.add(svmModel);






        return new ResponseEntity< List<SpecsModel>>(specsModelsList, HttpStatus.OK);
    }

    @GetMapping("/hello")
    public String getHello(Integer valor) {
        return valor.toString();
    }
}
