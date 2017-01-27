/*HeuristicFunction class will calculate the value of heuristic
 * 
 * */
public class HeuristicFunction {
	/*
	 * returns the number of tiles that are not in correct position
	 * 
	*/
	public static int notInCorrectPosition(int[] current, int[] goal){
		int result = 0;
		for(int i = 0; i < current.length; i++){
			if(current[i]!= 0 && current[i]!=goal[i]){
				result++;
			}
		}
		
		return result;
	}
	
	
	/*
	 *returns the Manhattan distance from current state to the goal state
	 */
	public static int manhattanDistance(int[] current, int[]goal){
		int result = 0;
		for(int i = 1; i< current.length; i++){
			
			result +=getManhattanDistance(getTilePosition(i,current),getTilePosition(i,goal));
			
		}
		return result;
	}
	/*
	 * return the Manhattan distance * tile cost
	 */
	public static int manhattanDistanceWithTileValue(int[] start,int[] goal){
		int result = 0;
		for(int i = 1; i< start.length; i++){
		
			result +=getManhattanDistance(getTilePosition(i,start),getTilePosition(i,goal))*i;
			
		}
		return result;
		
	}
	/*
	 * helper function to get tile position
	 */
	private static int getTilePosition(int tile, int[] state){
		for(int i = 0; i < state.length; i++){
			if(state[i] == tile){
				return i;
			}
		}
		return -1;
		
	}
	/*
	 * helper function to calculate Manhattan distance of a certain tile
	 */
	private static int getManhattanDistance(int start, int goal){
		
		int dist = Math.abs(start - goal);
	//	System.out.println(dist%3);
		if(dist%3 == 0){
			return dist/3;
		}
		return dist/3+dist%3 ;
	}

}
