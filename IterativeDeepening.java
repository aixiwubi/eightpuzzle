import java.util.ArrayList;
import java.util.HashSet;

public class IterativeDeepening implements Search {
	private GameBoardManager gbm = new GameBoardManager();
	public int maxQueueSize = 0;
	public IterativeDeepening(){
	}
	
	@Override
	public GameBoard Search(GameBoard start, GameBoard goal) {
	
		int i = 1;
		IterativeState result;
		//start doing iterative deepening.
		while(true){
			
			/*the limit is from 1 to infinity
			 * start depth limited search on 1 and continue to infinity
			*/
			
			result = dls(start,goal.getGameBoard(),i);
			// if the result contains a solution return the solution
			if(result.getState() == 2){
				System.out.println("Goal Found!");
				System.out.println("MaxQueue Size: " + this.maxQueueSize);
				return result.getGameBoard();
			}
			i++;
		}

	}
	public IterativeState dls(GameBoard start, int[] state, int limit){
		//create a hashset to track cycles 
		HashSet<String> hs = new HashSet<String>();
		hs.add(start.toString());
		// start depth search 
		return recursiveDls(start,state,limit, hs);
	}
	public IterativeState recursiveDls(GameBoard start,int[] state, int limit, HashSet<String> visited){
		// check if it is goal state, return a solution if goal found
		maxQueueSize ++;
		if(start.isArrayEqual(state)){

			IterativeState st = new IterativeState(2);
			st.setGameBoard(start);
			return st;
		}
		// if reaches  depth limit, return cutoff
		else if(limit == 0){
			
			return new IterativeState(1);
		}
		else{
			// get all children from current node
			ArrayList<GameBoard> gbs = new ArrayList<GameBoard>();
			gbs = this.gbm.successor(start);
			for(GameBoard gb : gbs){
				//for each child, if it is not visited, add to hashset and start recursive call on child
				if(visited.add(gb.toString())){
					IterativeState result = recursiveDls(gb,state,limit-1,visited);
					// if the return contains a solution, return solution and stop recursive calls.
					if(result.getState()==2){
						return result;
					}
				}
			}
		}
		//return failure if no solution found
		return new IterativeState(0);

	}
}
