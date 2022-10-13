package cardsanyone;
import java.util.*;

public abstract class Game {
    public abstract Boolean check_teamSize(int teamSize);
    public abstract Player[] initPlayer(Scanner userInput);
    public abstract void letsPlay();
}
