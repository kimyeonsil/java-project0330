/*
 *게임에 등장할 대상 단어가 각각 y축을 따로 갖고, 대량으로
 *만들어져야 하기 때문에 결국 재사용성을 위한 코드 집합 클래스 
 *
 *객체지향
 */
package game.word;

import java.awt.Graphics;

public class word {
	String name;// 이 객체가 담게 될 단어
	int x;
	int y;
	int velX;// 단어가 움직일 속도
	int velY;
	
	//이 단어가 태어날 때 갖추어야 할 초기화 값
	public word(String name, int x, int y) {
		this.name=name;
		this.x=x;
		this.y=y;
	}
	// 이 객체에 반영될 데이터 변환코드
	public void tick() {
		y+=5;
	}

	// 그 반영된 데이터를 이용하여 화면에 그리기
	public void render(Graphics g) {
		g.drawString(name,x,y);
	}
}
