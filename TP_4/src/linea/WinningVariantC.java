package linea;

public class WinVariantC extends WinVariants{
	
	@Override
	public boolean canHandle(char variantIdentifier) {
		return 'C' == variantIdentifier;
	}

	@Override
	public boolean checkWin(char player, LineGame game) {
		return game.checkVerticalWin(player) || game.checkHorizontalWin(player) || game.checkAscendingDiagonalWin(player) || game.checkDescendingDiagonalWin(player);
	}
}
