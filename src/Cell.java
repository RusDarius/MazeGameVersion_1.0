import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.util.List;
import javax.swing.*;
import java.awt.geom.*;

public class Cell extends Rectangle {

	int x; // row
	int y; // column
	int size; // lenght / width of cell
	int[] walls = { 1, 1, 1, 1 }; // TOP LEFT RIGHT BOTTOM --- the walls exist at first
	boolean visited = false;
	int isCurrent = 0;

	Cell(int x, int y, int size) {
		this.x = x * size;
		this.y = y * size;
		this.size = size;
	}

	public void draw(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		g2.setColor(Color.black);
		g2.setStroke(new BasicStroke(4));
		if (walls[0] == 1)
			g2.draw(new Line2D.Float(x, y, x + size, y)); // top
		if (walls[1] == 1)
			g2.draw(new Line2D.Float(x, y, x, y + size)); // left
		if (walls[2] == 1)
			g2.draw(new Line2D.Float(x + size, y, x + size, y + size)); // right
		if (walls[3] == 1)
			g2.draw(new Line2D.Float(x, y + size, x + size, y + size)); // bottom
		if (visited == true) {
			g2.setColor(Color.yellow);
			g2.fillRect(x + 2, y + 2, size, size);
		}
		if(isCurrent == 1) {
			g2.setColor(Color.red);
			g2.fillRect(x + 2, y + 2, size, size);
		}
	}

	public Cell checkNeighbors() {

		Cell[][] maze = MazePanel.getMaze();
		int i = x / size;
		int j = y / size;
		List<Cell> neighbors = new ArrayList<Cell>();

		if (j != 0)
			if (!maze[i][j - 1].visited) {
				neighbors.add(maze[i][j - 1]);
			}
		if (i != maze.length - 1)
			if (!maze[i + 1][j].visited) {
				neighbors.add(maze[i + 1][j]);
			}
		if (j != maze.length - 1)
			if (!maze[i][j + 1].visited) {
				neighbors.add(maze[i][j + 1]);
			}
		if (i != 0)
			if (!maze[i - 1][j].visited) {
				neighbors.add(maze[i - 1][j]);
			}

		if (neighbors.size() > 0) {
			Random r = new Random();
			return neighbors.get(r.nextInt(neighbors.size() - 0) + 0);
		} else {
			return null;
		}
	}

}
