package br.pucrio.tecmf.PACLearningModuleCalculator.service;

import br.pucrio.tecmf.PACLearningModuleCalculator.model.CalculatorModelBuilder;


public class RandomForestCalculatorService implements IPACLearningCalculator {

    private final Integer treeHeight;
    private Integer features;

public RandomForestCalculatorService(Integer features, Integer treeHeight) {
    this.features=features;
    this.treeHeight=treeHeight;
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
                .features(this.features)
                .treeHeight(this.treeHeight);


        return calculator.getVCDimForRandomForest();
    }


}
