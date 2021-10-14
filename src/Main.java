/*
 * Simple Console Snake Game
 * Author: MagicalWizzard - Simple Console Snake Game
 */

import java.util.Random;
import java.util.Scanner;
import java.util.Stack;

public class Main {
	int foodPosX, foodPosY;
	char bodyType;
	char worldType;
	int worldSize;
	int length;
	int x [] = new int [900];
	int y [] = new int [900];
	Main(int worldSize, char bodyType, char worldType){
		if(bodyType == 0) {
			this.bodyType = 'x';
		}
		if(worldType == 0) {
			this.worldType = '-';
		}
		this.worldSize = worldSize;
		this.length = 3;
		foodPosY = foodPosX = new Random().nextInt((this.worldSize-1) + 1);
		for(int i = 0; i < 900; i++) {
			x[i] = y[i] = 0;
		}
			x[0] = y[0] = x[1] = y[1] = x[2] = y[2]= new Random().nextInt((this.worldSize-1) + 1);

	}
	Main(int worldSize, int bodyType, int worldType){
		this(worldSize, (char)bodyType, (char)worldType);
	}
	
	public static void main(String [] args) {
		Scanner move = new Scanner(System.in);
		Main Snake = new Main(10, 0, 0);
		char world[][] = new char[Snake.worldSize][Snake.worldSize];
		while(true) {
			System.out.println("Score: " + Snake.length);
			logic(Snake, world);
			drawWorld(Snake, world);
			System.out.println("Your move? left=1, right=2, up=3, down=4");
			move(move, Snake);
		}   
	}
	
	public static void logic(Main Snake, char world[][]) {
		if(Snake.x[0] == world.length) {
			Snake.x[0] = 0;
		}else if(Snake.x[0] == -1) {
			Snake.x[0] = world.length-1;
		}
		if(Snake.y[0] == world.length) {
			Snake.y[0] = 0;
		}else if(Snake.y[0] == -1) {
			Snake.y[0] = world.length-1;
		}
		for(int i = 0; i < Snake.length; i++) {
			world[Snake.x[i]][Snake.y[i]] = Snake.bodyType;
		}
		if(Snake.foodPosX == Snake.x[0] && Snake.foodPosY == Snake.y[0]) {
			for(int i = 0; world[Snake.foodPosX][Snake.foodPosY] == Snake.bodyType && i <= Snake.length; i++) {
			Snake.foodPosX = Snake.foodPosY = new Random().nextInt((Snake.worldSize-1) + 1);
			}
			Snake.length++;
		}
		world[Snake.foodPosX][Snake.foodPosY] = 176;
	}
	
	public static void drawWorld(Main Snake, char world[][]) {
		for(int i = 0; i < world.length; i++) {
			for(int j = 0; j < world.length; j++) {
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
	
	public static void move(Scanner move, Main Snake) {
		switch(move.next()) {
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