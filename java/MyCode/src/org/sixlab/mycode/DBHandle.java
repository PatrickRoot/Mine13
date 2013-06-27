package org.sixlab.mycode;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class DBHandle
{
	/**
	 * 加载org.sqlite.JDBC数据库驱动，并创建数据库连接，然后将此连接返回。
	 * 
	 * @return 数据库的连接
	 */
	private static Connection connSqlite()
	{
		Connection conn = null;
		try
		{
			Class.forName("org.sqlite.JDBC");
			conn = DriverManager.getConnection("jdbc:sqlite:mycode.db");
		} catch (Exception e)
		{
			e.printStackTrace();
		}
		
		return conn;
	}
	
	/**
	 * 用于关闭数据库连接
	 * 
	 * @param conn
	 *            已建立连接的数据库连接
	 * @param stmt
	 *            数据库Statement
	 * @param rt
	 *            数据库ResultSet
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
	
	private static String queryBySql(String sql)
	{
		String context = "";
		Connection conn = connSqlite();
		Statement stmt = null;
		ResultSet rt = null;
		try
		{
			stmt = conn.createStatement();
			rt = stmt.executeQuery(sql);
			while (rt.next())
			{
				context = rt.getString(1);
			}
		} catch (Exception e)
		{
			e.printStackTrace();
		} finally
		{
			closeConn(conn, stmt, rt);
		}
		return context;
	}
	
	private static void queryBySql(ArrayList<String> titleList, String sql)
	{
		Connection conn = connSqlite();
		Statement stmt = null;
		ResultSet rt = null;
		try
		{
			stmt = conn.createStatement();
			rt = stmt.executeQuery(sql);
			titleList.clear();
			while (rt.next())
			{
				String id = rt.getString(1);
				String title = rt.getString(2);
				
				titleList.add(id + ":" + title);
			}
		} catch (Exception e)
		{
			e.printStackTrace();
		} finally
		{
			closeConn(conn, stmt, rt);
		}
	}
	
	private static boolean updateBySql(String sql)
	{
		boolean output = false;
		Connection conn = connSqlite();
		Statement stmt = null;
		ResultSet rt = null;
		try
		{
			stmt = conn.createStatement();
			output = (stmt.executeUpdate(sql) >= 0);
		} catch (Exception e)
		{
			e.printStackTrace();
		} finally
		{
			closeConn(conn, stmt, rt);
		}
		return output;
	}
	
	public static boolean insert(String title, String content)
	{
		Date today = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		String insertDate = sdf.format(today);
		String sql = "insert into code(title,context,insertdate) values('"
				+ title + "','" + content + "','" + insertDate + "')";
		return updateBySql(sql);
	}
	
	public static void getTitles(ArrayList<String> titleList)
	{
		String sql = "SELECT id,title FROM code";
		queryBySql(titleList, sql);
	}
	
	public static boolean delete(String id)
	{
		String sql = "delete from code where id='" + id + "'";
		return updateBySql(sql);
	}
	
	public static boolean search(ArrayList<String> titleList,
			String searchContent)
	{
		String sql = "SELECT id,title FROM code WHERE title LIKE '%"
				+ searchContent + "%' OR context LIKE '%" + searchContent
				+ "%'";
		queryBySql(titleList, sql);
		if (titleList.size() == 0)
		{
			return false;
		} else
		{
			return true;
		}
	}
	
	public static String getContext(String id)
	{
		String sql = "select context from code where id='" + id + "'";
		
		String context = queryBySql(sql);
		
		return context;
	}
	
	public static boolean update(String id, String title, String content)
	{
		String sql = "update code set title ='" + title + "',context='"
				+ content + "' where id='" + id + "'";
		return updateBySql(sql);
	}
}