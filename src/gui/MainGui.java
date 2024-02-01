package gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class MainGui extends JFrame implements ActionListener{
//시작페이지
	
	JPanel panel=new JPanel();//회원가입+로그인+비회원 버튼
	
	//제목 이미지
	ImageIcon titleIcon=new ImageIcon(
			MainGui.class.getResource("/image/title.PNG")
	);
	Image titleImage=titleIcon.getImage();
	Image titleSize=titleImage.getScaledInstance(330, 80 ,Image.SCALE_SMOOTH);
	ImageIcon titleLabel=new ImageIcon(titleSize);
	JLabel title=new JLabel(titleLabel);
	//동쪽에 이미지
	ImageIcon icon=new ImageIcon(
			MainGui.class.getResource("/image/joinInfo.PNG")
	);
	Image img=icon.getImage();
	Image sizeImg=img.getScaledInstance(200 ,  150 ,  Image.SCALE_SMOOTH);
	ImageIcon imgIcon=new ImageIcon(sizeImg);
	JLabel image=new JLabel(imgIcon);
	//회원가입 버튼 이미지
	ImageIcon joinIcon=new ImageIcon(
			MainGui.class.getResource("/image/join.PNG")
	);
	Image joinImg=joinIcon.getImage();
	Image joinSize=joinImg.getScaledInstance(150 ,70 , Image.SCALE_SMOOTH);
	ImageIcon join=new ImageIcon(joinSize);
	JButton joinButton=new JButton(join);
	//로그인 버튼 이미지
	ImageIcon loginIcon=new ImageIcon(
			MainGui.class.getResource("/image/login.PNG")
	);
	Image loginImg=loginIcon.getImage();
	Image loginSize=loginImg.getScaledInstance(150 ,70 ,Image.SCALE_SMOOTH);
	ImageIcon login=new ImageIcon(loginSize);
	JButton loginButton=new JButton(login);
	//비회원 버튼 이미지
	ImageIcon noIcon=new ImageIcon(
			MainGui.class.getResource("/image/noMember.PNG")
	);
	Image noImg=noIcon.getImage();
	Image noSize=noImg.getScaledInstance(150 , 70 , Image.SCALE_SMOOTH);
	ImageIcon no=new ImageIcon(noSize);
	JButton noMemberButton=new JButton(no);
	//꾸미기
	ImageIcon icon2=new ImageIcon(
			MainGui.class.getResource("/image/main.PNG")
	);
	Image img2=icon2.getImage();
	Image sizeImg2=img2.getScaledInstance(550,70,Image.SCALE_SMOOTH);
	ImageIcon imgIcon2=new ImageIcon(sizeImg2);
	JLabel image2=new JLabel(imgIcon2);

	//싱글톤
//	public static MainGui single=null;
//	public static MainGui getInstance() {
//		if(single==null) {
//			single=new MainGui();
//		}
//		return single;
//	}
	
	static MainGui mainGuiInstance;//객체 주소 저장
	
	public MainGui() {
		mainGuiInstance=this;
		
		setTitle("맛있는 길잡이");
		
		//main frame
		this.setBounds(450 , 300 , 550 , 500);
		this.getContentPane().setBackground(Color.WHITE);
		
		image.setBorder(new EmptyBorder(5,5,35,20));//위,왼쪽,아래,오른쪽
		title.setBorder(new EmptyBorder(5,20,0,0));
		//title.setHorizontalAlignment(JLabel.CENTER);

		//panel
		panel.setLayout(null);
		panel.setBackground(Color.white);
		//joinButton.setBorderPainted(false);//외곽선
		joinButton.setContentAreaFilled(false);//채우기X
		//joinButton.setFocusPainted(false);//선택시 테두리X
		joinButton.setBounds(100 ,45 , 130, 60);
		loginButton.setBounds(100, 115, 130, 60);
		noMemberButton.setBounds(100, 185, 130, 60);
		panel.add(joinButton);
		panel.add(loginButton);
		panel.add(noMemberButton);

		this.add(title , "North");
		this.add(image , "East");
		this.add(panel , "Center");
		this.add(image2 , "South");
		
		joinButton.addActionListener(this);
		loginButton.addActionListener(this);
		noMemberButton.addActionListener(this);
		
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==joinButton) {
			System.out.println("회원가입");
			//JoinGui.getInstance();
			new JoinGui();
		}else if(e.getSource()==loginButton) {
			System.out.println("로그인");
			new LoginGui();
		}else if(e.getSource()==noMemberButton) {
			System.out.println("비회원");
			new MenuGui(null);
			//MenuGui.getInstance(null);
			this.setVisible(false);//현재페이지 닫기
			//현재 로그인된 아이디를 매개변수로 전달하여 로그인이 안된 경우일 시 마이페이지 접속X
		}
	}

}
