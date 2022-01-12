import java.awt.*;
import java.awt.event.*;
import java.awt.geom.Line2D;
import java.util.*;
import java.util.List;
import javax.swing.JPanel;

public class PlayableMazePanel extends JPanel implements Runnable {

	static final int SIZE = MazePanel.SIZE;
	static final Dimension SCREEN_SIZE = new Dimension(SIZE, SIZE);

	Thread gameThread;
	Image image;
	Graphics graphics;
	Random random;
	Cell[][] maze;
	Cell current;
	Cell next;
	Cell[] neighbors;
	static final int CELL_SIZE = MazePanel.CELL_SIZE;
	Player player;
	Player treasure;
	Color c1 = new Color(0, 0, 255); // blue - Player
	Color c2 = new Color(0, 255, 0); // green - Treasure

	PlayableMazePanel(Cell[][] maze) {

		this.maze = maze;
		this.setFocusable(true);
		this.addKeyListener(new AL());
		this.setPreferredSize(SCREEN_SIZE);
		player = new Player(0, 0, CELL_SIZE, maze, c1);
		treasure = new Player(12, 12, CELL_SIZE, maze, c2);

		gameThread = new Thread(this);
		gameThread.start();
		
	}

	public void paint(Graphics g) {
		image = createImage(getWidth(), getHeight());
		graphics = image.getGraphics();
		draw(graphics);
		g.drawImage(image, 0, 0, this);
	}

	public void draw(Graphics g) {

		for (int i = 0; i < maze.length; i++)
			for (int j = 0; j < maze[0].length; j++) {
				maze[i][j].draw(g);
			}
		
		// we redraw our bottom and right borders since they get cropped a little
		Graphics2D g2 = (Graphics2D) g;
		g2.setColor(Color.black);
		g2.setStroke(new BasicStroke(4));
		g2.draw(new Line2D.Float(0, (maze.length) * CELL_SIZE, (maze.length) * CELL_SIZE, (maze.length) * CELL_SIZE)); // bottom
		g2.draw(new Line2D.Float((maze.length) * CELL_SIZE, 0, (maze.length) * CELL_SIZE, (maze.length) * CELL_SIZE)); // right
		player.draw(g2);
		treasure.draw(g2);
		
		if(player.x == treasure.x && player.y == treasure.y) { // if i win i do something
			player.x = 0; player.y = 0; // for now i just reposition the player
			player.draw(g2);
		}
		
	}

	public class AL extends KeyAdapter {

		@Override
		public void keyPressed(KeyEvent e) {
			player.keyPressed(e);
		}

		@Override
		public void keyReleased(KeyEvent e) {

		}
	}
	
	@Override
	public void run() {

		long lastTime = System.nanoTime();
		double amountOfTicks = 60.0;
		double ns = 1000000000 / amountOfTicks;
		double delta = 0;
		
		while (true) {
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;
			if (delta >= 1) {
				repaint();
				delta--;
			}
		}

	}
	
}
