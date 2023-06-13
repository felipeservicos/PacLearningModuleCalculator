package br.pucrio.tecmf.PACLearningModuleCalculator.service;

public interface IPACLearningCalculator {

    //TODO add more methods for PAC Learning

    public Integer calculateMinimalSample(Integer accuracy, Integer reliability, Integer VCDim);
    public Integer estimateVCDim(Integer features);
    public Integer[] [] lowerBoundsSamplesBetweenAccuracyAndReliability(Integer[] accuracy,Integer [] reliability,
                                                                        Integer VCDim, Integer range);

}
