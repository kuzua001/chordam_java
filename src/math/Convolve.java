package math;

/**
 * Created by paul-simon on 23.02.17.
 */
public class Convolve {
    public static double convolve(double[] f1, double[] f2, int from, int to)
    {
        double res = 0;
        for (int i = from; i < f1.length && i < to; i ++) {
            res += f1[i] * f2[i];
        }

        return res;
    }

    public static double sum(double[] f)
    {
        double res = 0;
        for (int i = 0; i < f.length; i ++) {
            res += f[i];
        }

        return res;
    }
}
