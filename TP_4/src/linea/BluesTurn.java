package linea;

public class BluesTurn extends GameStatus {
	@Override
	public void playBlueAt(int col) {
		// grid.placeChip(col - 1, 'B'); Se coloca la ficha
		// turn = 'R'; Se cambia de turno, ahora van las rojas

	}

	@Override
	public void playRedAt(int col) {

		throw new RuntimeException(ErrorItsBluesTurn);
	}

}
