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
//����������
	
	JPanel panel=new JPanel();//ȸ������+�α���+��ȸ�� ��ư
	
	//���� �̹���
	ImageIcon titleIcon=new ImageIcon(
			MainGui.class.getResource("/image/title.PNG")
	);
	Image titleImage=titleIcon.getImage();
	Image titleSize=titleImage.getScaledInstance(330, 80 ,Image.SCALE_SMOOTH);
	ImageIcon titleLabel=new ImageIcon(titleSize);
	JLabel title=new JLabel(titleLabel);
	//���ʿ� �̹���
	ImageIcon icon=new ImageIcon(
			MainGui.class.getResource("/image/joinInfo.PNG")
	);
	Image img=icon.getImage();
	Image sizeImg=img.getScaledInstance(200 ,  150 ,  Image.SCALE_SMOOTH);
	ImageIcon imgIcon=new ImageIcon(sizeImg);
	JLabel image=new JLabel(imgIcon);
	//ȸ������ ��ư �̹���
	ImageIcon joinIcon=new ImageIcon(
			MainGui.class.getResource("/image/join.PNG")
	);
	Image joinImg=joinIcon.getImage();
	Image joinSize=joinImg.getScaledInstance(150 ,70 , Image.SCALE_SMOOTH);
	ImageIcon join=new ImageIcon(joinSize);
	JButton joinButton=new JButton(join);
	//�α��� ��ư �̹���
	ImageIcon loginIcon=new ImageIcon(
			MainGui.class.getResource("/image/login.PNG")
	);
	Image loginImg=loginIcon.getImage();
	Image loginSize=loginImg.getScaledInstance(150 ,70 ,Image.SCALE_SMOOTH);
	ImageIcon login=new ImageIcon(loginSize);
	JButton loginButton=new JButton(login);
	//��ȸ�� ��ư �̹���
	ImageIcon noIcon=new ImageIcon(
			MainGui.class.getResource("/image/noMember.PNG")
	);
	Image noImg=noIcon.getImage();
	Image noSize=noImg.getScaledInstance(150 , 70 , Image.SCALE_SMOOTH);
	ImageIcon no=new ImageIcon(noSize);
	JButton noMemberButton=new JButton(no);
	//�ٹ̱�
	ImageIcon icon2=new ImageIcon(
			MainGui.class.getResource("/image/main.PNG")
	);
	Image img2=icon2.getImage();
	Image sizeImg2=img2.getScaledInstance(550,70,Image.SCALE_SMOOTH);
	ImageIcon imgIcon2=new ImageIcon(sizeImg2);
	JLabel image2=new JLabel(imgIcon2);

	//�̱���
//	public static MainGui single=null;
//	public static MainGui getInstance() {
//		if(single==null) {
//			single=new MainGui();
//		}
//		return single;
//	}
	
	static MainGui mainGuiInstance;//��ü �ּ� ����
	
	public MainGui() {
		mainGuiInstance=this;
		
		setTitle("���ִ� ������");
		
		//main frame
		this.setBounds(450 , 300 , 550 , 500);
		this.getContentPane().setBackground(Color.WHITE);
		
		image.setBorder(new EmptyBorder(5,5,35,20));//��,����,�Ʒ�,������
		title.setBorder(new EmptyBorder(5,20,0,0));
		//title.setHorizontalAlignment(JLabel.CENTER);

		//panel
		panel.setLayout(null);
		panel.setBackground(Color.white);
		//joinButton.setBorderPainted(false);//�ܰ���
		joinButton.setContentAreaFilled(false);//ä���X
		//joinButton.setFocusPainted(false);//���ý� �׵θ�X
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
			System.out.println("ȸ������");
			//JoinGui.getInstance();
			new JoinGui();
		}else if(e.getSource()==loginButton) {
			System.out.println("�α���");
			new LoginGui();
		}else if(e.getSource()==noMemberButton) {
			System.out.println("��ȸ��");
			new MenuGui(null);
			//MenuGui.getInstance(null);
			this.setVisible(false);//���������� �ݱ�
			//���� �α��ε� ���̵� �Ű������� �����Ͽ� �α����� �ȵ� ����� �� ���������� ����X
		}
	}

}
