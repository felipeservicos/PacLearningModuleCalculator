package br.pucrio.tecmf.PACLearningModuleCalculator.service;

import org.springframework.stereotype.Service;

@Service
public class RandomForestCalculatorService implements IPACLearningCalculator {


    /**
     * @param accuracy 
     * @param reliability
     * @param VCDim
     * @return
     */
    @Override
    public Integer calculateMinimalSample(Integer accuracy, Integer reliability, Integer VCDim) {
        return null;
    }

    /**
     * @param features 
     * @return
     */
    @Override
    public Integer estimateVCDim() {
        return null;
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
