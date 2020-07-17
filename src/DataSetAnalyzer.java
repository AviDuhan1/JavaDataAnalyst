import java.util.*;
import java.io.*;
import java.nio.*;

public class DataSetAnalyzer {

    //newDataset will take the length and height of a file and translate it into a two dimensional array for statistics to be performed on
    //NOTE: AS OF RIGHT NOW (7/17/20) THIS FUNCTION WILL NOT WORK WITH STRINGS. ONLY SOLELY NUMERICAL DATA
    //NOTE 2: AS OF RIGHT NOW (7/17/20) THIS FUNCTION WILL ONLY WORK WITH CSV FILES
    public static double[][] newDataset(File file){
        //Create a new 2d array with length and height of the dataset from the file
        double[][] dataset = new double[getDatasetLength(file)][getDatasetHeight(file)];
        //Initialize a counting variable to keep track of which row we're on
        int yCount = 0;
        try {
            //Begin reading inputs from the file
            Scanner inputStream = new Scanner(file);
            //While there are more lines to read into the 2d array this will read those lines
            while(inputStream.hasNext()){
                String data = inputStream.next();
                //Because this takes in a CSV file we can separate the lines into arrays of strings via the split function
                String[] values = data.split(",");
                //For each of those arrays put the resulting strings into the dataset 2d array
                for(int i = 0; i < values.length; i++){
                    //System.out.println(i);
                    //Test each of the "Strings" to see if they are numeric
                    if(isNumeric(values[i])) {
                        //If they are, put them into the arrays as doubles
                        dataset[i][yCount] = Double.parseDouble(values[i]);
                    }
                    else{
                        //If they're not, put them into the arrays as -9999999 so they can be trimmed later
                        dataset[i][yCount] = -9999999;
                    }
                }
                    yCount++;
            }
            inputStream.close();
        }
        catch(FileNotFoundException e){
            e.printStackTrace();
        }
        return dataset;
    }

    //Gets the length of a file's dataset by splitting a line into an array then getting the length of that array
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

    //Gets the height of a file's dataset by counting the number of lines contained within it
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

    //Attempts to parse the given string as a double then returns whether or not it was successful
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
