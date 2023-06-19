package br.pucrio.tecmf.PACLearningModuleCalculator.service;

import org.springframework.stereotype.Service;

@Service
public interface IPACLearningCalculator {

    //TODO add more methods for PAC Learning

    public Integer calculateMinimalSample(Integer accuracy, Integer reliability);
    public Integer estimateVCDim();
    public Integer[] [] lowerBoundsSamplesBetweenAccuracyAndReliability(Integer[] accuracy,Integer [] reliability,
                                                                        Integer VCDim, Integer range);

}
