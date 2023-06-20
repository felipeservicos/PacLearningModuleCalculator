package br.pucrio.tecmf.PACLearningModuleCalculator.service;


import br.pucrio.tecmf.PACLearningModuleCalculator.model.CalculatorModelBuilder;
import org.springframework.stereotype.Service;

@Service
public class SVMCalculatorService implements IPACLearningCalculator {


    private Integer features;

    public SVMCalculatorService(Integer features) {
        this.features=features;
    }

    /**
     * @param accuracy
     * @param reliability
     * @return
     */
    @Override
    public Integer calculateMinimalSample(Integer accuracy, Integer reliability) {

        return (int) (1/accuracy*(estimateVCDim()+Math.log(1/reliability)));
    }

    @Override
    public Integer estimateVCDim() {

        CalculatorModelBuilder calculator = new CalculatorModelBuilder()
                .features(this.features)
                .build();

        return calculator.getVCDimForSVM();
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
