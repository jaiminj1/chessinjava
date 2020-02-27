import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;

/**
 * @author Jaimin Jaffer
 * @version 1.0
 * @since 1.0
 */
public class Space extends JButton implements ActionListener {

    /**
     * X and Y location of the Space.
     */
    private int xLoc, yLoc;
    
    /**
     * Boolean that stores whether the Space has a piece.
     */
    private boolean hasPiece = false;
    
    /**
     * Piece it holds.
     */
    private Piece piece = null;

    /**
     * Creates a Space object. (it's just a JButton)
     *
     * @param x x location
     * @param y y location
     */
    public Space(int x, int y) {
        xLoc = x;
        yLoc = y;
    }
    
    /**
     * Adds a piece to the Space
     *
     * @param p Piece to be added
     */
    public void addPiece(Piece p) {
        piece = p;
        hasPiece = true;
        setIcon(piece.getImage());
    }
    
    /**
     * Removes the piece from the Space.
     */
    public void removePiece() {
        hasPiece = false;
        piece = null;
        setIcon(null);
    }
    
     /**
     * Moves the piece on this space to another space.
     * 
     * @param s The space to move the piece to.
     */
    public void movePiece(Space s) {
        s.addPiece(piece);
        removePiece();
    }
    
    /**
     * Gets the Piece object on the Space.
     * 
     * @return Piece object
     */
    public Piece getPiece() {
        return piece;
    }
    
     /**
     * Checks if this Space has a Piece.
     * 
     * @return Boolean on if it has a piece or not.
     */
    public boolean gethasPiece() {
        return hasPiece;
    }


    public void actionPerformed(ActionEvent e) {
    }
    
    /**
     * Gets the x location of this space.
     * 
     * @return x location
     */
    public int getxLoc() {
        return xLoc;
    }
    
    /**
     * Gets the y location of this space.
     * 
     * @return y location
     */
    public int getyLoc() {
        return yLoc;
    }

}
