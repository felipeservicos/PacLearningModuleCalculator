package br.pucrio.tecmf.PACLearningModuleCalculator.service;


import org.springframework.stereotype.Service;

@Service
public class NeuralNetworkCalculatorService implements IPACLearningCalculator {

    private Integer neurons;
    private Integer layers;

    public NeuralNetworkCalculatorService(Integer neurons, Integer layers) {
        this.neurons = neurons;
        this.layers = layers;
    }

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
//        return (features*neurons*layers)+2;
        return 0;
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
