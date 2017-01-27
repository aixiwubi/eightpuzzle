import java.util.ArrayList;
import java.util.Hashtable;
/*
 * GameBoardManager will manage all constructed game boards, make sure not having duplicated game boards
 */
public class GameBoardManager {
	// maintains only one game board for one state
	private Hashtable<String, GameBoard> gameBoards;

	public GameBoardManager (){
		this.gameBoards = new Hashtable<String,GameBoard>();
	}
	/*
	 * return the possible state after moving tile 0
	 */
	public ArrayList<GameBoard> successor(GameBoard gameBoard){
		ArrayList<GameBoard> gameBoards = new ArrayList<GameBoard>();
		if(canMoveUp(gameBoard)){
			//System.out.println("up");
			//System.out.println(gameBoard.getZero());
			gameBoards.add(move(gameBoard,"up",-3));
			//System.out.println(gameBoard.getZero());
		}
		if(canMoveDown(gameBoard)){
			//System.out.println("down");
			//System.out.println(gameBoard.getZero());
			gameBoards.add(move(gameBoard,"down",3));
			//System.out.println(gameBoard.getZero());
		}

		if(canMoveRight(gameBoard)){
			//System.out.println("right");
			//System.out.println(gameBoard.getZero());
			gameBoards.add(move(gameBoard,"right",1));
			//System.out.println(gameBoard.getZero());
		}
		if(canMoveLeft(gameBoard)){
			//System.out.println("left");
			//System.out.println(gameBoard.getZero());
			gameBoards.add(move(gameBoard,"left",-1));
			//System.out.println(gameBoard.getZero());
		}

		return gameBoards;
	}
	/*
	 * check if the tile 0 can be moved to a certain direction
	 */

	private boolean canMoveUp(GameBoard gameBoard){

		if(gameBoard.getZero()-3>=0){
			return true;
		}
		return false;
	}
	private boolean canMoveDown(GameBoard gameBoard){

		if(gameBoard.getZero()+3<=8){
			return true;
		}
		return false;
	}
	private boolean canMoveRight(GameBoard gameBoard){
		if(gameBoard.getZero()+1 == 3 || gameBoard.getZero()+1 ==6 || gameBoard.getZero()+1 ==9){
			return false;
		}
		return true;
	}
	private boolean canMoveLeft(GameBoard gameBoard){

		if(gameBoard.getZero()-1 == -1 || gameBoard.getZero()-1 ==2 || gameBoard.getZero()-1==5){
			return false;
		}
		return true;
	}
	/*
	 * create a new state function
	 */
	private int[] copyGameBoard(int[] gameBoard){
		int[] result = new int[9];
		for(int i = 0; i< gameBoard.length; i++){
			result[i] = gameBoard[i];		
		}
		return result;
	}
	/*
	 * return a new game board after moving up down left or right
	 */
	private GameBoard move(GameBoard gameBoard, String operation, int modifier){

		//System.out.println(gameBoard.getZero());
		int[] tempGameBoard = copyGameBoard(gameBoard.getGameBoard());
		//gameBoard.printGameBoard();
		int currentCost = gameBoard.getCurrentCost();
		int tempIndex = gameBoard.getZero();
		int temp = tempGameBoard[tempIndex+modifier];
		currentCost += temp;
		tempGameBoard[tempIndex] = temp;
		tempGameBoard[tempIndex+modifier] = 0;
		if(!this.gameBoards.contains(tempGameBoard.toString())){
			GameBoard result = new GameBoard();
			//gameBoard.printGameBoard();
			result.setGameBoard(tempGameBoard);
			result.setParentBoard(gameBoard);
			result.setOperation(operation);
			result.setCurrentCost(currentCost);
			//result.setNotInPosition(HeuristicFunction.notInCorrectPosition(tempGameBoard, goal.getGameBoard()));
			//System.out.println(result.getZero());
			return result;
		}
		else{
			//if the hashtable already have a game board with the same state, return the old game board
			return this.gameBoards.get(tempGameBoard.toString());
		}
	}

}
