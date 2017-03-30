/*
 * 모든 게임은 이 패널 안에서 그려질 예정임
 * 아무리 게임의 장면이 다양하더라도, 패널은 오직 1개만 사용된다.
 * 
 * 모든 오브젝트는 결국 이 패널에 그려져야 하므로, 이 패널의 paint 메서드의 인수로
 * 전달 되는 graphics 객체를 게임에 등장 할 모든 오브젝트가 전달 받아야한다.
 */
package game;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.util.Random;

import javax.swing.JPanel;

public class GamePanel extends JPanel implements Runnable {
	public static final int WIDTH = 400;
	public static final int HEIGHT = 300;
	public static final int SCALE = 2;// 화면을 늘리고 싶을 때 스케일
	boolean flag=true;//게임 가동 여부를 결정하는 변수
	Thread thread;//게임 운영 쓰레드
	Player player;//주인공 멤버변수로 빼기
	ObjectManager objectmanager;//객체 명단 관리
	
	public GamePanel() {
		thread=new Thread(this);
		thread.start();
		
		init();
		
		setBackground(Color.BLACK);
		// 크기지정
		setPreferredSize(new Dimension(WIDTH * SCALE, HEIGHT * SCALE));
	}
	
	
	public void init(){
		//명단 관리자 생성
		objectmanager=new ObjectManager();
		
		//주인공 등장 시키기
		player=new Player(objectmanager,ObjectId.Player,100,200,50,50);
		objectmanager.addObject(player);//1명 추가
		
		//적군 등장
		Random r=new Random();
		for(int i=0;i<10;i++){
			int x=r.nextInt((HEIGHT*SCALE-50)-50+1)+50;
			int y=r.nextInt((WIDTH*SCALE+500)-(WIDTH*SCALE)+1)+50;
			
		Enemy enemy=new Enemy(objectmanager, ObjectId.Enemy,x, y, 30, 30);
		objectmanager.addObject(enemy);
		}
		//패널과 키보드 리스너 연결
		this.addKeyListener(new KeyBoard(player));
	}	

	protected void paintComponent(Graphics g) {
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, WIDTH * SCALE, HEIGHT * SCALE);
		
		for(int i=0;i<objectmanager.list.size();i++){
			GameObject obj=objectmanager.list.get(i);
			obj.render(g);
		}
	}

	public void run() {
		while(flag){
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			//오브젝트 매니저에 등록 된 모든 객체를 대상으로 tick()을 호출하자
			for(int i=0;i<objectmanager.list.size();i++){
				GameObject obj=objectmanager.list.get(i);
				obj.tick();
			}
			//player.tick();
			repaint();//paintComponent를 간접호출
			//총알의 tick, render
			//적국의 tick, render
			//아이템 tick,render...
		}
	}
}
