package game;

import java.awt.FlowLayout;

import javax.swing.JFrame;

public class GameWindow extends JFrame {
	GamePanel gamePanel;

	public GameWindow() {
		
		gamePanel = new GamePanel();
		add(gamePanel);
		gamePanel.setFocusable(true);
		pack();
		setVisible(true);
		setLocationRelativeTo(null);// ȭ�� �߾�����
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}

	public static void main(String[] args) {
		new GameWindow();
	}
}
