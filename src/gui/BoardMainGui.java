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
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class BoardMainGui extends JFrame implements ActionListener,MouseListener{
//�Խ��� �޴�
	
		JPanel panel=new JPanel();//���ۼ�+���� �Խù� ��ư+��ü �Խù� ��ư+�ڷΰ��� ��ư

		//���� �̹���
		ImageIcon titleIcon=new ImageIcon(
				BoardMainGui.class.getResource("/image/boardTitle.PNG")
		);
		Image titleImage=titleIcon.getImage();
		Image titleSize=titleImage.getScaledInstance(330, 60 ,Image.SCALE_SMOOTH);
		ImageIcon titleLabel=new ImageIcon(titleSize);
		JLabel title=new JLabel(titleLabel);
		//���� �� ���� ��ư �̹���
		ImageIcon myIcon=new ImageIcon(
				BoardMainGui.class.getResource("/image/myboard.PNG")
		);
		Image myImg=myIcon.getImage();
		Image mySize=myImg.getScaledInstance(150 ,90 , Image.SCALE_SMOOTH);
		ImageIcon my=new ImageIcon(mySize);
		JButton myboardButton=new JButton(my);
		//���ۼ� ��ư �̹���
		ImageIcon boardIcon=new ImageIcon(
				BoardMainGui.class.getResource("/image/board.PNG")
		);
		Image boardImg=boardIcon.getImage();
		Image boardSize=boardImg.getScaledInstance(150 ,90 ,Image.SCALE_SMOOTH);
		ImageIcon board=new ImageIcon(boardSize);
		JButton boardButton=new JButton(board);
		//��ü���� ��ư �̹���
		ImageIcon viewIcon=new ImageIcon(
				BoardMainGui.class.getResource("/image/view.PNG")
		);
		Image viewImage=viewIcon.getImage();
		Image viewSize=viewImage.getScaledInstance(150,  90 , Image.SCALE_SMOOTH);
		ImageIcon view=new ImageIcon(viewSize);
		JButton viewButton=new JButton(view);
		//�ٹ̱�
		ImageIcon icon2=new ImageIcon(
				BoardMainGui.class.getResource("/image/main.PNG")
		);
		Image img2=icon2.getImage();
		Image sizeImg2=img2.getScaledInstance(550,70,Image.SCALE_SMOOTH);
		ImageIcon imgIcon2=new ImageIcon(sizeImg2);
		JLabel image2=new JLabel(imgIcon2);
		//�ڷΰ��� ��ư->â�ݱ�=����������(MypageGui)
		ImageIcon arrowIcon=new ImageIcon(
				ViewBoardGui.class.getResource("/image/arrow.png")
		);
		Image arrowImage=arrowIcon.getImage();
		Image arrowSize=arrowImage.getScaledInstance(30,30,Image.SCALE_SMOOTH);
		ImageIcon arrowLabel=new ImageIcon(arrowSize);
		JButton arrowButton=new JButton(arrowLabel);
		
		public BoardMainGui() {
			//main frame
			setTitle("Main board menu");
			this.setBounds(450 , 300 , 550 , 500);
			this.getContentPane().setBackground(Color.WHITE);
			
			title.setBorder(new EmptyBorder(20,0,0,0));
			title.setHorizontalAlignment(JLabel.CENTER);
			
			//panel
			panel.setLayout(null);
			panel.setBackground(Color.white);
			boardButton.setBounds(190 ,45 , 150, 60);
			myboardButton.setBounds(190, 115, 150, 60);
			viewButton.setBounds(190, 185, 150, 60);
			arrowButton.setBounds(15,270,40,40);
			arrowButton.setBorderPainted(false);//�ܰ���X
			arrowButton.setContentAreaFilled(false);//ä���X
			boardButton.setBorderPainted(false);
			myboardButton.setBorderPainted(false);
			viewButton.setBorderPainted(false);
			
			panel.add(boardButton);
			panel.add(myboardButton);
			panel.add(viewButton);
			panel.add(arrowButton);
			
			this.add(title , "North");
			this.add(panel , "Center");
			this.add(image2 , "South");
			
			boardButton.addActionListener(this);
			myboardButton.addActionListener(this);
			viewButton.addActionListener(this);
			arrowButton.addActionListener(this);
			
			arrowButton.setToolTipText(" �ڷΰ��� ");
			
			arrowButton.addMouseListener(this);
			
			this.setVisible(true);
			this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		}
		
		@Override
		public void actionPerformed(ActionEvent e) {
			if(e.getSource()==boardButton) {
				//�� �ۼ�
				new BoardGui();
			}else if(e.getSource()==myboardButton) {
				//���� �� ����
				new MyboardGui();
			}else if(e.getSource()==viewButton) {
				//��ü����
				new AllboardGui();
			}	
			if(e.getSource()==arrowButton) {//�ڷΰ���
				this.setVisible(false);
				new MypageGui();
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
