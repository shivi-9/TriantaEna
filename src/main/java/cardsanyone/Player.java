package cardsanyone;

public class Player {
    private String playerName = new String(); // playerName will be the unique player ID
    private String playerTeam = new String(); // playerTeam will be the identifier to distinguish banker from players
    private int playerScore; // current amount that is held by the player
    
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

    public void set_playerScore(int playerScore){
        this.playerScore += playerScore; 
    }

    public int get_playerScore(){
        return playerScore;
    }
    
}
