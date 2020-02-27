
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Dimension;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 * @author Jaimin Jaffer
 * @version 1.0
 * @since 1.0
 */
public class Board extends JPanel implements ActionListener {

    /**
     * Array of Spaces that's placed in a board layout.
     */
    private Space[][] space;

    /**
     * Stores who's turn it is.
     */
    private int whichTurn;

    /**
     * Stores the space currently selected.
     */
    private Space spaceSelected;

    /**
     * Stores the space selected if there's already a space selected.
     */
    private Space desiredMove;

    /**
     * Stores the location of the black and white king.
     */
    private Space blackKingLoc, whiteKingLoc;

    /**
     * Stores if there was a piece killed the previous turn.
     */
    private Piece lastPieceKilled;

    /**
     * Stores the JFrame that this board sits on.
     */
    private Game gui = null;

    /**
     * Prevents the board from giving out double play again quesstions.
     */
    private boolean gameActive = true;

    Color lightBeige = new Color(239, 215, 184);
    Color darkBeige = new Color(179, 133, 103);
    Color greyGreen = new Color(169, 179, 145);

    /**
     * Creates a Board
     *
     * @param v The game(JFrame) this board sits on.
     */
    public Board(Game v) {
        this.gui = v;

        setBorder(new EmptyBorder(5, 5, 5, 5));
        setLayout(new GridLayout(8, 8, 5, 5));
        setBackground(Color.black);

        space = new Space[8][8];

        //initializes the 2d array of jbuttons
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                this.space[i][j] = new Space(j, i);

                //determines colour. allows it to be back and forth.
                if (i % 2 == 0) {
                    Color[] colors = {lightBeige, darkBeige};
                    this.space[i][j].setBackground(colors[j % 2]);
                } else {
                    Color[] colors = {darkBeige, lightBeige};
                    this.space[i][j].setBackground(colors[j % 2]);
                }

                this.space[i][j].addActionListener(this);
                add(this.space[i][j]);
                this.space[i][j].setEnabled(true);
            }
        }

        //black pawn
        for (int i = 0; i < 8; i++) {
            this.space[1][i].addPiece(new Pawn(1));
        }
        //black rook
        this.space[0][0].addPiece(new Rook(1));
        this.space[0][7].addPiece(new Rook(1));
        //black knight
        this.space[0][1].addPiece(new Knight(1));
        this.space[0][6].addPiece(new Knight(1));
        //black bishop
        this.space[0][2].addPiece(new Bishop(1));
        this.space[0][5].addPiece(new Bishop(1));
        //black queen
        this.space[0][3].addPiece(new Queen(1));
        //black king
        this.space[0][4].addPiece(new King(1));
        blackKingLoc = new Space(this.space[0][4].getxLoc(), this.space[0][4].getyLoc());
        blackKingLoc.addPiece(new King(1));

        //white pawn
        for (int i = 0; i < 8; i++) {
            this.space[6][i].addPiece(new Pawn(0));
        }
        //white rook
        this.space[7][0].addPiece(new Rook(0));
        this.space[7][7].addPiece(new Rook(0));
        //white knight
        this.space[7][1].addPiece(new Knight(0));
        this.space[7][6].addPiece(new Knight(0));
        //white bishop
        this.space[7][2].addPiece(new Bishop(0));
        this.space[7][5].addPiece(new Bishop(0));
        //white queen
        this.space[7][3].addPiece(new Queen(0));
        //white king
        this.space[7][4].addPiece(new King(0));
        whiteKingLoc = new Space(this.space[7][4].getxLoc(), this.space[7][4].getyLoc());
        whiteKingLoc.addPiece(new King(0));

    }

    public void actionPerformed(ActionEvent e) {

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (e.getSource() == this.space[i][j]) {

                    if ((spaceSelected == null) && this.space[i][j].gethasPiece() != false && (this.space[i][j].getPiece()).getTeam() == whichTurn) {
                        spaceSelected = this.space[i][j];
                        this.space[i][j].setBackground(greyGreen);
                    } else if ((spaceSelected != null) && this.space[i][j] != spaceSelected) {
                        desiredMove = this.space[i][j];

                        if (spaceSelected.getyLoc() % 2 == 0) {
                            Color[] colors = {lightBeige, darkBeige};
                            spaceSelected.setBackground(colors[spaceSelected.getxLoc() % 2]);
                        } else {
                            Color[] colors = {darkBeige, lightBeige};
                            spaceSelected.setBackground(colors[spaceSelected.getxLoc() % 2]);
                        }

                        if ((spaceSelected.getPiece()).canMove(spaceSelected, desiredMove, this.space) == true) {

                            if (desiredMove.getPiece() != null) {
                                lastPieceKilled = desiredMove.getPiece();
                            }

                            Space tempO = new Space(spaceSelected.getxLoc(), spaceSelected.getyLoc());
                            stringToPieceObject(spaceSelected, tempO);
                            Space tempD = new Space(desiredMove.getxLoc(), desiredMove.getyLoc());
                            if (desiredMove.gethasPiece() == true) {
                                stringToPieceObject(desiredMove, tempD);
                            }

                            spaceSelected.movePiece(desiredMove);

                            if (whichTurn == 0) {
                                if (isCheckSingleNoKill(blackKingLoc, 1) == true) {
                                    if (isCheckmate(blackKingLoc)) {
                                        gui.endGame();
                                        gameActive = false;
                                    }
                                }
                            } else if (whichTurn == 1) {
                                if (isCheckSingleNoKill(whiteKingLoc, 0) == true) {
                                    if (isCheckmate(whiteKingLoc)) {
                                        gui.endGame();
                                        gameActive = false;
                                    }
                                }
                            }

                            if ((spaceSelected.getxLoc() == whiteKingLoc.getxLoc()) && (spaceSelected.getyLoc() == whiteKingLoc.getyLoc())) {
                                whiteKingLoc = new Space(desiredMove.getxLoc(), desiredMove.getyLoc());
                                whiteKingLoc.addPiece(new King(0));
                            } else if ((spaceSelected.getxLoc() == blackKingLoc.getxLoc()) && (spaceSelected.getyLoc() == blackKingLoc.getyLoc())) {
                                blackKingLoc = new Space(desiredMove.getxLoc(), desiredMove.getyLoc());
                                blackKingLoc.addPiece(new King(1));
                            }

                            Space tempChecker;

                            if (whichTurn == 0) {
                                tempChecker = whiteKingLoc;
                            } else {
                                tempChecker = blackKingLoc;
                            }

                            if (isCheck(tempChecker) == true) {
                                desiredMove.movePiece(spaceSelected);

                                if (lastPieceKilled != null) {
                                    desiredMove.addPiece(lastPieceKilled);
                                    lastPieceKilled = null;
                                }

                                if ((desiredMove.getxLoc() == whiteKingLoc.getxLoc()) && (desiredMove.getyLoc() == whiteKingLoc.getyLoc())) {
                                    whiteKingLoc = new Space(spaceSelected.getxLoc(), spaceSelected.getyLoc());
                                    whiteKingLoc.addPiece(new King(0));
                                } else if ((desiredMove.getxLoc() == blackKingLoc.getxLoc()) && (desiredMove.getyLoc() == blackKingLoc.getyLoc())) {
                                    blackKingLoc = new Space(spaceSelected.getxLoc(), spaceSelected.getyLoc());
                                    blackKingLoc.addPiece(new King(1));
                                }

                            } else {

                                if (desiredMove.getPiece() instanceof Pawn) {
                                    if (Pawn.checkUpgrade(desiredMove.getPiece(), desiredMove) == true) {
                                        int upgrade = displayPawnPromotion(desiredMove.getPiece().getTeam());

                                        // switch statement 
                                        switch (upgrade) {
                                            case 0:
                                                desiredMove.removePiece();
                                                desiredMove.addPiece(new Queen(whichTurn));
                                                break;
                                            case 1:
                                                desiredMove.removePiece();
                                                desiredMove.addPiece(new Bishop(whichTurn));
                                                break;
                                            case 2:
                                                desiredMove.removePiece();
                                                desiredMove.addPiece(new Knight(whichTurn));
                                                break;
                                            case 3:
                                                desiredMove.removePiece();
                                                desiredMove.addPiece(new Rook(whichTurn));
                                                break;
                                            default:
                                        }
                                    }
                                }

                                if (whichTurn == 0 && gameActive == true) {
                                    if (isCheckSingleNoKill(blackKingLoc, 1) == true) {
                                        gui.printMove(tempO, tempD, true);
                                        if (isCheckmate(blackKingLoc)) {
                                            gui.endGame();
                                        }
                                    }
                                } else if (whichTurn == 1 && gameActive == true) {
                                    if (isCheckSingleNoKill(whiteKingLoc, 0) == true) {
                                        gui.printMove(tempO, tempD, true);
                                        if (isCheckmate(whiteKingLoc)) {
                                            gui.endGame();
                                        }
                                    }
                                }

                                gui.printMove(tempO, tempD, false);

                                whichTurn ^= 1;

                            }

                            lastPieceKilled = null;
                            spaceSelected = null;
                            desiredMove = null;
                            gui.setTurnLabel(whichTurn);
                            repaint();
                        } else {
                            spaceSelected = null;
                            desiredMove = null;
                            repaint();
                        }

                    }

                }
            }
        }
    }

    /**
     * Checks if the king is in check after a player makes a move.
     * <p>
     * Checks every space, and if they have a piece, can it move onto the king.
     *
     * @param king location of the king.
     * @return whether the king is in check or not.
     */
    public boolean isCheck(Space king) {

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (space[i][j].gethasPiece() == true) {

                    if (space[i][j].getPiece().getTeam() == 1) {

                        if (whichTurn == 0) {

                            if (space[i][j].getPiece().canMove(space[i][j], king, space) == true) {
                                return true;
                            }

                        }

                    } else {

                        if (whichTurn == 1) {

                            if (space[i][j].getPiece().canMove(space[i][j], king, space) == true) {
                                return true;
                            }

                        }

                    }

                }
            }
        }
        return false;
    }

    /**
     * Checks if the king is in check after but also if they can kill the piece
     * putting it in check.
     * <p>
     * Checks every space, and if they have a piece, can it move onto the king.
     * If yes, it then checks if the king can move onto the threat. Then calls
     * another method for just checking if a piece can move onto it.
     *
     * @param king location of the king.
     * @param team team of piece
     *
     * @return whether the king is in check or not.
     *
     * @see #isCheckSingleNoKill(Space king, int team)
     */
    public boolean isCheckSingle(Space king, int team) {

        Space kingNew = new Space(king.getxLoc(), king.getyLoc());
        kingNew.addPiece(new King(team));

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (space[i][j].gethasPiece() == true) {

                    if (space[i][j].getPiece().getTeam() == 1 - team) {

                        System.out.println("checking is space can move onto kingNew");
                        System.out.println(kingNew.getxLoc());
                        System.out.println(kingNew.getyLoc());

                        if (space[i][j].getPiece().canMove(space[i][j], kingNew, this.space) == true) {

                            System.out.println("checking if king can move onto space");

                            if (kingNew.getPiece().canMove(kingNew, space[i][j], this.space) == true) {

                                System.out.println("call isCheckSingleNoKill");

                                if (isCheckSingleNoKill(space[i][j], team) == false) {
                                    return false;
                                }
                            }
                            return true;
                        }

                    }

                }
            }
        }
        return false;
    }

    /**
     * Checks if the king is in check.
     * <p>
     * Checks every space, and if they have a piece, can it move onto the king.
     * Also makes a whole new board since this method is meant for a
     * hypothetical situation of if the king could kill the piece putting it in
     * check, will it still be in check?
     *
     * @param king location of the king.
     * @param team The team of the king being checked.
     * @return whether the king is in check or not.
     */
    public boolean isCheckSingleNoKill(Space king, int team) {

        Space spaceFuture[][] = new Space[8][8];

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                spaceFuture[i][j] = new Space(this.space[i][j].getxLoc(), this.space[i][j].getyLoc());
                if ((this.space[i][j].gethasPiece())) {

                    if ((this.space[i][j].getPiece().getPieceLabel()).equals("Pawn")) {
                        spaceFuture[i][j].addPiece(new Pawn(this.space[i][j].getPiece().getTeam()));
                    } else if ((this.space[i][j].getPiece().getPieceLabel()).equals("Rook")) {
                        spaceFuture[i][j].addPiece(new Rook(this.space[i][j].getPiece().getTeam()));
                    } else if ((this.space[i][j].getPiece().getPieceLabel()).equals("Knight")) {
                        spaceFuture[i][j].addPiece(new Knight(this.space[i][j].getPiece().getTeam()));
                    } else if ((this.space[i][j].getPiece().getPieceLabel()).equals("Bishop")) {
                        spaceFuture[i][j].addPiece(new Bishop(this.space[i][j].getPiece().getTeam()));
                    } else if ((this.space[i][j].getPiece().getPieceLabel()).equals("Queen")) {
                        spaceFuture[i][j].addPiece(new Queen(this.space[i][j].getPiece().getTeam()));
                    }
                }
            }
        }

        int kingY = king.getyLoc();
        int kingX = king.getxLoc();
        spaceFuture[kingY][kingX].removePiece();
        spaceFuture[kingY][kingX].addPiece(new King(team));

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (spaceFuture[i][j].gethasPiece() == true) {

                    if (spaceFuture[i][j].getPiece().getTeam() != team) {

                        if (spaceFuture[i][j].getPiece().canMove(spaceFuture[i][j], spaceFuture[kingY][kingX], spaceFuture) == true) {

                            System.out.println("kingonking?");
                            System.out.println(spaceFuture[i][j].getPiece().getPieceLabel());

                            if ((spaceFuture[i][j].getyLoc() != king.getyLoc()) || (spaceFuture[i][j].getxLoc() != king.getxLoc())) {
                                System.out.println("returning true");
                                return true;
                            }
                        }
                    }

                }
            }
        }
        return false;
    }

    /**
     * Checks what space is putting the king in check.
     * <p>
     * Checks every space, and if they have a piece, can it move onto the king,
     * if so that's the threat.
     *
     * @param king location of the king.
     * @param team The team of the king being checked.
     * @return the Space threatening the King (if any).
     */
    public Space getThreat(Space king, int team) {

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (space[i][j].gethasPiece() == true) {

                    if (space[i][j].getPiece().getTeam() == 1 - team) {

                        if (space[i][j].getPiece().canMove(space[i][j], king, this.space) == true) {

                            if (space[i][j] == king) {
                                return null;
                            }
                            return space[i][j];
                        }

                    }

                }
            }
        }
        return null;
    }

    /**
     * Checks if the king is in checkmate
     * <p>
     * First checks if any of the 8 legal king moves are possible and if they're
     * safe from check. If none, it checks if any piece of their team can kill
     * the threat. If not, it checks if any piece of their team can block the
     * threat. 2D loop is done to check for all of the kings teammates. Once one
     * of his teammates are found, the threat piece is found and its found where
     * it’s location on the board is relative to the kings position. The piece
     * teammate that was selected earlier is checked to see if it can move to
     * any of the spaces in between the threat and the king. If it can the
     * checkmate method returns false and play continues, if it can’t it goes to
     * the next space on the 2d array(through the loop). Once the whole board
     * has been checked if no piece can block that means that it’s a checkmate
     * and the game is over.
     *
     * @param king location of the king.
     * @return whether the king is in check or not.
     */
    public boolean isCheckmate(Space king) {
        int sucTries = 0;

        System.out.println(king.getxLoc());
        System.out.println(king.getyLoc());
        //will not check the kings current position for check, that way this can also be used for stalemate checking

        if (king.getyLoc() != 7) {

            if ((king.getPiece().canMove(king, space[king.getyLoc() + 1][king.getxLoc()], space) == true) && (isCheckSingleNoKill(space[king.getyLoc() + 1][king.getxLoc()], king.getPiece().getTeam()) == false)) {
                sucTries = sucTries + 1;
                System.out.println("1 through");
            }

        }

        if (king.getyLoc() != 0) {

            if ((king.getPiece().canMove(king, space[king.getyLoc() - 1][king.getxLoc()], space) == true) && ((isCheckSingleNoKill(space[king.getyLoc() - 1][king.getxLoc()], king.getPiece().getTeam())) == false)) {
                sucTries = sucTries + 1;
                System.out.println("2 through");
            }

        }

        if (king.getxLoc() != 7) {

            if (((king.getPiece().canMove(king, space[king.getyLoc()][king.getxLoc() + 1], space) == true) && ((isCheckSingleNoKill(space[king.getyLoc()][king.getxLoc() + 1], king.getPiece().getTeam())) == false))) {
                sucTries = sucTries + 1;
                System.out.println("3 through");
            }

        }

        if (king.getxLoc() != 0) {

            if (((king.getPiece().canMove(king, space[king.getyLoc()][king.getxLoc() - 1], space) == true) && ((isCheckSingleNoKill(space[king.getyLoc()][king.getxLoc() - 1], king.getPiece().getTeam())) == false))) {
                sucTries = sucTries + 1;
                System.out.println("4 through");
            }

        }

        if (king.getyLoc() != 7 && king.getxLoc() != 0) {

            if (((king.getPiece().canMove(king, space[king.getyLoc() + 1][king.getxLoc() - 1], space) == true) && ((isCheckSingleNoKill(space[king.getyLoc() + 1][king.getxLoc() - 1], king.getPiece().getTeam())) == false))) {
                sucTries = sucTries + 1;
                System.out.println("5 through");
            }

        }

        if (king.getyLoc() != 7 && king.getxLoc() != 7) {

            if (((king.getPiece().canMove(king, space[king.getyLoc() + 1][king.getxLoc() + 1], space) == true) && ((isCheckSingleNoKill(space[king.getyLoc() + 1][king.getxLoc() + 1], king.getPiece().getTeam())) == false))) {
                sucTries = sucTries + 1;
                System.out.println("6 through");
            }

        }

        if (king.getyLoc() != 0 && king.getxLoc() != 0) {

            if (((king.getPiece().canMove(king, space[king.getyLoc() - 1][king.getxLoc() - 1], space) == true) && ((isCheckSingleNoKill(space[king.getyLoc() - 1][king.getxLoc() - 1], king.getPiece().getTeam())) == false))) {
                sucTries = sucTries + 1;
                System.out.println("7 through");
            }

        }

        if (king.getyLoc() != 0 && king.getxLoc() != 7) {

            if (((king.getPiece().canMove(king, space[king.getyLoc() - 1][king.getxLoc() + 1], space) == true) && ((isCheckSingleNoKill(space[king.getyLoc() - 1][king.getxLoc() + 1], king.getPiece().getTeam())) == false))) {
                sucTries = sucTries + 1;
                System.out.println("8 through");
            }

        }

        System.out.println(sucTries + "went through");

        if (sucTries == 0) {
//need to check for block abilities
            Space threat = getThreat(king, king.getPiece().getTeam());

            for (int i = 0; i < 8; i++) {
                for (int j = 0; j < 8; j++) {
                    if (space[i][j].gethasPiece() == true && (threat != null)) {

                        if (space[i][j].getPiece().getTeam() != threat.getPiece().getTeam()) {
                            if ((space[i][j].getPiece().canMove(space[i][j], threat, this.space) == true) && (space[i][j].getPiece().getPieceLabel() != king.getPiece().getPieceLabel())) {
                                System.out.println("it can be killed");
                                return false;

                            } else {

                                //if rook
                                if (threat.getPiece() instanceof Rook) {

                                    if ((threat.getxLoc()) == king.getxLoc()) {

                                        if ((threat.getyLoc()) < king.getyLoc()) {

                                            for (int v = threat.getyLoc() + 1; v <= king.getyLoc(); v++) {

                                                if (space[i][j].getPiece() instanceof Queen) {
                                                }

                                                if (space[i][j].getPiece().canMove(space[i][j], space[v][threat.getxLoc()], this.space) == true) {

                                                    if (space[i][j] != king) {
                                                        space[i][j].movePiece(space[v][threat.getxLoc()]);
                                                        if (isCheckSingleNoKill(king, king.getPiece().getTeam()) == false) {
                                                            space[v][threat.getxLoc()].movePiece(space[i][j]);
                                                            return false;
                                                        }
                                                        space[v][threat.getxLoc()].movePiece(space[i][j]);
                                                    }
                                                }
                                            }

                                        } else if ((threat.getyLoc()) > king.getyLoc()) {

                                            for (int v = threat.getyLoc() - 1; v >= king.getyLoc(); v--) {

                                                if (space[i][j].getPiece().canMove(space[i][j], space[v][threat.getxLoc()], this.space) == true) {

                                                    if (space[i][j] != king) {
                                                        space[i][j].movePiece(space[v][threat.getxLoc()]);
                                                        if (isCheckSingleNoKill(king, king.getPiece().getTeam()) == false) {
                                                            space[v][threat.getxLoc()].movePiece(space[i][j]);
                                                            return false;
                                                        }
                                                        space[v][threat.getxLoc()].movePiece(space[i][j]);
                                                    }
                                                }
                                            }

                                        }

                                    } else if ((threat.getyLoc()) == king.getyLoc()) {

                                        if ((threat.getxLoc()) < king.getxLoc()) {

                                            for (int v = threat.getxLoc() + 1; v <= king.getxLoc(); v++) {

                                                if (space[i][j].getPiece().canMove(space[i][j], space[threat.getyLoc()][v], this.space) == true) {

                                                    if (space[i][j] != king) {
                                                        space[i][j].movePiece(space[threat.getyLoc()][v]);
                                                        if (isCheckSingleNoKill(king, king.getPiece().getTeam()) == false) {
                                                            space[threat.getyLoc()][v].movePiece(space[i][j]);
                                                            return false;
                                                        }
                                                        space[threat.getyLoc()][v].movePiece(space[i][j]);
                                                    }
                                                }
                                            }

                                        } else if ((threat.getxLoc()) < king.getxLoc()) {

                                            for (int v = threat.getxLoc() - 1; v >= king.getxLoc(); v--) {

                                                if (space[i][j].getPiece().canMove(space[i][j], space[threat.getxLoc()][v], this.space) == true) {

                                                    if (space[i][j] != king) {
                                                        space[i][j].movePiece(space[threat.getyLoc()][v]);
                                                        if (isCheckSingleNoKill(king, king.getPiece().getTeam()) == false) {
                                                            space[threat.getyLoc()][v].movePiece(space[i][j]);
                                                            return false;
                                                        }
                                                        space[threat.getyLoc()][v].movePiece(space[i][j]);
                                                    }
                                                }
                                            }

                                        }

                                    }

                                } else if (space[i][j].getPiece() instanceof Bishop) {

                                    if (Math.abs(threat.getxLoc() - king.getxLoc()) == Math.abs(threat.getyLoc() - king.getyLoc())) {

                                        if (((threat.getxLoc()) < king.getxLoc()) && ((threat.getyLoc()) < king.getyLoc())) {
                                            int y = threat.getyLoc() + 1;
                                            int x = threat.getxLoc() + 1;
                                            while (y <= king.getyLoc()) {
                                                if (space[i][j].getPiece().canMove(space[i][j], space[y][x], this.space) == true) {

                                                    if (space[i][j] != king) {
                                                        space[i][j].movePiece(space[y][x]);
                                                        if (isCheckSingleNoKill(king, king.getPiece().getTeam()) == false) {
                                                            space[y][x].movePiece(space[i][j]);
                                                            return false;
                                                        }
                                                        space[y][x].movePiece(space[i][j]);
                                                    }
                                                }
                                                y++;
                                                x++;
                                            }

                                        } else if (((threat.getxLoc()) > king.getxLoc()) && ((threat.getyLoc()) < king.getyLoc())) {
                                            int y = threat.getyLoc() + 1;
                                            int x = threat.getxLoc() - 1;
                                            while (y <= king.getyLoc()) {
                                                if (space[i][j].getPiece().canMove(space[i][j], space[y][x], this.space) == true) {

                                                    if (space[i][j] != king) {
                                                        space[i][j].movePiece(space[y][x]);
                                                        if (isCheckSingleNoKill(king, king.getPiece().getTeam()) == false) {
                                                            space[y][x].movePiece(space[i][j]);
                                                            return false;
                                                        }
                                                        space[y][x].movePiece(space[i][j]);
                                                    }
                                                }
                                                y++;
                                                x--;
                                            }

                                        } else if (((threat.getxLoc()) > king.getxLoc()) && ((threat.getyLoc()) > king.getyLoc())) {
                                            int y = threat.getyLoc() - 1;
                                            int x = threat.getxLoc() - 1;
                                            while (y >= king.getyLoc()) {
                                                if (space[i][j].getPiece().canMove(space[i][j], space[y][x], this.space) == true) {

                                                    if (space[i][j] != king) {
                                                        space[i][j].movePiece(space[y][x]);
                                                        if (isCheckSingleNoKill(king, king.getPiece().getTeam()) == false) {
                                                            space[y][x].movePiece(space[i][j]);
                                                            return false;
                                                        }
                                                        space[y][x].movePiece(space[i][j]);
                                                    }
                                                }
                                                y--;
                                                x--;
                                            }

                                        } else if (((threat.getxLoc()) < king.getxLoc()) && ((threat.getyLoc()) > king.getyLoc())) {
                                            int y = threat.getyLoc() - 1;
                                            int x = threat.getxLoc() + 1;
                                            while (y >= king.getyLoc()) {
                                                if (space[i][j].getPiece().canMove(space[i][j], space[y][x], this.space) == true) {

                                                    if (space[i][j] != king) {
                                                        space[i][j].movePiece(space[y][x]);
                                                        if (isCheckSingleNoKill(king, king.getPiece().getTeam()) == false) {
                                                            space[y][x].movePiece(space[i][j]);
                                                            return false;
                                                        }
                                                        space[y][x].movePiece(space[i][j]);
                                                    }
                                                }
                                                y--;
                                                x++;
                                            }

                                        }

                                    }

                                } else if (space[i][j].getPiece() instanceof Queen) {

                                    if ((threat.getxLoc()) == king.getxLoc()) {

                                        if ((threat.getyLoc()) < king.getyLoc()) {

                                            for (int v = threat.getyLoc() + 1; v <= king.getyLoc(); v++) {

                                                if (space[i][j].getPiece().canMove(space[i][j], space[v][threat.getxLoc()], this.space) == true) {

                                                    if (space[i][j] != king) {
                                                        space[i][j].movePiece(space[v][threat.getxLoc()]);
                                                        if (isCheckSingleNoKill(king, king.getPiece().getTeam()) == false) {
                                                            space[v][threat.getxLoc()].movePiece(space[i][j]);
                                                            return false;
                                                        }
                                                        space[v][threat.getxLoc()].movePiece(space[i][j]);
                                                    }
                                                }
                                            }

                                        } else if ((threat.getyLoc()) > king.getyLoc()) {

                                            for (int v = threat.getyLoc() - 1; v >= king.getyLoc(); v--) {

                                                if (space[i][j].getPiece().canMove(space[i][j], space[v][threat.getxLoc()], this.space) == true) {

                                                    if (space[i][j] != king) {
                                                        space[i][j].movePiece(space[v][threat.getxLoc()]);
                                                        if (isCheckSingleNoKill(king, king.getPiece().getTeam()) == false) {
                                                            space[v][threat.getxLoc()].movePiece(space[i][j]);
                                                            return false;
                                                        }
                                                        space[v][threat.getxLoc()].movePiece(space[i][j]);
                                                    }
                                                }
                                            }

                                        }

                                    } else if ((threat.getyLoc()) == king.getyLoc()) {

                                        if ((threat.getxLoc()) < king.getxLoc()) {

                                            for (int v = threat.getxLoc() + 1; v <= king.getxLoc(); v++) {

                                                if (space[i][j].getPiece().canMove(space[i][j], space[threat.getyLoc()][v], this.space) == true) {

                                                    if (space[i][j] != king) {
                                                        space[i][j].movePiece(space[threat.getyLoc()][v]);
                                                        if (isCheckSingleNoKill(king, king.getPiece().getTeam()) == false) {
                                                            space[threat.getyLoc()][v].movePiece(space[i][j]);
                                                            return false;
                                                        }
                                                        space[threat.getyLoc()][v].movePiece(space[i][j]);
                                                    }
                                                }
                                            }

                                        } else if ((threat.getxLoc()) < king.getxLoc()) {

                                            for (int v = threat.getxLoc() - 1; v >= king.getxLoc(); v--) {

                                                if (space[i][j].getPiece().canMove(space[i][j], space[threat.getxLoc()][v], this.space) == true) {

                                                    if (space[i][j] != king) {
                                                        space[i][j].movePiece(space[threat.getyLoc()][v]);
                                                        if (isCheckSingleNoKill(king, king.getPiece().getTeam()) == false) {
                                                            space[threat.getyLoc()][v].movePiece(space[i][j]);
                                                            return false;
                                                        }
                                                        space[threat.getyLoc()][v].movePiece(space[i][j]);
                                                    }
                                                }
                                            }

                                        }

                                    }

                                    if (Math.abs(threat.getxLoc() - king.getxLoc()) == Math.abs(threat.getyLoc() - king.getyLoc())) {

                                        if (((threat.getxLoc()) < king.getxLoc()) && ((threat.getyLoc()) < king.getyLoc())) {
                                            int y = threat.getyLoc() + 1;
                                            int x = threat.getxLoc() + 1;
                                            while (y <= king.getyLoc()) {
                                                if (space[i][j].getPiece().canMove(space[i][j], space[y][x], this.space) == true) {

                                                    if (space[i][j] != king) {
                                                        space[i][j].movePiece(space[y][x]);
                                                        if (isCheckSingleNoKill(king, king.getPiece().getTeam()) == false) {
                                                            space[y][x].movePiece(space[i][j]);
                                                            return false;
                                                        }
                                                        space[y][x].movePiece(space[i][j]);
                                                    }
                                                }
                                                y++;
                                                x++;
                                            }

                                        } else if (((threat.getxLoc()) > king.getxLoc()) && ((threat.getyLoc()) < king.getyLoc())) {
                                            int y = threat.getyLoc() + 1;
                                            int x = threat.getxLoc() - 1;
                                            while (y <= king.getyLoc()) {
                                                if (space[i][j].getPiece().canMove(space[i][j], space[y][x], this.space) == true) {

                                                    if (space[i][j] != king) {
                                                        space[i][j].movePiece(space[y][x]);
                                                        if (isCheckSingleNoKill(king, king.getPiece().getTeam()) == false) {
                                                            space[y][x].movePiece(space[i][j]);
                                                            return false;
                                                        }
                                                        space[y][x].movePiece(space[i][j]);
                                                    }
                                                }
                                                y++;
                                                x--;
                                            }

                                        } else if (((threat.getxLoc()) > king.getxLoc()) && ((threat.getyLoc()) > king.getyLoc())) {
                                            int y = threat.getyLoc() - 1;
                                            int x = threat.getxLoc() - 1;
                                            while (y >= king.getyLoc()) {
                                                if (space[i][j].getPiece().canMove(space[i][j], space[y][x], this.space) == true) {

                                                    if (space[i][j] != king) {
                                                        space[i][j].movePiece(space[y][x]);
                                                        if (isCheckSingleNoKill(king, king.getPiece().getTeam()) == false) {
                                                            space[y][x].movePiece(space[i][j]);
                                                            return false;
                                                        }
                                                        space[y][x].movePiece(space[i][j]);
                                                    }
                                                }
                                                y--;
                                                x--;
                                            }

                                        } else if (((threat.getxLoc()) < king.getxLoc()) && ((threat.getyLoc()) > king.getyLoc())) {
                                            int y = threat.getyLoc() - 1;
                                            int x = threat.getxLoc() + 1;
                                            while (y >= king.getyLoc()) {
                                                if (space[i][j].getPiece().canMove(space[i][j], space[y][x], this.space) == true) {

                                                    if (space[i][j] != king) {
                                                        space[i][j].movePiece(space[y][x]);
                                                        if (isCheckSingleNoKill(king, king.getPiece().getTeam()) == false) {
                                                            space[y][x].movePiece(space[i][j]);
                                                            return false;
                                                        }
                                                        space[y][x].movePiece(space[i][j]);
                                                    }
                                                }
                                                y--;
                                                x++;
                                            }

                                        }

                                    }

                                }
                            }

                        }
                    }
                }

            }
            System.out.println("Checkmate.");
            return true;
        }
        return false;
    }

    /**
     * Upgrades a pawn with the ability to select the piece via JOptionPane.
     *
     * @param t The team that's trying to upgrade.
     *
     * @return the piece chosen to be upgraded to in int form.
     */
    public int displayPawnPromotion(int t) {
        JFrame frame = new JFrame("temp");

        int value;
        JOptionPane promotionDialog = new JOptionPane();

        if (t == 0) {

            Object[] options = {(new ImageIcon(getClass().getResource("whitequeen.png"))), (new ImageIcon(getClass().getResource("whitebishop.png"))), (new ImageIcon(getClass().getResource("whiteknight.png"))), (new ImageIcon(getClass().getResource("whiterook.png")))};

            value = promotionDialog.showOptionDialog(null,
                    "Choose what piece you want to promote your pawn to.",
                    "Pawn Promotion!",
                    JOptionPane.DEFAULT_OPTION,
                    JOptionPane.INFORMATION_MESSAGE,
                    null,
                    options,
                    null);

        } else {

            Object[] options = {(new ImageIcon(getClass().getResource("blackqueen.png"))), (new ImageIcon(getClass().getResource("blackbishop.png"))), (new ImageIcon(getClass().getResource("blackknight.png"))), (new ImageIcon(getClass().getResource("blackrook.png")))};
            value = promotionDialog.showOptionDialog(null,
                    "Choose what piece you want to promote your pawn to.",
                    "Pawn Promotion!",
                    JOptionPane.DEFAULT_OPTION,
                    JOptionPane.INFORMATION_MESSAGE,
                    null,
                    options,
                    null);

        }

        if (value == -1) {
            value = displayPawnPromotion(t);
        } else {
            return value;
        }

        return value;
    }

    /**
     * Gets the current turn.
     *
     * @return Who's turn it is.
     */
    public int getTurn() {
        return whichTurn;
    }

    /**
     * Sets the turn to the desired one.
     *
     * @param w The desired turn to be set to.
     */
    public void setTurn(int w) {
        whichTurn = w;
    }

    /**
     * Forces the minimum size to be the preferred size (a square).
     */
    @Override
    public Dimension getMinimumSize() {
        return getPreferredSize();
    }

    /**
     * Forces the maximum size to be the preferred size (a square).
     */
    @Override
    public Dimension getMaximumSize() {
        return getPreferredSize();
    }

    /**
     * Forces the preferred size to be a square.
     */
    @Override
    public Dimension getPreferredSize() {
        return new Dimension(700, 700);
    }

    public void stringToPieceObject(Space o, Space n) {
        if ((o.getPiece().getPieceLabel()).equals("Pawn")) {
            n.addPiece(new Pawn(o.getPiece().getTeam()));
        } else if ((o.getPiece().getPieceLabel()).equals("Rook")) {
            n.addPiece(new Rook(o.getPiece().getTeam()));
        } else if ((o.getPiece().getPieceLabel()).equals("Knight")) {
            n.addPiece(new Knight(o.getPiece().getTeam()));
        } else if ((o.getPiece().getPieceLabel()).equals("Bishop")) {
            n.addPiece(new Bishop(o.getPiece().getTeam()));
        } else if ((o.getPiece().getPieceLabel()).equals("Queen")) {
            n.addPiece(new Queen(o.getPiece().getTeam()));
        } else if ((o.getPiece().getPieceLabel()).equals("King")) {
            n.addPiece(new King(o.getPiece().getTeam()));
        }
    }

}
