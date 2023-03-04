package cardsanyone;
import java.util.*;

public class GameEngine {
    // This is the main class which calls the class of the game that user wants to play

    private static void beginTriantaEna(Scanner userInput){
        TriantaEna triObj = new TriantaEna();
        triObj.letsPlay();
    }

    public static void main(String args[]){ 
        Scanner userInput = new Scanner(System.in);
        //Welcome Message
        System.out.println("Welcome!\nLet's play Trianta Ena."); //Welcome Message
        beginTriantaEna(userInput); 
    }
}
