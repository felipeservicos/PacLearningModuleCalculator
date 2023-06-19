package br.pucrio.tecmf.PACLearningModuleCalculator.controller;

import br.pucrio.tecmf.PACLearningModuleCalculator.model.CalculatorModelBuilder;
import br.pucrio.tecmf.PACLearningModuleCalculator.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
public class PACLearningController {


    @GetMapping("runSimulations")
    ResponseEntity< List<IPACLearningCalculator>> calculate(Optional<Integer> features, Optional<Integer> neurons, Optional<Integer> layers,
                                     Optional<Integer> accuracy, Optional<Integer> reliability, Optional<Integer> range) {

        CalculatorModelBuilder calculatorModelBuilder = new CalculatorModelBuilder()
                .features(features.orElse(0))
                .neurons(neurons.orElse(0))
                .layers(layers.orElse(0))
                .accuracy(accuracy.orElse(0))
                .reliability(reliability.orElse(0))
                .range(range.orElse(0));

        List<IPACLearningCalculator> calculators = new ArrayList<>();

        IPACLearningCalculator calculatorLinearRegression = new LinearRegressionCalculatorService(calculatorModelBuilder.getFeatures());
        IPACLearningCalculator calculatorNeuralNetwork= new NeuralNetworkCalculatorService(calculatorModelBuilder.getFeatures(),
                calculatorModelBuilder.getNeurons(), calculatorModelBuilder.getLayers());
        IPACLearningCalculator calculatorRandomForest = new RandomForestCalculatorService(calculatorModelBuilder.getFeatures());
        IPACLearningCalculator calculatorSVM = new SVMCalculatorService(calculatorModelBuilder.getFeatures());



        calculators.add(calculatorLinearRegression);
        calculators.add(calculatorNeuralNetwork);
        calculators.add(calculatorRandomForest);
        calculators.add(calculatorSVM);



        return new ResponseEntity< List<IPACLearningCalculator>>(calculators, HttpStatus.OK);
    }
}
