package linea;

public class WinVariantB extends WinVariants {

	@Override
	public boolean canHandle(char variantIdentifier) {
		return 'B' == variantIdentifier;
	}

	@Override
	public boolean checkWin(char player, LineGame game) {
		return game.checkAscendingDiagonalWin(player) || game.checkDescendingDiagonalWin(player);
	}
}
