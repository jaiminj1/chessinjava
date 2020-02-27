
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;

/**
 * @author Jaimin Jaffer
 * @version 1.0
 * @since 1.0
 */
public class startMenu extends JFrame implements ActionListener {

    /**
     * Buttons to start game and get help.
     */
    private JButton startGame, helpButton;

    /**
     * JLabel for the word "Chess".
     */
    private JLabel chessLabel;

    /**
     * Creates the start menu. Uses a border layout.
     */
    public startMenu() {
        setSize(1000, 750);
        setDefaultCloseOperation(3);
        setResizable(false);
        Font p = new Font("Arial", 1, 32);
        Font large = new Font("Arial", 1, 90);

        this.chessLabel = new JLabel("Chess", SwingConstants.CENTER);
        this.chessLabel.setFont(large);

        this.startGame = new JButton("Start");
        this.startGame.setFont(large);
        this.startGame.addActionListener(this);
        this.startGame.setBackground(Color.white);

        this.helpButton = new JButton("Help");
        this.helpButton.setFont(large);
        this.helpButton.addActionListener(this);
        this.helpButton.setBackground(Color.white);

        setLayout(new BorderLayout());
        add(this.chessLabel, "North");
        add(this.startGame, "Center");
        add(this.helpButton, "South");

        setVisible(true);

    }

    /**
     * actionPerformed method. Checks for help button press and start button press
     * and acts accordingly.
     */
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this.startGame) {
            dispose();
            Game gui = new Game();
        }
        if (e.getSource() == this.helpButton) {

            JOptionPane.showMessageDialog(this,
                    "Please note that this version of the game does not support \n"
                    + "     - En passant \n"
                    + "     - Castling \n"
                    + "     - Any form of stalemate \n"
                    + "A text file will be saved describing each move.",
                    "Notes and Limitations",
                    JOptionPane.INFORMATION_MESSAGE,
                    (new ImageIcon(getClass().getResource("whitepawn.png"))));

        }
    }

}
