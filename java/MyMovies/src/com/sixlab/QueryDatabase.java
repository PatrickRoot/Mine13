package com.sixlab;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

public class QueryDatabase
{
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
	
	/**
	 * 用于向数据库中查询信息
	 * @param output 查询结果将放在此Vector<Vector<String>>参数中
	 * @param sql 要查询的SQL语句
	 */
	private static void queryBySql(Vector<Vector<String>> output, String sql)
	{
		Connection conn = connSqlite();
		Statement stmt = null;
		ResultSet rt = null;
		Vector<String> record = null;
		try
		{
			stmt = conn.createStatement();
			rt = stmt.executeQuery(sql);
			output.clear();
			while (rt.next())
			{
				String id = rt.getString(1);
				String name = rt.getString(2);
				String mark = rt.getString(3);
				String date = rt.getString(4);
				record = new Vector<String>();
				record.add(id);
				record.add(name);
				record.add(date);
				record.add(mark);
				output.add(record);
			}
		} catch (Exception e)
		{
			e.printStackTrace();
		} finally
		{
			closeConn(conn, stmt, rt);
		}
	}
	
	/**
	 * 用于向数据库中增加或者修改信息
	 * @param sql 要执行的SQL语句，可以使增、删、改
	 * @return 执行成功则为true，执行出问题则为false
	 */
	private static boolean updateBySql(String sql)
	{
		boolean output = false;
		Connection conn = connSqlite();
		Statement stmt = null;
		ResultSet rt = null;
		try
		{
			stmt = conn.createStatement();
			output = (stmt.executeUpdate(sql) != 0);
		} catch (Exception e)
		{
			e.printStackTrace();
		} finally
		{
			closeConn(conn, stmt, rt);
		}
		return output;
	}
	
	/**
	 * 当用户想要查询电影列表时，调用此方法。
	 * @param queryName 要被查询的电影名（全部或部分）
	 * @param queryMark 要被查询的电影的标记（全部或部分）
	 * @param queryDate 要被查询的观影日期（全部或部分）
	 * @param output 查询的结果存储在此Vector<Vector<String>>中，以便JTable更新。
	 */
	public static void queryFilm(String queryID,String queryName,
			String queryMark, String queryDate, Vector<Vector<String>> output)
	{
		String sql = "select * from saw ";
		int i = 0;
		if (!queryName.equals(""))
		{
			sql += " where saw.Name Like('%" + queryName + "%')";
			i++;
		}
		if (!queryDate.equals(""))
		{
			if (i != 0)
			{
				sql += " And ";
			}else {
				sql+=" where ";
			}
			sql += "saw.InDate Like('%" + queryDate + "%')";
			i++;
		}
		if (!queryID.equals(""))
		{
			if (i != 0)
			{
				sql += " And ";
			}else {
				sql+=" where ";
			}
			sql += "saw.ID Like('%" + queryID + "%')";
			i++;
		}
		if (!queryMark.equals(""))
		{
			if (i != 0)
			{
				sql += " And ";
			}else {
				sql+=" where ";
			}
			sql += "saw.Remark Like('%" + queryMark + "%')";
		}
		queryBySql(output, sql);
	}
	
	/**
	 * 当用户想要增加已观影片时，调用此方法。
	 * @param queryName 要被增加的电影名，必填。
	 * @param queryDate 要被增加的观影日期，默认为当日，必需是8个数字格式。
	 * @param queryMark 要被增加的电影标记，默认不是null，是空字符串。
	 * @return 整形成功返回true，反之为false。
	 */
	public static boolean addFilm(String queryName, String queryDate,
			String queryMark)
	{
		boolean isSuc = false;
		String sql = "INSERT INTO saw(Name,Remark,InDate)";
		sql += "VALUES('" + queryName + "','" + queryMark + "'," + queryDate
				+ ")";
		isSuc = updateBySql(sql);
		return isSuc;
	}
	
	/**
	 * 当用户想要修改已观影片时，调用此方法。只有ID的话，则修改此ID的观影日期为当日。
	 * @param queryID 要被修改的电影的ID。
	 * @param queryName 要被修改的电影名，默认不是null，是空字符串。
	 * @param queryMark 要被修改的电影标记，默认不是null，是空字符串。
	 * @param queryDate 要被修改的观影日期，默认为当日，必需是8个数字格式。
	 * @return 整形成功返回true，反之为false。
	 */
	public static boolean updateFilm(String queryID,
			String queryName, String queryMark, String queryDate)
	{
		boolean isSuc = false;
		String sql = "update saw set ";
		sql += ("InDate='"+queryDate+"'");
		if (!queryName.equals(""))
		{
			sql += (",Name='"+queryName+"'");
		}
		if (!queryMark.equals(""))
		{
			sql += (",Remark='"+queryMark+"'");
		}
		sql+=(" WHERE Id="+queryID);
		isSuc=updateBySql(sql);
		
		return isSuc;
	}
}