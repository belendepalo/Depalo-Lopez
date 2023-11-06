package linea;

import java.util.ArrayList;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Linea {
    private static String ErrorColumnIsFull = "Column is full!";
	public static String ErrorColumnOutOfBounds = "Column out of bounds!"; 
    public List<List<Character>> board;
    public char winVariant;
    public GameStatus gameStatus; 
    public int width;
    public int height;

    public Linea(int width, int height, char winVariant) {
        this.width = width;
        this.height = height;
        this.winVariant = winVariant;
        this.gameStatus = new OnGoingGame(new RedsTurn(), winVariant);
        initializeBoard(width, height);
        
    }

    public void playRedAt(int col) {
        if (checkIfAColumnIsOutOfParameter(col)) {
            throw new RuntimeException(ErrorColumnOutOfBounds);
        }else if(isColumnFull(col)) {
        	throw new RuntimeException(ErrorColumnIsFull);
        }
        else {
        	gameStatus.playRedAt(col, this.board);
        }
        gameStatus = gameStatus.checkAndUpdateGameState(this.board);
    }

    public void playBlueAt(int col) {
        if (checkIfAColumnIsOutOfParameter(col)) {
            throw new RuntimeException(ErrorColumnOutOfBounds);
        }else if(isColumnFull(col)) {
        	throw new RuntimeException(ErrorColumnIsFull);
        }
        else {
        	gameStatus.playBlueAt(col, this.board);
        }
        gameStatus = gameStatus.checkAndUpdateGameState(this.board);

    }
    
    public boolean isColumnFull(int col) {
    	return board.get(0).get(col - 1) != ' ' ;
    }

    public String show() {
    	return board.stream()
    	        .map(row -> "|" + row.stream().map(Object::toString)
    	        .collect(Collectors.joining("|")) + "|").collect(Collectors.joining("\n")) + "\n";
    }

    public boolean finished() {
        return gameStatus.finished(this.board);
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
    	return gameStatus.checkWin(chip, board);
    }

    public boolean checkTie() {
        return gameStatus.checkTie(this.board);
    }

    public boolean checkIfAColumnIsOutOfParameter(int col) {
		return col < 1 || col > width;
	}
    
    public boolean isRedsTurn() {
		return gameStatus.isRedsTurn();
    	
    }
    public boolean isBluesTurn() {
		return gameStatus.isBluesTurn();
    	
    }
    
    // ExtractMethods
    private void initializeBoard(int width, int height) {
		this.board = new ArrayList<>();
        this.board = IntStream.range(0, height)
        	    .mapToObj(rowIndex -> IntStream.range(0, width).mapToObj(colIndex -> ' ')
        	        .collect(Collectors.toList())).collect(Collectors.toList());
	}
}
