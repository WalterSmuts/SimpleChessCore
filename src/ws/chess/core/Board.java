package ws.chess.core;

import lombok.AllArgsConstructor;
import ws.chess.core.pieces.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static ws.chess.core.pieces.Piece.Color;
import static ws.chess.core.pieces.Piece.Color.BLACK;
import static ws.chess.core.pieces.Piece.Color.WHITE;

@AllArgsConstructor
public class Board {
    List<Piece> pieces;
    Piece[][] board;
    Color next;

    List<Move> getPossibleMoves(Color color) {
        return pieces.stream()
            .filter(piece -> piece.getColor().equals(color))
            .flatMap(piece -> piece.getPossibleMoves(this).stream())
            .collect(Collectors.toList());
    }

    public Piece getPiece(int x, int y) {
        return board[x][y];
    }

    public List<Move> getAvailableMoves() {
        return getPossibleMoves(next).stream()
            .filter(move -> !applyMove(move).inCheck(next))
            .collect(Collectors.toList());
    }

    public boolean isValidDestination(Move move) {
        Piece destination = board[move.getDestination().getX()][move.getDestination().getY()];
        return (destination == null) || !destination.getColor().equals(move.getOriginal().getColor());
    }

    public boolean hasCleanPath(Move move) {
        // There has to be a simpler way to check this!!!
        if (move.getOriginal() instanceof Knight) return true;
        int ax = move.getOriginal().getX();
        int ay = move.getOriginal().getY();
        int bx = move.getDestination().getX();
        int by = move.getDestination().getY();

        bx = stepTowards(bx, ax);
        by = stepTowards(by, ay);

        while (ax != bx || ay != by) {
            ax = stepTowards(ax, bx);
            ay = stepTowards(ay, by);
            if (!(board[ax][ay] == null)) return false;
        }
        return true;
    }

    static int stepTowards(int a, int b) {
        if (a == b) return a;
        a += (a > b) ? -1 : 1;
        return a;
    }

    public Board applyMove(Move move) {
        Color newNext = next.equals(BLACK) ? WHITE : BLACK;
        List<Piece> newPieces = new ArrayList<>();
        Piece[][] newBoard = new Piece[8][8];
        pieces.forEach(piece -> {
            Piece newPiece = piece.clone();
            newPieces.add(newPiece);
            newBoard[newPiece.getX()][newPiece.getY()] = newPiece;
        });
        newPieces.remove(newBoard[move.getDestination().getX()][move.getDestination().getY()]);
        newPieces.remove(move.getOriginal());
        newPieces.add(move.getDestination());
        newBoard[move.getDestination().getX()][move.getDestination().getY()] = move.getDestination();
        newBoard[move.getOriginal().getX()][move.getOriginal().getY()] = null;
        if (move.getDestination() instanceof Pawn && ((Pawn) move.getDestination()).isAttackPawn() &&
            board[move.getDestination().getX()][move.getDestination().getY()] == null) {
            int dir = move.getDestination().getColor().equals(WHITE) ? 1 : -1;
            Piece target = newBoard[move.getDestination().getX()][move.getDestination().getY() - dir];
            newPieces.remove(target);
            newBoard[target.getX()][target.getY()] = null;
        }
        return new Board(newPieces, newBoard, newNext);
    }

    boolean inCheck(Color color) {
        return getPossibleMoves(color.equals(BLACK) ? WHITE : BLACK)
            .stream()
            .anyMatch(this::consumesKing);
    }

    public boolean consumesKing(Move move) {
        return board[move.getDestination().getX()][move.getDestination().getY()] instanceof King;
    }

    public static boolean onBoard(Move move) {
        return (move.getDestination().getY() < 8) && (move.getDestination().getY() >= 0)
            && (move.getDestination().getX() < 8) && (move.getDestination().getX() >= 0);
    }


    public String toString() {
        StringBuilder sb = new StringBuilder(String.format("            %s's turn...%n", next));
        sb.append("    A   B   C   D   E   F   G   H   \n");
        sb.append("  ---------------------------------  \n");
        for (int i = 8; i >= 1; i--) {
           sb.append(String.format("%d |", i));
           for (int j = 1; j <= 8; j++) {
               sb.append(String.format(" %s |", board[j-1][i-1] == null ? " " : board[j-1][i-1].toString()));
           }
           sb.append(String.format(" %d%n", i));
           sb.append("  ---------------------------------  \n");
        }
        sb.append("    A   B   C   D   E   F   G   H   ");
        return sb.toString();
    }

    public Color getNext() {
        return next;
    }
}
