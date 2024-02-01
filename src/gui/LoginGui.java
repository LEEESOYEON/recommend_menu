package gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;

import dao.UserinfoDao;

public class LoginGui extends JFrame implements ActionListener{
//로그인
//뒤로가기 버튼  없이 창을 닫으면 시작페이지로 이동
	
	UserinfoDao userDao=new UserinfoDao();
	
	JLabel title=new JLabel(" 반갑습니다! 로그인 해주세요.");
	JLabel idTitle=new JLabel(" < 아이디 > ");
	JLabel pwTitle=new JLabel(" < 비밀번호 > ");
	
	JTextField id=new JTextField();
	JTextField pw=new JTextField();
	
	JButton login=new JButton("로그인");

	JPanel panel=new JPanel();//로그인
	
	//꾸미기
	ImageIcon icon2=new ImageIcon(
			MainGui.class.getResource("/image/main.PNG")
	);
	Image img2=icon2.getImage();
	Image sizeImg2=img2.getScaledInstance(450,70,Image.SCALE_SMOOTH);
	ImageIcon imgIcon2=new ImageIcon(sizeImg2);
	JLabel image2=new JLabel(imgIcon2);
	
	//폰트
	static Font font1=new Font("SanSerif",Font.BOLD,15);
	static Font font2=new Font("함초롬돋움",Font.BOLD,13);
	static Font font3=new Font("함초롬돋움",Font.BOLD,17);
	static Font font4=new Font("함초롬돋움",Font.BOLD,23);
	
	//컬러
	static Color navy=new Color(32,95,135);//네이비
	static Color orange=new Color(231,132,43);
	static Color green=new Color(174, 239, 94);
	static Color yellow=new Color(238,223,124);
	
	//메세지창 폰트
	static void messageFont() {
		UIManager.put("OptionPane.messageFont",LoginGui.font2);
		UIManager.put("OptionPane.buttonFont",LoginGui.font2);
	}
	
	public LoginGui() {
		//main frame
		setTitle("Login");
		this.getContentPane().setBackground(Color.white);
		this.setBounds(420 , 300 , 450 , 500);
		
		title.setBorder(new EmptyBorder(30,5,0,0));
		title.setForeground(orange);
		title.setFont(font2);
		title.setHorizontalAlignment(JLabel.CENTER);//가로정렬을 중앙으로 설정
		
		//panel
		panel.setLayout(null);
		panel.setBackground(Color.white);
		idTitle.setBounds(100,65,150,60);
		idTitle.setForeground(navy);
		idTitle.setFont(font2);
		id.setBounds(200 ,80 , 150, 30);
		pwTitle.setBounds(91, 160,150,60);
		pwTitle.setForeground(navy);
		pwTitle.setFont(font2);
		pw.setBounds(200 ,170 , 150, 30);
		login.setBounds(170,250,100,60);
		login.setFont(font2);
		login.setForeground(navy);
		
		panel.add(idTitle);
		panel.add(id);
		panel.add(pwTitle);
		panel.add(pw);
		panel.add(login);
		
		this.add(title,"North");
		this.add(panel,"Center");
		this.add(image2,"South");
		
		login.addActionListener(this);
		
		this.setVisible(true);
		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==login) {
			if(userDao.login(id.getText(), pw.getText())) {
				System.out.println("로그인완료");
				this.setVisible(false);//로그인 완료시 로그인 페이지 창 닫기
				MainGui.mainGuiInstance.setVisible(false);//로그인 완료시 시작페이지 창 닫기
				//메뉴
				new MenuGui(id.getText());
				//MenuGui.getInstance(id.getText());
			}else {
				loginMessage();
			}
		}	
	}

	//로그인 실패 경고창
	public void loginMessage() {
		messageFont();
		JOptionPane.showMessageDialog
		(null, "아이디 또는 비밀번호 오류입니다.\n 다시 입력해주세요.","login failed",JOptionPane.WARNING_MESSAGE);
	}
}
