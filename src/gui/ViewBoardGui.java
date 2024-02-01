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
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.UIManager;

import dao.BoardinfoDao;
import dao.UserinfoDao;
import dto.BoardinfoDto;

public class ViewBoardGui extends JFrame implements ActionListener,MouseListener{
//������ �Խ��� �����ֱ�

	BoardinfoDto boardDto;
	BoardinfoDao BD=new BoardinfoDao();	
	UserinfoDao UD=new UserinfoDao();
	
	//�ڷΰ��� ��ư->â�ݱ�
			ImageIcon arrowIcon=new ImageIcon(
					ViewBoardGui.class.getResource("/image/arrow.png")
			);
			Image arrowImage=arrowIcon.getImage();
			Image arrowSize=arrowImage.getScaledInstance(30,30,Image.SCALE_SMOOTH);
			ImageIcon arrowLabel=new ImageIcon(arrowSize);
			JButton arrowButton=new JButton(arrowLabel);
			
	//�����ϱ� ��ư
			ImageIcon deleteIcon=new ImageIcon(
					ViewBoardGui.class.getResource("/image/deleteBoard.png")
			);
			Image deleteImage=deleteIcon.getImage();
			Image deleteSize=deleteImage.getScaledInstance(60,60,Image.SCALE_SMOOTH);
			ImageIcon deleteLabel=new ImageIcon(deleteSize);
			JButton deleteButton=new JButton(deleteLabel);
			
	//�����ϱ� 	��ư
			ImageIcon modifyIcon=new ImageIcon(
					ViewBoardGui.class.getResource("/image/modify.png")
			);
			Image modifyImage=modifyIcon.getImage();
			Image modifySize=modifyImage.getScaledInstance(60,60, Image.SCALE_SMOOTH);
			ImageIcon modifyLabel=new ImageIcon(modifySize);
			JButton modifyButton=new JButton(modifyLabel);
	//���ƿ� ��ư
			ImageIcon likeIcon=new ImageIcon(
					ViewBoardGui.class.getResource("/image/like.png")
			);
			Image likeImage=likeIcon.getImage();
			Image likeSize=likeImage.getScaledInstance(60,60,Image.SCALE_SMOOTH);
			ImageIcon likeLabel=new ImageIcon(likeSize);
			JButton likeButton=new JButton(likeLabel);
;			
	JTextField title=new JTextField();
	JTextArea text=new JTextArea();
	
	String writer;
	
	public ViewBoardGui(BoardinfoDto boardDto,String type) {
		this.boardDto=boardDto;
		
		//main frame
		setTitle("Board viewer");
		this.setBounds(450,300,550,500);
		this.setLayout(null);
		this.getContentPane().setBackground(Color.white);
		
		arrowButton.setContentAreaFilled(false);//ä��� ����
		arrowButton.setBorderPainted(false);//�ܰ��� ����
		arrowButton.setFocusPainted(false);//Ű���� ��Ŀ�� �ܰ���
		
		deleteButton.setContentAreaFilled(false);
		deleteButton.setBorderPainted(false);
		
		modifyButton.setContentAreaFilled(false);
		modifyButton.setBorderPainted(false);
		
		likeButton.setContentAreaFilled(false);
		likeButton.setBorderPainted(false);
		
		JLabel num=new JLabel("NO . "+boardDto.getNum());
		num.setFont(LoginGui.font3);
		num.setForeground(LoginGui.orange);
		
		JLabel writerTitle=new JLabel("< �ۼ��� >");
		writerTitle.setFont(LoginGui.font2);
		writerTitle.setForeground(LoginGui.orange);
		
		JLabel writer=new JLabel(boardDto.getWriterid());
		writer.setFont(LoginGui.font3);
		this.writer=writer.getText();
		
		JLabel titleTitle=new JLabel("< ���� >");
		titleTitle.setFont(LoginGui.font2);
		titleTitle.setForeground(LoginGui.orange);
		
		title.setText(boardDto.getTitle());
		title.setFont(LoginGui.font3);
		
		JLabel textTitle=new JLabel("< ���� >");
		textTitle.setFont(LoginGui.font2);
		textTitle.setForeground(LoginGui.orange);
		
		JLabel categoryTitle=new JLabel("< ī�װ� >");
		categoryTitle.setFont(LoginGui.font2);
		categoryTitle.setForeground(LoginGui.orange);
		
		JLabel category=new JLabel(boardDto.getCategory());
		category.setFont(LoginGui.font3);
		
		arrowButton.setBounds(20,10,40,40);
		num.setBounds(450,10,70,30);
		writerTitle.setBounds(25,65,100,30);
		writer.setBounds(145,65,150,30);
		titleTitle.setBounds(25,115,100,30);
		title.setBounds(145,115,200,30);
		textTitle.setBounds(25,165,100,30);
		categoryTitle.setBounds(380,70,100,30);
		category.setBounds(380,100,100,30);
		deleteButton.setBounds(170,370,90,70);
		modifyButton.setBounds(320,373,90,70);
		likeButton.setBounds(200,370,90,70);
		
		text();
		JScrollPane scroll=new JScrollPane(text);
		scroll.setBounds(145,165,320,170);
		scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scroll.setFont(LoginGui.font2);
		
		this.boardDto.setText(text.getText());
		System.out.println(this.boardDto.getText());
		
		arrowButton.setToolTipText(" �ڷΰ��� ");
		deleteButton.setToolTipText(" �����ϱ� ");
		modifyButton.setToolTipText(" �����ϱ� ");
		likeButton.setToolTipText("���ƿ�");
		
		this.add(arrowButton);
		this.add(num);
		this.add(writerTitle);
		this.add(writer);
		this.add(titleTitle);
		this.add(title);
		this.add(textTitle);
		this.add(scroll);
		this.add(categoryTitle);
		this.add(category);
		if(type.equals("myboard")) {//���� �� ����(���� ����)
			this.add(deleteButton);
			this.add(modifyButton);
		}else if(type.equals("allboard")) {//��ü ����(���� �Ұ���)
			title.setEditable(false);
			text.setEditable(false);
			this.add(likeButton);
		}
		
		arrowButton.addActionListener(this);
		deleteButton.addActionListener(this);
		modifyButton.addActionListener(this);
		likeButton.addActionListener(this);
		
		deleteButton.addMouseListener(this);
		modifyButton.addMouseListener(this);
		likeButton.addMouseListener(this);
		arrowButton.addMouseListener(this);
		
		this.setVisible(true);
	}
	
