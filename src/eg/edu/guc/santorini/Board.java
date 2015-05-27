package eg.edu.guc.santorini;

import java.util.ArrayList;
import java.util.Arrays;

import eg.edu.guc.santorini.exceptions.InvalidMoveException;
import eg.edu.guc.santorini.exceptions.InvalidPlacementException;
import eg.edu.guc.santorini.players.Player;
import eg.edu.guc.santorini.tiles.Piece;
import eg.edu.guc.santorini.utilities.Location;

public class Board implements BoardInterface {
	private String[][] board;
	private Player p1, p2, winner;
	private int turnMove, turnPlace;
	private boolean gameOver;
	private Piece lastMoved;

	public Board(Player p1, Player p2) {
		p1.setPlayer(1);
		p2.setPlayer(2);
		p1.init();
		p2.init();
		this.p1 = p1;
		this.p2 = p2;
		board = new String[SIDE][SIDE];
		zabtBoard();
		winner = null;
		turnMove = 1;
		turnPlace = 2;
		gameOver = false;
	}

	public void zabtBoard() {
		for (String[] row : board) {
			Arrays.fill(row, "0");
		}
		board[0][0] += p1.getT1().signature() + "1";
		board[4][1] += p1.getT2().signature() + "1";
		board[0][3] += p2.getT1().signature() + "2";
		board[4][4] += p2.getT2().signature() + "2";
	}

	public void move(Piece piece, Location newLocation)
			throws InvalidMoveException {
		if (gameOver) {
			throw new InvalidMoveException("The game is over");
		}
		if (!(getOwner(piece) == p1 && turnMove == 1 && turnPlace == 2)
				&& !(getOwner(piece) == p2 && turnMove == -1 && turnPlace == -2)) {
			throw new InvalidMoveException("This is not your turn");
		}
		if (!canMove(piece, newLocation)) {
			throw new InvalidMoveException(
					"Cannot move this piece to this location");
		}
		String res = piece.signature();
		if (getOwner(piece) == p1) {
			res += "1";
		} else {
			res += "2";
		}
		Location oldLocation = piece.getLocation();
		String old = board[oldLocation.getColumn()][oldLocation.getRow()];
		String finalLocation = board[newLocation.getColumn()][newLocation
				.getRow()];
		board[oldLocation.getColumn()][oldLocation.getRow()] = ""
				+ old.charAt(0);
		board[newLocation.getColumn()][newLocation.getRow()] += res;
		piece.setLocation(newLocation);
		if (finalLocation.charAt(0) == '3') {
			setWinner(getOwner(piece));
			gameOver = true;
		}
		lastMoved = piece;
		turnMove *= -1;
	}

	public void place(Piece piece, Location newLocation)
			throws InvalidPlacementException {
		if (gameOver) {
			throw new InvalidPlacementException("The game is over");
		}
		if (!(getOwner(piece) == p1 && turnMove == -1 && turnPlace == 2)
				&& !(getOwner(piece) == p2 && turnMove == 1 && turnPlace == -2)) {
			throw new InvalidPlacementException("This is not your turn");
		}
		if (!canPlace(piece, newLocation) || piece != lastMoved) {
			throw new InvalidPlacementException(
					"Cannot place the tile on this location");
		}
		int layers = Integer.parseInt(""
				+ board[newLocation.getColumn()][newLocation.getRow()]
						.charAt(0));
		board[newLocation.getColumn()][newLocation.getRow()] = ""
				+ (layers + 1);
		if (getOwner(piece) == p1 && hasNoMoves(p2)) {
			setWinner(p1);
			gameOver = true;
		}
		if (getOwner(piece) == p2 && hasNoMoves(p1)) {
			setWinner(p2);
			gameOver = true;
		}
		turnPlace *= -1;
	}

	public boolean hasNoMoves(Player player) {
		ArrayList<Location> one = player.getT1().possibleMoves();
		ArrayList<Location> two = player.getT2().possibleMoves();
		for (Location x : one) {
			if (canMove(player.getT1(), x)) {
				return false;
			}
		}
		for (Location x : two) {
			if (canMove(player.getT2(), x)) {
				return false;
			}
		}
		return true;
	}

	public void printArrayList(ArrayList<Location> m) {
		for (int i = 0; i < m.size(); ++i) {
			System.out.println(m.get(i));
		}
	}

	public boolean canMove(Piece piece, Location location) {
		String newLoc = board[location.getColumn()][location.getRow()];
		if (!piece.possibleMoves().contains(location) || newLoc.length() > 1) {
			return false;
		}
		String currLoc = board[piece.getLocation().getColumn()][piece
				.getLocation().getRow()];
		int currLevel = Integer.parseInt("" + currLoc.charAt(0));
		int newLevel = Integer.parseInt("" + newLoc.charAt(0));
		if (currLevel < newLevel && newLevel - currLevel > 1) {
			return false;
		}
		return true;
	}

	public boolean canPlace(Piece piece, Location location) {

		return piece.possiblePlacements().contains(location)
				&& board[location.getColumn()][location.getRow()].length() == 1
				&& Integer.parseInt(""
						+ board[location.getColumn()][location.getRow()]
								.charAt(0)) < 4;
	}

	public Player getOwner(Piece piece) {
		Player res = null;
		if (p1.getT1() == piece || p1.getT2() == piece) {
			res = p1;
		} else if (p2.getT1() == piece || p2.getT2() == piece) {
			res = p2;
		}
		return res;
	}

	public String[][] display() {
		return board;
	}

	public Player getTurn() {
		if (turnPlace == 2) {
			return p1;
		}
		return p2;
	}

	public Player getWinner() {
		return winner;
	}

	public boolean isGameOver() {
		return gameOver;
	}

	public boolean isWinner(Player player) {
		return (player == winner);
	}

	public final void setWinner(Player winner) {
		this.winner = winner;
	}

	public Piece getLastMoved() {
		return lastMoved;
	}

	public void setLastMoved(Piece lastMoved) {
		this.lastMoved = lastMoved;
	}

	public String[][] getBoard() {
		return board;
	}

	public void setBoard(String[][] board) {
		this.board = board;
	}

	public Player getP1() {
		return p1;
	}

	public void setP1(Player p1) {
		this.p1 = p1;
	}

	public Player getP2() {
		return p2;
	}

	public void setP2(Player p2) {
		this.p2 = p2;
	}

	public int getTurnMove() {
		return turnMove;
	}

	public void setTurnMove(int turnMove) {
		this.turnMove = turnMove;
	}

	public int getTurnPlace() {
		return turnPlace;
	}

	public void setTurnPlace(int turnPlace) {
		this.turnPlace = turnPlace;
	}

	public void setGameOver(boolean gameOver) {
		this.gameOver = gameOver;
	}

}
