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

public class MenuGui extends JFrame implements ActionListener{
//���θ޴�
	
	JPanel panel=new JPanel();//�޴��˻� ��ư+���˻� ��ư+���������� ��ư

	//���� �̹���
	ImageIcon titleIcon=new ImageIcon(
			MainGui.class.getResource("/image/title.PNG")
	);
	Image titleImage=titleIcon.getImage();
	Image titleSize=titleImage.getScaledInstance(280, 80 ,Image.SCALE_SMOOTH);
	ImageIcon titleLabel=new ImageIcon(titleSize);
	JLabel title=new JLabel(titleLabel);
	//�޴��˻� ��ư �̹���
	ImageIcon menuIcon=new ImageIcon(
			MenuGui.class.getResource("/image/searchMenu.PNG")
	);
	Image menuImg=menuIcon.getImage();
	Image menuSize=menuImg.getScaledInstance(170 ,90 , Image.SCALE_SMOOTH);
	ImageIcon menu=new ImageIcon(menuSize);
	JButton menuButton=new JButton(menu);
	//���˻� ��ư �̹���
	ImageIcon stuffIcon=new ImageIcon(
			MenuGui.class.getResource("/image/searchStuff.PNG")
	);
	Image stuffImg=stuffIcon.getImage();
	Image stuffSize=stuffImg.getScaledInstance(170 ,90 ,Image.SCALE_SMOOTH);
	ImageIcon stuff=new ImageIcon(stuffSize);
	JButton stuffButton=new JButton(stuff);
	//���������� ��ư �̹���
	ImageIcon myIcon=new ImageIcon(
			MenuGui.class.getResource("/image/mypage.PNG")
	);
	Image myImage=myIcon.getImage();
	Image mySize=myImage.getScaledInstance(170,  90 , Image.SCALE_SMOOTH);
	ImageIcon my=new ImageIcon(mySize);
	JButton myButton=new JButton(my);
	//�ٹ̱�
	ImageIcon icon2=new ImageIcon(
			MainGui.class.getResource("/image/main.PNG")
	);
	Image img2=icon2.getImage();
	Image sizeImg2=img2.getScaledInstance(550,70,Image.SCALE_SMOOTH);
	ImageIcon imgIcon2=new ImageIcon(sizeImg2);
	JLabel image2=new JLabel(imgIcon2);
	
	//���� �α��ε� ����
	public static String id=null;
	JButton arrowButton;
	int x;
	String ment;
	
	//�̱���
//	public static MenuGui single=null;
//	public static MenuGui getInstance(String id) {
//		if(single==null) {
//			single=new MenuGui(id);
//		}
//		return single;
//	}
	public MenuGui(String id) {
		//main frame
		setTitle("Main menu");
		this.id=id;//���� �α��� �� ���̵� 
		this.setBounds(450 , 300 , 550 , 500);
		this.getContentPane().setBackground(Color.WHITE);
		
		title.setBorder(new EmptyBorder(15,5,0,0));
		title.setHorizontalAlignment(JLabel.CENTER);
		
		//�α׾ƿ� ��ư->â�ݱ�=���θ޴�(MainGui)
		ImageIcon arrowIcon=new ImageIcon(
				ViewBoardGui.class.getResource(memberCheck())
		);
		Image arrowImage=arrowIcon.getImage();
		Image arrowSize=arrowImage.getScaledInstance(x,x,Image.SCALE_SMOOTH);
		ImageIcon arrowLabel=new ImageIcon(arrowSize);
		JButton arrowButton=new JButton(arrowLabel);
		this.arrowButton=arrowButton;
		
		//panel
		panel.setLayout(null);
		panel.setBackground(Color.white);
		menuButton.setBounds(190 ,45 , 150, 60);
		stuffButton.setBounds(190, 115, 150, 60);
		myButton.setBounds(190, 185, 150, 60);
		arrowButton.setBounds(15,250,x+5,x+5);
		arrowButton.setBorderPainted(false);//�ܰ���X
		arrowButton.setContentAreaFilled(false);//ä���X
		menuButton.setBorderPainted(false);//ä���x
		stuffButton.setBorderPainted(false);
		myButton.setBorderPainted(false);
		
		panel.add(menuButton);
		panel.add(stuffButton);
		panel.add(myButton);
		panel.add(arrowButton);
		
		this.add(title , "North");
		this.add(panel , "Center");
		this.add(image2 , "South");
		
		menuButton.addActionListener(this);
		stuffButton.addActionListener(this);
		myButton.addActionListener(this);
		arrowButton.addActionListener(this);
		
		arrowButton.setToolTipText(ment);
		
		arrowButton.addMouseListener(new MouseListener() {
			@Override
			public void mouseEntered(MouseEvent e) {
				if(e.getSource()==arrowButton) {
					arrowButton.setContentAreaFilled(true);
					arrowButton.setBackground(LoginGui.yellow);
				}
			}

			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				
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
		});
		
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==menuButton) {
			//�޴��˻�
//			if(id==null) {//��ȸ��
//				//NoMemberMenuGui.getInstance();
//				new NoMemberMenuGui();
//			}else {//ȸ��
//				//SearchMenuGui.getInstance();
//				new SearchMenuGui();
//			}
			new SearchMenuGui();
		}else if(e.getSource()==stuffButton) {
			//��� �˻�
//			if(id==null) {
//				//NoMemberStuffGui.getInstance();
//				new NoMemberStuffGui();
//			}else {
//				//SearchStuffGui.getInstance();
//				new SearchStuffGui();
//			}
			new SearchStuffGui();
		}else if(e.getSource()==myButton) {
			//����������
			if(id==null) {
				userService();
			}else {
				//MypageGui.getInstance();
				//����������
				this.setVisible(false);//���� â �ݱ�
				new MypageGui();
			}
		}	
		if(e.getSource()==this.arrowButton) {
			String ment;
			LoginGui.messageFont();
			if(MenuGui.id!=null) {//ȸ���� ���
				ment="�α׾ƿ� �Ǿ����ϴ�.\n������������ �̵��մϴ�.";
			}else {//��ȸ���� ���
				ment="������������ �̵��մϴ�.";
			}
			JOptionPane.showMessageDialog
			(null,  ment,"Logout",JOptionPane.INFORMATION_MESSAGE);
			new MainGui();
			this.dispose();
		}
	}

	//�α��� �ʿ� �ȳ�â
	public void userService() {
		LoginGui.messageFont();
		int answer=JOptionPane.showConfirmDialog
				(null, "ȸ�������Դϴ�. \n ȸ�������Ͻðڽ��ϱ�?","ȸ������",JOptionPane.YES_NO_OPTION);
		if(answer==JOptionPane.YES_OPTION) {
			//JoinGui.getInstance();
			new JoinGui();
			this.setVisible(false);//ȸ�����Խ� ����â �ݱ�
		}
	}
	
	//�α��� ���� Ȯ���Ͽ� ��ư �̹��� ����
	public String memberCheck() {
		String path=null;
		if(id!=null) {//ȸ��(�α���O)�� ��쿡 �α׾ƿ� ��ư
			path="/image/logout.png";
			this.x=50;
			this.ment="�α׾ƿ�";
		}else if(id==null) {//��ȸ���� ��� �ڷΰ��� ��ư
			path="/image/arrow.png";
			this.x=30;
			this.ment="�ڷΰ���";
		}
		return path;
	}
}
