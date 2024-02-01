package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

import driver.Connect;
import dto.UserinfoDto;
import gui.MenuGui;

public class UserinfoDao extends Connect{

	//insert
	public void insert(UserinfoDto userDto) {
		PreparedStatement ps=null;
		if(getConnection()){
			try {
				String sql="insert into userinfo values (?,?,?,?,?,default)";
				ps=connection.prepareStatement(sql);
				ps.setString(1 , userDto.getName());
				ps.setString(2 , userDto.getId());
				ps.setString(3 , userDto.getPw());
				ps.setString(4 , userDto.getPhone());
				ps.setString(5 , userDto.getAddr());
				//ps.executeUpdate();
				System.out.println(ps.executeUpdate()+"�� ���ԿϷ�");
			}catch(Exception e) {
				System.out.println("���Խ���");
				e.printStackTrace();
			}finally {
				close(ps);
			}
		}
	}
	//���̵� �ߺ�Ȯ��
	public int selectId(String id) {
		PreparedStatement ps=null;
		ResultSet r=null;
		int cnt=0;
		if(getConnection()) {
			try {
				String sql="select * from userinfo where id=?";
				ps=connection.prepareStatement(sql);
				ps.setString(1 , id);
				r=ps.executeQuery();
				while(r.next()) {
					cnt++;
				}
			}catch(Exception e) {
				
			}finally {
				close(ps,r);
			}
		}
		return cnt;//0�� �ƴϸ� ���̵� �ߺ�
	}
	//�α��� Ȯ��
	public boolean login(String id,String pw) {
		PreparedStatement ps=null;
		ResultSet r=null;
		if(getConnection()) {
			try {
				String sql="select * from userinfo where id=?";
				ps=connection.prepareStatement(sql);
				ps.setString(1 , id);
				r=ps.executeQuery();
				if(r.next()) {
					if(r.getString(3).equals(pw)) {
						return true;
					}
				}
			}catch(Exception e) {
				
			}finally {
				close(ps,r);
			}
		}
		return false;
	}
	
	//����Ȯ��
	public UserinfoDto selectInfo(String row) {
		PreparedStatement ps=null;
		ResultSet r=null;
		if(getConnection()) {
			try {
				String sql="select * from userinfo where id=?";
				ps=connection.prepareStatement(sql);
				ps.setString(1,  row);
				r=ps.executeQuery();
				if(r.next()) {
					UserinfoDto userDto=new UserinfoDto();
					userDto.setName(r.getString(1));
					userDto.setId(r.getString(2));
					userDto.setPw(r.getString(3));
					userDto.setPhone(r.getString(4));
					userDto.setAddr(r.getString(5));
					userDto.setPoint(r.getInt(6));
					return userDto;
				}
			}catch(Exception e) {
				
			}finally {
				close(ps,r);
			}
		}
		return null;
	}
	
	//����Ʈ �ø���
	public void updatePoint(String row) {
		PreparedStatement ps=null;
		if(getConnection()) {
			try {
				String sql="update userinfo set point = point+100 where id = ?";
				ps=connection.prepareStatement(sql);
				ps.setString(1,  row);
				System.out.println(ps.executeUpdate()+" �� ����Ʈ ���� �Ϸ�");
			}catch(Exception e) {
				e.printStackTrace();
			}finally {
				close(ps);
			}
		}
	}
	
	//���������ϱ�
	public void updateUserinfo(UserinfoDto userDto) {
		PreparedStatement ps=null;
		if(getConnection()) {
			try {
				String sql=
				"update userinfo set name = ? , pw = ? , phone = ? , addr = ? where id = ?";
				ps=connection.prepareStatement(sql);
				ps.setString(1,  userDto.getName());
				ps.setString(2,  userDto.getPw());
				ps.setString(3,  userDto.getPhone());
				ps.setString(4, userDto.getAddr());
				ps.setString(5,  userDto.getId());
				System.out.println(ps.executeQuery()+" �� �����Ϸ�");
			}catch(Exception e) {
				e.printStackTrace();
			}finally {
				close(ps);
			}
		}
	}
	
	
	
	
	
	
	
}
