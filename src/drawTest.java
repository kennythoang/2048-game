public class drawTest{
    public static void main(String[] args) {
        PennDraw.enableAnimation(30);
        while (true) {
            PennDraw.clear(PennDraw.WHITE);
            PennDraw.setPenColor(PennDraw.BLACK);
            PennDraw.setFontSize(80);
            PennDraw.text(0.5, 0.75, "You win!!!");
            PennDraw.setFontSize(60);
            PennDraw.text(0.5, 0.6, "Moves: " + "5");
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
//        System.out.println(PennDraw.mousePressed());
//        System.out.println(PennDraw.mouseX());
//        System.out.println(PennDraw.mouseY());
            if (PennDraw.mousePressed() && 
                PennDraw.mouseX() > 0.55 && 
                PennDraw.mouseX() < 0.95 && 
                PennDraw.mouseY() > 0.23 && 
                PennDraw.mouseY() < 0.37) {
                System.out.println("play again");
                
            }
            // continue game if keep going button clicked
            if (PennDraw.mousePressed() && 
                PennDraw.mouseX() > 0.05 && 
                PennDraw.mouseX() < 0.45 && 
                PennDraw.mouseY() > 0.23 && 
                PennDraw.mouseY() < 0.37) {
                System.out.println("keep going");
            }
            PennDraw.advance();
        }
    }
}