/**
 * Name : Kenny Hoang
 * PennKey : kthoang
 * Recitation : 220
 * 
 * Execution: java Game2048 
 * 
 * Implements the highly addicitve 2048 game. User starts with 2 tiles and makes
 * moves by moving tiles up (press w), right (press d), left (press a) and down
 * (press s). If two adjacent tiles are crashed together with the same number,
 * they merge to form one with twice the value. After each turn, a new tile 
 * with value 2 or value 4 is created (with probability 0.9 and 0.1 
 * respectively). User loses no moves can be made. User wins if a 2048 tile is 
 * created. User can continue in "endless" mode if they win or restart when 
 * they lose or win. Displays number of moves made by user in victory or defeat 
 * messages.
 */
public class Game2048 {
    
    public static void main(String[] args) {
        PennDraw.enableAnimation(30);
        PennDraw.setCanvasSize(800, 800);
        PennDraw.setScale(0, 1);
        // see if game already won, this is for keep going feature
        boolean alreadyWon = false;
        Board board = new Board();
        
        while (true) {
            PennDraw.clear(PennDraw.WHITE);
            
            if (PennDraw.hasNextKeyTyped()) {
                char c = PennDraw.nextKeyTyped();
                if (c == 'a') {
                    board.moveLeft();
                } else if (c == 's') {
                    board.moveDown();
                } else if (c == 'd') {
                    board.moveRight();
                } else if (c == 'w') {
                    board.moveUp();
                }
            }
                       
            // check if board is full
            if (board.getCount() == 16) {
                // if board full check if no moves can be made
                if (board.checkGameOver()) {
                    // game over unless user wants to play again
                    boolean giveUp = true;
                    while (giveUp) {
                        PennDraw.clear(PennDraw.WHITE);
                        PennDraw.setPenColor(PennDraw.BLACK);
                        PennDraw.setFontSize(80);
                        PennDraw.text(0.5, 0.75, "Game over!");
                        PennDraw.setFontSize(60);
                        PennDraw.text(0.5, 0.6, "Moves: " + board.getMoves());
                        PennDraw.setPenColor(PennDraw.BOOK_LIGHT_BLUE);
                        PennDraw.filledRectangle(0.5, 0.3, 0.3, 0.1); 
                        PennDraw.setPenColor(PennDraw.BLACK);
                        PennDraw.rectangle(0.5, 0.3, 0.3, 0.1);  
                        PennDraw.setFontSize(40);
                        PennDraw.text(0.5, 0.3, "Play again?");
                        // start game over if play again button clicked
                        if (PennDraw.mousePressed() && 
                            PennDraw.mouseX() > 0.2 && 
                            PennDraw.mouseX() < 0.8 && 
                            PennDraw.mouseY() > 0.2 && 
                            PennDraw.mouseY() < 0.4) {
                            giveUp = false;
                            // clears key queue in case of carry over
                            while (PennDraw.hasNextKeyTyped()) {
                                PennDraw.nextKeyTyped();
                            }
                            // clear game board to start game over
                            board.resetMoves();
                            board.clearBoard();
                            // add two tiles for new board
                            board.addTile();
                            board.addTile();
                        }
                        PennDraw.advance();
                    }
                }
            }
            // check if game won
            if (!alreadyWon && board.getWin()) {
                // booleans for if user wants to play again or keep going
                boolean noNewGame = true;
                while (noNewGame) {
                    // if game won display victory screen
                    PennDraw.clear(PennDraw.WHITE);
                    PennDraw.setPenColor(PennDraw.BLACK);
                    PennDraw.setFontSize(80);
                    PennDraw.text(0.5, 0.75, "You win!!!");
                    PennDraw.setFontSize(60);
                    PennDraw.text(0.5, 0.6, "Moves: " + board.getMoves());
                    PennDraw.setPenColor(PennDraw.BOOK_RED);
                    PennDraw.filledRectangle(0.25, 0.3, 0.2, 0.07); 
                    PennDraw.setPenColor(PennDraw.BLACK);
                    PennDraw.rectangle(0.25, 0.3, 0.2, 0.07);  
                    PennDraw.setFontSize(35);
                    PennDraw.text(0.25, 0.3, "Keep going!");
                    PennDraw.setPenColor(PennDraw.BOOK_LIGHT_BLUE);
                    PennDraw.filledRectangle(0.75, 0.3, 0.2, 0.07); 
                    PennDraw.setPenColor(PennDraw.BLACK);
                    PennDraw.rectangle(0.75, 0.3, 0.2, 0.07);  
                    PennDraw.text(0.75, 0.3, "Play again?");                   
                    // start game over if play again button clicked
                    if (PennDraw.mousePressed() && 
                        PennDraw.mouseX() > 0.55 && 
                        PennDraw.mouseX() < 0.95 && 
                        PennDraw.mouseY() > 0.23 && 
                        PennDraw.mouseY() < 0.37) {
                        // clears key queue in case of carry over
                        while (PennDraw.hasNextKeyTyped()) {
                            PennDraw.nextKeyTyped();
                        }
                        // clear game board to start game over
                        board.clearBoard();
                        board.resetMoves();
                        // add two tiles for new board
                        board.addTile();
                        board.addTile();
                        // reset win boolean
                        board.resetWin();
                        noNewGame = false;
                    }
                    // continue game if keep going button clicked
                    if (PennDraw.mousePressed() && 
                        PennDraw.mouseX() > 0.05 && 
                        PennDraw.mouseX() < 0.45 && 
                        PennDraw.mouseY() > 0.23 && 
                        PennDraw.mouseY() < 0.37) {
                        noNewGame = false;
                        alreadyWon = true;
                    }
                    PennDraw.advance();
                }
            }   
            board.draw();
            PennDraw.advance();
        }
    }
}