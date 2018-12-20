import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;

import weka.classifiers.Evaluation;
import weka.classifiers.functions.MultilayerPerceptron;
import weka.core.Instances;
import weka.core.converters.ArffLoader;
import weka.core.converters.ArffSaver;
import weka.filters.Filter;
import weka.filters.unsupervised.attribute.Remove;

public class CarregaBase {
	public static void carregaBase(ArrayList<Integer> x){
        String s = x.toString();
        try {        
            
            ArffLoader loader = new ArffLoader();
            //loader.setSource(new File("./bases/hand-based-signature_keystroke.arff"));
            loader.setSource(new File("./bases/hand-based-signature_keystroke.arff"));
            Instances data = loader.getDataSet();
            // Load Arff
            String[] options = new String[2];
            options[0] = "-R"; // "range"
            options[1] = s.substring(1, s.length() - 1); 
                      
            Remove remove = new Remove(); // new instance of filter
            remove.setOptions(options); // set options
            remove.setInputFormat(data); // inform filter about dataset **AFTER** setting options

            Instances newData = Filter.useFilter(data, remove); // apply filter
            ArffSaver saver = new ArffSaver();
            saver.setInstances(newData);
            System.out.println(newData);
            saver.setFile(new File("./tempteste/resultado.arff"));
            saver.writeBatch();


        } catch (Exception e) {
            e.printStackTrace();
        }
        
    }

}
