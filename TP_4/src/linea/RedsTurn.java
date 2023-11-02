package linea;

public class RedsTurn extends GameStatus{

	@Override
	public void playBlueAt(int col) {
		throw new RuntimeException(ErrorItsRedsTurn);
	}

	@Override
	public void playRedAt(int col) {
		
		//placeChip(col - 1, 'R'); Se coloca la ficha
        //turn = 'B'; Se cambia de turno, ahora van las azules
	}
		
	

}
