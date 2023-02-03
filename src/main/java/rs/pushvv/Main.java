package rs.pushvv;

import java.util.Arrays;

import static java.util.stream.Collectors.joining;

public class Main {
    public static void main(String[] args) {
        String changes = args.length == 0 ? "C- F7 Bb^ Eb0 Abo" : args[0];
        System.out.println(changes);
        String symbolsRegex = ChordType.getAllSymbolsRegex();
        String[] split = changes.split("[^A-Gb" + symbolsRegex + "]+");
        String random = Arrays.stream(split)
                .map(Main::toChord)
                .map(c -> c.toNotes(ChordType::random))
                .collect(joining(" "));
        String randomInversion = Arrays.stream(split)
                .map(Main::toChord)
                .map(c -> c.toNotes(ChordType::randomInversion))
                .collect(joining(" "));

        System.out.println(random);
        System.out.println(randomInversion);
    }

    private static Chord toChord(String s) {
        String root = s.substring(0, s.length() - 1);
        ChordType type = ChordType.bySymbol(s.substring(s.length() - 1))
                .orElseThrow(() -> new IllegalArgumentException("Wrong symbol"));
        return new Chord(root, type);
    }
}