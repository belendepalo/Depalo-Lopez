package linea;
import java.util.List;
import java.util.stream.IntStream;

public class BluesTurn extends Turns{
    @Override
    public void playBlue(int col, List<List<Character>> board) {
        // Colocar la ficha 'B' en la posición correspondiente
    	 IntStream.rangeClosed(0, board.size() - 1)
         .filter(rowIndex -> board.get(board.size() - 1 - rowIndex).get(col - 1) == ' ')
         .findFirst()
         .ifPresent(rowIndex -> board.get(board.size() - 1 - rowIndex).set(col - 1, 'B'));
    }
        
	@Override
	public void playRed(int col, List<List<Character>> board) {
        throw new RuntimeException(ErrorItsBluesTurn);
    }

}
