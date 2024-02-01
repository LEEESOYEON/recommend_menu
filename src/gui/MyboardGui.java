package gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.Spring;
import javax.swing.SpringLayout;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import dao.BoardinfoDao;
import dto.BoardinfoDto;

public class MyboardGui extends JFrame implements ActionListener, MouseListener{
//나의 글 보기
	
	BoardinfoDao BD=new BoardinfoDao();
	
	JPanel mainPanel=new JPanel();
	JPanel panel=new JPanel();
	SpringLayout spring=new SpringLayout();
	//icon-1
	ImageIcon titleIcon=new ImageIcon(
			MyboardGui.class.getResource("/image/one.png")
	);
	Image titleImage=titleIcon.getImage();
	Image titleSize=titleImage.getScaledInstance(50, 50 ,Image.SCALE_SMOOTH);
	ImageIcon titleLabel=new ImageIcon(titleSize);
	JLabel title=new JLabel("' "+MenuGui.id+" ' 님의 작성글",titleLabel,JLabel.CENTER);
	//뒤로가기 버튼->창닫기=게시판메뉴(BoardMainGui)
	ImageIcon arrowIcon=new ImageIcon(
			ViewBoardGui.class.getResource("/image/arrow.png")
	);
	Image arrowImage=arrowIcon.getImage();
	Image arrowSize=arrowImage.getScaledInstance(30,30,Image.SCALE_SMOOTH);
	ImageIcon arrowLabel=new ImageIcon(arrowSize);
	JButton arrowButton=new JButton(arrowLabel);
	
	static MyboardGui myboardGuiInstance;
	
	public MyboardGui() {
		myboardGuiInstance=this;
		
		//main frame
		setTitle("My board");
		this.setBounds(450,300,550,500);
		this.getContentPane().setBackground(Color.white);
		
		//mainPanel
		mainPanel.setLayout(spring);
		mainPanel.setBackground(Color.WHITE);
		mainPanel.setPreferredSize(new Dimension(200,100));
		
		title.setFont(LoginGui.font4);
		title.setForeground(LoginGui.orange);
		springLayout(title,80,35,400,50);
		
		springLayout(arrowButton,15,10,40,40);
        arrowButton.setBorderPainted(false);//외곽선X
		arrowButton.setContentAreaFilled(false);//채우기X
		arrowButton.setFocusPainted(false);//키보드 포커스 외곽선
		
		mainPanel.add(title);
		mainPanel.add(arrowButton);
		
		//panel
		panel.setLayout(spring);
		panel.setBackground(LoginGui.green);
		panel.setPreferredSize(new Dimension(400,myboard()));
		//scroll
		//panel의 Layout이 springLayout이기 때문에 scroll자동생성X
		//(panel의 크기를 알 수 없기 때문)
		//따라서, panel의 크기를 임의로 지정 후 scroll 부착
		JScrollPane scroll=new JScrollPane(null);
		scroll.setViewportView(panel);
		scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
		scroll.setBorder(BorderFactory.createEmptyBorder(50, 50, 30, 50));
		scroll.setBackground(Color.white);
		
		arrowButton.addActionListener(this);
		
		arrowButton.setToolTipText(" 뒤로가기 ");
		
		arrowButton.addMouseListener(this);
		
		this.add(mainPanel,"North");
		this.add(scroll,"Center");
		
		this.setVisible(true);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		for(int i=0; i<buttonList.size(); i++) {
			if(e.getSource()==buttonList.get(i)) {
				System.out.println(buttonList.get(i).getText());
				System.out.println(boardDto.get(i).getTitle());
				new ViewBoardGui(boardDto.get(i),"myboard");
			}
		}
		if(e.getSource()==arrowButton) {
			this.setVisible(false);
		}
	}
	
	ArrayList<JButton> buttonList=new ArrayList<>();
	ArrayList<BoardinfoDto> boardDto=BD.select("writerid",MenuGui.id);
	//정보 가져오기
	public int myboard() {
		int y=20;
		int i=1;
		for(BoardinfoDto b : boardDto) {
			JButton button=new JButton(i+"");
			button.setFont(new Font("함초롬돋움",Font.BOLD,10));
			button.setContentAreaFilled(false);//채우기 없음
			button.setBorderPainted(false);//외각선 없음
			ImageIcon boardIcon=new ImageIcon(
					MyboardGui.class.getResource("/image/boardButton.png")
			);
			Image boardImg=boardIcon.getImage();
			Image boardSize=boardImg.getScaledInstance(40 ,40 ,Image.SCALE_SMOOTH);
			ImageIcon board=new ImageIcon(boardSize);
			button.setIcon(board);
			button.setHorizontalTextPosition(SwingConstants.CENTER); // 텍스트의 가로 위치 조정
 
			panel.add(button);
			button.addActionListener(this);
			button.addMouseListener(this);
			
			springLayout(button,50,y,50,50);
			
			JLabel titleLabel=new JLabel("  "+b.getTitle());
			titleLabel.setOpaque(true);//배경 불투명하게
			titleLabel.setBackground(Color.white);
			titleLabel.setFont(LoginGui.font2);
			
			panel.add(titleLabel);
			
			springLayout(titleLabel,125,y,230,40);
			
			JLabel categoryLabel=new JLabel(" "+b.getCategory());
			categoryLabel.setBackground(Color.white);
			categoryLabel.setFont(LoginGui.font2);
			
			panel.add(categoryLabel);
			
			springLayout(categoryLabel,370,y,50,40);
			
			buttonList.add(button);
			y+=60;
			i++;
		}
		return 64*boardDto.size();
	}
	
	//springLayout
	public void springLayout(JComponent c,int x,int y,int w,int h) {
		SpringLayout.Constraints constraints=spring.getConstraints(c);
		constraints.setX(Spring.constant(x));
		constraints.setY(Spring.constant(y));
		constraints.setWidth(Spring.constant(w));
		constraints.setHeight(Spring.constant(h));
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		for(int i=0; i<buttonList.size(); i++) {
			if(e.getSource()==buttonList.get(i)) {
				buttonList.get(i).setContentAreaFilled(true);
				buttonList.get(i).setBackground(LoginGui.yellow);
			}
		}
		if(e.getSource()==arrowButton) {
			arrowButton.setContentAreaFilled(true);
			arrowButton.setBackground(LoginGui.yellow);
		}
	}

	@Override
	public void mouseExited(MouseEvent e) {
		for(int i=0; i<buttonList.size(); i++) {
			if(e.getSource()==buttonList.get(i)) {
				buttonList.get(i).setContentAreaFilled(false);
			}
		}
		arrowButton.setContentAreaFilled(false);
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	
}
