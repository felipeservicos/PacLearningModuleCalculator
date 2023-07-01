package br.pucrio.tecmf.PACLearningModuleCalculator.model;


public class CalculatorModelBuilder {




    private Integer features;
    private Integer neurons;
    private Integer layers;
    private Integer VCDim;
    private Double accuracy;
    private Double reliability;
    private Integer range;

    private Integer treeHeight;


    public CalculatorModelBuilder features(Integer features) {
        this.features = features;
        return this;
    }

    public CalculatorModelBuilder neurons(Integer neurons) {
        this.neurons = neurons;
        return this;
    }

    public CalculatorModelBuilder layers(Integer layers) {
        this.layers = layers;
        return this;
    }


    public CalculatorModelBuilder VCDim(Integer VCDim) {
        this.VCDim = VCDim;
        return this;
    }

    public CalculatorModelBuilder accuracy(Double accuracy) {
        this.accuracy = accuracy;
        return this;
    }

    public CalculatorModelBuilder reliability(Double reliability) {
        this.reliability = reliability;
        return this;
    }

    public CalculatorModelBuilder treeHeight(Integer treeHeight) {
        this.treeHeight = treeHeight;
        return this;
    }


    public CalculatorModelBuilder range(Integer range) {
        this.range = range;
        return this;

    }

    public Integer getVCDimForLinearRegression() {
        return this.features + 1;
    }


    public Integer getVCDimForNeuralNetwork() {
        return (this.features * this.neurons * this.layers) + 2;
    }


    public Integer getVCDimForRandomForest() {
        return (int) Math.pow(2, this.features)*treeHeight;
    }


    public Integer getVCDimForSVM() {
        return (int) Math.pow(2, this.features);

    }



    public Integer getFeatures() {
        return features;
    }

    public Integer getNeurons() {
        return neurons;
    }

    public Integer getLayers() {
        return layers;
    }

    public Double getAccuracy() {
        return accuracy;
    }

    public Integer getTreeHeight() {
        return treeHeight;
    }

    public Double getReliability() {
        return reliability;
    }

    public int getRange() { return range; }

    public void accuracyDecrement() {
        this.accuracy = this.accuracy - 0.01;

    }

    public void reliabilityDecrement() {
        this.reliability = this.reliability - 0.01;
    }


}
