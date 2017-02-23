import gen.StringSpectreGenerator;
import math.Convolve;
import math.Fft;
import utils.AmplitudeLoader;
import utils.AmplitudeWriter;
import utils.ArrayAlghoritms;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.List;

public class Main {

    public static void redirectOutput(String fname) throws FileNotFoundException {
        File file = new File(fname);
        FileOutputStream fos = new FileOutputStream(file);
        PrintStream ps = new PrintStream(fos);
        System.setOut(ps);
    }

    public static void fouirier() throws FileNotFoundException {


        double[] list = AmplitudeLoader.loadFromFile("input.txt");
        int chunkSize = 4096;
        int sampleRate = 44100;
        int maxFreq = 2000;

        double[] matchingFreq = new double[] {
                82.0, 110.0, 146.0, 196.0, 247.0, 329.0,
                146.0 * Math.pow(2, 1.0/6.0),
                196.0 * Math.pow(2, 1.0/6.0),
                247.0 * Math.pow(2, 1.0/12.0),
        };
        double[] freqSpectresNormalize = new double[matchingFreq.length];
/*
        double[][] freqSpectres = new double[matchingFreq.length][chunkSize];
        for (int i = 0; i < matchingFreq.length; i ++) {
            freqSpectres[i] = StringSpectreGenerator.getSpectre(matchingFreq[i], chunkSize, sampleRate, 3);
            freqSpectresNormalize[i] = 1.0 / Convolve.sum(freqSpectres[i]);
        }
*/

        double t = 0;
        double w = 0;
        double[] real    = new double[chunkSize];
        double[] imag    = new double[chunkSize];
        double amp = 0;
        double[] spectre = new double[chunkSize];
        double[] convRes = new double[matchingFreq.length];
        redirectOutput("output.txt");

        for (int i = 0; i < list.length - chunkSize; i += chunkSize) {
            t = i / (double) sampleRate;

            for (int j = 0; j < chunkSize; j ++) {
                real[j] = list[i + j];
                imag[j] = 0;
            }

            Fft.transform(real, imag);
            for (int j = 0; j < chunkSize; j ++) {
                w = j / (double) chunkSize * sampleRate;
                amp = imag[j] * imag[j] + real[j] * real[j];
                //amp = Math.pow(amp, 2);
                System.out.format("%f\t%f\t%f\n", t, amp, w);
            }

          /*  for (int k = 0; k < matchingFreq.length; k ++)
            {
                convRes[k] = Convolve.convolve(spectre, freqSpectres[k], 0, chunkSize * maxFreq / sampleRate) *
                        freqSpectresNormalize[k];
            }*/
            //int[] topFreqIndexes = ArrayAlghoritms.indexesOfTopElements(convRes, 3);

            /*for (int k = 0; k < topFreqIndexes.length; k ++) {
                System.out.print(matchingFreq[topFreqIndexes[k]] + "\t");
            }*/
            System.out.println();
        }
    }

    public static void spectre() throws FileNotFoundException {
        redirectOutput("output.txt");
        double[] spectre = StringSpectreGenerator.getSpectre(329.0, 4096, 44100, 1);
        for (int i = 0; i < spectre.length; i ++) {
            System.out.format("%d\t%f\n", i, spectre[i]);
        }
    }

    public static void main(String[] args) throws FileNotFoundException {
        //spectre();
        fouirier();
    }
}
