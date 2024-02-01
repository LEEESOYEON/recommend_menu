package gui;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.UIManager;

import dao.MypageDao;
import dao.ShopDao;
import dto.ShopDto;

public class ShopGui extends JFrame implements ActionListener,MouseListener,ItemListener{
//마이페이지에서 장보기
	int sum=0;
	
	ShopDao SD=new ShopDao();
	MypageDao MD=new MypageDao();
	
	JPanel panel1=new JPanel(null);//스크롤+장바구니 버튼+삭제하기 버튼
	JPanel panel2=new JPanel(null);//장보기 정보
	
	JLabel priceInfo=new JLabel("  현재  ' " +sum+" ' 원 입니다.");
	
	//장바구니 버튼
	ImageIcon shopIcon=new ImageIcon(
			ShopGui.class.getResource("/image/shoppingCart.png")
	);
	Image shopImage=shopIcon.getImage();
	Image shopSize=shopImage.getScaledInstance(60, 60 ,Image.SCALE_SMOOTH);
	ImageIcon shopLabel=new ImageIcon(shopSize);
	JButton shopButton=new JButton("장바구니",shopLabel);
	
	//추가하기 버튼
	ImageIcon plusIcon=new ImageIcon(
			ShopGui.class.getResource("/image/shoppingPlus.png")
	);
	Image plusImage=plusIcon.getImage();
	Image plusSize=plusImage.getScaledInstance(60, 60 ,Image.SCALE_SMOOTH);
	ImageIcon plusLabel=new ImageIcon(plusSize);
	JButton plusButton=new JButton("추가하기",plusLabel);
	
	//제목 이미지
	ImageIcon titleIcon=new ImageIcon(
			ShopGui.class.getResource("/image/shop.jpg")
	);
	Image titleImage=titleIcon.getImage();
	Image titleSize=titleImage.getScaledInstance(280, 80 ,Image.SCALE_SMOOTH);
	ImageIcon titleLabel=new ImageIcon(titleSize);
	JLabel title=new JLabel(titleLabel);
	
	//뒤로가기 버튼->창닫기=메인메뉴(MypageGui)
	ImageIcon arrowIcon=new ImageIcon(
			ViewBoardGui.class.getResource("/image/arrow.png")
	);
	Image arrowImage=arrowIcon.getImage();
	Image arrowSize=arrowImage.getScaledInstance(30,30,Image.SCALE_SMOOTH);
	ImageIcon arrowLabel=new ImageIcon(arrowSize);
	JButton arrowButton=new JButton(arrowLabel);
	
	HashMap<String,Integer> shop=new HashMap<String,Integer>();

	//싱글톤
//	public static ShopGui single=null;
//	public static ShopGui getInstance() {
//		if(single==null) {
//			single=new ShopGui();
//		}
//		return single;
//	}
	
	public ShopGui() {
		priceInfo.setFont(LoginGui.font2);
		priceInfo.setForeground(LoginGui.orange);
		
		shopButton.setContentAreaFilled(false);//채우기 없음
		shopButton.setBorderPainted(false);//외곽선 없음
		plusButton.setContentAreaFilled(false);
		plusButton.setBorderPainted(false);
		
		shopButton.setVerticalTextPosition(SwingConstants.TOP);
		shopButton.setHorizontalTextPosition(SwingConstants.CENTER);
		shopButton.setFont(LoginGui.font2);
		shopButton.setForeground(LoginGui.orange);
		
		plusButton.setVerticalTextPosition(SwingConstants.TOP);
		plusButton.setHorizontalTextPosition(SwingConstants.CENTER);
		plusButton.setFont(LoginGui.font2);
		plusButton.setForeground(LoginGui.orange);
		
		arrowButton.setBounds(10,10,40,40);
		arrowButton.setBorderPainted(false);//외곽선X
		arrowButton.setContentAreaFilled(false);//채우기X
		arrowButton.setFocusPainted(false);//키보드 포커스 외곽선
		
		//main frame
		setTitle("Shop");
		this.setBounds(450,300,550,500);
		this.setLayout(null);
		this.getContentPane().setBackground(Color.white);
		
		title.setBounds(130,0,280,80);
		
		//panel1
		panel1.setLayout(null);
		panel1.setBackground(LoginGui.green);
		panel1.setBounds(30,90,470,357);
		shopButton.setBounds(330,75,130,90);
		plusButton.setBounds(330,190,130,90);
		
		//panel2
		panel2.setLayout(new GridLayout(20,1));
		shop();
		panel2.add(priceInfo);
		
		//scroll
		JScrollPane scroll=new JScrollPane(panel2);
		scroll.setBounds(20,15,300,320);
		scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		
		panel1.add(scroll);
		panel1.add(shopButton);
		panel1.add(plusButton);
		
		shopButton.addActionListener(this);
		plusButton.addActionListener(this);		
		arrowButton.addActionListener(this);
		
		arrowButton.setToolTipText("뒤로가기");
		
		arrowButton.addMouseListener(this);
		shopButton.addMouseListener(this);
		plusButton.addMouseListener(this);
		for(int i=0; i<boxList.size(); i++) {
			boxList.get(i).addItemListener(this);
		}
		
		this.add(arrowButton);
		this.add(title);
		this.add(panel1);
		
		this.setVisible(true);
	}

