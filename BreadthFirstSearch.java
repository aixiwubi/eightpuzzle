import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;


public class BreadthFirstSearch implements Search {
	private GameBoardManager gbm;
	private Queue<GameBoard> que;
	private HashSet<Integer> visited;
	private HashSet<Integer> frontier;
	private int maxQueueSize;
	public BreadthFirstSearch(){
		this.gbm = new GameBoardManager();
		//create empty queue and visited hashset
		this.que = new LinkedList<GameBoard>();
		this.visited = new HashSet<Integer>();
		this.frontier = new HashSet<Integer>();
		this.maxQueueSize = 0;
	}
	private void clear(){
		this.que.clear();
		this.visited.clear();
		this.frontier.clear();
		this.maxQueueSize = 0;
	}
	@Override
	public GameBoard Search(GameBoard start, GameBoard goal) {
		this.clear();
		//add start state to queue
		this.que.add(start);
		this.frontier.add(start.toInt());
		
		while(!que.isEmpty()){
			if(this.que.size() > this.maxQueueSize){
				this.maxQueueSize = this.que.size();
			}
			//get the first node from the queue
			GameBoard currentGameBoard = this.que.poll();
			this.frontier.remove(currentGameBoard.toInt());
			// mark visited
			this.visited.add(currentGameBoard.toInt());
			//get possible children
			for(GameBoard gb: gbm.successor(currentGameBoard)){
				// for each child, check if it is not visited or if it is not in the queue
				if(!this.visited.contains(gb.toInt()) || !this.frontier.contains(gb.toInt())){
					// check if it is goal state
					if(gb.isEqual(goal)){
						System.out.println("Found Solution");
						System.out.println("MaxQueue Size: " + this.maxQueueSize);
						return gb;
					}
					// add to queue if it is not goal state
					this.que.add(gb);
					this.frontier.add(gb.toInt());
					
				}
			}
		}
		//System.out.println(count);
		return null;
	}
	
	

}
