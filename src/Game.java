
import java.awt.BorderLayout;
import java.awt.Font;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JOptionPane;

/**
 * @author Jaimin Jaffer
 * @version 1.0
 * @since 1.0
 */
public class Game extends JFrame {

    /**
     * Label to display which player's turn it is.
     */
    private JLabel displayTurn;

    /**
     * Label to display how many moves have been played.
     */
    private JLabel displayMoves;

    /**
     * Board the game is using.
     */
    private Board board;

    /**
     * Name of the file to write the moves to.
     */
    private String fileName;

    /**
     * Counts number of moves
     */
    private int moveCounter = 0;

    /**
     * Houses the move counter and turn label
     */
    private JPanel sidePanel;

    /**
     * Creates a game object. Includes the full JFrame and set ups writing to
     * the text file.
     */
    public Game() {

        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy--HH-mm-ss");
        Date date = new Date();
        fileName = ("Chess game on " + formatter.format(date) + ".txt");
        IO.createOutputFile(fileName);
        IO.println("Location format is [y][x]. The top left corner is [0][0]");
        IO.closeOutputFile();

        setSize(1000, 750);
        setDefaultCloseOperation(3);
        setResizable(false);
        setLayout(new BorderLayout());

        Font p = new Font("Arial", 1, 50);

        this.board = new Board(this);
        add(this.board, "West");

        sidePanel = new JPanel(new GridLayout(2, 1));

        this.displayTurn = new JLabel("Turn: White");
        this.displayTurn.setFont(p);
        sidePanel.add(displayTurn, "North");

        this.displayMoves = new JLabel("Moves: 0");
        this.displayMoves.setFont(p);
        sidePanel.add(displayMoves, "South");

        add(sidePanel, "Center");

        setVisible(true);
    }

    /**
     * Gets the file name
     *
     * @return the file name
     */
    public String getFileName() {
        return fileName;
    }

    /**
     * Gets the board it's using
     *
     * @return the board main in this constructor
     */
    public Board getBoard() {
        return this.board;
    }

    /**
     * Determines the winner and asks the user if they'd like to play again.
     */
    public void endGame() {
        String turnText;

        if (board.getTurn() == 0) {
            turnText = "White";
        } else {
            turnText = "Black";
        }

        int n = JOptionPane.showConfirmDialog(
                null,
                "Checkmate. " + turnText + " is victorious.\n Would you like to play again?",
                "Game Over",
                JOptionPane.YES_NO_OPTION);

        IO.appendOutputFile(this.getFileName());
        IO.println("Checkmate. " + turnText + " is victorious.");
        IO.closeOutputFile();

        switch (n) {
            case -1:
                System.exit(0);
                break;
            case 0:
                this.dispose();
                startMenu gui = new startMenu();
                break;
            case 1:
                System.exit(0);
                break;
            default:
                System.exit(0);
                break;
        }

    }

    /**
     * Converts the current turn(integer form), to a String which is then
     * displayed. Also does the redrawing of the move counter.
     *
     * @param i Turn it needs to be set to.
     */
    public void setTurnLabel(int i) { //ADD TO UML
        if (i == 0) {
            this.displayTurn.setText("Turn: White");
            moveCounter++;
            this.displayMoves.setText("Moves: " + moveCounter);
        } else if (i == 1) {
            this.displayTurn.setText("Turn: Black");
        }
    }

    /**
     * Creates a line of text about the move that's then printed to the text
     * file.
     *
     * @param o Original space
     * @param d Where it moved to
     * @param check If a king is in check.
     */
    public void printMove(Space o, Space d, boolean check) {
        String orignalTeam;
        String otherTeam;

        if (this.board.getTurn() == 0) {
            orignalTeam = "White";
            otherTeam = "Black";
        } else {
            orignalTeam = "Black";
            otherTeam = "White";
        }

        String print;
        print = moveCounter+1 + ". " + orignalTeam + " " + o.getPiece().getPieceLabel() + " on [" + o.getyLoc() + "][" + o.getxLoc() + "] moved to [" + d.getyLoc() + "][" + d.getxLoc() + "]";

        if (d.gethasPiece() == true) {
            print = print + "and captured a " + otherTeam + " " + d.getPiece().getPieceLabel();
        }

        if (check == true) {
            print = (otherTeam + "King is in check");
        }

        IO.appendOutputFile(this.getFileName());
        IO.println(print);
        IO.closeOutputFile();
    }
}
