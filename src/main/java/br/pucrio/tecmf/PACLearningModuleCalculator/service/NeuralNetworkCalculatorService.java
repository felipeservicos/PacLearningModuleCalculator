package br.pucrio.tecmf.PACLearningModuleCalculator.service;


import br.pucrio.tecmf.PACLearningModuleCalculator.model.CalculatorModelBuilder;


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
    public Integer calculateMinimalSample(Double accuracy, Double reliability) {
         return (int) (1/(1-accuracy)*(estimateVCDim()+Math.log(1/(1-reliability))));
    }

    @Override
    public Integer estimateVCDim() {

        CalculatorModelBuilder calculator = new CalculatorModelBuilder()
                .features(this.features)
                .neurons(this.neurons)
                .layers(this.layers);



        return calculator.getVCDimForNeuralNetwork();
    }


}
