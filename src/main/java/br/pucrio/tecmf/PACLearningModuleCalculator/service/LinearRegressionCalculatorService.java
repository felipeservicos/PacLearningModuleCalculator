package br.pucrio.tecmf.PACLearningModuleCalculator.service;


import br.pucrio.tecmf.PACLearningModuleCalculator.model.CalculatorModelBuilder;


public class LinearRegressionCalculatorService implements IPACLearningCalculator {

    private Integer features;



    public LinearRegressionCalculatorService(Integer features) {
        this.features=features;
    }


    /**
     * @param accuracy
     * @param reliability
     * @return
     */
    @Override
    public Integer calculateMinimalSample(Double accuracy, Double reliability) {

         return (int) (1/(1-accuracy)*(estimateVCDim()+Math.log(1/(1-reliability))));
    }


    @Override
    public Integer estimateVCDim() {

        CalculatorModelBuilder calculator = new CalculatorModelBuilder()
               .features(this.features);



        return calculator.getVCDimForLinearRegression();


    }


}
