package cardsanyone;

public class BoardCells {
    private String cellPower = new String(); // will not be used for Trianta Ena
    private String cellValue; // will have associated game pieceName
    private int[] cellLocation = new int[2]; // i,j position on the board

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

    // public void set_cellValue(int cellValue){
    //     this.cellValue = cellValue;
    // }
    
}