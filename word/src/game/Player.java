/*
 * 이 클래스는 sun에서 자체 제작한 UI 컴포넌트가 아니기 때문에
 * 화면에 그려질 수 없다. 따라서 jpanel에 그려지려면 jpanel의 
 * graphics의 레퍼런스를 이 객체가 보유해야한다.
 */
package game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

public class Player extends GameObject{// 주인공
	
	public Player (ObjectManager objectManager,ObjectId id,int x, int y, int width, int height) {
		super(objectManager,id,x,y,width,height);
	}
	//총알 발사 행위를 정의한다.
	public void fire(){
		Bullet bullet=new Bullet(objectManager,ObjectId.Bullet,x, y, 10, 10);
		objectManager.addObject(bullet);
	}
	// x,y,width,height 등의 물리량 등의 변화를 제어하기 위한 메서드
	// 사람 비유 - 운동량 변화
	public void tick() {
		System.out.println(x);

		x += velX;
		y += velY;
		
		//사각형이 나를 따라다니게 값의 동기화
		rect.setBounds(x, y, width, height);
	}

	// 변화된 값을 화면에 그려지게 할 메서드
	public void render(Graphics g) {
		g.setColor(Color.CYAN);// 페인트 색 바꾸기
		//g.drawRect(x, y, width, height);
		Graphics2D g2=(Graphics2D)g;//그래픽 업그레이드
		g2.draw(rect);
		// System.out.println("render()");
	}
}
