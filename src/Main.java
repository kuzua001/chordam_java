import chords.Core;
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

    public static void main(String[] args) throws FileNotFoundException {
        Core c = new Core();
        c.run();
    }
}
