/*
 * �� Ŭ������ sun���� ��ü ������ UI ������Ʈ�� �ƴϱ� ������
 * ȭ�鿡 �׷��� �� ����. ���� jpanel�� �׷������� jpanel�� 
 * graphics�� ���۷����� �� ��ü�� �����ؾ��Ѵ�.
 */
package game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

public class Player extends GameObject{// ���ΰ�
	
	public Player (ObjectManager objectManager,ObjectId id,int x, int y, int width, int height) {
		super(objectManager,id,x,y,width,height);
	}
	//�Ѿ� �߻� ������ �����Ѵ�.
	public void fire(){
		Bullet bullet=new Bullet(objectManager,ObjectId.Bullet,x, y, 10, 10);
		objectManager.addObject(bullet);
	}
	// x,y,width,height ���� ������ ���� ��ȭ�� �����ϱ� ���� �޼���
	// ��� ���� - ��� ��ȭ
	public void tick() {
		System.out.println(x);

		x += velX;
		y += velY;
		
		//�簢���� ���� ����ٴϰ� ���� ����ȭ
		rect.setBounds(x, y, width, height);
	}

	// ��ȭ�� ���� ȭ�鿡 �׷����� �� �޼���
	public void render(Graphics g) {
		g.setColor(Color.CYAN);// ����Ʈ �� �ٲٱ�
		//g.drawRect(x, y, width, height);
		Graphics2D g2=(Graphics2D)g;//�׷��� ���׷��̵�
		g2.draw(rect);
		// System.out.println("render()");
	}
}