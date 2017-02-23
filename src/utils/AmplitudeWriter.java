package utils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by paul-simon on 23.02.17.
 */
public class AmplitudeWriter {
    public static void writeToFile(String fname, double[] amplitudeLst)
    {
        try{
            PrintWriter writer = new PrintWriter(fname, "UTF-8");
            for (Double value : amplitudeLst) {
                writer.println(value);
            }
            writer.close();
        } catch (IOException e) {
            System.err.println("Unable to open file for write");
        }
    }
}
