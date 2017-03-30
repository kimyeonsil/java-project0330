/*
 * �÷��̾��� �������� ��������
 */
package game;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class KeyBoard extends KeyAdapter {
	Player player;

	public KeyBoard(Player player) {
		this.player = player;
	}

	// Ű���� ������
	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();

		switch (key) {// �Ұ�ȣ ���� ���� ���� ������ ��������

		case KeyEvent.VK_LEFT:
			System.out.println("left");
			player.velX = -3;
			break;
		case KeyEvent.VK_RIGHT:
			player.velX = 3;
			break;
		case KeyEvent.VK_UP:
			player.velY = -3;
			break;
		case KeyEvent.VK_DOWN:
			player.velY = 3;
			break;
		case KeyEvent.VK_SPACE:
			//�Ѿ˻���
			player.fire();
			break;

		}
	}

	// Ű���� ����
	public void keyReleased(KeyEvent e) {
		int key = e.getKeyCode();

		switch (key) {// �Ұ�ȣ ���� ���� ���� ������ ��������

		case KeyEvent.VK_LEFT:
			player.velX = 0;
			break;
		case KeyEvent.VK_RIGHT:
			player.velX = 0;
			break;
		case KeyEvent.VK_UP:
			player.velY = 0;
			break;
		case KeyEvent.VK_DOWN:
			player.velY = 0;
			break;
		}
	}
}
