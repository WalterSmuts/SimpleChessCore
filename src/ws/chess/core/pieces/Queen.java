package ws.chess.core.pieces;

import ws.chess.core.Move;

import java.util.List;

public class Queen extends Piece {

    public Queen(Color color, int x, int y) {
        super(color, x, y);
    }

    List<Move> getUniqueMovePattern() {
        List<Move> moves = getDiagonalPattern();
        moves.addAll(getLinearPattern());
        return moves;
    }

    String getSymbol() {
        return "Q";
    }

    public Piece clone() {
        return new Queen(this.getColor(), this.getX(), this.getY());
    }
}
