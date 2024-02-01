package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import driver.Connect;
import dto.MenuinfoDto;

public class MenuinfoDao extends Connect{

	
	//�޴��˻��� ���� ��������(��ü��ġ)
	public MenuinfoDto allSelectMenu(String menu) {
		PreparedStatement ps=null;
		ResultSet r=null;
		if(getConnection()) {
			try {
				String sql="select * from menuinfo where menuname = ?";
				System.out.println(sql);
				ps=connection.prepareStatement(sql);
				ps.setString(1 , menu);
				r=ps.executeQuery();
				if(r.next()) {
					MenuinfoDto menuDto=new MenuinfoDto();
					menuDto.setMenuname(r.getString(1));
					menuDto.setMainstuff(r.getString(2));
					menuDto.setSidestuff(r.getString(3));
					menuDto.setCook(r.getString(4));
					menuDto.setFoodImage(r.getString(5));
					return menuDto;
				}
			}catch(Exception e) {
				e.printStackTrace();
			}finally {
				close(ps,r);
			}
		}
		return null;
	}
	
	//�޴��˻��� ���� ��������(�κ���ġ)
	public ArrayList<MenuinfoDto> selectMenu(String menu) {
		ArrayList<MenuinfoDto> menuList=new ArrayList<>();
		PreparedStatement ps=null;
		ResultSet r=null;
		if(getConnection()) {
			try {
				String sql="select * from menuinfo where menuname like ?";
				ps=connection.prepareStatement(sql);
				ps.setString(1 , "%"+menu+"%");
				r=ps.executeQuery();
				while(r.next()) {
					MenuinfoDto menuDto=new MenuinfoDto();
					menuDto.setMenuname(r.getString(1));
					menuDto.setMainstuff(r.getString(2));
					menuDto.setSidestuff(r.getString(3));
					menuDto.setCook(r.getString(4));
					menuDto.setFoodImage(r.getString(5));
					menuList.add(menuDto);
				}
				return menuList;
			}catch(Exception e) {
				e.printStackTrace();
			}finally {
				close(ps,r);
			}
		}
		return null;
	}
	
	//���˻��� ���� ��������
	public ArrayList<MenuinfoDto> allSelectStuff(String stuff) {
		ArrayList<MenuinfoDto> menuList=new ArrayList<>();
		PreparedStatement ps=null;
		ResultSet r=null;
		if(getConnection()) {
			try {
				String sql="select * from menuinfo where mainstuff = ?";
				ps=connection.prepareStatement(sql);
				ps.setString(1 , stuff);
				r=ps.executeQuery();
				while(r.next()) {
					MenuinfoDto MD=new MenuinfoDto();
					MD.setMenuname(r.getString(1));
					MD.setMainstuff(r.getString(2));
					MD.setSidestuff(r.getString(3));
					MD.setCook(r.getString(4));
					MD.setFoodImage(r.getString(5));
					menuList.add(MD);
				}
				return menuList;
			}catch(Exception e) {
				e.printStackTrace();
			}finally {
				close(ps,r);
			}
		}
		return null;
	}
	
	//��ü
	public ArrayList<String> selectMenuname() {
		ArrayList<String> menuNameList=new ArrayList<>();
		PreparedStatement ps=null;
		ResultSet r=null;
		if(getConnection()) {
			try {
				String sql="select menuname from menuinfo";
				ps=connection.prepareStatement(sql);
				r=ps.executeQuery();
				while(r.next()) {
					menuNameList.add(r.getString(1));
				}
				return menuNameList;
			}catch(Exception e) {
				e.printStackTrace();
			}finally {
				close(ps,r);
			}
		}
		return null;
	}
	
	
	
	
	
	
}
