/*
 * 우리가 제작 중인 게임은 게임 루프가 오직 1개만 존재하기 때문에
 * while 문 내에서 게임에 등장할 모든 오브젝트의 tick,render 호출해야할 의무가 있다.
 * 하지만, 게임에 등장할 객체들이 너무나 여러 클래스 걸쳐서 복잡하게 등장하기 때문에
 * 게임루프인 while 문 내에서 모든 객체들의 레퍼런스를 접근하기란 쉽지 않다.
 * 
 * 해결책?
 * 게임에 등장할 모든 객체들을 관리해주는 존재가 필요하다
 */
package game;

import java.util.ArrayList;

public class ObjectManager {
	//데이터베이스 역할을 할 존재가 필요하다, 집단으로 모을 수 있고 
	//객체만을 모을 수 있는 존재 크기 왔다리 갔다리 할 수 있는!!
	ArrayList<GameObject> list= new ArrayList<GameObject>();
	
	//객체 등록 모든 게임에 등장 할 객체는 생성 될 때 아래의 메서드를 통해
	//데이터베이스에 등록된다
	public void addObject(GameObject obj){
		list.add(obj);
	}
}
