/*
 *���ӿ� ������ ��� �ܾ ���� y���� ���� ����, �뷮����
 *��������� �ϱ� ������ �ᱹ ���뼺�� ���� �ڵ� ���� Ŭ���� 
 *
 *��ü����
 */
package game.word;

import java.awt.Graphics;

public class word {
	String name;// �� ��ü�� ��� �� �ܾ�
	int x;
	int y;
	int velX;// �ܾ ������ �ӵ�
	int velY;
	
	//�� �ܾ �¾ �� ���߾�� �� �ʱ�ȭ ��
	public word(String name, int x, int y) {
		this.name=name;
		this.x=x;
		this.y=y;
	}
	// �� ��ü�� �ݿ��� ������ ��ȯ�ڵ�
	public void tick() {
		y+=5;
	}

	// �� �ݿ��� �����͸� �̿��Ͽ� ȭ�鿡 �׸���
	public void render(Graphics g) {
		g.drawString(name,x,y);
	}
}
