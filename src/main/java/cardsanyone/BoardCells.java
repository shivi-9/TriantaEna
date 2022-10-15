package cardsanyone;
import java.util.*;

public class BoardCells {
    // This class handles what goes into the boards. In most game, some cells have special powers, 
    // this class handles and stores the power of a particular board cell, but will have an empty
    // string in the cellPower attribute for games like Tick-tac-toe, chess and most card games too.
    
    private String cellPower = ""; // Power of that cell
    private String cellValue = ""; //Value/Name of that cell
    private int[] cellLocation = new int[2]; //Location of that cell

    public BoardCells(String cellPower, String cellValue, int cellLocation[]){
        this.cellPower = cellPower; 
        this.cellValue = cellValue;
        for(int i = 0; i < cellLocation.length; i++){
            this.cellLocation[i] = cellLocation[i];
        }
    }

    public int[] get_cellLocation(){
        return cellLocation;
    }

    public String get_cellPower(){
        return cellPower;
    }

    public String get_cellValue(){
        return cellValue;
    }

    public void set_cellLocation(int cellLocation[]){
        this.cellLocation = cellLocation;
    }

    public void set_cellPower(String cellPower){
        this.cellPower = cellPower;
    }

    public void set_cellValue(String cellValue){
        this.cellValue = cellValue;
    }
}