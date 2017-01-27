
import java.util.HashSet;

import java.util.PriorityQueue;



public class UniformCostSearch implements Search {

	private GameBoardManager gbm;
	private PriorityQueue<GameBoard> que;	
	private HashSet<Integer> visited;
	private HashSet<Integer> frontier;
	private int maxQueueSize;
	public UniformCostSearch(){
		CostComparator c = new CostComparator();
		this.gbm = new GameBoardManager();
		this.que = new PriorityQueue<GameBoard>(c);
		this.visited = new HashSet<Integer>();
		this.frontier = new HashSet<Integer>();
		this.maxQueueSize = 0;

	}
	private void clear(){
		this.frontier.clear();
		this.que.clear();
		this.visited.clear();
		this.maxQueueSize = 0;
	}


	@Override
	public GameBoard Search(GameBoard start, GameBoard goal) {
		this.clear();
		// add start state to visited set
		this.que.add(start);		
		this.frontier.add(start.toInt());
		while(!que.isEmpty()){
			if(this.que.size() > this.maxQueueSize){
				this.maxQueueSize = this.que.size();
			}
			//get first node
			GameBoard currentGameBoard = this.que.poll();
			this.frontier.remove(currentGameBoard.toInt());
			// add the current node to visited
			this.visited.add(currentGameBoard.toInt());

			// get current node's children

			for(GameBoard child: gbm.successor(currentGameBoard)){
		
				/*if the visited set does not have child or the priority queue does not have child,
				 * add child to  priority queue
				 */
				if(!this.visited.contains(child.toInt()) || !this.frontier.contains(child.toInt())){
					//check if is goal
					if(child.isEqual(goal)){
						System.out.println("Found Solution");
						System.out.println("MaxQueue Size: " + this.maxQueueSize);
						return child;
					}
					// if not goal add to queue
					this.que.add(child);	
					this.frontier.add(child.toInt());
				}
			}
		}
		//System.out.println(count);
		return null;
	}


}
