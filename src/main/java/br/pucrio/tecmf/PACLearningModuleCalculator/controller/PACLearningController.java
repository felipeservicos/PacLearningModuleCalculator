package br.pucrio.tecmf.PACLearningModuleCalculator.controller;

import br.pucrio.tecmf.PACLearningModuleCalculator.model.CalculatorModelBuilder;
import br.pucrio.tecmf.PACLearningModuleCalculator.model.MachineLearningModelEnum;
import br.pucrio.tecmf.PACLearningModuleCalculator.model.SpecsModel;
import br.pucrio.tecmf.PACLearningModuleCalculator.service.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@RestController
public class PACLearningController {


    @CrossOrigin
    @GetMapping("/runSimulations")
    ResponseEntity<List<SpecsModel>> calculate(Optional<Integer> features, Optional<Integer> neurons,
                                               Optional<Integer> layers, Optional<Double> accuracy,
                                               Optional<Double> reliability, Optional<Integer> range, Optional<Integer> treeHeight) {

        CalculatorModelBuilder calculatorModelBuilder = new CalculatorModelBuilder()
                .features(features.orElse(0))
                .neurons(neurons.orElse(0))
                .layers(layers.orElse(0))
                .accuracy(accuracy.orElse(0.0))
                .reliability(reliability.orElse(0.0))
                .range(range.orElse(0))
                .treeHeight(treeHeight.orElse(0));


        //Out Object
        List<SpecsModel> specsModelsList = new ArrayList<>();


        // //Calculate for all implementations of PACLearningCalculator and after, add to the list of models and return it

        //Linear Regression
        IPACLearningCalculator calculatorLinearRegression = new LinearRegressionCalculatorService(calculatorModelBuilder.getFeatures());


        SpecsModel linearRegressionModel = new SpecsModel(MachineLearningModelEnum.LINEAR_REGRESSION, calculatorLinearRegression.estimateVCDim(),
                calculatorLinearRegression.calculateMinimalSample(calculatorModelBuilder.getAccuracy(), calculatorModelBuilder.getReliability()));

        specsModelsList.add(linearRegressionModel);

        //Neural Network
        IPACLearningCalculator calculatorNeuralNetwork = new NeuralNetworkCalculatorService(calculatorModelBuilder.getFeatures(),
                calculatorModelBuilder.getNeurons(), calculatorModelBuilder.getLayers());
        SpecsModel neuralNetworkModel = new SpecsModel(MachineLearningModelEnum.NEURAL_NETWORK, calculatorNeuralNetwork.estimateVCDim(),
                calculatorNeuralNetwork.calculateMinimalSample(calculatorModelBuilder.getAccuracy(), calculatorModelBuilder.getReliability()));

        specsModelsList.add(neuralNetworkModel);

        //Random Forest
        IPACLearningCalculator calculatorRandomForest = new RandomForestCalculatorService(calculatorModelBuilder.getFeatures(),
                calculatorModelBuilder.getTreeHeight());
        SpecsModel randomForestModel = new SpecsModel(MachineLearningModelEnum.RANDOM_FOREST, calculatorRandomForest.estimateVCDim(),
                calculatorRandomForest.calculateMinimalSample(calculatorModelBuilder.getAccuracy(), calculatorModelBuilder.getReliability()));
        specsModelsList.add(randomForestModel);

        //SVM
        IPACLearningCalculator calculatorSVM = new SVMCalculatorService(calculatorModelBuilder.getFeatures());
        SpecsModel svmModel = new SpecsModel(MachineLearningModelEnum.SVM, calculatorSVM.estimateVCDim(),
                calculatorSVM.calculateMinimalSample(calculatorModelBuilder.getAccuracy(), calculatorModelBuilder.getReliability()));
        specsModelsList.add(svmModel);


        return new ResponseEntity<List<SpecsModel>>(specsModelsList, HttpStatus.OK);
    }

