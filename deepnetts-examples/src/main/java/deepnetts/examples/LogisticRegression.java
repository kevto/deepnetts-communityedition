/**  
 *  DeepNetts is pure Java Deep Learning Library with support for Backpropagation 
 *  based learning and image recognition.
 * 
 *  Copyright (C) 2017  Zoran Sevarac <sevarac@gmail.com>
 *
 *  This file is part of DeepNetts.
 *
 *  DeepNetts is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with this program.  If not, see <https://www.gnu.org/licenses/>.package deepnetts.core;
 */
    
package deepnetts.examples;

import deepnetts.data.BasicDataSet;
import deepnetts.data.DataSet;
import deepnetts.net.FeedForwardNetwork;
import deepnetts.net.NeuralNetwork;
import deepnetts.net.layers.activation.ActivationType;
import deepnetts.net.loss.LossType;
import deepnetts.net.train.BackpropagationTrainer;
import java.io.File;
import java.io.IOException;

/**
 * Minimal example for logistic regression using FeedForwardNetwork.
 * Can perform binary classification (single output true/false)
 Uses only input and output addLayer with sigmoid activation function
 Just specify number of inputs and provide data set
 * 
 * @author Zoran Sevarac
 */
public class LogisticRegression {

    
    public static void main(String[] args) throws IOException {
        
        String datasetFile = "datasets/sonar.csv";
        int inputsCount = 60;
        int outputsCount = 1;
        String delimiter = ",";
        
        DataSet dataSet = BasicDataSet.fromCSVFile(new File(datasetFile), inputsCount, outputsCount, delimiter);
        
        NeuralNetwork neuralNet = FeedForwardNetwork.builder()
                .addInputLayer(60)
                .addOutputLayer(1, ActivationType.SIGMOID)
                .withLossFunction(LossType.MEAN_SQUARED_ERROR)
                .build();
        
        BackpropagationTrainer trainer = new BackpropagationTrainer();
        trainer.setLearningRate(0.1f)
               .train(neuralNet, dataSet);        
                
    }
    
}
