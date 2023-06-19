package br.pucrio.tecmf.PACLearningModuleCalculator.model;

public class SpecsModel {
    private MachineLearningModelEnum model;
    private Integer VCDim;
    private Integer minimalSample;

    // getters and setters
    public MachineLearningModelEnum getModel() {
        return model;
    }
    public void setModel(MachineLearningModelEnum model) {
        this.model = model;
    }
    public Integer getVCDim() {
        return VCDim;
    }
    public void setVCDim(Integer vCDim) {
        VCDim = vCDim;
    }
    public Integer getMinimalSample() {
        return minimalSample;
    }
    public void setMinimalSample(Integer minimalSample) {
        this.minimalSample = minimalSample;
    }


}
