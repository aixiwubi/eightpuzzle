/*
 *GameBoard class represents a search node
 *Stores the state, parent node, current cost, previous move and heuristic value
 */

public class GameBoard  {
	// data stored
	private GameBoard parentBoard;
	private int[] gameBoard;
	private int currentCost;
	private String operation;
	private int notInPosition;
	public GameBoard(){
	
		this.currentCost = 0;
		this.parentBoard = null;
		this.operation = null;
		
	}
	/*Setters and getters
	 */
	public void setNotInPosition(int cost){
		this.notInPosition = cost;
	}
	public int getNotInposition(){
		return this.notInPosition;
	}
	public void setCurrentCost(int cost){
		this.currentCost += cost;
	}
	public int getCurrentCost(){
		return this.currentCost;
	}
	public String toString(){
		StringBuilder sb = new StringBuilder();
		for(int i: this.gameBoard){
			sb.append(this.gameBoard[i]);
			
		}
		return sb.toString();
	}
	public void setOperation(String operation){
		this.operation = operation;
	}
	public String getOperation(){
		return this.operation;
	}
	public void setGameBoard(int[] input){
		this.gameBoard = input;
		
	}
	public int[] getGameBoard(){
		return this.gameBoard;
	}
	public void setParentBoard(GameBoard gameBoard){
		this.parentBoard = gameBoard;
	}
	public GameBoard getParentBoard(){
		return this.parentBoard;
	}
	/*
	 * returns the 0 tile position
	 */
	public int getZero(){
		for(int i = 0; i < this.getGameBoard().length; i++){
			if(this.gameBoard[i] == 0){
				return i;
			}
		}
		return -1;
	}
	/*
	 * check if two game board has the same state
	 */
	
	public boolean isEqual(GameBoard gameBoard){
		for(int i = 0; i < gameBoard.getGameBoard().length; i ++){
			if(this.gameBoard[i]!= gameBoard.getGameBoard()[i]){
				return false;
			}
		}
		return true;
	}
	/*
	 * convert the state into an integer value
	 */
	public int toInt(){
		int a = 0;
		for(int i=0;i< this.gameBoard.length; i++){
			a += Math.pow(10,i)*this.gameBoard[i];
		}
		return a;
	}
	/*
	 * check if the state is equal
	 */
	public boolean isArrayEqual(int[] gameBoard){
		for(int i = 0; i < gameBoard.length; i ++){
			if(this.gameBoard[i]!= gameBoard[i]){
				return false;
			}
		}
		return true;
	}
	/*
	 * get a specific tile position (used for manhattan distance)
	 */
	public int getTilePosition(int tile){
		for(int i = 0; i < this.getGameBoard().length; i++){
			if(this.gameBoard[i] == tile){
				return i;
			}
		}
		return -1;
		
	}
	/*
	 * print a pretty game board
	 */
	public void printGameBoard(){
		System.out.println("--------------");
		for(int i = 0; i < this.getGameBoard().length; i++){
			System.out.print(this.gameBoard[i] + "  ");
			if(i == 2 || i == 5 || i == 8){
				System.out.println();
			}
		}
		System.out.println("--------------");
	}
	
	
}
