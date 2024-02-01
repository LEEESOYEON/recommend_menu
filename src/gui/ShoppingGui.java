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
import dto.ShoppingDto;

public class ShoppingGui extends JFrame implements ActionListener,MouseListener,ItemListener{
	//마이페이지에서 장바구니
		int sum=0;
		
		ShopDao SD=new ShopDao();
		MypageDao MD=new MypageDao();
		
		JPanel panel1=new JPanel(null);//스크롤+주문하기 버튼+삭제하기 버튼
		JPanel panel2=new JPanel(null);//장바구니 정보
		
		JLabel priceInfo=new JLabel("  현재  ' " +sum+" ' 원 입니다.");
		
		//주문하기 버튼
		ImageIcon deliveryIcon=new ImageIcon(
				ShopGui.class.getResource("/image/delivery.png")
		);
		Image deliveryImage=deliveryIcon.getImage();
		Image deliverySize=deliveryImage.getScaledInstance(60, 60 ,Image.SCALE_SMOOTH);
		ImageIcon deliveryLabel=new ImageIcon(deliverySize);
		JButton deliveryButton=new JButton("주문하기",deliveryLabel);
		
		//삭제하기 버튼
		ImageIcon deleteIcon=new ImageIcon(
				ShopGui.class.getResource("/image/delete.png")
		);
		Image deleteImage=deleteIcon.getImage();
		Image deleteSize=deleteImage.getScaledInstance(60, 60 ,Image.SCALE_SMOOTH);
		ImageIcon deleteLabel=new ImageIcon(deleteSize);
		JButton deleteButton=new JButton("삭제하기",deleteLabel);
		
