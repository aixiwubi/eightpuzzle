import java.util.HashSet;
import java.util.PriorityQueue;

public class BestFirstSearch implements Search {
	private GameBoardManager gbm;
	private PriorityQueue<GameBoard> que;	
	private HashSet<Integer> visited;
	private int maxQueueSize;
	public BestFirstSearch(){
		NotInPositionComparator c = new NotInPositionComparator();
		this.gbm = new GameBoardManager();
		this.que = new PriorityQueue<GameBoard>(c);
		this.visited = new HashSet<Integer>();
	}
	private void clear(){
		this.que.clear();
		this.visited.clear();
		this.maxQueueSize = 0;
	}

	@Override
	public GameBoard Search(GameBoard start, GameBoard goal) {
		this.clear();
		//add start state to queue
		start.setNotInPosition(HeuristicFunction.notInCorrectPosition(start.getGameBoard(), goal.getGameBoard()));
		this.que.add(start);
		
		while(!que.isEmpty()){
			if(this.que.size() > this.maxQueueSize){
				this.maxQueueSize = this.que.size();
			}
			//get the first node from the queue
			GameBoard currentGameBoard = this.que.poll();
			if(currentGameBoard.isEqual(goal)){
				System.out.println("Found Solution");
				System.out.println("MaxQueue Size: " + this.maxQueueSize);
				return currentGameBoard;
			}
			//currentGameBoard.printGameBoard();
			// mark visited
			this.visited.add(currentGameBoard.toInt());
			//get possible children
			for(GameBoard gb: gbm.successor(currentGameBoard)){
				// for each child, check if it is not visited
				if(!this.visited.contains(gb.toInt())){
					//calculate the f(n) = g(n) + h(n);
					gb.setNotInPosition(HeuristicFunction.notInCorrectPosition(gb.getGameBoard(),
							goal.getGameBoard()));
					//add to queue
					this.que.add(gb);
					this.visited.add(gb.toInt());
				}
			}
		}
		//System.out.println(count);
		return null;
	}

}