    @CrossOrigin
    @GetMapping("/lowerBoundsSamplesBetweenAccuracyAndReliability")
    ResponseEntity<Integer[][]> calculate(Optional<Integer> features, Optional<Integer> neurons,
                                          Optional<Integer> layers, Optional<Integer> range, Optional<Integer> treeHeight, Optional<MachineLearningModelEnum> model) {

        if (!model.isPresent()) {
            return new ResponseEntity<Integer[][]>(HttpStatus.BAD_REQUEST);
        }

        CalculatorModelBuilder calculatorModelBuilder = new CalculatorModelBuilder()
                .features(features.orElse(0))
                .neurons(neurons.orElse(0))
                .layers(layers.orElse(0))
                .range(range.orElse(0))
                .treeHeight(treeHeight.orElse(0));

        switch (model.get()) {
            case LINEAR_REGRESSION -> {
                IPACLearningCalculator calculator = new LinearRegressionCalculatorService(calculatorModelBuilder.getFeatures());

                Integer[][] samplesMatrix = getMatrix(calculatorModelBuilder, calculator);

                //return matrix with minimals samples for specified range.
                return new ResponseEntity<Integer[][]>(samplesMatrix, HttpStatus.OK);

            }
            case NEURAL_NETWORK -> {
                IPACLearningCalculator calculator = new NeuralNetworkCalculatorService(calculatorModelBuilder.getFeatures(),
                        calculatorModelBuilder.getNeurons(), calculatorModelBuilder.getLayers());
                Integer[][] samplesMatrix = getMatrix(calculatorModelBuilder, calculator);

                //return matrix with minimals samples for specified range.
                return new ResponseEntity<Integer[][]>(samplesMatrix, HttpStatus.OK);
            }
            case RANDOM_FOREST -> {
                IPACLearningCalculator calculator = new RandomForestCalculatorService(calculatorModelBuilder.getFeatures(), calculatorModelBuilder.getTreeHeight());
                Integer[][] samplesMatrix = getMatrix(calculatorModelBuilder, calculator);

                //return matrix with minimals samples for specified range.
                return new ResponseEntity<Integer[][]>(samplesMatrix, HttpStatus.OK);
            }
            case SVM -> {
                IPACLearningCalculator calculator = new SVMCalculatorService(calculatorModelBuilder.getFeatures());
                Integer[][] samplesMatrix = getMatrix(calculatorModelBuilder, calculator);

                //return matrix with minimals samples for specified range.
                return new ResponseEntity<Integer[][]>(samplesMatrix, HttpStatus.OK);
            }
            default -> {
                return new ResponseEntity<Integer[][]>(HttpStatus.BAD_REQUEST);
            }
        }


    }


    /**
     * Generate a matrix with minimals samples for specified range. This method is reusable for all implementations of IPACLearningCalculator
     * @param calculatorModelBuilder
     * @param calculator is a implementation of IPACLearningCalculator according to the model
     * @return
     */

    private static Integer[][] getMatrix(CalculatorModelBuilder calculatorModelBuilder, IPACLearningCalculator calculator) {
        //generate a matrix sample value given accuracy and reliability
        Integer[][] samplesMatrix = new Integer[calculatorModelBuilder.getRange()][calculatorModelBuilder.getRange()];

        int accuracyValue = 100 - calculatorModelBuilder.getRange();
        int reliabilityValue = 100 - calculatorModelBuilder.getRange();
        for (int i = 0; i < calculatorModelBuilder.getRange(); i++) {
            for (int j = 0; j < calculatorModelBuilder.getRange(); j++) {

                //save in matrix
                samplesMatrix[i][j] = calculator.calculateMinimalSample(((double)accuracyValue / 100), ((double)reliabilityValue / 100));

                accuracyValue = accuracyValue+ i;
                reliabilityValue = reliabilityValue + j;

            }

        }

        System.out.println("MATRIX (Just for view in output console):================");
        for (Integer[] row : samplesMatrix){
            System.out.println(Arrays.toString(row));
        }
        return samplesMatrix;
    }



}
