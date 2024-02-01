package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import driver.Connect;
import dto.ShopDto;

public class ShopDao extends Connect{

	//정보가져오기
	public ArrayList<ShopDto> selectAll() {
		PreparedStatement ps=null;
		ResultSet r=null;
		ArrayList<ShopDto> shopDto=new ArrayList<>();
		if(getConnection()) {
			try {
				String sql="select * from shop";
				ps=connection.prepareStatement(sql);
				r=ps.executeQuery();
				while(r.next()) {
					ShopDto SD=new ShopDto();
					SD.setGoods(r.getString(1));
					SD.setPrice(r.getInt(2));
					shopDto.add(SD);
				}
				return shopDto;
			}catch(Exception e) {
				e.printStackTrace();
			}finally {
				close(ps,r);
			}
		}
		return null;
	}
}
