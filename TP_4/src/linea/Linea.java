package linea;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Linea {
    public static String ErrorItsRedsTurn = "It's Red's Turn!";
    public static String ErrorItsBluesTurn = "It's Blue's Turn!";
    public static String ErrorColumnOutOfBounds = "Column out of bounds!";
    public List<List<Character>> board;
    public int width;
    public int height;
    public char winVariant;
    public GameStatus gameStatus;
    public WinningVariants winVariants;
    private List<WinningVariants> winningVariants = Arrays.asList(new WinningVariantA(), new WinningVariantB(), new WinningVariantC());
    

    public Linea(int width, int height, char winVariant) {
        this.width = width;
        this.height = height;
        this.winVariant = winVariant;
        this.gameStatus = new RedsTurn();
        initializeWinningVariant(winVariant);
        this.board = new ArrayList<>();
        for (int rowIndex = 0; rowIndex < height; rowIndex++) {
            List<Character> row = new ArrayList<>();
            for (int colIndex = 0; colIndex < width; colIndex++) {
                row.add(' ');
            }
            this.board.add(row);
        }
        
    }
    
    public void initializeWinningVariant(char winVariant) {
    	
    	winningVariants.stream()
        .filter(variant -> variant.canHandle(winVariant))
        .findFirst()
        .ifPresent(variant -> winVariants = variant);
    }


    public void playRedAt(int col) {
        if (checkIfAColumnIsOutOfParameter(col)) {
            throw new RuntimeException(ErrorColumnOutOfBounds);
        }else {
        	gameStatus.playRedAt(col, this.board);
        	gameStatus = new BluesTurn();
        }
    }


    public void playBlueAt(int col) {
        if (checkIfAColumnIsOutOfParameter(col)) {
            throw new RuntimeException(ErrorColumnOutOfBounds);
        }else {
        	gameStatus.playBlueAt(col, this.board);
        	gameStatus = new RedsTurn();
        }
    }

    public void placeChip(int col, char chip) {
        for (int rowIndex = height - 1; rowIndex >= 0; rowIndex--) {
            if (board.get(rowIndex).get(col) == ' ') {
                board.get(rowIndex).set(col, chip);
                return;
            }
        }
        throw new RuntimeException("Column is full!");
    }

    public String show() {
        StringBuilder boardStr = new StringBuilder();
        for (List<Character> row : board) {
            for (char cell : row) {
                boardStr.append("|").append(cell);
            }
            boardStr.append("|\n");
        }
        return boardStr.toString();
    }

    public boolean finished() {
        return checkWin('R') || checkWin('B') || checkTie();
    }

    public String winner() {
        if (checkWin('R')) {
            return "Red is the winner!";
        } else if (checkWin('B')) {
            return "Blue is the winner!";
        } else if (checkTie()) {
            return "The game ended in a tie!";
        } else {
            return "The game is still ongoing.";
        }
    }

    public boolean checkWin(char chip) {
    	return winVariants.checkWin(chip, board);
    }

    public boolean checkTie() {
        for (char cell : board.get(0)) {
            if (cell == ' ') {
                return false;
            }
        }
        return true;
    }

    public boolean checkIfAColumnIsOutOfParameter(int col) {
		return col < 1 || col > width;
	}
}
