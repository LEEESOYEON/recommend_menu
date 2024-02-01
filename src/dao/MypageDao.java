package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import driver.Connect;
import dto.ShoppingDto;

public class MypageDao extends Connect{

	//insert
	public int insert(String myid,String goods) {
		PreparedStatement ps=null;
		if(getConnection()){
			try {
				String sql="insert into mypage values (mypage_seq.nextval,?,?)";
				ps=connection.prepareStatement(sql);
				ps.setString(1 , myid);
				ps.setString(2 , goods);
				System.out.println("장바구니 삽입 완료");
				return ps.executeUpdate();
			}catch(Exception e) {
				e.printStackTrace();
			}finally {
				close(ps);
			}
		}
		return 0;
	}
	
	//회원 별 정보 가져오기
	public ArrayList<ShoppingDto> selectId(String id) {
		//ArrayList<MypageDto> pageList=new ArrayList<>();
		ArrayList<ShoppingDto> shoppingList=new ArrayList<>();
		PreparedStatement ps=null;
		ResultSet r=null;
		if(getConnection()) {
			try {
				String sql=
				"select * from mypage inner join shop on mypage.mygoods = shop.goods where mypage.myid=?";
				ps=connection.prepareStatement(sql);
				ps.setString(1 , id);
				r=ps.executeQuery();
				while(r.next()) {
					ShoppingDto SP=new ShoppingDto();
					SP.setNum(r.getInt(1));
					SP.setGoods(r.getString(4));
					SP.setPrice(r.getInt(5));
					shoppingList.add(SP);
				}
				return shoppingList;
			}catch(Exception e) {
				e.printStackTrace();
			}finally {
				close(ps,r);
			}
		}
		return null;
	}


	public int delete(String mygoods, String myid, int num) {
		PreparedStatement ps=null;
		if(getConnection()) {
			try {
				String sql="delete from mypage where mygoods = ? and myid = ? and num = ?";
				ps=connection.prepareStatement(sql);
				ps.setString(1 ,  mygoods);
				ps.setString(2 , myid);
				ps.setInt(3 , num);
				System.out.println("장바구니 삭제 완료");
				return ps.executeUpdate();
			}catch(Exception e) {
				e.printStackTrace();
			}finally {
				close(ps);
			}
		}
		return 0;
	}
	
	
}
