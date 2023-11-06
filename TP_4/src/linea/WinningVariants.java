package linea;

import java.util.List;

public abstract class WinningVariants {
	protected char variantIdentifier;

    public WinningVariants(char variantIdentifier) {
        this.variantIdentifier = variantIdentifier;
    }
    public abstract boolean canHandle(char variantIdentifier);

	public abstract boolean checkWin(char chip, List<List<Character>> board);

}
