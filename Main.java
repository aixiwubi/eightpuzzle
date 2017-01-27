import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner scanner = new Scanner(System. in); 
		EightPuzzleSolver solver = new EightPuzzleSolver();


		System.out.println("What Game Mode? (easy,med,hard)");
		String mode = scanner.nextLine();
		System.out.println("What algorithms? ('bfs','dfs','ids','ufs','best','astar')");
		String input = scanner. nextLine();
		int mod = 0;
		if(input.equals("astar")){
			System.out.println("Which heuristic? (1,2,3)");
			mod = scanner.nextInt();
			solver.aStarSolver(mode, mod);
		}
		else{

			solver.solve(mode, input);



		}

	}

}
