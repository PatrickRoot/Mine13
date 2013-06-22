import java.sql.*;

public class TestJDBC {

    public static void main(String[] args) {
        ResultSet rs = null;
        Statement stmt = null;
        Connection conn = null;
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            conn = DriverManager.getConnection("jdbc:oracle:thin:@192.168.0.1:1521:SXT", "scott", "tiger");
            stmt = conn.createStatement();
            rs = stmt.executeQuery("select * from dept");
            while(rs.next()) {
                System.out.println(rs.getString("deptno"));
                System.out.println(rs.getInt("deptno"));
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if(rs != null) {
                    rs.close();
                    rs = null;
                }
                if(stmt != null) {
                    stmt.close();
                    stmt = null;
                }
                if(conn != null) {
                    conn.close();
                    conn = null;
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}


	/**
	 * 加载org.sqlite.JDBC数据库驱动，并创建数据库连接，然后将此连接返回。
	 * @return 数据库的连接
	 */
	private static Connection connSqlite()
	{
		Connection conn = null;
		try
		{
			Class.forName("org.sqlite.JDBC");
			conn = DriverManager.getConnection("jdbc:sqlite:sawmovies.db");
		} catch (Exception e)
		{
			e.printStackTrace();
		}
		
		return conn;
	}
	
	/**
	 * 用于关闭数据库连接
	 * @param conn 已建立连接的数据库连接
	 * @param stmt 数据库Statement
	 * @param rt 数据库ResultSet
	 */
	private static void closeConn(Connection conn, Statement stmt, ResultSet rt)
	{
		
		if (conn != null)
		{
			try
			{
				conn.close();
			} catch (SQLException e)
			{
				e.printStackTrace();
			}
		}
		if (stmt != null)
		{
			try
			{
				stmt.close();
			} catch (SQLException e)
			{
				e.printStackTrace();
			}
		}
		if (rt != null)
		{
			try
			{
				rt.close();
			} catch (SQLException e)
			{
				e.printStackTrace();
			}
		}
	}

