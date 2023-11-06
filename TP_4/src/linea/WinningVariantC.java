package linea;

import java.util.List;

public class WinningVariantC extends WinningVariants{
	public WinningVariants winHorizontallyAndVertically;
	public WinningVariants winDiagonally;
	
	public WinningVariantC() {
        super('C');
        winHorizontallyAndVertically = new WinningVariantA();
        winDiagonally = new WinningVariantB();
    }
	@Override
	public boolean canHandle(char variantIdentifier) {
		return 'C' == variantIdentifier;
	}
	

	@Override
    public boolean checkWin(char chip, List<List<Character>> board) {
        return ((WinningVariantA) winHorizontallyAndVertically).checkHorizontal(chip, board) || ((WinningVariantA) winHorizontallyAndVertically).checkVertical(chip, board) || ((WinningVariantB) winDiagonally).checkDiagonal(chip, board);
    }

}

