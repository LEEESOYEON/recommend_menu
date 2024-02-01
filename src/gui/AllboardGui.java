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
//��ü �� ����
	
		BoardinfoDao BD=new BoardinfoDao();
		
		JPanel mainPanel=new JPanel();//����+ī�װ�+���� ��ư+�ڷΰ��� ��ư
		SpringLayout spring=new SpringLayout();
		
		JPanel cardPanel=new JPanel();//��ũ��
		CardLayout cardLayout=new CardLayout();

		JPanel panel=new JPanel();//�Խù� ����
		
		//�ٹ̱�
		ImageIcon titleIcon=new ImageIcon(
				MyboardGui.class.getResource("/image/everyone.png")
		);
		Image titleImage=titleIcon.getImage();
		Image titleSize=titleImage.getScaledInstance(50, 50 ,Image.SCALE_SMOOTH);
		ImageIcon titleLabel=new ImageIcon(titleSize);
		JLabel title=new JLabel("   ��ü �� ����",titleLabel,JLabel.CENTER);
		
		//�ڷΰ��� ��ư->â�ݱ�=�Խ��Ǹ޴�(BoardMainGui)
		ImageIcon arrowIcon=new ImageIcon(
				ViewBoardGui.class.getResource("/image/arrow.png")
		);
		Image arrowImage=arrowIcon.getImage();
		Image arrowSize=arrowImage.getScaledInstance(30,30,Image.SCALE_SMOOTH);
		ImageIcon arrowLabel=new ImageIcon(arrowSize);
		JButton arrowButton=new JButton(arrowLabel);
		
		//���� ��ư
		JButton viewButton=new JButton("����");
		
		JLabel categoryLabel=new JLabel("ī�װ�");
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
			choice.add("��ü����");
			choice.add("�丮");
			choice.add("����");
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
	        constraints1.setWidth(Spring.constant(100));  // ���� ũ��
	        constraints1.setHeight(Spring.constant(50));  // ���� ũ��
	        springLayout(categoryLabel,50,90,80,50);
	        springLayout(viewButton,260,100,70,30);
	        springLayout(title,165,10,200,50);
	        springLayout(arrowButton,15,10,40,40);
	        arrowButton.setBorderPainted(false);//�ܰ���X
			arrowButton.setContentAreaFilled(false);//ä���X
			arrowButton.setFocusPainted(false);//Ű���� ��Ŀ�� �ܰ���
	        
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
			//add() �� ������ ��������� �� �� �ڼ��� �����̳ʿ��� �˷��ִ� �޼���
			
			viewButton.addActionListener(this);
			arrowButton.addActionListener(this);
			
			arrowButton.setToolTipText(" �ڷΰ��� ");
			
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
		//���� ��������
		public int allBoard() {
			panel.removeAll();
			int y=20;
			int i=1;
			for(BoardinfoDto b : boardDto) {
				JButton button=new JButton(i+"");
				button.setFont(new Font("���ʷҵ���",Font.BOLD,10));
				button.setContentAreaFilled(false);//ä��� ����
				button.setBorderPainted(false);//�ܰ��� ����
				ImageIcon boardIcon=new ImageIcon(
						MyboardGui.class.getResource("/image/boardButton.png")
				);
				Image boardImg=boardIcon.getImage();
				Image boardSize=boardImg.getScaledInstance(40 ,40 ,Image.SCALE_SMOOTH);
				ImageIcon board=new ImageIcon(boardSize);
				button.setIcon(board);
				button.setHorizontalTextPosition(SwingConstants.CENTER); // �ؽ�Ʈ�� ���� ��ġ ����
	 
				panel.add(button);
				button.addActionListener(this);
				
				springLayout(button,50,y,50,50);
				
				JLabel titleLabel=new JLabel("  "+b.getTitle());
				titleLabel.setOpaque(true);//��� �������ϰ�
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
			if(choice.getSelectedItem().equals("��ü����")){
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
