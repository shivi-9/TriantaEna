package cardsanyone;

public class Player {
    // This class has the details about a particular player

    private String playerName = ""; // unique player ID
    private String playerTeam = ""; 
    private int playerScore = 0; 

    public Player(String playerTeam, String playerName, int playerScore) {
        this.playerTeam = playerTeam;
        this.playerName = playerName;
        this.playerScore = playerScore;
    }

    public String get_playerTeam(){
        return playerTeam;
    }
    
    public String get_playerName(){
        return playerName;
    }

    public int get_playerScore(){
        return playerScore;
    }

    // update the player score
    public void update_playerScore(int playerScore){
        this.playerScore += playerScore; 
    }

    public void set_playerTeam(String playerTeam){
        this.playerTeam = playerTeam;
    }

    public void set_playerName(String playerName){
        this.playerName = playerName; 
    }
}