	ArrayList<JCheckBox> boxList=new ArrayList<>();//체크박스 저장
	ArrayList<ShopDto> shopDto=SD.selectAll();
	//장보기 등록 물품 가져오기
	public void shop() {
		for(ShopDto s : shopDto) {
			shop.put(s.getGoods() , s.getPrice());
			JCheckBox checkBox=new JCheckBox(s.getGoods()+"  "+s.getPrice()+" 원");
			checkBox.setFont(LoginGui.font2);
			panel2.add(checkBox);
			boxList.add(checkBox);
		}
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==shopButton) {
			//장바구니
			//ShoppingGui.getInstance();
			new ShoppingGui();
		}else if(e.getSource()==plusButton) {
			//장바구니에 추가
			insertMypage(this.index);
		}
		if(e.getSource()==arrowButton) {
			this.setVisible(false);
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {//마우스가 버튼을 클릭했을 때
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {//마우스가 버튼위에 위치했을 때
		if(e.getSource()==shopButton) {
			shopButton.setContentAreaFilled(true);
			shopButton.setBackground(new Color(238,223,124));
		}else if(e.getSource()==plusButton) {
			plusButton.setContentAreaFilled(true);
			plusButton.setBackground(new Color(238,223,124));
		}else if(e.getSource()==arrowButton) {
			arrowButton.setContentAreaFilled(true);
			arrowButton.setBackground(LoginGui.yellow);
		}
	}

	@Override
	public void mouseExited(MouseEvent e) {//마우스가 버튼위에서 벗어났을 때
		shopButton.setContentAreaFilled(false);
		plusButton.setContentAreaFilled(false);	
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

	ArrayList<Integer> index=new ArrayList<>();//장바구니에 추가하기 위한 인덱스를 저장
	@Override
	public void itemStateChanged(ItemEvent e) {
		for(int i=0; i<boxList.size(); i++) {
			final int index=i;
			if(e.getStateChange()==ItemEvent.SELECTED) {//체크했을때
				if(e.getItem()==boxList.get(index)) {
					sum+=shop.get(shopDto.get(index).getGoods());
					this.index.add(index);
				}
			}else if(e.getStateChange()==ItemEvent.DESELECTED) {//체크 취소했을때
				if(e.getItem()==boxList.get(index)) {
					sum-=shop.get(shopDto.get(index).getGoods());
					this.index.remove(this.index.indexOf(index));
				}
			}
		}
		priceInfo.setText("  현재  ' " +sum+" ' 원 입니다.");
	}
	
	//장바구니에 담기
	public void insertMypage(ArrayList<Integer> index) {
		for(int i=0; i<index.size(); i++) {
			if(MD.insert(MenuGui.id,shopDto.get(index.get(i)).getGoods())!=0) {
				if(i==index.size()-1) {//마지막에 한번만
					LoginGui.messageFont();
					JOptionPane.showMessageDialog
					(null,"⋆⁺₊⋆ 장바구니에 추가했습니다.⋆⁺₊⋆","mypage insert success",JOptionPane.PLAIN_MESSAGE);
				}
			}else {
				System.out.println("장바구니 삽입 실패");
			}
		}
	}
	
	
	
	
	
	
}
