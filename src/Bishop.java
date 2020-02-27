
/**
 * @author Jaimin Jaffer
 * @version 1.0
 * @since 1.0
 */
public class Bishop extends Piece {

    /**
     * Name of the piece.
     */
    private final String PIECE_LABEL;

    /**
     * Creates a bishop
     *
     * @param T The Bishop's team. (Represented in integer form)
     */
    public Bishop(int T) {
        super(T);
        this.PIECE_LABEL = "Bishop";

        if (T == 0) {
            setImage("whitebishop.png");
        } else if (T == 1) {
            setImage("blackbishop.png");
        }
    }

    /**
     * Returns a boolean based on if the bishop can move to one space to another
     * <p>
     * Goes through a loop to make sure it's diagonal. (The loop it goes through
     * depends on the direction of the move.) Then checks if there's a piece in
     * the way before returning the boolean.
     *
     * @param o Original space
     * @param d Destination space
     * @param b Array of the spaces it's using for the move. (The board
     * essentially)
     * @return whether the bishop can move to Space d or not.
     */
    @Override
    public boolean canMove(Space o, Space d, Space[][] b) {

        if (Math.abs(o.getxLoc() - d.getxLoc()) == Math.abs(o.getyLoc() - d.getyLoc())) {

            if (((o.getxLoc()) < d.getxLoc()) && ((o.getyLoc()) < d.getyLoc())) {
                int y = o.getyLoc() + 1;
                int x = o.getxLoc() + 1;
                while (y <= d.getyLoc()) {
                    if ((b[y][x]).getPiece() != null) {
                        if (((b[d.getyLoc()][d.getxLoc()]) == b[y][x]) && ((d.getPiece()).getTeam() != (o.getPiece()).getTeam())) {
                            return true;
                        }
                        return false;
                    }
                    y++;
                    x++;
                }

            } else if (((o.getxLoc()) > d.getxLoc()) && ((o.getyLoc()) < d.getyLoc())) {
                int y = o.getyLoc() + 1;
                int x = o.getxLoc() - 1;
                while (y <= d.getyLoc()) {
                    if ((b[y][x]).getPiece() != null) {
                        if (((b[d.getyLoc()][d.getxLoc()]) == b[y][x]) && ((d.getPiece()).getTeam() != (o.getPiece()).getTeam())) {
                            return true;
                        }
                        return false;
                    }
                    y++;
                    x--;
                }

            } else if (((o.getxLoc()) > d.getxLoc()) && ((o.getyLoc()) > d.getyLoc())) {
                int y = o.getyLoc() - 1;
                int x = o.getxLoc() - 1;
                while (y >= d.getyLoc()) {
                    if ((b[y][x]).getPiece() != null) {
                        if (((b[d.getyLoc()][d.getxLoc()]) == b[y][x]) && ((d.getPiece()).getTeam() != (o.getPiece()).getTeam())) {
                            return true;
                        }
                        return false;
                    }
                    y--;
                    x--;
                }

            } else if (((o.getxLoc()) < d.getxLoc()) && ((o.getyLoc()) > d.getyLoc())) {
                int y = o.getyLoc() - 1;
                int x = o.getxLoc() + 1;
                while (y >= d.getyLoc()) {
                    if ((b[y][x]).getPiece() != null) {
                        if (((b[d.getyLoc()][d.getxLoc()]) == b[y][x]) && ((d.getPiece()).getTeam() != (o.getPiece()).getTeam())) {
                            return true;
                        }
                        return false;
                    }
                    y--;
                    x++;
                }

            }
            return true;
        }
        return false;
    }

    /**
     * Returns a string representing the Bishop's name
     * 
     * @return Name of the piece
     */
    @Override
    public String getPieceLabel() {
        return PIECE_LABEL;
    }

}
