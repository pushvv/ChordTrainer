package rs.pushvv;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.function.Function;

@Data
@AllArgsConstructor
public class Chord {
    private String root;
    private ChordType type;

    public String toNotes(Function<ChordType, String> mapper) {
        return root + "(" + mapper.apply(type) + ")";
    }
}
