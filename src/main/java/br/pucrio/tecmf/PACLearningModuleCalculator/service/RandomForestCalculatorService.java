package br.pucrio.tecmf.PACLearningModuleCalculator.service;

import br.pucrio.tecmf.PACLearningModuleCalculator.model.CalculatorModelBuilder;
import org.springframework.stereotype.Service;


public class RandomForestCalculatorService implements IPACLearningCalculator {

private Integer features;

public RandomForestCalculatorService(Integer features) {
    this.features=features;
}
    /**
     * @param accuracy
     * @param reliability
     * @return
     */
    @Override
    public Integer calculateMinimalSample(Double accuracy, Double reliability) {

        return (int) (1/accuracy*(estimateVCDim()+Math.log(1/reliability)));
    }


    @Override
    public Integer estimateVCDim() {

        CalculatorModelBuilder calculator = new CalculatorModelBuilder()
                .features(this.features);


        return calculator.getVCDimForRandomForest();
    }

    /**
     * @param accuracy 
     * @param reliability
     * @param VCDim
     * @param range
     * @return
     */
    @Override
    public Integer[][] lowerBoundsSamplesBetweenAccuracyAndReliability(Double[] accuracy, Double[] reliability, Integer VCDim, Integer range) {
        return new Integer[0][];
    }


}
