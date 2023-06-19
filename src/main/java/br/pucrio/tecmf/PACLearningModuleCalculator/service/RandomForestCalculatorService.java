package br.pucrio.tecmf.PACLearningModuleCalculator.service;

import br.pucrio.tecmf.PACLearningModuleCalculator.model.CalculatorModelBuilder;
import org.springframework.stereotype.Service;

@Service
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
    public Integer calculateMinimalSample(Integer accuracy, Integer reliability) {
        return null;
    }


    @Override
    public Integer estimateVCDim() {

        CalculatorModelBuilder calculator = new CalculatorModelBuilder()
                .features(this.features)
                .build();

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
    public Integer[][] lowerBoundsSamplesBetweenAccuracyAndReliability(Integer[] accuracy, Integer[] reliability, Integer VCDim, Integer range) {
        return new Integer[0][];
    }


}
