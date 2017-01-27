/*
 * used for iterative deepening
 * can store the result gameboard
 * state represent solution(2), reach limit(1), no answer(0)
 */
public class IterativeState {
	private GameBoard gb;
	private int state;
	public IterativeState(int c){
		this.state =c;
	}
	public void setState(int c){
		this.state =c;
	}
	public int getState(){
		return this.state;
	}
	public void setGameBoard(GameBoard gb){
		this.gb =gb;
	}
	public GameBoard getGameBoard(){
		return this.gb;
	}
}
