package server;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

import server.Room.Direction;

public class Cube {
	/*
	 * A note on directions:
	 * Directions will be defined as followed for a 3 dimensional array
	 * 
	 * North: [x][y + 1][z]
	 * South: [x][y - 1][z]
	 * East:  [x - 1][y][z]
	 * West:  [x + 1][y][z]
	 * Up:    [x][y][z + 1]
	 * Down:  [x][y][z - 1]
	 * 
	 * This leads to the following coordinates:
	 * Someone at [0][0][0] is on the bottom layer, southeast corner
	 * because you can not go any further south or east.
	 * 
	 * Someone at [x][y][z] are at the top layer, northwest corner
	 * because you can not go any further north or west.
	 * 
	 * It is easiest to picture if you picture a layer of squares.
	 *  _______
	 * |   |   | ^ N
	 * |___|___| |
	 * |   |   | |
	 * |___|___| |
	 * x------>  y S
	 * E       W 
	 * 
	 * Z then represents which layer you're on.
	 */

	private Room[][][] _cube;

	private ArrayList<Room.Direction> dir;
	
	final Direction[] dirs = { Direction.north, Direction.south, Direction.west,
			Direction.east, Direction.up, Direction.down };

	/**
	 * Generates a new "cube" with the specified parameters.
	 * 
	 * @param length
	 *            of the cube
	 * @param width
	 *            of the cube
	 * @param height
	 *            of the cube
	 */
	public Cube(int length, int width, int height) {
		
		// populate a list of directions. It will be shuffled for each room to
		// determine which way to attempt to shift.
		// A BFS variant may be a better solution to handle shifting of rooms.
		// For now this will do.
		dir = new ArrayList<>();
		
		for(Direction d : dirs){
			dir.add(d);
		}

		// some constants

		// percent chance of a blank room
		int BLANK_CHANCE = 15;

		// specify the maximum number of empty spots to make
		// If length * width * height = 125 then no more than 25 empty spaces
		// will be created
		int numberOfBlanks = (length * width * height) / 5;

		int blanksMade = 0;

		_cube = new Room[length][width][height];

		// A place holder for descriptions. This designates what a room
		// "looks like"
		String[] colors = { "blue", "green", "yellow", "red" };

		Random rand = new Random();

		// keep track of how many rooms are actually created
		int roomsMade = 0;

		// generate rooms
		for (int i = 0; i < _cube.length; i++) {
			for (int j = 0; j < _cube[i].length; j++) {
				for (int k = 0; k < _cube[i][j].length; k++) {
					// "roll" for a blank room. The
					int blankChance = rand.nextInt(100);

					if (blankChance < BLANK_CHANCE
							&& blanksMade < numberOfBlanks) {
						// no room here
						_cube[i][j][k] = null;
					} else {

						// pick a color and make a room
						Room r = new Room(colors[rand.nextInt(colors.length)]);
						_cube[i][j][k] = r;

						roomsMade++;
					}
				}
			}
		}

		System.out.println("roomsMade: " + roomsMade);

		connectRooms();
	}

	/**
	 * This method will connect each room together into an undirected graph.
	 */
	private void connectRooms() {
		// connect rooms
		for (int i = 0; i < _cube.length; i++) {
			for (int j = 0; j < _cube[i].length; j++) {
				for (int k = 0; k < _cube[i][j].length; k++) {
					Room r = _cube[i][j][k];

					// not all spaces have rooms
					// if a room was not found here move on to the next one
					if (r == null) {
						continue;
					}

					// north ([j + 1])
					try {
						r.setRoom(Direction.north, _cube[i][j + 1][k]);
					} catch (IndexOutOfBoundsException e) {
						r.setRoom(Direction.north, null);
					}

					// south ([j-1]
					try {
						r.setRoom(Direction.south, _cube[i][j - 1][k]);
					} catch (IndexOutOfBoundsException e) {
						r.setRoom(Direction.south, null);

					}

					// east [i + 1]
					try {
						r.setRoom(Direction.east, _cube[i + 1][j][k]);
					} catch (IndexOutOfBoundsException e) {
						r.setRoom(Direction.east, null);
					}

					// west [i - 1]
					try {
						r.setRoom(Direction.west, _cube[i - 1][j][k]);
					} catch (IndexOutOfBoundsException e) {
						r.setRoom(Direction.west, null);
					}

					// up [k + 1]
					try {
						r.setRoom(Direction.up, _cube[i][j][k + 1]);
					} catch (IndexOutOfBoundsException e) {
						r.setRoom(Direction.up, null);
					}

					// down [k + 1]
					try {
						r.setRoom(Direction.down, _cube[i][j][k - 1]);
					} catch (IndexOutOfBoundsException e) {
						r.setRoom(Direction.down, null);
					}
				}
			}
		}
	}

