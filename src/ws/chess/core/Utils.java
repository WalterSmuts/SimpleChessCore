package ws.chess.core;

import ws.chess.core.pieces.*;

import java.util.ArrayList;
import java.util.List;

import static ws.chess.core.pieces.Piece.Color.BLACK;
import static ws.chess.core.pieces.Piece.Color.WHITE;

public class Utils {
    public static Board getStartingBoard() {
        List<Piece> pieces = new ArrayList<>();
        Piece[][] board = new Piece[8][8];

        for (int i = 0; i < 8 ; i++) {
            pieces.add(new Pawn(WHITE, i, 1, false, false, false));
            pieces.add(new Pawn(BLACK, i, 6, false, false, false));
        }

        pieces.add(new Rook(WHITE, 0, 0));
        pieces.add(new Bishop(WHITE, 1, 0));
        pieces.add(new Knight(WHITE, 2, 0));
        pieces.add(new Queen(WHITE, 3, 0));
        pieces.add(new King(WHITE, 4, 0));
        pieces.add(new Knight(WHITE, 5, 0));
        pieces.add(new Bishop(WHITE, 6, 0));
        pieces.add(new Rook(WHITE, 7, 0));

        pieces.add(new Rook(BLACK, 0, 7));
        pieces.add(new Bishop(BLACK, 1, 7));
        pieces.add(new Knight(BLACK, 2, 7));
        pieces.add(new Queen(BLACK, 3, 7));
        pieces.add(new King(BLACK, 4, 7));
        pieces.add(new Knight(BLACK, 5, 7));
        pieces.add(new Bishop(BLACK, 6, 7));
        pieces.add(new Rook(BLACK, 7, 7));

        pieces.forEach(piece -> board[piece.getX()][piece.getY()] = piece);
        return new Board(pieces, board, WHITE);
    }
}
