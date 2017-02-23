package utils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.List;
import java.util.ArrayList;

/**
 * Created by paul-simon on 23.02.17.
 */
public class AmplitudeLoader {
    public static double[] loadFromFile(String fname)
    {
        List<Double> ret = new ArrayList<Double>();
        int i = 0;
        try (BufferedReader br = new BufferedReader(new FileReader(fname))) {
            String line;
            try {
                while ((line = br.readLine()) != null) {
                    i ++;
                    ret.add(Double.parseDouble(line));
                }
            } catch (Exception ex) {
                System.err.println("Failed to read file");
            }

        } catch (Exception ex) {
            System.err.println("Failed to open file");
        }

        double[] target = new double[ret.size()];
        for (i = 0; i < target.length; i++) {
            target[i] = ret.get(i);                // java 1.5+ style (outboxing)
        }

        return target;
    }
}
