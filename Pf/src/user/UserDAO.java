package user;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class UserDAO {

	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;

	public UserDAO() {
		try {
			String dbURL = "jdbc:mysql://localhost:3306/PF";
			String dbID = "root";
			String dbPassword = "9137852";
			Class.forName("com.mysql.jdbc.Driver"); // mysql 드라이버를 찾을 수 있는 매개체
			conn = DriverManager.getConnection(dbURL, dbID, dbPassword);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public int login(String userID, String userPassword) {
		String SQL = "SELECT userPassword FROM USER WHERE userID = ?";
		try {
			pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1, userID);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				if (rs.getString(1).equals(userPassword))
					return 1; // 로그인 성공
				else
					return 0; // 비밀번호 불일치
			}
			return -1; // 아이디가 없음
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (pstmt != null)
					pstmt.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return -2; // 데이터베이스 오류
	}

	public int join(User user) {
		String SQL = "INSERT INTO USER VALUES (?, ?, ?, ?, ?)";
		try {
			pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1, user.getUserID());
			pstmt.setString(2, user.getUserPassword());
			pstmt.setString(3, user.getUserName());
			pstmt.setString(4, user.getUserGender());
			pstmt.setString(5, user.getUserEmail());
			return pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (pstmt != null)
					pstmt.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return -1; // 데이터베이스 오류
	}

}

/*
 * public int registerCheck(String userID) { PreparedStatement pstmt = null;
 * ResultSet rs = null; String SQL = "SELECT * FROM USER WHERE userID = ?"; try
 * { pstmt = conn.prepareStatement(SQL); pstmt.setString(1, userID); rs =
 * pstmt.executeQuery(); if(rs.next()) { return 0; // 이미 존재하는 회원 } else { return
 * 1; // 가입 가능한 회원 아이디 } } catch (Exception e) { e.printStackTrace(); } finally
 * { try { if(rs != null) rs.close(); if(pstmt != null) pstmt.close(); } catch
 * (Exception e) { e.printStackTrace(); } } return -1; // 데이터베이스 오류 }
 */

/*
 * public int rsgister(String userID, String userPassword, String userName,
 * String userAge, String userGender, String userEmail) { PreparedStatement
 * pstmt = null; ResultSet rs = null; String SQL =
 * "INSERT INTO USER VALUES (?, ?, ?, ?, ?, ?)"; try { pstmt =
 * conn.prepareStatement(SQL); pstmt.setString(1, userID); pstmt.setString(2,
 * userPassword); pstmt.setString(3, userName); pstmt.setInt(4,
 * Integer.parseInt(userAge)); pstmt.setString(5, userGender);
 * pstmt.setString(6, userEmail); return pstmt.executeUpdate(); } catch
 * (Exception e) { e.printStackTrace(); } finally { try { if(rs != null)
 * rs.close(); if(pstmt != null) pstmt.close(); } catch (Exception e) {
 * e.printStackTrace(); } } return -1; // 데이터베이스 오류 } }
 */
