package gui;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;

import dao.MenuinfoDao;
import dto.MenuinfoDto;

public class SearchMenuGui extends JFrame implements ActionListener,MouseListener{
// 메뉴검색
	
	MenuinfoDao MD=new MenuinfoDao();
	Random r=new Random();
	
	//cardLayout
	JPanel cardPanel=new JPanel();
	CardLayout cardLayout=new CardLayout();
	
	JPanel mainPanel=new JPanel();//꾸미기+검색창+검색버튼
	JPanel panel1=new JPanel();//검색정보+panel2
	JPanel panel2=new JPanel();//스크롤+조리방법 정보
	
	JTextField search=new JTextField();
	
	JButton searchButton=new JButton(" 검색  ");
	
	JLabel menuTitle=new JLabel(" < 메뉴 >");
	JLabel menu;
	JLabel mainStuffTitle=new JLabel(" - 주 재료 ");
	JLabel mainStuff;
	JLabel sideStuffTitle=new JLabel(" - 부 재료 ");
	JLabel sideStuff;
	JLabel cookTitle=new JLabel(" ♨ 조리방법 ♨");

	JTextArea cook=new JTextArea();//행,글자수
	
	//꾸미기
	ImageIcon icon=new ImageIcon(
			SearchMenuGui.class.getResource("/image/icon_3.png")
	);
	Image img=icon.getImage();
	Image sizeImg=img.getScaledInstance(50,50,Image.SCALE_SMOOTH);
	ImageIcon imgIcon=new ImageIcon(sizeImg);
	JLabel image=new JLabel(imgIcon);
	
	//뒤로가기 버튼->창닫기=메인메뉴(MenuGui)
	ImageIcon arrowIcon=new ImageIcon(
			ViewBoardGui.class.getResource("/image/arrow.png")
	);
	Image arrowImage=arrowIcon.getImage();
	Image arrowSize=arrowImage.getScaledInstance(30,30,Image.SCALE_SMOOTH);
	ImageIcon arrowLabel=new ImageIcon(arrowSize);
	JButton arrowButton=new JButton(arrowLabel);
	
	//로그인  권유----------------------------------------------------------
	ImageIcon iconL=new ImageIcon(
			SearchMenuGui.class.getResource("/image/recommendJoin.PNG")
	);
	Image imgL=iconL.getImage();
	Image sizeImgL=imgL.getScaledInstance(300,200,Image.SCALE_SMOOTH);
	ImageIcon imgIconL=new ImageIcon(sizeImgL);
	JLabel imageL=new JLabel(imgIconL);
	
	//싱긅톤
//	public static SearchMenuGui single=null;
//	public static SearchMenuGui getInstance() {
//		if(single==null) {
//			return new SearchMenuGui();
//		}
//		return null;
//	}
	
