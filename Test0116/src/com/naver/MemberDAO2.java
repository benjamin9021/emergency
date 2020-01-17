package com.naver;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class MemberDAO2 {
	private DataSource dataFactory;
	
	public MemberDAO2() {
		try {
			Context ctx = new InitialContext();
			dataFactory = (DataSource) ctx.lookup("java:comp/env/jdbc/oracle11g");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	
	private void closeAll(ResultSet rs, PreparedStatement pstmt, Connection conn) {
		try {
			if (rs !=null) rs.close();
			if (pstmt != null) pstmt.close();
			if (conn != null) conn.close();
		} catch (Exception e) {
			 e.printStackTrace();
		}
	}
	
	public List<MemberDTO> list() {
		List<MemberDTO> list = new ArrayList<MemberDTO>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select * from member2";
		
		try {
			conn = dataFactory.getConnection();
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				String id = rs.getString("id");
				String name = rs.getString("name");
				int age = rs.getInt("age");
				list.add(new MemberDTO(id, name, age));
			}
		} catch (Exception e) {
			 e.printStackTrace();
		}finally {
			closeAll(rs, pstmt, conn);
		}
		return list;
	}
	
	public void insert(MemberDTO dto) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "insert into member2 (id,name,age) values (?,?,?)";
		try {
			conn = dataFactory.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, dto.getId());
			pstmt.setString(2, dto.getName());
			pstmt.setInt(3, dto.getAge());
			pstmt.executeUpdate();
			
		} catch (Exception e) {
			 e.printStackTrace();
		}finally {
			closeAll(null, pstmt, conn);
		}
	}
	public MemberDTO read(String id) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select * from member2 where id = ?";
	
		MemberDTO dto = null;
		try {
			conn = dataFactory.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1,id);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				String name = rs.getString("name");
				int age = rs.getInt("age");
				dto = new MemberDTO(id, name, age);
			}
		} catch (Exception e) {
			 e.printStackTrace();
		}finally {
			closeAll(rs, pstmt, conn);
		}
		return dto;
	}
	
	public MemberDTO updateui(String id) {
		MemberDTO dto = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select * from member2 where id = ?";
	
		try {
			conn = dataFactory.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1,id);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				String name = rs.getString("name");
				int age = rs.getInt("age");
				dto = new MemberDTO(id, name, age);
			}
		} catch (Exception e) {
			 e.printStackTrace();
		}finally {
			closeAll(rs, pstmt, conn);
		}
		return dto;
	}
	
	public void update(MemberDTO dto) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "update member2 set name=?, age=? where id=?";
	
		try {
			conn = dataFactory.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, dto.getName());
			pstmt.setInt(2, dto.getAge());
			pstmt.setString(3, dto.getId());
			pstmt.executeUpdate();
			
		} catch (Exception e) {
			 e.printStackTrace();
		}finally {
			closeAll(null, pstmt, conn);
		}
	}
	
	public void delete(String id) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "delete id from member2 where id=?";
		
		try {
			conn = dataFactory.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				String name = rs.getString("name");
				int age = rs.getInt("age");
					
					pstmt.executeUpdate();
				
			}
		} catch (Exception e) {
			 e.printStackTrace();
		}finally {
			closeAll(rs, pstmt, conn);
		}
		
	}

	public MemberDTO login(String id, int age) {
		MemberDTO dto = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "select * from member2 where id = ? age = ?";
		ResultSet rs = null;
		
		try {
			conn = dataFactory.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.setInt(2, age);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				String name  = rs.getString("name");
				dto = new MemberDTO(id, name, age);
			}
		} catch (Exception e) {
			 e.printStackTrace();
		}finally {
			closeAll(rs, pstmt, conn);
		}
		
		return null;
	}
	
}
