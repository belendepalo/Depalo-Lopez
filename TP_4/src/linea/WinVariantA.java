package linea;

public class WinVariantA extends WinVariants {

	@Override
	public boolean canHandle(char variantIdentifier) {
		return 'A' == variantIdentifier;
	}

	@Override
	public boolean checkWin(char player, LineGame game) {
		return game.checkVerticalWin(player) || game.checkHorizontalWin(player);
	}

}
