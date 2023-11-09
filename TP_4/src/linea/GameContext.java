package linea;

public class GameContext {
    private boolean isRedTurn = true;
    private boolean hasWinner = false;
    private boolean isTie = false;
    private char lastPlayedChip;

    public GameContext(char lastPlayedChip) {
        this.lastPlayedChip = lastPlayedChip;
    }

    public boolean isRedTurn() {
        return isRedTurn;
    }

    public void setRedTurn(boolean redTurn) {
        isRedTurn = redTurn;
    }

    public boolean hasWinner() {
        return hasWinner;
    }

    public void setWinner(boolean winner) {
        hasWinner = winner;
    }

    public boolean isTie() {
        return isTie;
    }

    public void setTie(boolean tie) {
        isTie = tie;
    }

    public char getLastPlayedChip() {
        return lastPlayedChip;
    }

    public void setLastPlayedChip(char lastPlayedChip) {
        this.lastPlayedChip = lastPlayedChip;
    }

    public void updateContextAfterPlay(char chip) {
        this.lastPlayedChip = chip;
        this.isRedTurn = !this.isRedTurn;
    }

}
