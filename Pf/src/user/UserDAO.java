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
			Class.forName("com.mysql.jdbc.Driver"); // mysql ����̹��� ã�� �� �ִ� �Ű�ü
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
					return 1; // �α��� ����
				else
					return 0; // ��й�ȣ ����ġ
			}
			return -1; // ���̵� ����
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
		return -2; // �����ͺ��̽� ����
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
		return -1; // �����ͺ��̽� ����
	}

}

/*
 * public int registerCheck(String userID) { PreparedStatement pstmt = null;
 * ResultSet rs = null; String SQL = "SELECT * FROM USER WHERE userID = ?"; try
 * { pstmt = conn.prepareStatement(SQL); pstmt.setString(1, userID); rs =
 * pstmt.executeQuery(); if(rs.next()) { return 0; // �̹� �����ϴ� ȸ�� } else { return
 * 1; // ���� ������ ȸ�� ���̵� } } catch (Exception e) { e.printStackTrace(); } finally
 * { try { if(rs != null) rs.close(); if(pstmt != null) pstmt.close(); } catch
 * (Exception e) { e.printStackTrace(); } } return -1; // �����ͺ��̽� ���� }
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
 * e.printStackTrace(); } } return -1; // �����ͺ��̽� ���� } }
 */
