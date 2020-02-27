/**
 * @author Jaimin Jaffer
 * @version 1.0
 * @since 1.0
 */
public class Knight extends Piece {
    
    /**
     * Name of the piece.
     */
    private final String PIECE_LABEL;

    /**
     * Creates a knight
     *
     * @param T The knight's team. (Represented in integer form)
     */
    public Knight(int T) {
        super(T);
        this.PIECE_LABEL = "Knight";

        if (T == 0) {
            setImage("whiteknight.png");
        } else if (T == 1) {
            setImage("blackknight.png");
        }
    }

    /**
     * Returns a boolean based on if the knight can move to one space to another
     * <p>
     * Hard coded to check what if the desired space is one the 8 legal knight
     * moves. Doesn't need to check for a piece in the way
     *
     * @param o Original space
     * @param d Destination space
     * @param b Array of the spaces it's using for the move. (The board
     * essentially)
     * @return whether the knight can move to Space d or not.
     */
    @Override
    public boolean canMove(Space o, Space d, Space[][] b) {

        if (((o.getxLoc() - 1) == d.getxLoc()) && ((o.getyLoc() - 2) == d.getyLoc())) {

            if (d.getPiece() != null) {
                if ((d.getPiece()).getTeam() == (o.getPiece()).getTeam()) {
                    return false;
                }
            }
            return true;

        } else if (((o.getxLoc() - 1) == d.getxLoc()) && ((o.getyLoc() + 2) == d.getyLoc())) {

            if (d.getPiece() != null) {
                if ((d.getPiece()).getTeam() == (o.getPiece()).getTeam()) {
                    return false;
                }
            }
            return true;

        } else if (((o.getxLoc() + 1) == d.getxLoc()) && ((o.getyLoc() - 2) == d.getyLoc())) {

            if (d.getPiece() != null) {
                if ((d.getPiece()).getTeam() == (o.getPiece()).getTeam()) {
                    return false;
                }
            }
            return true;

        } else if (((o.getxLoc() + 1) == d.getxLoc()) && ((o.getyLoc() + 2) == d.getyLoc())) {

            if (d.getPiece() != null) {
                if ((d.getPiece()).getTeam() == (o.getPiece()).getTeam()) {
                    return false;
                }
            }
            return true;

        } else if (((o.getxLoc() - 2) == d.getxLoc()) && ((o.getyLoc() - 1) == d.getyLoc())) {

            if (d.getPiece() != null) {
                if ((d.getPiece()).getTeam() == (o.getPiece()).getTeam()) {
                    return false;
                }
            }
            return true;

        } else if (((o.getxLoc() - 2) == d.getxLoc()) && ((o.getyLoc() + 1) == d.getyLoc())) {

            if (d.getPiece() != null) {
                if ((d.getPiece()).getTeam() == (o.getPiece()).getTeam()) {
                    return false;
                }
            }
            return true;

        } else if (((o.getxLoc() + 2) == d.getxLoc()) && ((o.getyLoc() - 1) == d.getyLoc())) {

            if (d.getPiece() != null) {
                if ((d.getPiece()).getTeam() == (o.getPiece()).getTeam()) {
                    return false;
                } 
            }
            return true;

        } else if (((o.getxLoc() + 2) == d.getxLoc()) && ((o.getyLoc() + 1) == d.getyLoc())) {

            if (d.getPiece() != null) {
                if ((d.getPiece()).getTeam() == (o.getPiece()).getTeam()) {
                    return false;
                }
            }
            return true;

        }
        return false;
    }
    
    /**
     * Returns a string representing the knights's name
     *
     * @return Name of the piece
     */
    @Override
    public String getPieceLabel() {
        return PIECE_LABEL;
    }
}
