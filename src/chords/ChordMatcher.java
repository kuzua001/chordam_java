package chords;

import math.Fft;
import utils.ArrayAlghoritms;
import utils.Tuple;

import java.util.ArrayList;
import java.util.List;

import static math.Fft.chunkSize;
import static utils.ArrayAlghoritms.severalTop;

/**
 * Created by paul-simon on 23.02.17.
 */
public class ChordMatcher {
    List<Chord> chords;
    int chunkSize = 4096;
    int sampleRate = 44100;
    int THRESHOLD = 100000;
    public ChordMatcher()
    {
        this.chords = new ArrayList<Chord>();
        chords.add(new Chord("Am", new double[] {82,110,163,220,260,329}));
        chords.add(new Chord("C", new double[] {82,131,163,196,260,329}));
        chords.add(new Chord("Dm", new double[] {82,110,146,220,292,348}));
        chords.add(new Chord("G", new double[] {97,123,146,196,247,391}));
    }

    public String match(double[] pcm)
    {
        double t = 0;
        double w;
        double[] real    = new double[chunkSize];
        double[] imag    = new double[chunkSize];
        double[] spectrum = new double[chunkSize];
        double max = Double.MIN_VALUE;
        int numStrings = 6;

        for (int i = 0; i < pcm.length - chunkSize; i += chunkSize) {
            t = i / (double) sampleRate;

            for (int j = 0; j < chunkSize; j++) {
                real[j] = pcm[i + j];
                imag[j] = 0;
            }

            Fft.transform(real, imag);
            for (int j = 0; j < chunkSize; j++) {
                spectrum[j] += imag[j] * imag[j] + real[j] * real[j];
            }
        }
        for (int i = 0; i < chunkSize; i++) {
            if (spectrum[i] > max) {
                max = spectrum[i];
            }
        }
        if (max<THRESHOLD) {return "none";}
        Tuple<double[], double[]> result = ArrayAlghoritms.severalTop(spectrum, numStrings);

        double maxMatch = 0;
        Chord matched = new Chord("none", new double[] {});

        for (Chord c : this.chords) {

            double matchValue = c.match(result.x, result.y);
            if (matchValue > maxMatch) {
                maxMatch = matchValue;
                matched = c;
            }
        }
        return matched.getName();
    }
}
