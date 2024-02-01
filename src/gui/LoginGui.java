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
//�α���
//�ڷΰ��� ��ư  ���� â�� ������ ������������ �̵�
	
	UserinfoDao userDao=new UserinfoDao();
	
	JLabel title=new JLabel(" �ݰ����ϴ�! �α��� ���ּ���.");
	JLabel idTitle=new JLabel(" < ���̵� > ");
	JLabel pwTitle=new JLabel(" < ��й�ȣ > ");
	
	JTextField id=new JTextField();
	JTextField pw=new JTextField();
	
	JButton login=new JButton("�α���");

	JPanel panel=new JPanel();//�α���
	
	//�ٹ̱�
	ImageIcon icon2=new ImageIcon(
			MainGui.class.getResource("/image/main.PNG")
	);
	Image img2=icon2.getImage();
	Image sizeImg2=img2.getScaledInstance(450,70,Image.SCALE_SMOOTH);
	ImageIcon imgIcon2=new ImageIcon(sizeImg2);
	JLabel image2=new JLabel(imgIcon2);
	
	//��Ʈ
	static Font font1=new Font("SanSerif",Font.BOLD,15);
	static Font font2=new Font("���ʷҵ���",Font.BOLD,13);
	static Font font3=new Font("���ʷҵ���",Font.BOLD,17);
	static Font font4=new Font("���ʷҵ���",Font.BOLD,23);
	
	//�÷�
	static Color navy=new Color(32,95,135);//���̺�
	static Color orange=new Color(231,132,43);
	static Color green=new Color(174, 239, 94);
	static Color yellow=new Color(238,223,124);
	
	//�޼���â ��Ʈ
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
		title.setHorizontalAlignment(JLabel.CENTER);//���������� �߾����� ����
		
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
				System.out.println("�α��οϷ�");
				this.setVisible(false);//�α��� �Ϸ�� �α��� ������ â �ݱ�
				MainGui.mainGuiInstance.setVisible(false);//�α��� �Ϸ�� ���������� â �ݱ�
				//�޴�
				new MenuGui(id.getText());
				//MenuGui.getInstance(id.getText());
			}else {
				loginMessage();
			}
		}	
	}

	//�α��� ���� ���â
	public void loginMessage() {
		messageFont();
		JOptionPane.showMessageDialog
		(null, "���̵� �Ǵ� ��й�ȣ �����Դϴ�.\n �ٽ� �Է����ּ���.","login failed",JOptionPane.WARNING_MESSAGE);
	}
}
