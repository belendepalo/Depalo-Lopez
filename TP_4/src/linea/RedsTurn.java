package linea;
import java.util.List;

public class RedsTurn extends GameStatus{
	@Override
	public void playBlueAt(int col, List<List<Character>> board) {
	    throw new RuntimeException(ErrorItsRedsTurn);
	}

    @Override
    public void playRedAt(int col, List<List<Character>> board) {
        // Verificar si la columna está llena
        if (board.get(0).get(col - 1) != ' ') {
            throw new RuntimeException("Column is full!");
        }

        // Colocar la ficha 'B' en la posición correspondiente
        for (int rowIndex = board.size() - 1; rowIndex >= 0; rowIndex--) {
            if (board.get(rowIndex).get(col - 1) == ' ') {
                board.get(rowIndex).set(col - 1, 'R');
                return;
            }
        }
    }
		
	

}