	public SearchMenuGui() {
		cook.setEditable(false);//textArea 수정불가
		
		menu=new JLabel("test");//메뉴 검색시 검색한 메뉴 이름 등록
		mainStuff=new JLabel("test");//재료 검색시 검색한 재료 등록
		sideStuff=new JLabel("test");//검색한 내용 토대로 DB에서 가져온 부재료 등록
		
		//main frame
		setTitle("Search menu");
		this.setBounds(450, 300, 550, 500);
		this.setLayout(null);
		//this.getContentPane().setBackground(Color.WHITE);
		
		//mainPanel
		mainPanel.setLayout(null);
		mainPanel.setBounds(0,0,550,70);
		mainPanel.setBackground(Color.white);
		
		image.setBounds(60,10,50,50);
		search.setBounds(130, 20, 200,30);
		searchButton.setFont(new Font("맑은 고딕",Font.BOLD,12));
		searchButton.setBackground(new Color(128, 238, 215));
		searchButton.setBounds(350,20,70,30);
		
		//panel1
		panel1.setLayout(null);
		panel1.setBounds(0,70,550,500);
		panel1.setBackground(Color.white);
		
		menuTitle.setFont(LoginGui.font2);
		menuTitle.setBounds(100,0,70,30);
		menu.setFont(LoginGui.font2);
		menu.setBounds(200,0,450,30);//수정
		mainStuffTitle.setFont(LoginGui.font2);
		mainStuffTitle.setBounds(93,30,100,30);
		mainStuff.setFont(LoginGui.font2);
		mainStuff.setBounds(200,30,100,30);//수정
		sideStuffTitle.setFont(LoginGui.font2);
		sideStuffTitle.setBounds(93,60,100,30);
		sideStuff.setFont(LoginGui.font2);
		sideStuff.setBounds(200,60,450,30);//수정
		arrowButton.setBounds(5,350,35,35);
		arrowButton.setBorderPainted(false);//외곽선X
		arrowButton.setContentAreaFilled(false);//채우기X		
		imageL.setBounds(100,150,300,200);//--------------------
		
		//panel2
		panel2.setLayout(null);
		panel2.setBackground(new Color(174, 239, 94));
		panel2.setBounds(45,100,450,265);
		
		cookTitle.setFont(LoginGui.font3);
		cookTitle.setBounds(5, 0, 150, 30);
		cook.setBounds(7,30,438,220);
		
		//scroll
		JScrollPane scroll=new JScrollPane(cook);
		scroll.setBounds(7,30,300,220);
		scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		
		//panel2 add
		panel2.add(scroll);
		panel2.add(cookTitle);
		
		//mainPanel add
		mainPanel.add(searchButton);
		mainPanel.add(search);
		mainPanel.add(image);
		
		//cardLayout
		cardPanel.setLayout(cardLayout);
		cardPanel.setBounds(0,70,550,500);
//		cardPanel.setBackground(Color.GREEN);
		cardPanel.add(panel1, "1");
		
		searchButton.addActionListener(this);
		arrowButton.addActionListener(this);
//		searchButton.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent e) {
//				cardLayout.next(cardPanel);
//				//cardPanel에 담을 panel만들기 or 내용 덮어쓰기
//			}
//		});
		
		arrowButton.setToolTipText("뒤로가기");
		
		arrowButton.addMouseListener(this);
		
		this.add(mainPanel);
		this.add(cardPanel);
		this.setVisible(true);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==searchButton) {
			reJLabel();
			cardLayout.next(cardPanel);
		}
		if(e.getSource()==arrowButton) {
			this.setVisible(false);
		}
		
	}
	
	//메뉴검색한 내용 가져오기
	public void reJLabel() {
		MenuinfoDto menuDto=MD.allSelectMenu(search.getText());
		if(menuDto!=null) {
			menu.setText(menuDto.getMenuname());
			mainStuff.setText(menuDto.getMainstuff());
			sideStuff.setText(menuDto.getSidestuff());
			foodImage(menuDto.getFoodImage());
			text(menuDto);
		}else { //검색결과가 없을 경우
			LoginGui.messageFont();
			int answer=JOptionPane.showConfirmDialog
			(null,  "검색결과가 없습니다.\n추천메뉴를 보시겠습니까?","추천메뉴",JOptionPane.YES_NO_OPTION);
			if(answer==JOptionPane.YES_OPTION) {
				reLabel(search.getText());
			}
		}
	}
	//문자열 나누기
	public void text(MenuinfoDto menuDto) {
		cook.setText("");
		String originalCook=null;
		originalCook=menuDto.getCook();
		//split 메서드는 정규식을 인수로 사용하여 점(.)은 모든 문자와 일치한다.
		//따라서, 점을 기준으로 분할하려면 백슬래시로 문자열을 이스케이프 해야한다.
		//System.out.println(originalCook.split("\\."));
		String[] reCook=null;
		reCook=originalCook.split("\\.");
		System.out.println(reCook[0]);
		for(String line : reCook) {
			System.out.println(line);
			cook.append(line+"\n");
			System.out.println(cook.getText());
		}
	}
	//메뉴검색한 내용 가져오기(부분일치)
	public void reLabel(String search) {
		ArrayList<MenuinfoDto> menuDto=MD.selectMenu(search);
		if(!menuDto.isEmpty()) {
			for(int i=0; i<menuDto.size();) {
				if(menuDto.size()>1) {
					i=r.nextInt(menuDto.size()-1);
				}
				System.out.println(i);
				menu.setText(menuDto.get(i).getMenuname());
				mainStuff.setText(menuDto.get(i).getMainstuff());
				sideStuff.setText(menuDto.get(i).getSidestuff());
				foodImage(menuDto.get(i).getFoodImage());
				text(menuDto.get(i));
				break;
			}
		}
//		if(menuDto!=null) {//부분일치
//			menu.setText(menuDto.getMenuname());
//			mainStuff.setText(menuDto.getMainstuff());
//			sideStuff.setText(menuDto.getSidestuff());
//			foodImage(menuDto.getFoodImage());
//			text(menuDto);
//		}
		else {
			LoginGui.messageFont();
			JOptionPane.showMessageDialog
			(null, "추천메뉴가 없습니다.", "추천메뉴", JOptionPane.INFORMATION_MESSAGE);
			panel1.removeAll();
		}
	}
	//음식사진 가져오기
	JLabel food=new JLabel();
	public void foodImage(String imagePath) {
		//음식사진
		String path=imagePath;
		System.out.println(path);
		ImageIcon foodI=new ImageIcon(
				SearchMenuGui.class.getResource(path)
		);
		Image foodImage=foodI.getImage();
		Image sizeFood=foodImage.getScaledInstance(125 , 120 , Image.SCALE_SMOOTH);
		ImageIcon foodIcon=new ImageIcon(sizeFood);
		food.setIcon(foodIcon); 
		food.setBounds(315,70,125,120);
		panel2.add(food);
		//-------------------------------
		panel1.add(menuTitle);
		panel1.add(menu);
		panel1.add(mainStuffTitle);
		panel1.add(mainStuff);
		panel1.add(sideStuffTitle);
		panel1.add(sideStuff);
		panel1.add(arrowButton);
		
		if(MenuGui.id!=null) {//회원일 경우
			panel1.add(panel2);
		}else if(MenuGui.id==null) {//비회원일 경우
			panel1.add(imageL);
		}
		//-------------------------------
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		if(e.getSource()==arrowButton) {
			arrowButton.setContentAreaFilled(true);
			arrowButton.setBackground(LoginGui.yellow);
		}
	}

	@Override
	public void mouseExited(MouseEvent e) {
		arrowButton.setContentAreaFilled(false);
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
}
