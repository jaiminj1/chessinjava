
/**
 * @author Jaimin Jaffer
 * @version 1.0
 * @since 1.0
 */
public class Rook extends Piece {

    /**
     * Name of the piece.
     */
    private final String PIECE_LABEL;

    /**
     * Creates a Rook
     *
     * @param T The pieces team. (Represented in integer form).
     */
    public Rook(int T) {
        super(T);
        this.PIECE_LABEL = "Rook";

        if (T == 0) {
            setImage("whiterook.png");
        } else if (T == 1) {
            setImage("blackrook.png");
        }
    }

    /**
     * Returns a boolean based on if the rook can move to one space to another
     * <p>
     * Goes through a loop to make sure it's straight. (The loop it goes through
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
        //if (getTeam() == 0) {
        if ((o.getxLoc()) == d.getxLoc()) {

            if ((o.getyLoc()) < d.getyLoc()) {

                for (int i = o.getyLoc() + 1; i <= d.getyLoc(); i++) {
                    if ((b[i][o.getxLoc()]).getPiece() != null) {
                        if (((b[d.getyLoc()][d.getxLoc()]) == b[i][o.getxLoc()]) && ((d.getPiece()).getTeam() != o.getPiece().getTeam())) {
                            return true;
                        }
                        return false;
                    }

                }
                return true;

            } else if ((o.getyLoc()) > d.getyLoc()) {

                for (int i = o.getyLoc() - 1; i >= d.getyLoc(); i--) {
                    if ((b[i][o.getxLoc()]).getPiece() != null) {
                        if (((b[d.getyLoc()][d.getxLoc()]) == b[i][o.getxLoc()]) && ((d.getPiece()).getTeam() != o.getPiece().getTeam())) {
                            return true;
                        }
                        return false;
                    }

                }
                return true;

            }

        } else if ((o.getyLoc()) == d.getyLoc()) {

            if ((o.getxLoc()) < d.getxLoc()) {

                for (int i = o.getxLoc() + 1; i <= d.getxLoc(); i++) {
                    if ((b[o.getyLoc()][i]).getPiece() != null) {
                        if (((b[d.getyLoc()][d.getxLoc()]) == b[o.getyLoc()][i]) && ((d.getPiece()).getTeam() != o.getPiece().getTeam())) {
                            return true;
                        }
                        return false;
                    }

                }
                return true;

            } else if ((o.getxLoc()) > d.getxLoc()) {

                for (int i = o.getxLoc() - 1; i >= d.getxLoc(); i--) {
                    if ((b[o.getyLoc()][i]).getPiece() != null) {
                        if (((b[d.getyLoc()][d.getxLoc()]) == b[o.getyLoc()][i]) && ((d.getPiece()).getTeam() != o.getPiece().getTeam())) {
                            return true;
                        }
                        return false;
                    }

                }
                return true;

            }
        }

//        } else if (getTeam() == 1) {
//
//            if ((o.getxLoc()) == d.getxLoc()) {
//
//                if ((o.getyLoc()) < d.getyLoc()) {
//
//                    for (int i = o.getyLoc() + 1; i <= d.getyLoc(); i++) {
//                        if ((b[i][o.getxLoc()]).getPiece() != null) {
//                            if (((b[d.getyLoc()][d.getxLoc()]) == b[i][o.getxLoc()]) && ((d.getPiece()).getTeam() == 0)) {
//                                return true;
//                            }
//                            return false;
//                        }
//
//                    }
//                    return true;
//
//                } else if ((o.getyLoc()) > d.getyLoc()) {
//
//                    for (int i = o.getyLoc() - 1; i >= d.getyLoc(); i--) {
//                        if ((b[i][o.getxLoc()]).getPiece() != null) {
//                            if (((b[d.getyLoc()][d.getxLoc()]) == b[i][o.getxLoc()]) && ((d.getPiece()).getTeam() == 0)) {
//                                return true;
//                            }
//                            return false;
//                        }
//
//                    }
//                    return true;
//
//                }
//
//            } else if ((o.getyLoc()) == d.getyLoc()) {
//
//                if ((o.getxLoc()) < d.getxLoc()) {
//
//                    for (int i = o.getxLoc() + 1; i <= d.getxLoc(); i++) {
//                        if ((b[o.getyLoc()][i]).getPiece() != null) {
//                            if (((b[d.getyLoc()][d.getxLoc()]) == b[o.getyLoc()][i]) && ((d.getPiece()).getTeam() == 0)) {
//                                return true;
//                            }
//                            return false;
//                        }
//
//                    }
//                    return true;
//
//                } else if ((o.getxLoc()) > d.getxLoc()) {
//
//                    for (int i = o.getxLoc() - 1; i >= d.getxLoc(); i--) {
//                        if ((b[o.getyLoc()][i]).getPiece() != null) {
//                            if (((b[d.getyLoc()][d.getxLoc()]) == b[o.getyLoc()][i]) && ((d.getPiece()).getTeam() == 0)) {
//                                return true;
//                            }
//                            return false;
//                        }
//
//                    }
//                    return true;
//
//                }
//                return false;
//            }
//
//        }
        return false;
    }

    /**
     * Returns a string representing the Queen's name
     *
     * @return Name of the piece
     */
    @Override
    public String getPieceLabel() {
        return PIECE_LABEL;
    }
}
