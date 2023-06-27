package br.pucrio.tecmf.PACLearningModuleCalculator.service;

import org.springframework.stereotype.Service;

@Service
public interface IPACLearningCalculator {

    //TODO add more methods for PAC Learning

    public Integer calculateMinimalSample(Double accuracy, Double reliability);
    public Integer estimateVCDim();
    public Integer[] [] lowerBoundsSamplesBetweenAccuracyAndReliability(Double[] accuracy,Double [] reliability,
                                                                        Integer VCDim, Integer range);

}
