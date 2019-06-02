package ws.chess.core;

import lombok.Builder;
import lombok.Data;
import ws.chess.core.pieces.Piece;

@Builder
@Data
public class Move {
    private Piece original;
    private Piece destination;

    public String toString() {
        return String.format("%s: %s%d moves to %s%d",
            original.getClass().getSimpleName(),
            getLetter(original.getX()),
            original.getY() + 1,
            getLetter(destination.getX()),
            destination.getY() + 1);
    }

    char getLetter(int number) {
        return (char)(number + 65);
    }
}
