package game.word;

import java.awt.BorderLayout;
import java.awt.Choice;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class GamePanel extends JPanel implements ItemListener, ActionListener, Runnable {
	GameWindow gameWindow;

	JPanel p_west; // 왼쪽 컨트롤 영역
	JPanel p_center; // 단어 그래픽 처리 영역

	JLabel la_user; // 게임 로그인 유저명
	JLabel la_score; // 게임 점수
	Choice choice; // 단어 선택 드랍박스
	JTextField t_input; // 게임 입력창
	JButton bt_start, bt_pause, bt_stop; // 게임 시작 버튼
	String res = "C:/java__workspace2/project0329/src/res/";

	FileInputStream fis;
	InputStreamReader reader;// 파일 대상 문자스트림
	BufferedReader buffr;// 문자기반 버퍼스트림

	// 조사한 단어를 담아놓자 게임에 써먹기 위해
	ArrayList<String> wordList = new ArrayList<String>();
	Thread thread;// 단어게임을 진행할 쓰레드
	boolean flag = true;// 게임을 멈출 수 있는 변수
	boolean isDown = true;
	ArrayList<word> words = new ArrayList<word>();

	public GamePanel(GameWindow gameWindow) {
		setLayout(new BorderLayout());

		this.gameWindow = gameWindow;

		p_west = new JPanel();

		// 이영역은 지금부터 그림을 그릴 영역
		p_center = new JPanel() {

			public void paintComponent(Graphics g) {
				// 기존 그림 지우기
				g.setColor(Color.WHITE);
				g.fillRect(0, 0, 750, 700);

				g.setColor(Color.BLUE);

				// 모든 워드들에 대한 render();
				for (int i = 0; i < words.size(); i++) {
					words.get(i).render(g);
				}

			}
		};
		la_user = new JLabel("김연실 님");
		la_score = new JLabel("0점");
		choice = new Choice();
		t_input = new JTextField(10);
		bt_start = new JButton("start");
		bt_pause = new JButton("pause");
		bt_stop = new JButton("종료");

		p_west.setPreferredSize(new Dimension(150, 700));
		p_west.setBackground(Color.ORANGE);

		choice.add("▼ 단어를 선택해주세요");
		choice.setPreferredSize(new Dimension(150, 40));
		choice.addItemListener(this);

		p_west.add(la_user);
		p_west.add(choice);
		p_west.add(t_input);
		p_west.add(bt_start);
		p_west.add(bt_pause);
		p_west.add(bt_stop);
		p_west.add(la_score);

		add(p_west, BorderLayout.WEST);
		add(p_center);

		// 버튼과 리스너 연결
		bt_start.addActionListener(this);
		bt_pause.addActionListener(this);
		bt_stop.addActionListener(this);

		// 텍스트 필드와 리스너 연결
		t_input.addKeyListener(new KeyAdapter() {

			public void keyReleased(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					System.out.println("키눌렀어?");
					// 화면에 존재하는 words와 입력 값 비교하여 맞으면 words 에서 객체 삭제
					String value = t_input.getText();
					for (int i = 0; i < words.size(); i++) {
						if (words.get(i).name.equals(value)) {
							words.remove(i);
						}
					}
				}
			}
		});

		setVisible(false); // 최초에 등장하지 않음

		// setBackground(Color.PINK);
		setPreferredSize(new Dimension(900, 700));

		getCategory();
	}

	// 초이스 컴포넌트에 채워질 파일명 조사하기
	public void getCategory() {
		File file = new File(res);

		// 파일+디렉토리 섞여있는 배열반환
		File[] files = file.listFiles();

		for (int i = 0; i < files.length; i++) {

			if (files[i].isFile()) {
				String name = files[i].getName();// memo.txt
				String[] arr = name.split("\\.");
				if (arr[1].equals("txt")) {// 메모장이라면
					choice.add(name);
				}
			}
		}
	}

	// 파일 읽어오기
	public void getword() {
		int index = choice.getSelectedIndex();

		if (index != 0) {// 첫번재 요소는 빼고
			String name = choice.getSelectedItem();
			System.out.println(res + name);

			try {
				fis = new FileInputStream(res + name);
				try {
					reader = new InputStreamReader(fis, "utf-8");
				} catch (UnsupportedEncodingException e1) {
					e1.printStackTrace();
				}

				// 스트림을 버퍼 처리 수준까지 올림
				buffr = new BufferedReader(reader);
				String data = "";

				wordList.removeAll(wordList);

				while (true) {

					try {
						data = buffr.readLine();
					} catch (IOException e) {
						e.printStackTrace();
					}
					if (data == null)
						break;
					System.out.println(data);

					wordList.add(data);
				}
				// 준비 된 단어를 화면에 보여주기
				createword();

			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} finally {
				if (reader != null) {
					try {
						reader.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
				if (fis != null) {
					try {
						fis.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
				if (buffr != null) {
					try {
						buffr.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}

		}
	}

	public void createword() {// 화면의 워드를 생성하는 메서드
		for (int i = 0; i < wordList.size(); i++) {
			String name = wordList.get(i);
			word word = new word(name, i * (75 + 10), 100);

			// 워드객체명단 만들기
			words.add(word);

		}
	}

	// System.out.println(wordList.size);
	// 게임시작
	public void startGame() {
		if (thread == null) {
			flag = true;// while 문 작동하도록
			thread = new Thread(this);
			thread.start();
		}
	}

	// 게임중지 or 계속
	public void pauseGame() {
		isDown = !isDown;
	}

	/*------------------------------처음으로 돌아가자
	 * 
	 * 1.wordList(단어들이 들어있는)비우기
	 * 2.words(word 인스턴스들이 들어있는)비우기
	 * 3.choice 초기화(index=0)
	 * 4.flag=false
	 * 5.thread를 null로 다시 초기화
	 * 
	 * -----------------------------------------------
	 */
	// 게임종료
	public void stopGame() {
		wordList.removeAll(wordList);
		words.removeAll(words);
		choice.select(0);// 첫번째 요소 강제 선택
		flag = false;// while 문 중지 목적
		thread = null;
	}

	public void itemStateChanged(ItemEvent e) {
		getword();
	}

	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();

		if (obj == bt_start) {
			startGame();

		} else if (obj == bt_pause) {
			pauseGame();
		} else if (obj == bt_stop) {
			stopGame();
		}
	}

	public void run() {
		while (true) {
			try {
				Thread.sleep(500);
				// down();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

			if (isDown) {
				// 모든 단어들에 대해서 tick()
				for (int i = 0; i < words.size(); i++) {
					words.get(i).tick();
				}
				// 모든 단어들에 대해서 render()
				p_center.repaint();
			}
		}
	}

}
