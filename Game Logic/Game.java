package cardsanyone;

interface Game {
    //This is the interface for all games. It has declarations for functions which are common to all the games
    
    public abstract void set_teamSize();
    public abstract Player[] initPlayer();
    public abstract void letsPlay();
}
