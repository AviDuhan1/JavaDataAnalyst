import java.awt.*;
import java.util.*;
import java.io.*;
import java.nio.*;

public class DataTest extends DataSetAnalyzer{
    public static void main(String[] args){
        //System.out.println(isNumeric("112345"));
        //System.out.println(isNumeric("Product1"));
        File file = new File("TestSet.csv");
        double[][] dataset = newDataset(file);
        System.out.println(dataset.length + " " + dataset[0].length);
        for(int i = 0; i < dataset.length; i++){
            System.out.println(Arrays.toString(dataset[i]));
        }
        double [][] newDataset = trim(dataset);
        System.out.println(newDataset.length + " " + newDataset[0].length);
    }
}
