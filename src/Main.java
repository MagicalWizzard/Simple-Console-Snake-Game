/*
 * Simple Console Snake Game
 * Author: MagicalWizzard - Simple Console Snake Game
 */

import java.util.Random;
import java.util.Scanner;
import java.util.Stack;

public class Main {
	int foodPosX, foodPosY; //Exactly what you think it is, position of food on the x annd y axis
	char bodyType; //what char is the body
	char worldType; // what char is the world/board
	int worldSize; // size of the world a*a
	int length; //length of the snake
	int x [] = new int [900]; // we have two arrays they are used for storing previous position that the snake was in
	int y [] = new int [900]; // they are then used for printing out the snake body on the board
	Main(int worldSize, char bodyType, char worldType){
		if(bodyType == 0) {
			this.bodyType = 'x'; // set bodytype x if 0
		}
		if(worldType == 0) {
			this.worldType = '-'; // set worldtype - if 0
		}
		this.worldSize = worldSize;
		this.length = 3;
		foodPosY = foodPosX = new Random().nextInt((this.worldSize-1) + 1); // generate a random position for the food on the x and y axis
		for(int i = 0; i < 900; i++) { //null out the x and y
			x[i] = y[i] = 0;
		}
			x[0] = y[0] = x[1] = y[1] = x[2] = y[2]= new Random().nextInt((this.worldSize-1) + 1); // generate random position of the snake

	}
	Main(int worldSize, int bodyType, int worldType){ // constructor for the snake, set wordlSize, bodyType, worldType 
		this(worldSize, (char)bodyType, (char)worldType); // all in chars
	}
	
	public static void main(String [] args) {
		Scanner move = new Scanner(System.in); // this snake is a little bad so we use scanner for the movement
		Main Snake = new Main(10, 0, 0); // we create a world of 10x10 size
		char world[][] = new char[Snake.worldSize][Snake.worldSize]; // set the world array max size
		while(true) { // game loop
			System.out.println("Score: " + Snake.length);
			logic(Snake, world); // logic method where all the snake game logic is
			drawWorld(Snake, world); // just draw the world
			System.out.println("Your move? left=1, right=2, up=3, down=4"); // print out the controlls
			move(move, Snake); // and move the snake method
		}   
	}
	
	public static void logic(Main Snake, char world[][]) {
		if(Snake.x[0] == world.length) { // make the x pos of the snake go around the board
			Snake.x[0] = 0;
		}else if(Snake.x[0] == -1) { // make the x pos of the snake go around the board the other side the minus side
			Snake.x[0] = world.length-1;
		}
		if(Snake.y[0] == world.length) { // the same as for the x pos, but for y
			Snake.y[0] = 0;
		}else if(Snake.y[0] == -1) { // the same as for the x minus pos, but for y
			Snake.y[0] = world.length-1;
		}
		for(int i = 0; i < Snake.length; i++) { // put the snake body on the board
			world[Snake.x[i]][Snake.y[i]] = Snake.bodyType;
		}
		if(Snake.foodPosX == Snake.x[0] && Snake.foodPosY == Snake.y[0]) { // if the head of the snake hits apple the score gets bigger and also the snake is
			for(int i = 0; world[Snake.foodPosX][Snake.foodPosY] == Snake.bodyType && i <= Snake.length; i++) {
			Snake.foodPosX = Snake.foodPosY = new Random().nextInt((Snake.worldSize-1) + 1); // when the apple hits the snake body, the apple gets new position
			}
			Snake.length++; // make the snake bigger
		}
		world[Snake.foodPosX][Snake.foodPosY] = 176; // just set the char of the apple
	}
	
	public static void drawWorld(Main Snake, char world[][]) { // draw the world out to the console
		for(int i = 0; i < world.length; i++) {
			for(int j = 0; j < world[0].length; j++) {
				System.out.print(world[i][j]);
				world[i][j] = Snake.worldType;
			}	
			System.out.print('\n');
		}	
	}
	
	public static void shiftXY(Main Snake) {// shifts index values of two arrays x[], y[] to the right
		for(int i = Snake.length-1; i > 0; i--) {
			Snake.y[i] = Snake.y[i-1]; 
			Snake.x[i] = Snake.x[i-1];
		}
	}
	
	public static void move(Scanner move, Main Snake) { // y and x positions start from the left upper corner of the screen or the array and move the snake according to that
		switch(move.next()) { // we need to shiftxy position to the right because index 0 is the head and to make a room for new snake body coordinates
		case "a":
			shiftXY(Snake);
			Snake.y[0] -= 1;
		break;
		case "d":
			shiftXY(Snake);
			Snake.y[0] += 1;
		break;
		case "w":
			shiftXY(Snake);
			Snake.x[0] -= 1;
		break;
		case "s":
			shiftXY(Snake);
			Snake.x[0] += 1;
		break;
		}
	}
}
