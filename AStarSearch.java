import java.util.ArrayList;
import java.util.HashSet;
import java.util.PriorityQueue;

public class AStarSearch implements Search{
	private GameBoardManager gbm;
	private PriorityQueue<GameBoard> que;	
	private HashSet<Integer> visited;
	private int maxQueueSize;
	//initializer
	public AStarSearch(){
		NotInPositionComparator c = new NotInPositionComparator();
		this.gbm = new GameBoardManager();
		this.que = new PriorityQueue<GameBoard>(c);
		this.visited = new HashSet<Integer>();
		this.maxQueueSize = 0;
	}
	// clear datas
	private void clear(){
		this.que.clear();
		this.visited.clear();
		this.maxQueueSize = 0;
	}
	/*
	 * A*1  h is the number of tiles not in correct position
	 */
	public GameBoard search1(GameBoard start, GameBoard goal){
		this.clear();
		// get f(n) = g(n) + h(n)
		
		int startCost = HeuristicFunction.notInCorrectPosition(start.getGameBoard(), goal.getGameBoard());
		// set f(n) of start node
		start.setNotInPosition(startCost);
		// add start node to the queue
		this.que.add(start);	
		
		while(!que.isEmpty()){
			//track queue size
			if(this.que.size() > this.maxQueueSize){
				this.maxQueueSize = this.que.size();
			}
			//get first node off the queue
			GameBoard currentGameBoard = this.que.poll();
			this.visited.add(currentGameBoard.toInt());
			//get next states 
			for(GameBoard child: gbm.successor(currentGameBoard)){
				// if not visited 
				if(!this.visited.contains(child.toInt())){
					//check if is goal state
					if(child.isEqual(goal)){
						System.out.println("MaxQueue Size: " + this.maxQueueSize);
						return child;
					}
					
					// get f(n) = g(n) + h(n)
					int notCorrectPosition = HeuristicFunction.notInCorrectPosition(child.getGameBoard(), goal.getGameBoard());
					// set heuristic
					notCorrectPosition+= child.getCurrentCost();
					child.setNotInPosition(notCorrectPosition);
					// add to queue
					this.que.add(child);	
				}

			}
		}
		return null;
	}
	/*
	 * A*2 h is the Manhattan distance of each tile to goal state
	 */
	public GameBoard search2(GameBoard start, GameBoard goal){
		this.clear();
		// get f(n) = g(n) + h(n)
		int startCost = HeuristicFunction.manhattanDistance(start.getGameBoard(), goal.getGameBoard());
		start.setNotInPosition(startCost);
		this.que.add(start);	
		while(!que.isEmpty()){
			if(this.que.size() > this.maxQueueSize){
				this.maxQueueSize = this.que.size();
			}
			GameBoard currentGameBoard = this.que.poll();
			this.visited.add(currentGameBoard.toInt());
			//currentGameBoard.printGameBoard();
			//currentGameBoard.printGameBoard();

			ArrayList<GameBoard> children;
			children = gbm.successor(currentGameBoard);
			for(GameBoard child: children){
				if(!this.visited.contains(child.toInt())){
					if(child.isEqual(goal)){
						System.out.println("MaxQueue Size: " + this.maxQueueSize);
						return child;
					}
					// get f(n) = g(n) + h(n)
					int notCorrectPosition = HeuristicFunction.manhattanDistance(child.getGameBoard(), goal.getGameBoard());
					notCorrectPosition+= child.getCurrentCost();
					child.setNotInPosition(notCorrectPosition);
					this.que.add(child);	
				}

			}
		}
		System.out.println("MaxQueue Size: " + this.maxQueueSize);
		return null;
	}
	/*
	 * A*3 h is the Manhatthan distance * the tile cost of all tiles to goal state
	 */
	public GameBoard search3(GameBoard start, GameBoard goal){
		this.clear();
		// get f(n) = g(n) + h(n)
		int startCost = HeuristicFunction.manhattanDistanceWithTileValue(start.getGameBoard(), goal.getGameBoard());
		start.setNotInPosition(startCost);
		this.que.add(start);	
		while(!que.isEmpty()){
			if(this.que.size() > this.maxQueueSize){
				this.maxQueueSize = this.que.size();
			}
			GameBoard currentGameBoard = this.que.poll();
			this.visited.add(currentGameBoard.toInt());
			//currentGameBoard.printGameBoard();
			//currentGameBoard.printGameBoard();

			ArrayList<GameBoard> children;
			children = gbm.successor(currentGameBoard);
			for(GameBoard child: children){
				if(!this.visited.contains(child.toInt())){
					if(child.isEqual(goal)){
						System.out.println("MaxQueue Size: " + this.maxQueueSize);
						return child;
					}
					// get f(n) = g(n) + h(n)
					int notCorrectPosition = HeuristicFunction.manhattanDistanceWithTileValue(child.getGameBoard(), goal.getGameBoard());
					notCorrectPosition+= child.getCurrentCost();
					child.setNotInPosition(notCorrectPosition);
					this.que.add(child);	
				}

			}
		}
		System.out.println("MaxQueue Size: " + this.maxQueueSize);
		return null;
	}

	@Override
	public GameBoard Search(GameBoard start, GameBoard goal) {
		// TODO Auto-generated method stub
		return null;
	}
}
