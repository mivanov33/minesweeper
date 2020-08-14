package game;

import java.util.Random;
import java.util.Scanner;

public class BeginnerBoard implements Board {
    private int[][] mines;
    private char[][] boardgame;
    private int row, column;
    Random random = new Random();
    Scanner input = new Scanner(System.in);
    
    public BeginnerBoard() {
    	mines = new int[10][10];
        boardgame = new char[10][10];
        initMines();
        randomMines();
        fillTiles();
        initBoard();
    }
    
    public boolean win() {
        int count = 0;
        for (int row = 0; row < 9; row++)
            for (int column = 0; column < 9; column++)
                if (boardgame[row][column] == '-')
                    count++;
        if (count == 10) 
            return true;
        else
            return false;                
    }
    
    public void openAdjacentTiles() {
        for (int i = -1; i < 2; i++)
            for (int j = -1; j < 2; j++)
                if ((mines[row+i][column+j] != -1) && (row != 0 && row != 9 && column != 0 && column != 9))
                    boardgame[row+i][column+j] = Character.forDigit(mines[row+i][column+j], 10);
        
    }
    
    public int getPosition(int row, int column) {
        return mines[row][column];
    }
    
    public boolean setPosition() {
            do {
                System.out.print("\nRow: "); 
                row = input.nextInt();
                System.out.print("Column: "); 
                column = input.nextInt();
                
                if ((boardgame[row][column] != '-') && ((row < 9 && row > -1) && (column < 9 && column > -1))) {
                    System.out.println("Tile is already opened");
                }
                
                if (row < 0 || row > 8 || column < 0 || column > 8) {
                    System.out.println("Choose a number between 0 and 8");
                }
                
            } while((row < 0 || row > 8 || column < 0 || column > 8) || (boardgame[row][column] != '-') );
            
            if (getPosition(row, column) == -1) {
            	return true;
            } else {
            	return false;
            }   
    }
    
    public void displayBoard(){
        System.out.println("\nCurrent status of board :");
        System.out.println("  0 1 2 3 4 5 6 7 8");
        for (int row = 0; row < 9; row++) {
            System.out.print(row);
            
            for (int column = 1; column < 10; column++){
                    System.out.print(" " + boardgame[row + 1][column]);
            }
            System.out.println();
        }
    }
    
    public void fillTiles() {
        for (int row = 1; row < 9; row++)
            for (int column = 1; column < 9; column++) {
                    for (int i = -1; i <= 1; i++)
                        for (int j = -1; j <= 1; j++)
                            if (mines[row][column] != -1)
                                if (mines[row+i][column+j] == -1)
                                    mines[row][column]++;      
            }      
    }
    
    public void displayMines() {
        for (int i = 1; i < 9; i++)
            for (int j = 1; j < 9; j++)
                if (mines[i][j] == -1)
                    boardgame[i][j] = '*';
        
        displayBoard();
    }
    
    public void initBoard() {
        for (int i = 1; i < mines.length; i++)
            for (int j = 1; j < mines.length; j++)
                boardgame[i][j] = '-';
    }
    
    public void initMines() {
        for (int i = 0; i < mines.length; i++)
            for (int j = 0; j < mines.length; j++)
                mines[i][j] = 0;
    }
    
    public void randomMines() {
        boolean raffled;
        int row, column;
        for (int i = 0; i < 10; i++){  
            do {
                row = random.nextInt(8) + 1;
                column = random.nextInt(8) + 1;
                
                if (mines[row][column] == -1)
                    raffled = true;
                else
                    raffled = false;
            } while(raffled);
            
            mines[row][column] = -1;
        }
    }
}