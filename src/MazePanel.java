import java.awt.*;
import java.awt.event.*;
import java.awt.geom.Line2D;
import java.util.*;
import java.util.List;

import javax.swing.*;

public class MazePanel extends JPanel implements Runnable {

	static final int SIZE = 650;
	static final Dimension SCREEN_SIZE = new Dimension(SIZE, SIZE);

	Thread gameThread;
	Image image;
	Graphics graphics;
	Random random;
	static Cell[][] maze = new Cell[13][13];
	Cell current;
	Cell next;
	Cell[] neighbors;
	static final int CELL_SIZE = 50;
	Stack<Cell> stack = new Stack<Cell>();

	MazePanel() {

		this.setFocusable(true);
		this.setPreferredSize(SCREEN_SIZE);
		startBuilder();

		gameThread = new Thread(this);
		gameThread.start();
		
	}

	public static Cell[][] getMaze() {
		return maze;
	}

	public void paint(Graphics g) {
		image = createImage(getWidth(), getHeight());
		graphics = image.getGraphics();
		draw(graphics);
		g.drawImage(image, 0, 0, this);
	}

	public void startBuilder() {
		for (int i = 0; i < maze.length; i++)
			for (int j = 0; j < maze[0].length; j++)
				maze[i][j] = new Cell(i, j, CELL_SIZE);
		current = maze[0][0];
	}

	public void draw(Graphics g) {

		current.visited = true;

		// STEP 1 we pick a random naighbor if one exists
		next = current.checkNeighbors();

		if (next != null) {
			// we visit the neighbor
			next.visited = true;

			// STEP 2 we add the cell to the stack
			stack.push(current);

			// STEP 3 we remove the walls between current and next
			removeWalls(next);

			// STEP 4 the neighbor becomes our new current cell
			current = next;
		}
		if (next == null)
			if (stack.size() > 0) {
				// we pop the current dead END and we proceed with the previous element
				Cell aux = stack.pop();
				current = aux;
			}
		current.isCurrent = 1;
		for (int i = 0; i < maze.length; i++)
			for (int j = 0; j < maze[0].length; j++) {
				maze[i][j].draw(g);
				maze[i][j].isCurrent = 0;
			}

		// we redraw our bottom and right borders since they get cropped a little
		Graphics2D g2 = (Graphics2D) g;
		g2.setColor(Color.black);
		g2.setStroke(new BasicStroke(4));
		g2.draw(new Line2D.Float(0, (maze.length) * CELL_SIZE, (maze.length) * CELL_SIZE, (maze.length) * CELL_SIZE)); // bottom
		g2.draw(new Line2D.Float((maze.length) * CELL_SIZE, 0, (maze.length) * CELL_SIZE, (maze.length) * CELL_SIZE)); // right
	}

	private void removeWalls(Cell next) {

		if ((current.x / CELL_SIZE) - (next.x / CELL_SIZE) == 1) { // iff diff == 1 next is to the left
			current.walls[1] = 0;
			next.walls[2] = 0;
		}

		if ((current.x / CELL_SIZE) - (next.x / CELL_SIZE) == -1) { // iff diff == -1 next is to the right
			current.walls[2] = 0;
			next.walls[1] = 0;
		}

		if ((current.y / CELL_SIZE) - (next.y / CELL_SIZE) == 1) { // iff diff == 1 next is UP
			current.walls[0] = 0;
			next.walls[3] = 0;
		}

		if ((current.y / CELL_SIZE) - (next.y / CELL_SIZE) == -1) { // iff diff == 1 next is DOWN
			current.walls[3] = 0;
			next.walls[0] = 0;
		}
	}

	public static boolean checkIfOver() { // verify if the maze has finished generating
		for (int i = 0; i < maze.length; i++)
			for (int j = 0; j < maze[0].length; j++)
				if(maze[i][j].visited == false) {
					return false;
				}
		return true;
	}
	
	@Override
	public void run() {

		long lastTime = System.nanoTime();
		double amountOfTicks = 60.0;
		double ns = 1000000000 / amountOfTicks;
		double delta = 0;
		
		while (checkIfOver() == false) {
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;
			if (delta >= 1) {
				repaint();
				delta--;
			}
		}
		
		if(checkIfOver() == true) { // if the maze has generated we close the frame
			MenuPanel.closeFrame(); // method to close the MazeFrame
		}

	}

}
