import weka.classifiers.Classifier;
import weka.classifiers.Evaluation;
import weka.classifiers.functions.MultilayerPerceptron;
import weka.classifiers.functions.SMO;
import weka.classifiers.lazy.IBk;
import weka.core.Instances;
import weka.core.converters.ArffLoader;
import weka.gui.beans.CrossValidationFoldMaker;

import java.io.File;
import java.util.Random;

public class Wekapi {
    public static Classifier getClassificador(){
        MultilayerPerceptron mlp = new MultilayerPerceptron();
        mlp.setTrainingTime(5000);
        mlp.setMomentum(0.9);
        mlp.setLearningRate(0.01);

        SMO svm = new SMO();

        IBk knn = new IBk();

        return svm;
    }

    public static double construirClassificador(Classifier classifier){
        try {
            ArffLoader loader = new ArffLoader();
            loader.setSource(new File("./tempteste/resultado.arff"));
            Instances data = loader.getDataSet();
            data.setClassIndex(data.numAttributes() - 1);

            classifier.buildClassifier(data);
            Evaluation evaluation = new Evaluation(data);

            CrossValidationFoldMaker crossValidation = new CrossValidationFoldMaker();
            crossValidation.setFolds(3);
            System.out.println(crossValidation.getFolds());
            //evaluation.evaluateModel(classifier, data);
            evaluation.crossValidateModel(classifier, data, crossValidation.getFolds(), new Random(1));
            return  evaluation.pctCorrect();
        }catch (Exception e){
            return -1;
        }

    }

    public static void main(String[] args) {
        System.out.println(getClassificador().getClass());
        System.out.println(construirClassificador(getClassificador()));
    }
}
