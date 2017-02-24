package chords;

/**
 * Created by paul-simon on 23.02.17.
 */
public class Chord {
    final int delta = 5;
    private String name;
    private double[] freqArr;

    public Chord(String name, double[] freqArr) {
        this.name = name;
        this.freqArr = freqArr;
    }

    public double match(double[] freqArr, double[] ampl) {
        double minDelta = 0;
        double curAmpl = 0;
        double curFreq = 0;
        double matchVal = 0;

        for (int i = 0; i < freqArr.length; i ++) {
            minDelta = 99999999;
            for (int j = 0; j < this.freqArr.length; j ++) {
                if (Math.abs(freqArr[i] - this.freqArr[j]) < minDelta) {
                    minDelta = Math.abs(freqArr[i] - this.freqArr[j]);
                    curAmpl = ampl[i];
                    curFreq = freqArr[i];
                }
            }

            if (minDelta < delta) {
                matchVal += curAmpl / minDelta;
            }
        }

        return matchVal;
    }

    public String getName() {
        return name;
    }
}
