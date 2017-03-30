/*
 * 자바에서 지원되는 객체 중 상수만을 모아놓은 집합을 가리켜
 * enum 객체라 한다. 이놈!!이라고 기억하자
 * enum 객체는 상수를 모아놓기 때문에 이 상수 값들 사이에 index가 존재하며 배열처럼 
 * 사용할 수도 있다.
 */
package game;

public enum ObjectId {
	Player,Enemy, Block, Bullet, Item //==>index 객체를 배열처럼!! , (상수)
	
	
}