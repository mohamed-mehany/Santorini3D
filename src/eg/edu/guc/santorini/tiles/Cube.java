package eg.edu.guc.santorini.tiles;

import com.jme3.scene.Mesh;
import java.util.ArrayList;
import eg.edu.guc.santorini.utilities.Location;

public class Cube extends Piece {
        
	public Cube(Location location) {
		super(location);
	}

	@Override
	public ArrayList<Location> possibleMoves() {
		ArrayList<Location> result = new ArrayList<Location>();
		Location cubeLocation = this.getLocation();
		int[] dy = {-1, 0, 1, 0 };
		int[] dx = {0, -1, 0, 1 };
		for (int i = 0; i < 4; ++i) {
			Location newLocation = new Location(cubeLocation.getColumn()
					+ dy[i], cubeLocation.getRow() + dx[i]);
			if (newLocation.validLocation()) {
				result.add(newLocation);
			}
		}
		return result;
	}

	public String signature() {
		return "C";
	}

}
