
import javax.swing.ImageIcon;

/**
 * @author Jaimin Jaffer
 * @version 1.0
 * @since 1.0
 */
public abstract class Piece {

    /**
     * The pieces team.
     */
    private final int TEAM;

    /**
     * Stores the icon of the piece.
     */
    private ImageIcon IMAGE;

    /**
     * Name of the piece.
     */
    private final String PIECE_LABEL;

    /**
     * Creates an usable "Generic Piece"
     *
     * @param T The piece's team. (Represented in integer form)
     */
    public Piece(int T) {
        this.PIECE_LABEL = "Generic Piece";
        TEAM = T;
    }

    /**
     * Returns the piece's team
     *
     * @return team in integer form.
     */
    public int getTeam() {
        return TEAM;
    }

    /**
     * Returns the piece's image
     *
     * @return Image in ImageIcon form.
     */
    public ImageIcon getImage() {
        return IMAGE;
    }

    /**
     * Returns a boolean based on if the piece can move to one space to another
     * Has no use in the abstract class
     *
     * @param o Original space
     * @param d Destination space
     * @param b Array of the spaces it's using for the move. (The board
     * essentially)
     * @return whether the Piece can move to Space d or not.
     */
    public boolean canMove(Space o, Space d, Space[][] b) {
        return true;
    }

    /**
     * Sets an ImageIcon from an image directory.
     *
     * @param i String of image directory.
     */
    public void setImage(String i) {
        IMAGE = new ImageIcon(getClass().getResource(i));
    }

    /**
     * Returns a string representing the piece's name
     *
     * @return Name of the piece
     */
    public String getPieceLabel() {
        return PIECE_LABEL;
    }
}
