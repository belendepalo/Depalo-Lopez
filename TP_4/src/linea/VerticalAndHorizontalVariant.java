package linea;

import java.util.List;

public class WinningVariantA extends WinningVariants{
	public WinningVariantA() {
        super('A');
    }
	
	@Override
	public boolean canHandle(char variantIdentifier) {
		return 'A' == variantIdentifier;
	}
	
	@Override
    public boolean checkWin(char chip, List<List<Character>> board) {
        return checkHorizontal(chip, board) || checkVertical(chip, board);
    }
	
	public boolean checkHorizontal(char chip, List<List<Character>> board) {
	
	 for (int rowIndex = 0; rowIndex < board.size(); rowIndex++) {
         int count = 0;
         for (int colIndex = 0; colIndex < board.get(rowIndex).size(); colIndex++) {
             if (board.get(rowIndex).get(colIndex) == chip) {
                 count++;
                 if (count == 4) {
                     return true;
                 }
             } else {
                 count = 0;
             }
         }
     }
     return false;
 }
	
	public boolean checkVertical(char chip, List<List<Character>> board) {
        for (int colIndex = 0; colIndex < board.get(0).size(); colIndex++) {
            int count = 0;
            for (int rowIndex = 0; rowIndex < board.size(); rowIndex++) {
                if (board.get(rowIndex).get(colIndex) == chip) {
                    count++;
                    if (count == 4) {
                        return true;
                    }
                } else {
                    count = 0;
                }
            }
        }
        return false;
    }


	
	
}
