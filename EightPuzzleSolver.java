import java.util.ArrayList;
import java.util.Stack;

public class EightPuzzleSolver {
	private ArrayList<String> algs;
	private GameBoard start;
	private GameBoard goal;
	private void setGameMode(String gameMode){
		this.goal = new GameBoard();
		int[] data = new int [9];
		data[0] = 1;
		data[1] = 2;
		data[2] = 3;
		data[3] = 8;
		data[4] = 0;
		data[5] = 4;
		data[6] = 7;
		data[7] = 6;
		data[8] = 5;
		this.goal.setGameBoard(data);
		if(gameMode.equals("easy")){

			this.start = new GameBoard();
			int[] data1 = new int [9];
			data1[0] = 1;
			data1[1] = 3;
			data1[2] = 4;
			data1[3] = 8;
			data1[4] = 6;
			data1[5] = 2;
			data1[6] = 7;
			data1[7] = 0;
			data1[8] = 5;
			this.start.setGameBoard(data1);
			//Config Medium GameBoard


		}
		else if(gameMode.equals("med")){
			//Config Medium GameBoard
			this.start = new GameBoard();
			int[] data2 = new int [9];
			data2[0] = 2;
			data2[1] = 8;
			data2[2] = 1;
			data2[3] = 0;
			data2[4] = 4;
			data2[5] = 3;
			data2[6] = 7;
			data2[7] = 6;
			data2[8] = 5;
			this.start.setGameBoard(data2);
		}
		else if(gameMode.equals("hard")){
			this.start = new GameBoard();
			int[] data3= new int [9];
			data3[0] = 5;
			data3[1] = 6;
			data3[2] = 7;
			data3[3] = 4;
			data3[4] = 0;
			data3[5] = 8;
			data3[6] = 3;
			data3[7] = 2;
			data3[8] = 1;
			this.start.setGameBoard(data3);
		}
	}

	public EightPuzzleSolver(){
		
		this.algs = new ArrayList<String>();
		this.algs.add("best");
		this.algs.add("bfs");
		this.algs.add("dfs");
		this.algs.add("ids");
		this.algs.add("ufs");
	}
	/*
	 * back trace the path
	 */
	private static void getPath(GameBoard result){
		// count the length of the path
		int length = 0;
		if(result!=null){
			// get result gameboard
			Stack<GameBoard> path = new Stack<GameBoard>();
			path.push(result);
			// push parent of game board to a stack
			while(result.getParentBoard()!= null){
				result= result.getParentBoard();
				path.push(result);

			}
			while(!path.isEmpty()){
				// pop the parent. print start to goal
				length++;
				GameBoard temp;
				temp = path.pop();
				temp.printGameBoard();
				System.out.println("f(n) = " + temp.getNotInposition());
				System.out.println("The previous move was: " + temp.getOperation());
				System.out.println("The current total Cost is: "+ temp.getCurrentCost());
			}
			System.out.println("The length of the Path is " + length);



		}
		else{
			System.out.println("no solution");
		}

	}

	public void aStarSolver(String gameMode, int postFix){
			this.setGameMode(gameMode);
			AStarSearch Asearch = (AStarSearch) SearchFactory.getSearch("Astar");
			GameBoard result;
			//mark start time and get result gameboard
			long start = System.currentTimeMillis();
			if(postFix == 1){
				result = Asearch.search1(this.start, this.goal);
				getPath(result);	
				// get solution time cost
				
			}
			else if(postFix == 2){
				result = Asearch.search2(this.start, this.goal);
				getPath(result);
				
				// get solution time cost
				
			}
			else if(postFix == 3){
				result = Asearch.search3(this.start, this.goal);
				getPath(result);
				
				// get solution time cost
				
			}
			double constant = 1000;
			// get solution time cost
			double timeCost = (System.currentTimeMillis()-start)/constant;
			
			System.out.println("The time is " + timeCost);

		
	}
	public void solve(String gameMode, String searchAlgorithm){
		this.setGameMode(gameMode);
		 if(this.algs.contains(searchAlgorithm)){
			Search search = SearchFactory.getSearch(searchAlgorithm);
			GameBoard result;
			//mark start time and get result gameboard
			long start = System.currentTimeMillis();

			result = search.Search(this.start, goal);
			double constant = 1000;
			// get solution time cost
			double timeCost = (System.currentTimeMillis()-start)/constant;
			// trace the path
			getPath(result);
			System.out.println("The time is " + timeCost);
		}
		else{
			System.out.println("System does not have the algorithm");
		}
	}
	


}
