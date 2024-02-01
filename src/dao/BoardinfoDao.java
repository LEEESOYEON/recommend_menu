package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import driver.Connect;
import dto.BoardinfoDto;
import gui.MenuGui;

public class BoardinfoDao extends Connect{

	//insert
	public void insert(BoardinfoDto boardDto) {
		PreparedStatement ps=null;
		if(getConnection()) {
			try {
				System.out.println(boardDto.getCategory());
				if(boardDto.getCategory().equals("")) {
					boardDto.setCategory("수다");
				}
				System.out.println(boardDto.getCategory());
				String sql="insert into boardinfo values (boardinfo_seq.nextval,?,?,?,?)";
				ps=connection.prepareStatement(sql);
				ps.setString(1 ,boardDto.getWriterid());
				ps.setString(2,  boardDto.getTitle());
				ps.setString(3, boardDto.getText());
				ps.setString(4,  boardDto.getCategory());
				System.out.println(ps.executeUpdate()+" 건 삽입완료");
			}catch(Exception e) {
				e.printStackTrace();
			}finally {
				close(ps);
			}			
		}
	}
	
	//delete
	public void delete(int num) {
		PreparedStatement ps=null;
		if(getConnection()) {
			try {
				String sql="delete from boardinfo where num = ?";
				ps=connection.prepareStatement(sql);
				ps.setInt(1, num);
				System.out.println(ps.executeUpdate()+" 건 삭제완료");
			}catch(Exception e) {
				e.printStackTrace();
			}finally {
				close(ps);
			}
		}
	}
	
	//selectid
	public ArrayList<BoardinfoDto> select(String col,String row) {
		ArrayList<BoardinfoDto> boardList=new ArrayList<>();
		PreparedStatement ps=null;
		ResultSet r=null;
		if(getConnection()) {
			try {
				String sql="select * from boardinfo where "+col+" = ?";
				ps=connection.prepareStatement(sql);
				ps.setString(1, row);
				r=ps.executeQuery();
				while(r.next()) {
					BoardinfoDto boardDto=new BoardinfoDto();
					boardDto.setNum(r.getInt(1));
					boardDto.setWriterid(r.getString(2));
					boardDto.setTitle(r.getString(3));
					boardDto.setText(r.getString(4));
					boardDto.setCategory(r.getString(5));
					boardList.add(boardDto);
				}
				return boardList;
			}catch(Exception e) {
				e.printStackTrace();
			}finally {
				close(ps,r);
			}
		}
		return null;
	}
	
	//selectAll
	public ArrayList<BoardinfoDto> selectAll() {
		ArrayList<BoardinfoDto> boardList=new ArrayList<>();
		PreparedStatement ps=null;
		ResultSet r=null;
		if(getConnection()) {
			try {
				String sql="select * from boardinfo";
				ps=connection.prepareStatement(sql);
				r=ps.executeQuery();
				while(r.next()) {
					BoardinfoDto boardDto=new BoardinfoDto();
					boardDto.setNum(r.getInt(1));
					boardDto.setWriterid(r.getString(2));
					boardDto.setTitle(r.getString(3));
					boardDto.setText(r.getString(4));
					boardDto.setCategory(r.getString(5));
					boardList.add(boardDto);
				}
				return boardList;
			}catch(Exception e) {
				e.printStackTrace();
			}finally {
				close(ps,r);
			}
		}
		return null;
	}
	
	//update
	public void update(BoardinfoDto boardDto) {
		PreparedStatement ps=null;
		if(getConnection()) {
			try {
				String sql="update boardinfo set title = ? , text = ? where num = ? ";
				ps=connection.prepareStatement(sql);
				ps.setString(1,  boardDto.getTitle());
				ps.setString(2,  boardDto.getText());
				ps.setInt(3,  boardDto.getNum());
				System.out.println(ps.executeUpdate()+" 건 수정 완료");
			}catch(Exception e) {
				e.printStackTrace();
			}finally {
				close(ps);
			}
		}
	}
}
