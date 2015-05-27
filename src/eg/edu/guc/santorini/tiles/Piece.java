package eg.edu.guc.santorini.tiles;
import com.jme3.scene.Geometry;
import com.jme3.scene.Mesh;
import com.jme3.scene.Spatial;
import com.jme3.scene.shape.Box;
import java.util.ArrayList;

import eg.edu.guc.santorini.utilities.Location;

public abstract class Piece extends Geometry implements PieceInterface {

	private Location location;

        public void init(String name, Mesh m) {
            this.setName(name);
            this.setMesh(m);
        }
        
	public Piece(Location location) {
		this.location = location;
	}

	public Location getLocation() {
		return location;
	}

	public void setLocation(Location location) {
		this.location = location;
	}

	public abstract ArrayList<Location> possibleMoves();

	public abstract String signature();

	public ArrayList<Location> possiblePlacements() {
		int[] dx = {1, 1, 0, -1, -1, -1, 0, 1 };
		int[] dy = {0, 1, 1, 1, 0, -1, -1, -1 };
		ArrayList<Location> result = new ArrayList<Location>();
		Location pyLocation = this.getLocation();
		for (int i = 0; i < 8; ++i) {
			Location newLocation = new Location(pyLocation.getColumn() + dy[i],
					pyLocation.getRow() + dx[i]);
			if (newLocation.validLocation()) {
				result.add(newLocation);
			}
		}
		return result;
	}
}
