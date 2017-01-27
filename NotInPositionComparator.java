import java.util.Comparator;
/*
 * a comparator for checking tile not in position 
 * A* also use this
 */
public class NotInPositionComparator implements Comparator<GameBoard>{

	@Override
	public int compare(GameBoard o1, GameBoard o2) {
		return o1.getNotInposition()- o2.getNotInposition();
		
	}

}
