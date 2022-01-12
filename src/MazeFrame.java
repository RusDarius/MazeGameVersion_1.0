import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;

public class MazeFrame extends JFrame {

	MazePanel panel;

	MazeFrame() {

		panel = new MazePanel();

		this.add(panel);
		this.setTitle("Maze 2.0");
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.pack();
		this.setVisible(true);
		this.setLocationRelativeTo(null);

	}

}
