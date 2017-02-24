package utils;

import java.util.Arrays;

public class ArrayAlghoritms {

    public static int[] indexesOfTopElements(double[] orig, int nummax) {
        double[] copy = Arrays.copyOf(orig,orig.length);
        Arrays.sort(copy);
        double[] honey = Arrays.copyOfRange(copy,copy.length - nummax, copy.length);
        int[] result = new int[nummax];
        int resultPos = 0;
        for(int i = 0; i < orig.length; i++) {
            double onTrial = orig[i];
            double index = Arrays.binarySearch(honey,onTrial);
            if(index < 0) continue;
            result[resultPos++] = i;
        }

        return result;
    }
    public static Tuple<double[], double[]> severalTop(double[] orig, int numtop) {
        int chunkSize = 4098;
        int sampleRate = 44100;

        double[] copy = Arrays.copyOf(orig,orig.length);
        double max = Double.MIN_VALUE;

        int maxIndex = 0;
        double[] freqArr = new double[numtop];
        double[] values = new double[numtop];
        for(int j = 0; j<numtop;j++) {
            for (int i = 0; i < copy.length; i++) {
                if (copy[i] > max) {
                    max = copy[i];
                    maxIndex = i;
                }
            }
            values[j] = max;
            freqArr[j] = maxIndex * sampleRate / chunkSize;
            for(int k = j-2;k<j+2;k++){
                copy[k] = 0;
            }

        }
        return new Tuple<double[], double[]>(freqArr, values);
    }


}
