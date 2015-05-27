package eg.edu.guc.santorini.tiles;

import com.jme3.scene.Mesh;
import java.util.ArrayList;
import eg.edu.guc.santorini.utilities.Location;

public class Pyramid extends Piece {

	public Pyramid(Location location) {
		super(location);
	}

	@Override
	public ArrayList<Location> possibleMoves() {
		ArrayList<Location> result = new ArrayList<Location>();
		Location pyLocation = this.getLocation();
		int[] dy = {-1, 1, -1, 1 };
		int[] dx = {-1, -1, 1, 1 };

		for (int i = 0; i < 4; ++i) {
			Location newLocation = new Location(pyLocation.getColumn() + dy[i],
					pyLocation.getRow() + dx[i]);
			if (newLocation.validLocation()) {
				result.add(newLocation);
			}
		}
		return result;
	}

	public String signature() {
		return "P";
	}

}
