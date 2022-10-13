package cardsanyone;
import java.util.*;

public class GameEngine {
    private static int set_teamSize(Scanner userInput){
        System.out.println("How many players are going today?");
        int teamSize;
        try {
            teamSize = Integer.parseInt(userInput.nextLine());
            return teamSize;
        }catch(Exception e){
            Scanner userInput1 = new Scanner(System.in);
            System.out.println("Input is invalid!\nLets try again!");
            teamSize = set_teamSize(userInput1);
            return teamSize;
        }
    }

    public static void beginTriantaEna(Scanner userInput){
        int teamSize;
        System.out.println("Let's create the teams");
        teamSize = set_teamSize(userInput); // will ask user to input the number of players
        TriantaEna triObj = new TriantaEna();
        while(!triObj.check_teamSize(teamSize)){
            teamSize = set_teamSize(userInput);
        }
        triObj.letsPlay(); 
    }
    
    public static void main(String args[]){ 
        Scanner userInput = new Scanner(System.in);
        //Welcome Message
        System.out.println("Welcome!\nLet's play Trianta Ena."); //Welcome Message
        beginTriantaEna(userInput); 
    }
}
