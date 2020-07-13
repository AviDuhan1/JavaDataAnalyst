import java.util.*;
import java.io.*;
import java.nio.*;

public class DataSetAnalyzer {
    //First we will read a CSV (comma separated value) file into Java and turn it into an ArrayList

    public static boolean isNumeric(String s){
        if(s == null){
            return false;
        }
        try{
            Double d = Double.parseDouble(s);
        }
        catch(NumberFormatException e){
            return false;
        }
        return true;
    }

    public static double[][] getDatasetLength(File file){
        double[][] failSafe = new double[-1][-1];
        try{
            Scanner inputStream = new Scanner(file);
            int height = 0;
            int length = 0;
            String data = inputStream.next();
            String[] values = data.split(",");
            length = values.length;
            for(int i = 0; i < values.length; i++){
                if(!isNumeric(values[i])){
                    length--;
                }
            }
            while(inputStream.hasNext()){
                height++;
            }
            double[][] dataset = new double[length][height];
            return dataset;
        }
        catch(FileNotFoundException e){
            e.printStackTrace();
        }
        return failSafe;
    }
}
