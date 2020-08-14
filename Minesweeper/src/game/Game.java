package game;

import java.util.Scanner;

public class Game {
    private Board board;
    boolean finish = false;
    boolean win = false;
    Scanner input = new Scanner(System.in);
    
    public Game(){
    	System.out.print("Enter the difficulty level");
        System.out.print("\nPress 0 for  BEGINNER (9 * 9 Cells and 10 Mines)");
        System.out.print("\nPress 1 for  INTERMEDIATE (16 * 16 Cells and 40 Mines)");
        System.out.print("\nPress 2 for  ADVANCED (24 * 24 Cells and 99 Mines)\n");
        
    	int level = input.nextInt();
    	
    	switch(level) {
    		case 0:
    			board = new BeginnerBoard();
    	        play(board);
    	        break;
    	        
    		case 1:
    			board = new IntermediateBoard();
    			play(board);
    			break;
    			
    		case 2:
    			board = new AdvancedBoard();
    			play(board);
    			break;
    		
    		default:
    			System.out.println("Invalid difficulty level");
    	}
    }
    
    public void play(Board board){
        do {
            board.displayBoard();
            finish = board.setPosition();
            
            if (!finish) {
                board.openAdjacentTiles();
                finish = board.win();
            }
            
        } while(!finish);
        
        if (board.win()) {
            System.out.println("Congratulations, you won!");
            board.displayMines();
        } else {
            System.out.println("You lost!");
            board.displayMines();
        }
    }
}