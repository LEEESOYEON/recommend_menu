package gui;

import java.awt.CardLayout;
import java.awt.Choice;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;

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

public class AllboardGui extends JFrame implements ActionListener,ItemListener,MouseListener{
//전체 글 보기
	
		BoardinfoDao BD=new BoardinfoDao();
		
		JPanel mainPanel=new JPanel();//제목+카테고리+보기 버튼+뒤로가기 버튼
		SpringLayout spring=new SpringLayout();
		
		JPanel cardPanel=new JPanel();//스크롤
		CardLayout cardLayout=new CardLayout();

		JPanel panel=new JPanel();//게시물 정보
		
		//꾸미기
		ImageIcon titleIcon=new ImageIcon(
				MyboardGui.class.getResource("/image/everyone.png")
		);
		Image titleImage=titleIcon.getImage();
		Image titleSize=titleImage.getScaledInstance(50, 50 ,Image.SCALE_SMOOTH);
		ImageIcon titleLabel=new ImageIcon(titleSize);
		JLabel title=new JLabel("   전체 글 보기",titleLabel,JLabel.CENTER);
		
		//뒤로가기 버튼->창닫기=게시판메뉴(BoardMainGui)
		ImageIcon arrowIcon=new ImageIcon(
				ViewBoardGui.class.getResource("/image/arrow.png")
		);
		Image arrowImage=arrowIcon.getImage();
		Image arrowSize=arrowImage.getScaledInstance(30,30,Image.SCALE_SMOOTH);
		ImageIcon arrowLabel=new ImageIcon(arrowSize);
		JButton arrowButton=new JButton(arrowLabel);
		
		//보기 버튼
		JButton viewButton=new JButton("보기");
		
		JLabel categoryLabel=new JLabel("카테고리");
		Choice choice=new Choice();
		
		public AllboardGui() {
			//main frame
			setTitle("All board");
			this.setBounds(450,300,550,500);
			this.getContentPane().setBackground(Color.white);
			
			//mainPanel
			mainPanel.setLayout(spring);
			mainPanel.setBackground(Color.white);
			mainPanel.setPreferredSize(new Dimension(100, 130));
			
			choice.add("");
			choice.add("전체보기");
			choice.add("요리");
			choice.add("수다");
			choice.setFont(LoginGui.font2);
			
			categoryLabel.setFont(LoginGui.font2);
			categoryLabel.setForeground(new Color(208,101,91));

			viewButton.setFont(LoginGui.font2);
			viewButton.setForeground(new Color(255,234,206));
			viewButton.setBackground(new Color(208,101,91));
			viewButton.setBorderPainted(false);
			
			title.setFont(LoginGui.font4);
			title.setForeground(LoginGui.orange);

			mainPanel.add(categoryLabel);
			mainPanel.add(choice);
			mainPanel.add(viewButton);
			mainPanel.add(title);
			mainPanel.add(arrowButton);
			
			SpringLayout.Constraints constraints1=spring.getConstraints(choice);
			constraints1.setX(Spring.constant(140));
	        constraints1.setY(Spring.constant(90));
	        constraints1.setWidth(Spring.constant(100));  // 가로 크기
	        constraints1.setHeight(Spring.constant(50));  // 세로 크기
	        springLayout(categoryLabel,50,90,80,50);
	        springLayout(viewButton,260,100,70,30);
	        springLayout(title,165,10,200,50);
	        springLayout(arrowButton,15,10,40,40);
	        arrowButton.setBorderPainted(false);//외곽선X
			arrowButton.setContentAreaFilled(false);//채우기X
			arrowButton.setFocusPainted(false);//키보드 포커스 외곽선
	        
			//panel
	        panel.setLayout(spring);
	        panel.setBackground(LoginGui.green);
	        panel.setPreferredSize(new Dimension(400,size));
	        
	        //scroll
	        JScrollPane scroll=new JScrollPane(null);
	        scroll.setViewportView(panel);
	        scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
	        scroll.setBorder(BorderFactory.createEmptyBorder(10, 30, 30, 30));
	        scroll.setBackground(Color.white);

	        //cardPanel
	        cardPanel.setLayout(cardLayout);
	        cardPanel.setBorder(new EmptyBorder(10,10,10,10));
	        cardPanel.setBackground(Color.white);
//	        cardPanel.add(start,"1");
	        cardPanel.add(scroll,"1");
			
			this.getContentPane().add(mainPanel,"North");
			this.getContentPane().add(cardPanel,"Center");
			//add() 와 동일한 기능이지만 좀 더 자세히 컨테이너에게 알려주는 메서드
			
			viewButton.addActionListener(this);
			arrowButton.addActionListener(this);
			
			arrowButton.setToolTipText(" 뒤로가기 ");
			
			arrowButton.addMouseListener(this);
			
			choice.addItemListener(this);
			
			this.setVisible(true);
		}
		
		@Override
		public void actionPerformed(ActionEvent e) {
			if(e.getSource()==viewButton) {
				panel.revalidate();
				choice.addItemListener(null);
				System.out.println(size);
				panel.setPreferredSize(new Dimension(400,size));
				cardLayout.next(cardPanel);
			}
			for(Entry<JButton,BoardinfoDto> entry : buttonMap.entrySet()) {
				if(e.getSource()==entry.getKey()) {
					new ViewBoardGui(entry.getValue(),"allboard");
				}
			}
			if(e.getSource()==arrowButton) {
				this.setVisible(false);
			}
			
		}
	
		HashMap<JButton,BoardinfoDto> buttonMap=new HashMap<>();
		ArrayList<BoardinfoDto> boardDto;
		//정보 가져오기
		public int allBoard() {
			panel.removeAll();
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

				buttonMap.put(button, b);
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
		int size=400;
		
		@Override
		public void itemStateChanged(ItemEvent e) {
			if(choice.getSelectedItem().equals("전체보기")){
				boardDto=BD.selectAll();
				this.size=allBoard();
			}else {
				boardDto=BD.select("category", choice.getSelectedItem());
				System.out.println(choice.getSelectedItem());
				this.size=allBoard();
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

		

}
