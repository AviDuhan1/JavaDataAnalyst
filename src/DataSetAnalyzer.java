import java.util.*;
import java.io.*;
import java.nio.*;

public class DataSetAnalyzer {
    //First we will read a CSV (comma separated value) file into Java and turn it into an ArrayList

    public static double[][] newDataset(File file){
        double[][] dataset = new double[getDatasetLength(file)][getDatasetHeight(file)];
        int yCount = 0;
        try {
            Scanner inputStream = new Scanner(file);
            while(inputStream.hasNext()){
                String data = inputStream.next();
                String[] values = data.split(",");
                for(int i = 0; i < values.length; i++){
                    //System.out.println(i);
                    if(isNumeric(values[i])) {
                        dataset[i][yCount] = Double.parseDouble(values[i]);
                    }
                    else{
                        dataset[i][yCount] = -1;
                    }
                }
                if(yCount < 998)
                    yCount++;
            }
            inputStream.close();
        }
        catch(FileNotFoundException e){
            e.printStackTrace();
        }
        return dataset;
    }

    public static int getDatasetLength(File file){
        try{
            Scanner inputStream = new Scanner(file);
            if(inputStream.hasNext()){
                String data = inputStream.next();
                String[] values = data.split(",");
                return values.length;
            }
        }
        catch(FileNotFoundException e){
            e.printStackTrace();
        }
        return -1;
    }

    public static int getDatasetHeight(File file){
        try {
            BufferedReader reader = new BufferedReader(new FileReader(file));
            try {
                int lines = 0;
                while (reader.readLine() != null) {
                    lines++;
                }
                reader.close();
                return lines;
            }
            catch(IOException e){
                e.printStackTrace();
            }
        }
        catch(FileNotFoundException e){
            e.printStackTrace();
        }
        return -1;
    }

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

}
