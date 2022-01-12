import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;

public class Player extends Rectangle {

	int x;
	int y;
	int size;
	Cell[][] maze;
	Color c;

	Player(int x, int y, int size, Cell[][] maze, Color c) {

		super(x + size + 10, y * size + 10, size - 20, size - 20);
		this.x = x;
		this.y = y;
		this.size = size;
		this.maze = maze;
		this.c = c;

	}

	public void keyPressed(KeyEvent e) {

		if (e.getKeyCode() == KeyEvent.VK_W) {
			if (y > 0 && maze[x][y].walls[0] == 0) {
				move(3);
			}
		}
		if (e.getKeyCode() == KeyEvent.VK_S) {
			if (y < maze.length && maze[x][y].walls[3] == 0) {
				move(4);
			}
		}
		if (e.getKeyCode() == KeyEvent.VK_A) {
			if (x > 0 && maze[x][y].walls[1] == 0) {
				move(1);
			}
		}
		if (e.getKeyCode() == KeyEvent.VK_D) {
			if (x < maze.length && maze[x][y].walls[2] == 0) {
				move(2);
			}
		}
		
	}

	public void move(int inDir) {
		switch (inDir) {
		case 1: // stanga = 1
			x--;
			break;
		case 2: // dreapta = 2
			x++;
			break;
		case 3: // sus = 3
			y--;
			break;
		case 4: // jos = 4
			y++;
			break;
		}
	}

	public void draw(Graphics g) {

		g.setColor(c);
		g.fillRect(x * size + 10, y * size + 10, width, height);

	}

}
