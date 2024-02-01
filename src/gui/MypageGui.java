package gui;

import java.awt.Color;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import dao.UserinfoDao;

public class MypageGui extends JFrame implements ActionListener,MouseListener{
//회원서비스 마이페이지
	
	UserinfoDao UD=new UserinfoDao();
	
	JPanel panel=new JPanel();//장보기+장바구니+게시판+회원정보(이름+포인트)+뒤로가기 버튼
	
	//제목 이미지
	ImageIcon titleIcon=new ImageIcon(
			MainGui.class.getResource("/image/mypageTitle.PNG")
	);
	Image titleImage=titleIcon.getImage();
	Image titleSize=titleImage.getScaledInstance(280, 80 ,Image.SCALE_SMOOTH);
	ImageIcon titleLabel=new ImageIcon(titleSize);
	JLabel title=new JLabel(titleLabel);

	//정보수정 버튼 이미지
	ImageIcon userIcon=new ImageIcon(
			MainGui.class.getResource("/image/userInfo.PNG")
	);
	Image userImg=userIcon.getImage();
	Image userSize=userImg.getScaledInstance(150 ,70 , Image.SCALE_SMOOTH);
	ImageIcon user=new ImageIcon(userSize);
	JButton userButton=new JButton(user);
	
	//장보기 버튼 이미지
	ImageIcon shopIcon=new ImageIcon(
			MainGui.class.getResource("/image/shop.PNG")
	);
	Image shopImg=shopIcon.getImage();
	Image shopSize=shopImg.getScaledInstance(150 ,70 ,Image.SCALE_SMOOTH);
	ImageIcon shop=new ImageIcon(shopSize);
	JButton shopButton=new JButton(shop);
	
	//장바구니 버튼 이미지
	ImageIcon shoppingIcon=new ImageIcon(
			MainGui.class.getResource("/image/shopping.PNG")
	);
	Image shoppingImg=shoppingIcon.getImage();
	Image shoppingSize=shoppingImg.getScaledInstance(150 , 70 , Image.SCALE_SMOOTH);
	ImageIcon shopping=new ImageIcon(shoppingSize);
	JButton shoppingButton=new JButton(shopping);
	
	//게임하기 버튼 이미지
	ImageIcon gameIcon=new ImageIcon(
			MainGui.class.getResource("/image/game.PNG")
	);
	Image gameImg=gameIcon.getImage();
	Image gameSize=gameImg.getScaledInstance(150 , 70 , Image.SCALE_SMOOTH);
	ImageIcon game=new ImageIcon(gameSize);
	JButton gameButton=new JButton(game);
	
	//꾸미기
	ImageIcon icon2=new ImageIcon(
			MainGui.class.getResource("/image/main.PNG")
	);
	Image img2=icon2.getImage();
	Image sizeImg2=img2.getScaledInstance(550,70,Image.SCALE_SMOOTH);
	ImageIcon imgIcon2=new ImageIcon(sizeImg2);
	JLabel image2=new JLabel(imgIcon2);
	
	JLabel name=new JLabel
			(" '"+UD.selectInfo(MenuGui.id).getName()+"' 님 환영합니다! ");
	JLabel point=new JLabel
			("누적 포인트  ✪ "+Integer.toString(UD.selectInfo(MenuGui.id).getPoint())+" ✪");
	
	//뒤로가기 버튼->창닫기=메인메뉴(MenuGui)
	ImageIcon arrowIcon=new ImageIcon(
			ViewBoardGui.class.getResource("/image/arrow.png")
	);
	Image arrowImage=arrowIcon.getImage();
	Image arrowSize=arrowImage.getScaledInstance(30,30,Image.SCALE_SMOOTH);
	ImageIcon arrowLabel=new ImageIcon(arrowSize);
	JButton arrowButton=new JButton(arrowLabel);

	//싱글톤
//	public static MypageGui single=null;
//	public static MypageGui getInstance() {
//		if(single==null) {
//			single=new MypageGui();
//		}
//		return single;
//	}
	
	static MypageGui mypageGuiInstance;
	
	public MypageGui() {
		mypageGuiInstance=this;
		
		//main frame
		setTitle("My page");
		this.setBounds(450 , 300 , 550 , 500);
		this.getContentPane().setBackground(Color.WHITE);

		title.setBorder(new EmptyBorder(15,5,0,0));
		title.setHorizontalAlignment(JLabel.CENTER);
		userButton.setContentAreaFilled(false);//채우기X

		panel.setLayout(null);
		panel.setBackground(Color.white);
		
		userButton.setBounds(100 ,10 , 130, 60);
		shopButton.setBounds(100, 80, 130, 60);
		shoppingButton.setBounds(100, 150, 130, 60);
		gameButton.setBounds(100,220,130,60);
		arrowButton.setBounds(15,260,40,40);
		arrowButton.setBorderPainted(false);//외곽선X
		arrowButton.setContentAreaFilled(false);//채우기X
		name.setBounds(300,80,250,60);
		point.setBounds(302,130,250,60);
		
		name.setFont(LoginGui.font3);
		point.setFont(LoginGui.font3);
		
		panel.add(userButton);
		panel.add(shopButton);
		panel.add(shoppingButton);
		panel.add(gameButton);
		panel.add(arrowButton);
		panel.add(name);
		panel.add(point);

		this.add(title , "North");
		this.add(panel , "Center");
		this.add(image2 , "South");
		
		userButton.addActionListener(this);
		shopButton.addActionListener(this);
		shoppingButton.addActionListener(this);
		gameButton.addActionListener(this);
		arrowButton.addActionListener(this);
		
		arrowButton.setToolTipText(" 뒤로가기 ");
		
		arrowButton.addMouseListener(this);
		
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==userButton) {
			System.out.println("정보수정");
			passwordConfirmation();
		}else if(e.getSource()==shopButton) {
			System.out.println("장보기");
			//ShopGui.getInstance();
			new ShopGui();
		}else if(e.getSource()==shoppingButton) {
			System.out.println("장바구니");
			//ShoppingGui.getInstance();
			//현재 로그인된 아이디를 매개변수로 전달하여 로그인이 안된 경우일 시 마이페이지 접속X
			new ShoppingGui();
		}else if(e.getSource()==gameButton) {
			System.out.println("게시판");
			new BoardMainGui();
			this.setVisible(false);//현재 창 닫기
		}
		if(e.getSource()==arrowButton) {
			this.setVisible(false);
			//new MenuGui(UD.selectInfo(MenuGui.id).getId());
			new MenuGui(MenuGui.id);
		}
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

	//비밀번호 입력시 정보수정 가능
	public void passwordConfirmation() {
		String pw=JOptionPane.showInputDialog("비밀번호를 입력하세요.");
		if(UD.login(MenuGui.id,pw)) {//비밀번호 일치
			new UserGui();
		}else {//비밀번호 불일치
			JOptionPane.showMessageDialog
			(null,  "비밀번호가 틀렸습니다.","Password inconsistency",JOptionPane.INFORMATION_MESSAGE);
		}
	}
}
