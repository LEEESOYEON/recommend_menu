package gui;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import dao.UserinfoDao;
import dto.UserinfoDto;
import front.SignFront;

public class UserGui extends JFrame implements ActionListener{
//�������������� ��������
//���̵� ���� �Ұ�
	
	UserinfoDao userDao=new UserinfoDao();
	UserinfoDto userDto=userDao.selectInfo(MenuGui.id);
	SignFront SF=new SignFront();
	
	JLabel title=new JLabel(" ������ �κ��� �Է����ּ���.(���̵� �����Ұ�) ");
	JLabel idTitle=new JLabel(" < ���̵� > ");
	JLabel pwTitle=new JLabel(" < ��й�ȣ(�ʼ�) > ");
	JLabel nameTitle=new JLabel(" < �̸�(�ʼ�) > ");
	JLabel phoneTitle=new JLabel("<html>  < ��ȭ��ȣ(����) > <br/> 000-0000-0000</html>");
	JLabel addrTitle=new JLabel(" < �ּ�(����) > ");
	
	JTextField id=new JTextField(MenuGui.id);
	JTextField pw=new JTextField(userDto.getPw());
	JTextField name=new JTextField(userDto.getName());
	JTextField addr=new JTextField(userDto.getAddr());
	JTextField phone1=new JTextField(3);
	JTextField phone2=new JTextField(4);
	JTextField phone3=new JTextField(4);
	
	JButton join=new JButton("�������� �ϱ�");	
	
	GridBagLayout GB=new GridBagLayout();
	GridBagConstraints GBC=new GridBagConstraints();
	
	//�̱���
//	public static JoinGui single=null;
//	public static JoinGui getInstance() {
//		if(single==null) {
//			single=new JoinGui();
//		}
//		return single;
//	}
	public UserGui() {	
		
		//main frame
		this.getContentPane().setBackground(Color.WHITE);
		setTitle("SignUp");
		setLayout(GB);
		setBounds(420 , 300 , 450 , 500);
		
		id.setEditable(false);//�����Ұ�
		
		if(userDto.getPhone()!=null) {
			phone1.setText(userDto.getPhone().substring(0,3));
			phone2.setText(userDto.getPhone().substring(3,7));
			phone3.setText(userDto.getPhone().substring(7,11));
		}
		
		title.setFont(LoginGui.font2);
		title.setForeground(LoginGui.orange);
		title.setHorizontalAlignment(JLabel.CENTER);
		idTitle.setHorizontalAlignment(JLabel.CENTER);
		idTitle.setFont(LoginGui.font2);
		idTitle.setForeground(LoginGui.navy);
		pwTitle.setHorizontalAlignment(JLabel.CENTER);
		pwTitle.setFont(LoginGui.font2);
		pwTitle.setForeground(LoginGui.navy);
		nameTitle.setHorizontalAlignment(JLabel.CENTER);
		nameTitle.setFont(LoginGui.font2);
		nameTitle.setForeground(LoginGui.navy);
		phoneTitle.setHorizontalAlignment(JLabel.CENTER);
		phoneTitle.setFont(LoginGui.font2);
		phoneTitle.setForeground(LoginGui.navy);
		addrTitle.setHorizontalAlignment(JLabel.CENTER);
		addrTitle.setFont(LoginGui.font2);
		addrTitle.setForeground(LoginGui.navy);
		GBC.fill=GridBagConstraints.BOTH;
		GBC.weightx=1.0;
		GBC.weighty=1.0;
		//����
		GBadd(title,0,0,0,1);
		//���̵�
		GBadd(idTitle,0,1,1,1);
		GBadd(id,1,1,3,1);
		//��й�ȣ
		GBadd(pwTitle,0,2,1,1);
		GBadd(pw,1,2,3,1);
		//�̸�
		GBadd(nameTitle,0,3,1,1);
		GBadd(name,1,3,3,1);
		//��ȭ
		JPanel p=new JPanel(new FlowLayout(FlowLayout.LEFT));
		p.setBackground(Color.WHITE);
		p.add(phone1);
		p.add(new JLabel(" - "));
		p.add(phone2);
		p.add(new JLabel(" - "));
		p.add(phone3);
		GBadd(phoneTitle,0,4,1,1);
		GBadd(p,1,4,3,1);
		//�ּ�
		GBadd(addrTitle,0,5,1,1);
		GBadd(addr,1,5,3,1);
		//��ư
		join.setFont(LoginGui.font2);
		join.setForeground(LoginGui.navy);
		GBadd(join,0,10,0,1);
		
		join.addActionListener(this);
		
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	
	public void GBadd(JComponent c,int x,int y,int w,int h) {
		GBC.gridx=x;
		GBC.gridy=y;
		GBC.gridwidth=w;
		GBC.gridheight=h;
		GBC.insets=new Insets(2,2,2,2);
		add(c,GBC);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource()==join) {
			if(pw.getText().equals("") || name.getText().equals("")) {
				nullMessage();
			}else {
					if(SF.pwCheck(pw.getText())) {//��й�ȣ ��ȿ�� ����
							UserinfoDto userDto=new UserinfoDto();
							userDto.setName(name.getText());
							userDto.setId(id.getText());
							userDto.setPw(pw.getText());
							userDto.setAddr(addr.getText());
							userDto.setPhone(phone1.getText()+phone2.getText()+phone3.getText());
							userDao.updateUserinfo(userDto);
							System.out.println("�������� �Ϸ�");
							message();					
					}else {
						pwCheck();
					}
			}
			//�α��� �������� �̵�
			//���̵� ��ȿ�� �˻�
		}
	}

	//���ȳ�â
	public void message() {
		LoginGui.messageFont();
		JOptionPane.showMessageDialog
		(null, "ȸ������ ������ �Ϸ�Ǿ����ϴ�.\n�α��� �������� �̵��մϴ�.","Relogin",JOptionPane.INFORMATION_MESSAGE);
		this.setVisible(false);
		MypageGui.mypageGuiInstance.dispose();
		new MainGui();
	}
	
	//���̵� �Ǵ� ��й�ȣ�� null�϶� ���â
	public void nullMessage() {
		JOptionPane.showMessageDialog
		(null, "�ʼ���������� Ȯ�����ּ���.","join failed",JOptionPane.WARNING_MESSAGE);
	}
	
	//��й�ȣ ��ȿ�� �ȳ�â
	public void pwCheck() {
		JOptionPane.showMessageDialog(null,"��й�ȣ ����/����/Ư������ ���� 5~10����. \n �ٽ��Է����ּ���.");
	}
	
}
