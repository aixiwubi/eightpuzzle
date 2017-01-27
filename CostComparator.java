import java.util.Comparator;
// a cost comparator for priority queue
public class CostComparator implements Comparator<GameBoard> {

	@Override
	public int compare(GameBoard o1, GameBoard o2) {
		// TODO Auto-generated method stub
		return o1.getCurrentCost()-o2.getCurrentCost();
	}

}
