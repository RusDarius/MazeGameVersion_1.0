import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;

public class MenuFrame extends JFrame{

	MenuPanel panel;
	
	MenuFrame() {

		panel = new MenuPanel();
		this.add(panel);
		//this.setBackground(Color.YELLOW);
		this.setTitle("MazeMenu");
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.pack();
		this.setVisible(true);
		this.setLocationRelativeTo(null);
		
	}
	
}
