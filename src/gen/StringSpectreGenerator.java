package gen;

/**
 * Created by paul-simon on 23.02.17.
 */
public class StringSpectreGenerator {
    public static double[] getSpectre(double frequency, int chunkSize, int sampleRate, int blur)
    {
        double[] ret = new double[chunkSize];

        double n = (chunkSize * frequency / sampleRate);

        System.out.print(n);

        int level = 0;
        for (double i = n; i < chunkSize && level < 3; i += n) {
            level ++;
            int i1 = (int) i;
            int i2 = i1 + 1;
            double alpha = i - i1;
            ret[i1] = alpha;
            ret[i2] = 1 - alpha;
        }


        double convolve[] = new double[] {1, 2, 1};
        double normalize = 0.25;
        double res = 0;

        double retTmp[] = new double[chunkSize];

        for (int j = 0; j < blur; j ++) {
            for (int i = 0; i < chunkSize; i ++) {
                if (i >= convolve.length) {
                    res = 0;
                    for (int k = 0; k < convolve.length; k ++) {
                        res += ret[i - k] * convolve[k];
                    }
                    retTmp[i] = res * normalize;
                } else {
                    retTmp[i] = ret[i];
                }
            }

            System.arraycopy(retTmp, 0, ret, 0, chunkSize);
        }

        return ret;
    }
}