	/**
	 * Places a player randomly into the cube.
	 * 
	 * @param p
	 *            the player to be placed.
	 */
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

	/**
	 * Shifts applicable rooms in the cube. If a room has an space adjacent to
	 * it then it has a chance of moving into that spot. All 6 directions are
	 * tried in a random order. This ensures that there is not a predictable
	 * pattern.
	 * 
	 */
	public void shiftRooms() {

		Random r = new Random();

		int counter = 0;

		// this traverses the cube starting at the "bottom plane" up to the top
		// plane of the cube.
		// TODO: suggestion: randomize the the order of traversal to introduce
		// another level of randomness
		for (int x = 0; x < _cube.length; x++) {
			for (int y = 0; y < _cube[0].length; y++) {
				for (int z = 0; z < _cube[0][0].length; z++) {

					// roll to determine whether or not to attempt to shift this
					// room
					// TODO: Remove magic number and make it a percent chance &
					// a parameter
					if (_cube[x][y][z] != null && r.nextInt(6) == 3) {
						if (attemptShift(_cube[x][y][z], dir, x, y, z)) {
							counter++;
						}
					}
				}
			}
		}

		// once all rooms have moved the doors need to be reconnected
		connectRooms();

		//echo how many rooms have moved
		System.out.println(counter + " rooms have moved!");
	}

	private boolean attemptShift(Room r, ArrayList<Direction> dirs, int x,
			int y, int z) {
		// randomize the directions
		Collections.shuffle(dirs);

		//attempt to move the room. If it is out the bounds of cube then simply continue
		//see above at the top of the class for how directions are defined
		for (int i = 0; i < dirs.size(); i++) {
			Direction d = dirs.get(i);

			try {
				switch (d) {
				case down:
					if (_cube[x][y][z - 1] == null) {
						_cube[x][y][z - 1] = r;
						_cube[x][y][z] = null;

					} else {
						continue;
					}
					break;
				case east:
					if (_cube[x + 1][y][z] == null) {
						_cube[x + 1][y][z] = r;
						_cube[x][y][z] = null;
					} else {
						continue;
					}
					break;
				case north:
					if (_cube[x][y + 1][z] == null) {
						_cube[x][y + 1][z] = r;
						_cube[x][y][z] = null;
					} else {
						continue;
					}
					break;
				case south:
					if (_cube[x][y - 1][z] == null) {
						_cube[x][y - 1][z] = r;
						_cube[x][y][z] = null;
					} else {
						continue;
					}
					break;
				case up:
					if (_cube[x][y][z + 1] == null) {
						_cube[x][y][z + 1] = r;
						_cube[x][y][z] = null;
					} else {
						continue;
					}
					break;
				case west:
					if (_cube[x - 1][y][z] == null) {
						_cube[x - 1][y][z] = r;
						_cube[x][y][z] = null;
					} else {
						continue;
					}
					break;
				default:
					break;
				}

				for (Player player : r.getPlayersInRoom()) {
					player.alert("You hear a loud rumbling and the ground seems to shake!");
				}

				return true;
			} catch (IndexOutOfBoundsException e) {
				continue; // this is a lazy approach to checking indexes
			}

		}

		return false;
	}

	/**
	 * Retrieve the cube. It can not be modified from outside of this method.
	 * @return
	 */
	public final Room[][][] getCube() {
		return _cube;
	}

}
