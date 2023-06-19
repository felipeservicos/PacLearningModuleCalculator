package br.pucrio.tecmf.PACLearningModuleCalculator.controller;

import br.pucrio.tecmf.PACLearningModuleCalculator.model.CalculatorModelBuilder;
import br.pucrio.tecmf.PACLearningModuleCalculator.service.IPACLearningCalculator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
public class PACLearningController {

    @Autowired
    IPACLearningCalculator calculator;

    @GetMapping("runSimulations")
    ResponseEntity<String> calculate(Optional<Integer> features, Optional<Integer> neurons, Optional<Integer> layers,
                                     Optional<Integer> accuracy, Optional<Integer> reliability, Optional<Integer> range) {

        CalculatorModelBuilder calculatorModelBuilder = new CalculatorModelBuilder()
                .features(features.orElse(0))
                .neurons(neurons.orElse(0))
                .layers(layers.orElse(0))
                .accuracy(accuracy.orElse(0))
                .reliability(reliability.orElse(0))
                .range(range.orElse(0));


        return new ResponseEntity<String>("OláMundo!", HttpStatus.OK);
    }
}
