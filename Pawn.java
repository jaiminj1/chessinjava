
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JOptionPane;

/**
 * @author Jaimin Jaffer
 * @version 1.0
 * @since 1.0
 */
public class Pawn extends Piece {

    /**
     * Name of the piece.
     */
    private final String PIECE_LABEL;

    /**
     * Creates a pawn
     *
     * @param T The pieces team. (Represented in integer form)
     */
    public Pawn(int T) {
        super(T);
        this.PIECE_LABEL = "Pawn";

        if (T == 0) {
            setImage("whitepawn.png");
        } else if (T == 1) {
            setImage("blackpawn.png");
        }
    }

    /**
     * Returns a boolean based on if the pawn can move to one space to another
     * <p>
     * Checks if the pawn has moved yet, if not it can do a double. Checks if
     * there's a piece in between in that case. Checks if there's an enemy piece
     * diagonally to see if it can kill it
     *
     * @param o Original space
     * @param d Destination space
     * @param b Array of the spaces it's using for the move. (The board
     * essentially)
     * @return whether the pawn can move to Space d or not.
     */
    @Override
    public boolean canMove(Space o, Space d, Space[][] b) {

        if (getTeam() == 0) {
            if (o.getyLoc() == 6) {
                if (((o.getyLoc() - d.getyLoc() == 2) || (o.getyLoc() - d.getyLoc() == 1)) && (o.getxLoc()) == d.getxLoc()) {

                    for (int i = o.getyLoc() - 1; i >= d.getyLoc(); i--) {
                        if ((b[i][o.getxLoc()]).getPiece() != null) {
                            return false;
                        }

                    }
                    return true;
                } else if ((o.getyLoc() - d.getyLoc() == 1) && (((o.getxLoc() - d.getxLoc() == 1)) || (o.getxLoc() - d.getxLoc() == -1))) {

                    if (((b[d.getyLoc()][d.getxLoc()]).getPiece() != null) && ((d.getPiece()).getTeam() == 1)) {
                        return true;
                    }
                    return false;

                }
            } else {
                if (((o.getyLoc() - d.getyLoc() == 1)) && (o.getxLoc()) == d.getxLoc()) {

                    for (int i = o.getyLoc() - 1; i >= d.getyLoc(); i--) {
                        if ((b[i][o.getxLoc()]).getPiece() != null) {
                            return false;
                        }

                    }
                    return true;
                } else if ((o.getyLoc() - d.getyLoc() == 1) && (((o.getxLoc() - d.getxLoc() == 1)) || (o.getxLoc() - d.getxLoc() == -1))) {

                    if (((b[d.getyLoc()][d.getxLoc()]).getPiece() != null) && ((d.getPiece()).getTeam() == 1)) {
                        return true;
                    }
                    return false;

                }
            }

        } else if (getTeam() == 1) {
            if (o.getyLoc() == 1) {
                if (((o.getyLoc() - d.getyLoc() == -2) || (o.getyLoc() - d.getyLoc() == -1)) && (o.getxLoc()) == d.getxLoc()) {

                    for (int i = o.getyLoc() + 1; i <= d.getyLoc(); i++) {
                        if ((b[i][o.getxLoc()]).getPiece() != null) {
                            return false;
                        }

                    }
                    return true;
                } else if ((o.getyLoc() - d.getyLoc() == -1) && (((o.getxLoc() - d.getxLoc() == 1)) || (o.getxLoc() - d.getxLoc() == -1))) {

                    if (((b[d.getyLoc()][d.getxLoc()]).getPiece() != null) && ((d.getPiece()).getTeam() == 0)) {
                        return true;
                    }
                    return false;

                }

            } else {
                if (((o.getyLoc() - d.getyLoc() == -1)) && (o.getxLoc()) == d.getxLoc()) {

                    for (int i = o.getyLoc() + 1; i <= d.getyLoc(); i++) {
                        if ((b[i][o.getxLoc()]).getPiece() != null) {
                            return false;
                        }

                    }
                    return true;
                } else if ((o.getyLoc() - d.getyLoc() == -1) && (((o.getxLoc() - d.getxLoc() == 1)) || (o.getxLoc() - d.getxLoc() == -1))) {

                    if (((b[d.getyLoc()][d.getxLoc()]).getPiece() != null) && ((d.getPiece()).getTeam() == 0)) {
                        return true;
                    }
                    return false;

                }

            }
        }

        return false;
    }

    /**
     * Checks if the pawn is in a position to be upgraded.
     * 
     * @param piece 
     * @param position position of pawn
     * @return boolean if the pawn can upgrade or not
     */
    public static boolean checkUpgrade(Piece piece, Space position) {
        if ((piece.getTeam() == 0) && (position.getyLoc() == 0)) {
            return true;
        } else if ((piece.getTeam() == 1) && (position.getyLoc() == 7)) {
            return true;
        }
        return false;
    }

    /**
     * Returns a string representing the Pawn's name
     * 
     * @return Name of the piece
     */
    @Override
    public String getPieceLabel() {
        return PIECE_LABEL;
    }
}
