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
		int numberOfBlanks = (length * width * height)/5;
		
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

		// connect rooms
		for (int i = 0; i < _cube.length; i++) {
			for (int j = 0; j < _cube[i].length; j++) {
				for (int k = 0; k < _cube[i][j].length; k++) {
					Room r = _cube[i][j][k];
					
					//not all spaces have rooms
					if(r == null){
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
		
		while(r == null){
			int x = rand.nextInt(_cube.length);
			int y = rand.nextInt(_cube[0].length);
			int z = rand.nextInt(_cube[0][0].length);
			
			r = _cube[x][y][z];
		}
		
		r.placePlayer(p);
		
	}

	public void shiftRooms() {
		Direction[] dirs = {Direction.north,Direction.south,Direction.west,Direction.east,Direction.up,Direction.down};
		ArrayList<Direction> dir = new ArrayList<>();
	
	}
	/*
	 * This is only for testing!!
	 */
	public Room[][][] getCube(){
		return _cube;
	}

}
