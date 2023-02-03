package rs.pushvv;

import lombok.RequiredArgsConstructor;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public enum ChordType {
    MAJ("^", Arrays.asList("1", "3", "5", "7")),
    MIN("-", Arrays.asList("1", "b3", "5", "b7")),
    DOM("7", Arrays.asList("1", "3", "5", "b7")),
    HDIM("0", Arrays.asList("1", "b3", "b5", "b7")),
    DIM("o", Arrays.asList("1", "b3", "b5", "bb7"));

    private final String symbol;
    private final List<String> notes;

    public static String getAllSymbolsRegex() {
        return Arrays.stream(values()).map(t -> t.symbol)
                .map(s -> s.matches("^\\p{Alnum}*$") ? s : "\\" + s)
                .collect(Collectors.joining());
    }

    public static Optional<ChordType> bySymbol(String symbol) {
        return Arrays.stream(values()).filter(s -> s.symbol.equals(symbol)).findFirst();
    }

    public String random() {
        ArrayList<String> list = new ArrayList<>(notes);
        Collections.shuffle(list);
        return notesToString(list);
    }

    public String randomInversion() {
        ArrayList<String> list = new ArrayList<>(notes);
        Collections.rotate(list, ThreadLocalRandom.current().nextInt(4));
        return notesToString(list);
    }

    private String notesToString(List<String> notes) {
        return String.join(" ", notes);
    }
}
