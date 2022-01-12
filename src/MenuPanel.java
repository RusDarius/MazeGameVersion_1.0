import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;

public class MenuPanel extends JPanel {

	static final int SIZE = 400;
	static final Dimension SCREEN_SIZE = new Dimension(SIZE, SIZE);

	public static MazeFrame buildMaze;
	Image image;
	Graphics graphics;
	Thread menuThread;
	JButton b1, b2;
	static JLabel l1;
	JLabel l2;

	MenuPanel() {

		this.setFocusable(true);
		this.setPreferredSize(SCREEN_SIZE);
		this.setLayout(null);

		b1 = new JButton("Generate Maze");
		b1.setBounds(125, 100, 150, 30);
		this.add(b1);
		b1.addActionListener(new ActionListener() { 
			public void actionPerformed(ActionEvent e) {
				buildMaze = new MazeFrame(); // we generate the maze we intend to play in
				b2.setEnabled(true); // we activate the play button
			}
		});

		l1 = new JLabel("Maze not generated");
		l1.setBounds(125, 50, 150, 30);
		this.add(l1);
		
		l2 = new JLabel("Generate a maze then PLAY");
		l2.setBounds(125, 150, 200, 30);
		this.add(l2);
		
		b2 = new JButton("Play");
		b2.setBounds(125, 200, 150, 30);
		this.add(b2);
		b2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				JFrame gameFrame = new JFrame();
				PlayableMazePanel pP = new PlayableMazePanel(MazePanel.maze);
				gameFrame.add(pP);
				gameFrame.setTitle("Maze 2.0");
				gameFrame.setResizable(false);
				gameFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				gameFrame.pack();
				gameFrame.setVisible(true);
				gameFrame.setLocationRelativeTo(null);
				
			}
		});
		b2.setEnabled(false);

	}

	public static void closeFrame() { // function to close the buildMaze frame
		buildMaze.setVisible(false);
		buildMaze.dispose();
		l1.setText("Maze generated!");
		l1.setForeground(new Color(0, 102, 0));
	}

}
