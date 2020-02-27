/**
 * @author Jaimin Jaffer
 * @version 1.0
 * @since 1.0
 */
public class Queen extends Piece {
    
    /**
     * Name of the piece.
     */
    private final String PIECE_LABEL;

    /**
     * Creates a Queen
     *
     * @param T The pieces team. (Represented in integer form)
     */
    public Queen(int T) {
        super(T);
        this.PIECE_LABEL = "Queen";
        if (T == 0) {
            setImage("whitequeen.png");
        } else if (T == 1) {
            setImage("blackqueen.png");
        }
    }

    /**
     * Returns a boolean based on if the Queen can move to one space to another
     * <p>
     * Literally just the Rook canMove method and the Bishop canMove method. 
     * Honestly, this should've been setup to just be calling the bishop and 
     * rook canMoves method.
     *
     * @param o Original space
     * @param d Destination space
     * @param b Array of the spaces it's using for the move. (The board
     * essentially)
     * @return whether the bishop can move to Space d or not.
     * 
     * @see Rook#canMove(Space o, Space d, Space[][] b)
     * @see Bishop#canMove(Space o, Space d, Space[][] b)
     */
    public boolean canMove(Space o, Space d, Space[][] b) {

        //REUSED BISHOP CODE
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
        
        //REUSED ROOK CODE
        if (getTeam() == 0) {
            if ((o.getxLoc()) == d.getxLoc()) {

                if ((o.getyLoc()) < d.getyLoc()) {

                    for (int i = o.getyLoc() + 1; i <= d.getyLoc(); i++) {
                        if ((b[i][o.getxLoc()]).getPiece() != null) {
                            if (((b[d.getyLoc()][d.getxLoc()]) == b[i][o.getxLoc()]) && ((d.getPiece()).getTeam() == 1)) {
                                return true;
                            }  
                            return false;
                        }

                    }
                    return true;

                } else if ((o.getyLoc()) > d.getyLoc()) {

                    for (int i = o.getyLoc() - 1; i >= d.getyLoc(); i--) {
                        if ((b[i][o.getxLoc()]).getPiece() != null) {
                            if (((b[d.getyLoc()][d.getxLoc()]) == b[i][o.getxLoc()]) && ((d.getPiece()).getTeam() == 1)) {
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
                            if (((b[d.getyLoc()][d.getxLoc()]) == b[o.getyLoc()][i]) && ((d.getPiece()).getTeam() == 1)) {
                                return true;
                            }
                            return false;
                        }

                    }
                    return true;

                } else if ((o.getxLoc()) > d.getxLoc()) {

                    for (int i = o.getxLoc() - 1; i >= d.getxLoc(); i--) {
                        if ((b[o.getyLoc()][i]).getPiece() != null) {
                            if (((b[d.getyLoc()][d.getxLoc()]) == b[o.getyLoc()][i]) && ((d.getPiece()).getTeam() == 1)) {
                                return true;
                            }
                            return false;
                        }

                    }
                    return true;

                }
                return false;
            }

        } else if (getTeam() == 1) {

            if ((o.getxLoc()) == d.getxLoc()) {

                if ((o.getyLoc()) < d.getyLoc()) {

                    for (int i = o.getyLoc() + 1; i < d.getyLoc(); i++) {
                        if ((b[i][o.getxLoc()]).getPiece() != null) {
                            if (((b[d.getyLoc()][d.getxLoc()]) == b[i][o.getxLoc()]) && ((d.getPiece()).getTeam() == 0)) {
                                return true;
                            }
                            return false;
                        }

                    }
                    return true;

                } else if ((o.getyLoc()) > d.getyLoc()) {

                    for (int i = o.getyLoc() - 1; i >= d.getyLoc(); i--) {
                        if ((b[i][o.getxLoc()]).getPiece() != null) {
                            if (((b[d.getyLoc()][d.getxLoc()]) == b[i][o.getxLoc()]) && ((d.getPiece()).getTeam() == 0)) {
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
                            if (((b[d.getyLoc()][d.getxLoc()]) == b[o.getyLoc()][i]) && ((d.getPiece()).getTeam() == 0)) {
                                return true;
                            }
                            return false;
                        }

                    }
                    return true;

                } else if ((o.getxLoc()) > d.getxLoc()) {

                    for (int i = o.getxLoc() - 1; i >= d.getxLoc(); i--) {
                        if ((b[o.getyLoc()][i]).getPiece() != null) {
                            if (((b[d.getyLoc()][d.getxLoc()]) == b[o.getyLoc()][i]) && ((d.getPiece()).getTeam() == 0)) {
                                return true;
                            }
                            return false;
                        }

                    }
                    return true;

                }
                return false;
            }

        }
        
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
