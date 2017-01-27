
public class SearchFactory {
	public static Search getSearch(String type){
		if(type.equals("bfs")){
			return new BreadthFirstSearch();
		}
		else if(type.equals("dfs")){
			return new DepthFirstSearch();
		}
		else if(type.equals("ids")){
			return new IterativeDeepening();
		}
		else if(type.equals("ufs")){
			return new UniformCostSearch();
		}
		else if(type.equals("best")){
			return new BestFirstSearch();
		}
		else if(type.equals("Astar")){
			return new AStarSearch();
		}
		
		return null;

	}

}
