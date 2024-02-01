package gui;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
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

public class JoinGui extends JFrame implements ActionListener{
//회원가입
//뒤로가기 버튼 없이 창을 닫으면 시작페이지로 이동	

	UserinfoDao userDao=new UserinfoDao();
	SignFront SF=new SignFront();
	
	JLabel title=new JLabel(" 환영합니다!  회원가입 해주세요. ");
	JLabel idTitle=new JLabel(" < 아이디(필수) > ");
	JLabel pwTitle=new JLabel(" < 비밀번호(필수) > ");
	JLabel nameTitle=new JLabel(" < 이름(필수) > ");
	JLabel phoneTitle=new JLabel(" < 전화번호(선택) > ");
	JLabel addrTitle=new JLabel(" < 주소(선택) > ");
	
	JTextField id=new JTextField(15);
	JTextField pw=new JTextField(15);
	JTextField name=new JTextField(15);
	JTextField addr=new JTextField(17);
	JTextField phone1=new JTextField(6);
	JTextField phone2=new JTextField(6);
	JTextField phone3=new JTextField(6);
	
	JButton join=new JButton("회원가입 하기");	
	
	GridBagLayout GB=new GridBagLayout();
	GridBagConstraints GBC=new GridBagConstraints();
	
	//싱글톤
//	public static JoinGui single=null;
//	public static JoinGui getInstance() {
//		if(single==null) {
//			single=new JoinGui();
//		}
//		return single;
//	}
	public JoinGui() {
		
		
		//main frame
		this.getContentPane().setBackground(Color.WHITE);
		setTitle("SignUp");
		setLayout(GB);
		setBounds(420 , 300 , 450 , 500);
		
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
		//제목
		GBadd(title,0,0,0,1);
		//아이디
		GBadd(idTitle,0,1,1,1);
		GBadd(id,1,1,3,1);
		//비밀번호
		GBadd(pwTitle,0,2,1,1);
		GBadd(pw,1,2,3,1);
		//이름
		GBadd(nameTitle,0,3,1,1);
		GBadd(name,1,3,3,1);
		//전화
		JPanel p=new JPanel(new FlowLayout(FlowLayout.LEFT));
		p.setBackground(Color.WHITE);
		p.add(phone1);
		p.add(new JLabel(" - "));
		p.add(phone2);
		p.add(new JLabel(" - "));
		p.add(phone3);
		GBadd(phoneTitle,0,4,1,1);
		GBadd(p,1,4,3,1);
		//주소
		GBadd(addrTitle,0,5,1,1);
		GBadd(addr,1,5,3,1);
		//버튼
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
			if(id.getText().equals("") || pw.getText().equals("") || name.getText().equals("")) {
				nullMessage();
			}else {
				if(SF.idCheck(id.getText())) {//아이디 유효성 충족
					if(SF.pwCheck(pw.getText())) {//비밀번호 유효성 충족
						if(userDao.selectId(this.id.getText())==0) {//아이디 중복 충족
							UserinfoDto userDto=new UserinfoDto();
							userDto.setName(name.getText());
							userDto.setId(id.getText());
							userDto.setPw(pw.getText());
							userDto.setAddr(addr.getText());
							userDto.setPhone(phone1.getText()+phone2.getText()+phone3.getText());
							userDao.insert(userDto);
							System.out.println("회원가입 완료");
							message();
						}else {//아아디 중복 불충족
							idMessage();
						}
					}else {
						pwCheck();
					}
				}else {//아이디 유효성 불충족
					idCheck();
				}
			}
			//로그인 페이지로 이동
			//아이디 유효성 검사
		}
	}

	//경고안내창
	public void message() {
		LoginGui.messageFont();
		int answer=JOptionPane.showConfirmDialog
				(null, "회원가입이 완료되었습니다.\n 로그인하시겠습니까?", "login",JOptionPane.YES_NO_OPTION);
		if(answer==JOptionPane.YES_OPTION) {
			System.out.println("로그인화면");
			setVisible(false);//회원가입창 닫기
			new LoginGui();
		}else if(answer==JOptionPane.NO_OPTION) {
			System.out.println("메인화면");
			setVisible(false);//회원가입창 닫기
		}
	}
	//아이디 중복 안내창
	public void idMessage() {
		JOptionPane.showMessageDialog(null,"중복된 아이디입니다. \n 다시입력해주세요.");
	}
	//아이디 또는 비밀번호가 null일때 경고창
	public void nullMessage() {
		JOptionPane.showMessageDialog
		(null, "필수기재사항을 확인해주세요.","join failed",JOptionPane.WARNING_MESSAGE);
	}
	//아이디 유효성 안내창
	public void idCheck() {
		JOptionPane.showMessageDialog(null,"아이디 영문/숫자 포함 5~10글자. \n 다시입력해주세요.");
	}
	//비밀번호 유효성 안내창
	public void pwCheck() {
		JOptionPane.showMessageDialog(null,"비밀번호 영문/숫자/특수문자 포함 5~10글자. \n 다시입력해주세요.");
	}
}
