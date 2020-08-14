package game;

public interface Board {
	
	public boolean win();
	public void openAdjacentTiles();
	public int getPosition(int row, int column);
	public boolean setPosition();
	public void displayBoard();
	public void fillTiles();
	public void displayMines();
	public void initBoard();
	public void initMines();
	public void randomMines();
	
}
