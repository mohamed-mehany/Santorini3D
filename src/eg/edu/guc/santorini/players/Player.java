package eg.edu.guc.santorini.players;

import eg.edu.guc.santorini.tiles.Cube;
import eg.edu.guc.santorini.tiles.Piece;
import eg.edu.guc.santorini.tiles.Pyramid;
import eg.edu.guc.santorini.utilities.Location;

public class Player {

	private String name;
	private int type;
	private Piece t1;
	private Piece t2;
	private int player;

	public Player(String name, int type) {
		this.name = name;
		this.type = type;
		init();
	}

	public void init() {
		Location loc1 = null, loc2 = null;
		if (player == 1) {
			loc1 = new Location(0, 0);
			loc2 = new Location(4, 1);
		} else if (player == 2) {
			loc1 = new Location(0, 3);
			loc2 = new Location(4, 4);
		}
		if (type == 1) {
			t1 = new Cube(loc1);
			t2 = new Cube(loc2);
		} else if (type == 2) {
			t1 = new Pyramid(loc1);
			t2 = new Pyramid(loc2);
		}
	}

	public int getPlayer() {
		return player;
	}

	public void setPlayer(int player) {
		this.player = player;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Piece getT1() {
		return t1;
	}

	public void setT1(Piece t1) {
		this.t1 = t1;
	}

	public Piece getT2() {
		return t2;
	}

	public void setT2(Piece t2) {
		this.t2 = t2;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}
}
