import java.util.ArrayList;
import java.util.Deque;
import java.util.HashSet;
import java.util.LinkedList;

public class DepthFirstSearch implements Search {
	private Deque<GameBoard> stack = new LinkedList<GameBoard>();
	private HashSet<Integer> visited = new HashSet<Integer>();
	private GameBoardManager gbm = new GameBoardManager();	
	public static int maxQueueSize = 0;
	private void clear(){
		this.stack.clear();
		this.visited.clear();
		maxQueueSize = 0;
	}
	@Override
	public GameBoard Search(GameBoard start, GameBoard goal) {
		this.clear();
		// check if it is goal state
		if(start.isEqual(goal)){
			return start;
		}
		// add start state to visited
		this.visited.add(start.toInt());
		// get all child and push to stack
		ArrayList<GameBoard> adjs = gbm.successor(start);
		for(GameBoard adj: adjs){
			this.stack.push(adj);
		}
	

		while(!this.stack.isEmpty()){
			if(this.stack.size() > maxQueueSize){
				maxQueueSize = this.stack.size();
			}
			//get the node from top of stack
			GameBoard gb = this.stack.pop();
			// check if it is goal
			if(gb.isEqual(goal)){
				System.out.println("Goal Found!");
				System.out.println("MaxQueue Size: " + maxQueueSize);
				return gb;
			}
			else{
				// if not goal get  all children
				ArrayList<GameBoard> successors = gbm.successor(gb);
				for(GameBoard successor: successors){
					// for each child if not visited, mark visited and push to stack
					if(this.visited.add(successor.toInt())){	
						this.stack.push(successor);
					}

				}
			}
		}
		System.out.println(this.visited.size());
		return null;
	}
	
	

}
