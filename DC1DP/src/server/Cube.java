package server;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Random;

import server.Room.Direction;

public class Cube {

	private Room[][][] _cube;

	public Cube(int length, int width, int height) {
		int numberOfBlanks = (length * width * height) / 5;

		System.out.println("num of blanks: " + numberOfBlanks);

		int blanksMade = 0;

		_cube = new Room[length][width][height];

		String[] colors = { "blue", "green", "yellow", "red" };

		Random rand = new Random();

		int roomsMade = 0;

		// generate rooms
		for (int i = 0; i < _cube.length; i++) {
			for (int j = 0; j < _cube[i].length; j++) {
				for (int k = 0; k < _cube[i][j].length; k++) {
					int blankChance = rand.nextInt(100);

					if (blankChance < 15 && blanksMade < numberOfBlanks) {
						_cube[i][j][k] = null;
					} else {

						Room r = new Room(colors[rand.nextInt(colors.length)]);
						// Room r = new Room("["+i+","+j+","+k+"]");
						_cube[i][j][k] = r;

						roomsMade++;
					}
				}
			}
		}

		System.out.println("roomsMade: " + roomsMade);

		connectRooms();
	}

	private void connectRooms() {
		// connect rooms
		for (int i = 0; i < _cube.length; i++) {
			for (int j = 0; j < _cube[i].length; j++) {
				for (int k = 0; k < _cube[i][j].length; k++) {
					Room r = _cube[i][j][k];

					// not all spaces have rooms
					if (r == null) {
						continue;
					}

					// north ([i][j])
					try {
						r.setRoom(Direction.north, _cube[i][j + 1][k]);
					} catch (IndexOutOfBoundsException e) {
						r.setRoom(Direction.north, null);
					}

					// south
					try {
						r.setRoom(Direction.south, _cube[i][j - 1][k]);
					} catch (IndexOutOfBoundsException e) {
						r.setRoom(Direction.south, null);

					}

					// east
					try {
						r.setRoom(Direction.east, _cube[i + 1][j][k]);
					} catch (IndexOutOfBoundsException e) {
						r.setRoom(Direction.east, null);
					}

					// west
					try {
						r.setRoom(Direction.west, _cube[i - 1][j][k]);
					} catch (IndexOutOfBoundsException e) {
						r.setRoom(Direction.west, null);
					}

					// up
					try {
						r.setRoom(Direction.up, _cube[i][j][k + 1]);
					} catch (IndexOutOfBoundsException e) {
						r.setRoom(Direction.up, null);
					}

					// down
					try {
						r.setRoom(Direction.down, _cube[i][j][k - 1]);
					} catch (IndexOutOfBoundsException e) {
						r.setRoom(Direction.down, null);
					}
				}
			}
		}
	}

	public void placePlayer(Player p) {
		Room r = null;
		Random rand = new Random();

		while (r == null) {
			int x = rand.nextInt(_cube.length);
			int y = rand.nextInt(_cube[0].length);
			int z = rand.nextInt(_cube[0][0].length);

			r = _cube[x][y][z];
		}

		r.placePlayer(p);

	}

	public void shiftRooms() {
		// populate a list of directions. It will be shuffled for each room to
		// determine which way to attempt to shift.
		// A BFS variant may be a better solution to handle shifting of rooms.
		// For now this will do.
		Direction[] dirs = { Direction.north, Direction.south, Direction.west,
				Direction.east, Direction.up, Direction.down };
		ArrayList<Direction> dir = new ArrayList<>();

		for (Direction d : dirs) {
			dir.add(d);
		}
		
		Random r = new Random();

		for (int x = 0; x < _cube.length; x++) {
			for (int y = 0; y < _cube[0].length; y++) {
				for (int z = 0; z < _cube[0][0].length; z++) {
					if (_cube[x][y][z] != null && r.nextInt(6) == 3) {
						attemptShift(_cube[x][y][z], dir, x, y, z);
					}
				}
			}
		}
		
		connectRooms();
	}

	private void attemptShift(Room r, ArrayList<Direction> dirs, int x, int y,
			int z) {
		Collections.shuffle(dirs);

		for (int i = 0; i < dirs.size(); i++) {
			Direction d = dirs.get(i);

			try {
				switch (d) {
				case down:
					if (_cube[x][y][z - 1] == null) {
						_cube[x][y][z - 1] = r;
						_cube[x][y][z] = null;
						
					}
					break;
				case east:
					if (_cube[x + 1][y][z] == null) {
						_cube[x + 1][y][z] = r;
						_cube[x][y][z] = null;
					}
					break;
				case north:
					if (_cube[x][y + 1][z] == null) {
						_cube[x][y + 1][z] = r;
						_cube[x][y][z] = null;
					}
					break;
				case south:
					if (_cube[x][y - 1][z] == null) {
						_cube[x][y - 1][z] = r;
						_cube[x][y][z] = null;
					}
					break;
				case up:
					if (_cube[x][y][z + 1] == null) {
						_cube[x][y][z + 1] = r;
						_cube[x][y][z] = null;
					}
					break;
				case west:
					if (_cube[x - 1][y][z] == null) {
						_cube[x - 1][y][z] = r;
						_cube[x][y][z] = null;
					}
					break;
				default:
					break;
				}

				for (Player player : r.getPlayersInRoom()) {
					player.alert("You hear a loud rumbling and the ground seems to shake!");
				}

				break;
			} catch (IndexOutOfBoundsException e) {
				continue; // this is a lazy approach to checking indexes
			}

		}

	}

	/*
	 * This is only for testing!!
	 */
	public Room[][][] getCube() {
		return _cube;
	}

}
