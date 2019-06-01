/**
 * Name : Kenny Hoang
 * PennKey : kthoang
 * Recitation : 220
 * 
 * Execution: java Board
 * 
 * Board object for the 2048 game. Note that this program will not be directly 
 * executed and is instead an object for the usage of Game2048. Contains all
 * methods for a board object, which is a 2D array of tiles, a subclass. Each
 * tile has a position and value. Methods involve resetting board, making board
 * moves, checking if game is over, and appropriate underlying helper functions
 */
import java.util.*;
public class Board {
    private Tile[][] tiles;
    private int tileCount;
    private int moves;
    private boolean win;
    
    /** Constructor for Board object. Starts with two tiles
      */    
    public Board() {
        tiles = new Tile[4][4];
        win = false;
        tileCount = 0;
        moves = 0;
        // test code for end game. Starts board with two 1024 tiles in top left 
//        tiles[0][0] = new Tile(0.125, 0.875, 1024);
//        tiles[0][1] = new Tile(0.125 + 0.25, 0.875, 1024);
        addTile();
        addTile();
        
    }
    
    /** Clears all tiles on game board
      */      
    public void clearBoard() {
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (tiles[i][j] != null) {
                    tiles[i][j] = null;
                    tileCount--;
                }
            }
        }
    }
    
    /** Adds random tile. Value 2 with probability 0.9, value 4 with prob 0.1
      */    
    public void addTile() {
        ArrayList<Integer> openSpots = new ArrayList<Integer>();
        for (int row = 0; row < tiles.length; row++) {
            for (int col = 0; col < tiles[row].length; col++) {
                if (isEmpty(row, col)) {
                    openSpots.add(row * 4 + col);
                }
            }
        }
        // pick random open tile spot
        int randSpot = openSpots.get((int) (Math.random() * openSpots.size()));
        
        // 0.9 chance of 2 spawning, 0.1 chance for 4
        int newTileValue = 0;
        if (Math.random() < 0.9) {
            newTileValue = 2;
        }
        else newTileValue = 4;
        
        // create new tile at open tile spot with new tile value
        tiles[randSpot / 4][randSpot % 4] = 
            new Tile(0.125 + randSpot % 4 / 4.0, 
                     1.0 - 0.125 - randSpot / 4 / 4.0, newTileValue);
        tileCount++;
    }
    
    /** Returns if tile at board position is empty
      * @param a - index of row of board's tile 2D array
      * @param b - index of column of board's tile 2D array
      * @returns boolean, true if no tile at positios, false if there is one
      */   
    public boolean isEmpty(int a, int b) {
        return tiles[a][b] == null;
    }
    
    /** Draws the board on the screen
      */
    public void draw() {
        for (int row = 0; row < tiles.length; row++) {
            for (int col = 0; col < tiles[row].length; col++) {
                if (tiles[row][col] == null) {
                    continue;
                }
                tiles[row][col].draw();
            }
        }
    }
    
    /** Moves the board left on the screen
      */
    public void moveLeft() {
        boolean change = false;
        
        // two arrays to compare at end to see if a move was made or not
        int[] initialValues = new int[4];
        int[] finalValues = new int[4];
        
        // move one row at a time
        for (int row = 0; row < tiles.length; row++) {
            // create array list for tile values in a row
            ArrayList<Integer> valueList = new ArrayList<Integer>();
            for (int col = 0; col < tiles[row].length; col++) {
                // add tile value to initial value array
                if (tiles[row][col] == null) {
                    initialValues[col] = 0;
                }
                else initialValues[col] = tiles[row][col].getValue();
                
                // add tile to list if not null
                if (tiles[row][col] != null) {
                    valueList.add(tiles[row][col].getValue());
                }
                // clear tile's spot in board
                tiles[row][col] = null;
            }
            
            // find values for new row
            for (int col = 1; col < valueList.size(); col++) {
                // if tile same as to the right, combine them
                if (valueList.get(col).equals(valueList.get(col - 1))) {
                    valueList.remove(col);
                    valueList.set(col - 1, 2 * valueList.get(col - 1));
                    if (valueList.get(col - 1).equals(2048)) {
                        win = true;
                    }
                    tileCount--;
                    change = true;
                }             
            }
            
            // fill row with updated tiles
            for (int col = 0; col < valueList.size(); col++) {
                tiles[row][col] = new Tile(0.125 + col / 4.0, 
                                           1 - 0.125 - row / 4.0, 
                                           valueList.get(col));
                // add tile value to final value array
                finalValues[col] = valueList.get(col);
            }
            
            // fill rest of finalValues with zeroes for empty tiles if needed
            for (int i = 0; i < 4 - valueList.size(); i++) {
                finalValues[3 - i] = 0;
            }
            
            // see if move was successfully made or not
            for (int col = 0; col < tiles.length; col++) {
                if (initialValues[col] != finalValues[col]) {
                    change = true;
                    break;
                }
            }
        }
        // if a move was successfully made, add a new tile
        if (change) {
            addTile();
            moves++;
        }
    }
    /** Moves the board right on the screen
      */
    public void moveRight() {
        boolean change = false;
        
        // two arrays to compare at end to see if a move was made or not
        int[] initialValues = new int[4];
        int[] finalValues = new int[4];   
        
        // move one row at a time
        for (int row = 0; row < tiles.length; row++) {
            // create array list for tile values in a row
            ArrayList<Integer> valueList = new ArrayList<Integer>();
            for (int col = 3; col >= 0; col--) {
                
                // add tile value to initial value array
                if (tiles[row][col] == null) {
                    initialValues[col] = 0;
                }
                else initialValues[col] = tiles[row][col].getValue();
                
                // add tile to list if not null
                if (tiles[row][col] != null) {
                    valueList.add(tiles[row][col].getValue());
                }
                // clear tile's spot in board
                tiles[row][col] = null;
            }
            
            // find values for new row
            for (int col = 1; col < valueList.size(); col++) {
                // if tile same as to the left, combine them
                if (valueList.get(col).equals(valueList.get(col - 1))) {
                    valueList.remove(col);                   
                    valueList.set(col - 1, 2 * valueList.get(col - 1));
                    if (valueList.get(col - 1).equals(2048)) {
                        win = true;
                    }
                    tileCount--;
                    change = true;
                }             
            }
            
            // fill row with updated tiles
            for (int col = 0; col < valueList.size(); col++) {
                tiles[row][3 - col] = new Tile(0.125 + (3 - col) / 4.0, 
                                               1 - 0.125 - row / 4.0, 
                                               valueList.get(col));
                // add tile value to final value array
                finalValues[3 - col] = valueList.get(col);                
            }
            
            // fill rest of finalValues with zeroes for empty tiles if needed
            for (int i = 0; i < 4 - valueList.size(); i++) {
                finalValues[i] = 0;
            }            
            
            // see if move was successfully made or not
            for (int col = 0; col < tiles.length; col++) {
                if (initialValues[col] != finalValues[col]) {
                    change = true;
                    break;
                }
            }            
        }
        if (change) {
            addTile();
            moves++;
        }
    }    
    /** Moves the board up on the screen
      */
    public void moveUp() {
        boolean change = false;
        
        // two arrays to compare at end to see if a move was made or not
        int[] initialValues = new int[4];
        int[] finalValues = new int[4];  
        
        // move one column at a time
        for (int col = 0; col < tiles.length; col++) {           
            // create array list for tile values in a column
            ArrayList<Integer> valueList = new ArrayList<Integer>();
            for (int row = 0; row < tiles.length; row++) {
                // add tile value to initial value array
                if (tiles[row][col] == null) {
                    initialValues[row] = 0;
                }
                else initialValues[row] = tiles[row][col].getValue();  
                
                // add tile value to initial value array
                if (tiles[row][col] == null) {
                    initialValues[row] = 0;
                }
                else initialValues[row] = tiles[row][col].getValue();
                
                // add tile to list if not null
                if (tiles[row][col] != null) {
                    valueList.add(tiles[row][col].getValue());
                }
                // clear tile's spot in board
                tiles[row][col] = null;
            }
            
            // find values for new row
            for (int row = 1; row < valueList.size(); row++) {
                // if tile same as to the bottom, combine them
                if (valueList.get(row).equals(valueList.get(row - 1))) {
                    valueList.remove(row);
                    valueList.set(row - 1, 2 * valueList.get(row - 1));
                    if (valueList.get(row - 1).equals(2048)) {
                        win = true;
                    }                    
                    tileCount--;
                    change = true;
                }             
            }
            
            // fill column with updated tiles
            for (int row = 0; row < valueList.size(); row++) {
                tiles[row][col] = new Tile(0.125 + col / 4.0, 
                                           1 - 0.125 - row / 4.0, 
                                           valueList.get(row));
                // add tile value to final value array
                finalValues[row] = valueList.get(row);
            }
            // fill rest of finalValues with zeroes for empty tiles if needed
            for (int i = 0; i < 4 - valueList.size(); i++) {
                finalValues[3 - i] = 0;
            }            
            
            // see if move was successfully made or not
            for (int i = 0; i < tiles.length; i++) {
                if (initialValues[i] != finalValues[i]) {
                    change = true;
                    break;
                }
            }                        
        }
        if (change) {
            addTile();
            moves++;
        }
    }
    /** Moves the board down on the screen
      */
    public void moveDown() {
        boolean change = false;
        
        // two arrays to compare at end to see if a move was made or not
        int[] initialValues = new int[4];
        int[] finalValues = new int[4];   
        
        // move one column at a time
        for (int col = 0; col < tiles.length; col++) {
            // create array list for tile values in a column
            ArrayList<Integer> valueList = new ArrayList<Integer>();
            
            for (int row = 3; row >= 0; row--) {                
                // add tile value to initial value array
                if (tiles[row][col] == null) {
                    initialValues[row] = 0;
                }
                else initialValues[row] = tiles[row][col].getValue();  
                
                // add tile to list if not null
                if (tiles[row][col] != null) {
                    valueList.add(tiles[row][col].getValue());
                }
                // clear tile's spot in board
                tiles[row][col] = null;
            }
            
            // find values for new column
            for (int row = 1; row < valueList.size(); row++) {
                
                // if tile same as to the above, combine them
                if (valueList.get(row).equals(valueList.get(row - 1))) {
                    valueList.remove(row);
                    valueList.set(row - 1, 2 * valueList.get(row - 1));
                    if (valueList.get(row - 1).equals(2048)) {
                        win = true;
                    }                    
                    tileCount--;
                }             
            }
            
            // fill column with updated tiles
            for (int row = 0; row < valueList.size(); row++) {
                tiles[3 - row][col] = new Tile(0.125 + col / 4.0, 
                                               1 - 0.125 - (3 - row) / 4.0, 
                                               valueList.get(row));
                // add tile value to final value array
                finalValues[3 - row] = valueList.get(row);                      
            }
            // fill rest of finalValues with zeroes for empty tiles if needed
            for (int i = 0; i < 4 - valueList.size(); i++) {
                finalValues[i] = 0;
            }            
            
            // see if move was successfully made or not
            for (int i = 0; i < tiles.length; i++) {
                if (initialValues[i] != finalValues[i]) {
                    change = true;
                    break;
                }
            }                     
        }
        if (change) {
            addTile();
            moves++;
        }
    }
    
    /** Returns if no more moves can be made (game over)
      * @returns boolean - true if no moves can be made, false if a move can be
      */   
    public boolean checkGameOver() {
        // check if any like-adjacent tile values horizontally
        for (int row = 0; row < tiles.length; row++) {
            for (int col = 0; col < tiles[row].length - 1; col++) {
                if (tiles[row][col].getValue() == 
                    tiles[row][col + 1].getValue()) {
                    return false;
                }
            }
        }
        // check if any like-adjacent tile values vertically
        for (int col = 0; col < tiles.length; col++) {
            for (int row = 0; row < tiles.length - 1; row++) {
                if (tiles[row][col].getValue() == 
                    tiles[row + 1][col].getValue()) {
                    return false;
                }
            }
        }
        // if no like-adjacent tiles, game is not over as move can be made
        return true;
    }
    
    /** Returns number of tiles on board
      * @returns tileCount - integer for number of tiles on board
      */   
    public int getCount() {
        return tileCount;
    }
    
    /** Returns number of moves made
      * @returns moves - integer for number of moves made
      */   
    public int getMoves() {
        return moves;
    }
    
    /** Resets number of moves made
      */   
    public void resetMoves() {
        moves = 0;
    }
    
    /** Returns if game won
      * @returns boolean - true if 2048 tile made, false if not
      */   
    public boolean getWin() {
        return win;
    }
    
    /** resets boolean if game won to false for new game
      */   
    public void resetWin() {
        win = false;
    }
    
    class Tile {
        private double x;
        private double y;
        private int value;
        public static final double SIZE = 0.125;
        
        /** Constructor for a tile
          * @param x - the x coordinate of the tile
          * @param y - the y coordinate of the tile
          * @param value - the value of the tile
          */
        public Tile(double x, double y, int value) {
            this.x = x;
            this.y = y;
            this.value = value;
        }
        /** Draws the tile
          */
        public void draw() {
            PennDraw.setPenColor(PennDraw.YELLOW);
            PennDraw.filledSquare(x, y, SIZE);
            PennDraw.setPenColor(PennDraw.BLACK);
            PennDraw.square(x, y, SIZE);
            PennDraw.setFontSize(30);
            PennDraw.text(x, y, Integer.toString(value));
        }
        
        /** Returns value of the tile
          * returns integer value of the tile
          */
        public int getValue() {
            return value;
        }
    }
}