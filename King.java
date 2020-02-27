
/**
 * @author Jaimin Jaffer
 * @version 1.0
 * @since 1.0
 */
public class King extends Piece {

    /**
     * Name of the piece.
     */
    private final String PIECE_LABEL;

    /**
     * Creates a king
     *
     * @param T The King's team. (Represented in integer form)
     */
    public King(int T) {
        super(T);
        this.PIECE_LABEL = "King";
        if (T == 0) {
            setImage("whiteking.png");
        } else if (T == 1) {
            setImage("blackking.png");
        }
    }

    /**
     * Returns a boolean based on if the king can move to one space to another
     * <p>
     * Hard coded to check what if the desired space is one the 8 legal king
     * moves. Then checks if there's a piece in the way before returning the
     * boolean.
     *
     * @param o Original space
     * @param d Destination space
     * @param b Array of the spaces it's using for the move. (The board
     * essentially)
     * @return whether the king can move to Space d or not.
     */
    public boolean canMove(Space o, Space d, Space[][] b) {

        if (((o.getxLoc() + 1 == d.getxLoc()) && o.getyLoc() == d.getyLoc())
                || ((o.getxLoc() - 1 == d.getxLoc()) && o.getyLoc() == d.getyLoc())
                || ((o.getyLoc() + 1 == d.getyLoc()) && o.getxLoc() == d.getxLoc())
                || ((o.getyLoc() - 1 == d.getyLoc()) && o.getxLoc() == d.getxLoc())
                || ((o.getxLoc() + 1 == d.getxLoc()) && (o.getyLoc() - 1 == d.getyLoc()))
                || ((o.getxLoc() - 1 == d.getxLoc()) && (o.getyLoc() + 1 == d.getyLoc()))
                || ((o.getxLoc() + 1 == d.getxLoc()) && (o.getyLoc() + 1 == d.getyLoc()))
                || ((o.getxLoc() - 1 == d.getxLoc()) && (o.getyLoc() - 1 == d.getyLoc()))) {

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
     * Returns a string representing the King's name
     * 
     * @return Name of the piece
     */
    public String getPieceLabel() {
        return PIECE_LABEL;
    }
}