		//제목 이미지
		ImageIcon titleIcon=new ImageIcon(
				ShopGui.class.getResource("/image/shopping.jpg")
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

		//싱글톤
//		public static ShoppingGui single=null;
//		public static ShoppingGui getInstance() {
//			if(single==null) {
//				single=new ShoppingGui();
//			}
//			return single;
//		}
		
		public ShoppingGui() {
			priceInfo.setFont(LoginGui.font2);
			priceInfo.setForeground(LoginGui.orange);
			
			deliveryButton.setContentAreaFilled(false);//채우기 없음
			deliveryButton.setBorderPainted(false);//외곽선 없음
			deleteButton.setContentAreaFilled(false);
			deleteButton.setBorderPainted(false);
			
			deliveryButton.setVerticalTextPosition(SwingConstants.TOP);
			deliveryButton.setHorizontalTextPosition(SwingConstants.CENTER);
			deliveryButton.setFont(LoginGui.font2);
			deliveryButton.setForeground(LoginGui.orange);
			
			deleteButton.setVerticalTextPosition(SwingConstants.TOP);
			deleteButton.setHorizontalTextPosition(SwingConstants.CENTER);
			deleteButton.setFont(LoginGui.font2);
			deleteButton.setForeground(LoginGui.orange);
			
			arrowButton.setBounds(10,10,40,40);
			arrowButton.setBorderPainted(false);//외곽선X
			arrowButton.setContentAreaFilled(false);//채우기X
			arrowButton.setFocusPainted(false);//키보드 포커스 외곽선

			//main frame
			setTitle("Shopping basket");
			this.setBounds(450,300,550,500);
			this.setLayout(null);
			this.getContentPane().setBackground(Color.white);
			
			title.setBounds(130,0,280,80);
			
			//panel1
			panel1.setLayout(null);
			panel1.setBackground(LoginGui.green);
			panel1.setBounds(30,90,470,357);
			
			//panel2
			panel2.setLayout(new GridLayout(20,1));
			shop();
			panel2.add(priceInfo);
			
			//scroll
			JScrollPane scroll=new JScrollPane(panel2);
			scroll.setBounds(20,15,300,320);
			scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
			
			deliveryButton.setBounds(330,75,130,90);
			deleteButton.setBounds(330,190,130,90);
			
			panel1.add(scroll);
			panel1.add(deliveryButton);
			panel1.add(deleteButton);
			
			deliveryButton.addActionListener(this);
			deleteButton.addActionListener(this);		
			arrowButton.addActionListener(this);
			
			arrowButton.setToolTipText("뒤로가기");
			
			arrowButton.addMouseListener(this);
			deliveryButton.addMouseListener(this);
			deleteButton.addMouseListener(this);
			for(int i=0; i<boxList.size(); i++) {
				boxList.get(i).addItemListener(this);
			}
			
			this.add(arrowButton);
			this.add(title);
			this.add(panel1);
			
			this.setVisible(true);
		}

		ArrayList<JCheckBox> boxList=new ArrayList<>();
//		ArrayList<Object[][]> goods_price=MD.selectId(MenuGui.id);
		ArrayList<ShoppingDto> shoppingDto=MD.selectId(MenuGui.id);
		//장보기 등록 물품 가져오기
		public void shop() {
			for(ShoppingDto s : shoppingDto) {
				JCheckBox checkBox=new JCheckBox(s.getGoods()+"  "+s.getPrice()+" 원");
				checkBox.setFont(LoginGui.font2);
				panel2.add(checkBox);
				boxList.add(checkBox);
			}
//			for(Object[][] s : goods_price) {
//				for(int i=0; i<s.length; i++) {
//					if(s[i][0] instanceof String && s[i][1] instanceof Integer) {
//						String str=(String) s[i][0];
//						Integer integer=(Integer) s[i][1];
//						shop.put(str , integer);
//						JCheckBox checkBox=new JCheckBox(str+"  "+integer+" 원");
//						checkBox.setFont(LoginGui.font2);
//						panel2.add(checkBox);
//						boxList.add(checkBox);
//					}
//				}
//			}
		}
		
		@Override
		public void actionPerformed(ActionEvent e) {
			if(e.getSource()==deliveryButton) {
				//주문하기
			}else if(e.getSource()==deleteButton) {
				//삭제하기
				deleteMypage();
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
			if(e.getSource()==deliveryButton) {
				deliveryButton.setContentAreaFilled(true);
				deliveryButton.setBackground(LoginGui.yellow);
			}else if(e.getSource()==deleteButton) {
				deleteButton.setContentAreaFilled(true);
				deleteButton.setBackground(LoginGui.yellow);
			}else if(e.getSource()==arrowButton) {
				arrowButton.setContentAreaFilled(true);
				arrowButton.setBackground(LoginGui.yellow);
			}
		}

		@Override
		public void mouseExited(MouseEvent e) {//마우스가 버튼위에서 벗어났을 때
			deliveryButton.setContentAreaFilled(false);
			deleteButton.setContentAreaFilled(false);	
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

		ArrayList<Integer> index=new ArrayList<>();
		@Override
		public void itemStateChanged(ItemEvent e) {
			for(int i=0; i<boxList.size(); i++) {
				final int index=i;
				if(e.getStateChange()==ItemEvent.SELECTED) {//체크했을때
					if(e.getItem()==boxList.get(index)) {
						sum+=shoppingDto.get(index).getPrice();
						this.index.add(index);
					}
				}else if(e.getStateChange()==ItemEvent.DESELECTED) {//체크 취소했을때
					if(e.getItem()==boxList.get(index)) {
						sum-=shoppingDto.get(index).getPrice();
						this.index.remove(this.index.indexOf(index));
					}
				}
			}
			priceInfo.setText("  현재  ' " +sum+" ' 원 입니다.");
		}
		
		//장바구니 삭제하기
		public void deleteMypage() {		
			for(int i=0; i<this.index.size(); i++) {
				if(MD.delete(shoppingDto.get(this.index.get(i)).getGoods(), MenuGui.id, shoppingDto.get(this.index.get(i)).getNum())!=0) {
					System.out.println("삭제가 되는지");
					if(i==this.index.size()-1) {
						System.out.println("마지막 인덱스에 도달하는지");
						LoginGui.messageFont();
						JOptionPane.showMessageDialog
						(null,"✗ 장바구니에서 삭제했습니다. ✗","mypage delete success",JOptionPane.PLAIN_MESSAGE);
						this.setVisible(false);
						new ShoppingGui();
					}
				}else {
					System.out.println("장바구니 삭제 실패");
				}
			}
		}
		
}
