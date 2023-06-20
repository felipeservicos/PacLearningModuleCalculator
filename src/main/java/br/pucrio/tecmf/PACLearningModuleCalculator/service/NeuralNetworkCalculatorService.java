package br.pucrio.tecmf.PACLearningModuleCalculator.service;


import br.pucrio.tecmf.PACLearningModuleCalculator.model.CalculatorModelBuilder;
import org.springframework.stereotype.Service;

@Service
public class NeuralNetworkCalculatorService implements IPACLearningCalculator {

    private Integer features;
    private Integer neurons;
    private Integer layers;

    public NeuralNetworkCalculatorService(Integer features, Integer neurons, Integer layers) {
        this.features = features;
        this.neurons = neurons;
        this.layers = layers;
    }


    @Override
    public Integer calculateMinimalSample(Integer accuracy, Integer reliability) {
        return (int) (1/accuracy*(estimateVCDim()+Math.log(1/reliability)));
    }

    @Override
    public Integer estimateVCDim() {

        CalculatorModelBuilder calculator = new CalculatorModelBuilder()
                .features(this.features)
                .neurons(this.neurons)
                .layers(this.layers)
                .build();


        return calculator.getVCDimForNeuralNetwork();
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
