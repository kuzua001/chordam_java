package chords;

import javax.sound.sampled.*;
import java.util.Vector;

/**
 * Created by paul-simon on 23.02.17.
 */
public class Core {



    public void run() {
        AudioFormat format = new AudioFormat(AudioFormat.Encoding.PCM_SIGNED, 44100, 16, 1, 2, 44100, false);
        TargetDataLine line;
        DataLine.Info info = new DataLine.Info(TargetDataLine.class,
                format); // format is an AudioFormat object


        if (!AudioSystem.isLineSupported(info)) {
            // Handle the error ...

        }
// Obtain and open the line.
        try {
            line = (TargetDataLine) AudioSystem.getLine(info);
            line.open(format);


            line.start();

            for (int t = 0; t < 1000; t ++) {
                int chunkSize = 4096;
                byte[] buff = new byte[chunkSize * 2];
                line.read(buff, 0, chunkSize);
                double[] pcm = new double[chunkSize];
                for (int i = 0; i < chunkSize ; i ++) {
                    pcm[i] = 256 * buff[i * 2] + buff[i * 2 + 1];
                    System.out.println(pcm[i]);
                }
            }




        } catch (LineUnavailableException ex) {
            // Handle the error ...
        }
    }
}
