package chords;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by paul-simon on 23.02.17.
 */
public class ChordMatcher {
    List<Chord> chords;

    public ChordMatcher()
    {
        this.chords = new ArrayList<Chord>();
        chords.add(new Chord("Am", new double[] {0}));
        chords.add(new Chord("C", new double[] {0}));
        chords.add(new Chord("Dm", new double[] {0}));
        chords.add(new Chord("G", new double[] {0}));
        chords.add(new Chord("Em", new double[] {0}));
        chords.add(new Chord("H7", new double[] {0}));
    }

    public String match(double[] pcm)
    {

        return "none";
    }
}
