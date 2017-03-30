/*
 * 게임에 등장할 모든 객체의 최상위 클래스를 정의한다
 * 왜? 상속을 이용하면 코드 중복을 방지할 수 있고, 최상위 클래스
 * 자료형으로 자식들을 가리킬수도 있으므로, 코드가 유연해진다.
 */
package game;

import java.awt.Graphics;
import java.awt.Rectangle;

abstract public class GameObject {
	ObjectManager objectManager;
	ObjectId id;
	int x;
	int y;
	int width;
	int height;
	int velX;
	int velY;
	Rectangle rect;//교차 테스트에 써먹을 사각형 객체
	
	public GameObject(ObjectManager objectManager,ObjectId id, int x, int y, int width, int height) {
		this.objectManager=objectManager;
		this.id=id;
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		rect=new Rectangle(x, y, width, height);
	}

	abstract public void tick();// 총알이될지 뭐가 될지 모르겠다! 그러므로 추상으로 간다

	abstract public void render(Graphics g);
}