	//���ڿ� ������
	public void text() {
		String[] reText=null;
		if(boardDto.getText()==null) {
			return;
		}else {
			reText=boardDto.getText().split("\n");
			for(String line : reText) {
				text.append(line+"\n");
			}
		}
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==deleteButton) {
			//�����ϱ�
			LoginGui.messageFont();
			int answer=JOptionPane.showConfirmDialog
					(null,"���� �����Ͻðڽ��ϱ�?","delete board",JOptionPane.YES_NO_OPTION);
			if(answer==JOptionPane.YES_OPTION) {
				BD.delete(boardDto.getNum());
				LoginGui.messageFont();
				JOptionPane.showMessageDialog
				(null,"�Խ��ǿ��� �����߽��ϴ�. ","board delete success",JOptionPane.PLAIN_MESSAGE);
				this.setVisible(false);
				MyboardGui.myboardGuiInstance.setVisible(false);
				new MyboardGui();
			}else {
				
			}
		}else if(e.getSource()==modifyButton) {
			//�����ϱ�
			LoginGui.messageFont();
			int answer=JOptionPane.showConfirmDialog
					(null,"�����Ͻðڽ��ϱ�?","modify board",JOptionPane.YES_NO_OPTION);
			if(answer==JOptionPane.YES_OPTION) {
				this.boardDto.setTitle(title.getText());
				this.boardDto.setText(text.getText());
				BD.update(this.boardDto);
				LoginGui.messageFont();
				JOptionPane.showMessageDialog
				(null,"������ ������ �����߽��ϴ�.","board modify success",JOptionPane.PLAIN_MESSAGE);
				this.setVisible(false);
				MyboardGui.myboardGuiInstance.setVisible(false);
				new MyboardGui();
			}else {
				
			}
		}else if(e.getSource()==arrowButton) {
			this.setVisible(false);
			//new MyboardGui();
		}else if(e.getSource()==likeButton) {
			if(MenuGui.id.equals(this.writer)) {
				LoginGui.messageFont();
				JOptionPane.showMessageDialog
				(null,"������ �ۼ��� �Խù��� \n���ƿ䰡 �Ұ����մϴ�.","like falied",JOptionPane.PLAIN_MESSAGE);
			}else {
				UD.updatePoint(this.writer);
			}
		}
	}


	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void mouseEntered(MouseEvent e) {
		if(e.getSource()==deleteButton) {
			deleteButton.setContentAreaFilled(true);
			deleteButton.setBackground(LoginGui.yellow);
		}else if(e.getSource()==modifyButton) {
			modifyButton.setContentAreaFilled(true);
			modifyButton.setBackground(LoginGui.yellow);
		}else if(e.getSource()==likeButton) {
			likeButton.setContentAreaFilled(true);
			likeButton.setBackground(LoginGui.yellow);
		}else if(e.getSource()==arrowButton) {
			arrowButton.setContentAreaFilled(true);
			arrowButton.setBackground(LoginGui.yellow);
		}
	}


	@Override
	public void mouseExited(MouseEvent e) {
		deleteButton.setContentAreaFilled(false);
		modifyButton.setContentAreaFilled(false);
		likeButton.setContentAreaFilled(false);
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
