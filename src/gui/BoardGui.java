package gui;

import java.awt.Choice;
import java.awt.Color;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;

import dao.BoardinfoDao;
import dao.MenuinfoDao;
import dao.UserinfoDao;
import dto.BoardinfoDto;

public class BoardGui extends JFrame implements ActionListener,ItemListener,MouseListener{
//글 작성
	
	BoardinfoDao BD=new BoardinfoDao();
	UserinfoDao UD=new UserinfoDao();
	MenuinfoDao MD=new MenuinfoDao();
	
	JLabel writerTitle=new JLabel("< 작성자 >");
	JLabel writerId=new JLabel(MenuGui.id);
	JLabel titleTitle=new JLabel("< 제목 (10글자 이내) > ");//10글자
	JTextField title=new JTextField(10);
	Choice titleChoice=new Choice();
	JLabel categoryTitle=new JLabel("< 카테고리 >");
	JLabel category=new JLabel();
	Choice choice=new Choice();
	JLabel textTitle=new JLabel("<html>내용<br/>(250자 이내) : </html>");
	JTextArea text=new JTextArea();
	
	//등록하기 버튼
	JButton addButton=new JButton("등록하기");
	
	//뒤로가기 버튼->창닫기=게시판메뉴(BoardMainGui)
	ImageIcon arrowIcon=new ImageIcon(
			ViewBoardGui.class.getResource("/image/arrow.png")
	);
	Image arrowImage=arrowIcon.getImage();
	Image arrowSize=arrowImage.getScaledInstance(30,30,Image.SCALE_SMOOTH);
	ImageIcon arrowLabel=new ImageIcon(arrowSize);
	JButton arrowButton=new JButton(arrowLabel);

	public BoardGui() {
		//main frame
		setTitle("Insert board");
		this.setLayout(null);
		this.getContentPane().setBackground(Color.WHITE);
		this.setBounds(420,300,450,500);
		
		arrowButton.setBounds(20,10,40,40);
		arrowButton.setContentAreaFilled(false);//채우기 없음
		arrowButton.setBorderPainted(false);//외각선 없음
		arrowButton.setFocusPainted(false);//키보트 포커스 외곽선
		
		choice.add("");
		choice.add("요리");
		choice.add("수다");
		writerTitle.setBounds(150,20,70,30);
		writerTitle.setFont(LoginGui.font2);
		writerTitle.setForeground(LoginGui.orange);
		
		writerId.setBounds(250,20,250,30);
		writerId.setFont(LoginGui.font3);
		
//		titleTitle.setBounds(20,60,150,30);
//		titleTitle.setFont(LoginGui.font2);
//		titleTitle.setForeground(LoginGui.orange);
//		
//		title.setBounds(200,60,150,30);
//		
//		categoryTitle.setBounds(20,100,100,30);
//		categoryTitle.setFont(LoginGui.font2);
//		categoryTitle.setForeground(LoginGui.orange);
//		
//		choice.setBounds(200,100,70,30);
//		choice.setFont(LoginGui.font2);
//		choice.setForeground(LoginGui.green);
		
		categoryTitle.setBounds(20,60,100,30);
		categoryTitle.setFont(LoginGui.font2);
		categoryTitle.setForeground(LoginGui.orange);
		
		choice.setBounds(200,60,70,30);
		choice.setFont(LoginGui.font2);
		choice.setForeground(LoginGui.green);
		
		category.setBounds(300,60,70,30);
		
		titleTitle.setBounds(20,100,150,30);
		titleTitle.setFont(LoginGui.font2);
		titleTitle.setForeground(LoginGui.orange);
		
		title.setBounds(200,100,150,30);
		titleChoice.setBounds(200,100,150,30);
		titleChoice.add("");
		menuNameChoice();
		
		textTitle.setBounds(20,140,100,30);
		textTitle.setFont(LoginGui.font2);
		textTitle.setForeground(LoginGui.orange);
		
		//scroll
		text.setWrapStyleWord(true);//엔터를 입력하면 줄바꿈이 될 수 있게 하는 기능
		JScrollPane scroll=new JScrollPane(text);
		scroll.setBounds(150,140,250,200);
		scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
		
		addButton.setBounds(180,365,100,50);
		addButton.setFont(LoginGui.font2);
		
		this.add(arrowButton);
		this.add(writerTitle);
		this.add(writerId);
		this.add(titleTitle);
//		this.add(title);
		this.add(categoryTitle);
		this.add(choice);
		this.add(category);
		this.add(textTitle);
		this.add(scroll);
		this.add(addButton);
		
		arrowButton.setToolTipText(" 뒤로가기 ");
		
		arrowButton.addActionListener(this);
		addButton.addActionListener(this);
		
		arrowButton.addMouseListener(this);
		
		choice.addItemListener(this);
		titleChoice.addItemListener(this);
		
		this.setVisible(true);
	}
			
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==addButton) {
			if(category.getText().equals("수다")) {
				if(title.getText().length()>30) {
					LoginGui.messageFont();
					JOptionPane.showMessageDialog
					(null," 제목은 30글자 미만까지 가능합니다. ","title failed",JOptionPane.ERROR_MESSAGE);
				}else if(title.getText().length()<=0) {
					LoginGui.messageFont();
					JOptionPane.showMessageDialog
					(null," 제목을 입력해주세요. ","title failed",JOptionPane.ERROR_MESSAGE);
				}
			}else if(category.getText().equals("요리")) {
				
			}
			if(text.getText().length()>250) {
				LoginGui.messageFont();
				JOptionPane.showMessageDialog
				(null," 글자수 제한을 확인해주세요. ","text failed",JOptionPane.ERROR_MESSAGE);
			}else {
				BoardinfoDto boardDto=new BoardinfoDto();
				boardDto.setWriterid(writerId.getText());
				boardDto.setCategory(category.getText());
				if(category.getText().equals("요리")) {
					boardDto.setTitle(titleChoice.getSelectedItem());
				}else {
					boardDto.setTitle(title.getText());
				}
				boardDto.setText(text.getText());
				BD.insert(boardDto);
				UD.updatePoint(MenuGui.id);
				LoginGui.messageFont();
				JOptionPane.showMessageDialog
				(null,"☆ 글 등록이 되었습니다. ☆","board insert success",JOptionPane.PLAIN_MESSAGE);
				this.setVisible(false);
			}
		}else if(e.getSource()==arrowButton) {
			this.setVisible(false);
		}
		
	}

	@Override
	public void itemStateChanged(ItemEvent e) {
		category.setText(choice.getSelectedItem());
		this.revalidate();
		this.repaint();
		if(choice.getSelectedItem().equals("요리")) {
			this.remove(title);
			this.add(titleChoice);
		}else {
			this.remove(titleChoice);
			this.add(title);
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
	
	//메뉴이름가져오기
	public void menuNameChoice() {
		ArrayList<String> menuNameList=MD.selectMenuname();
		for(String menuName : menuNameList) {
			titleChoice.add(menuName);
		}
	}
}
