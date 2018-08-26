package dao;

import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class DBUtil {
	
	static Connection conn = null;
	static Statement stmt = null;

	static String DBDRIVER = "com.mysql.jdbc.Driver";
	static String DBURL = "jdbc:mysql://localhost:3306/MobileDB";
	static String DBUID = "root";
	static String DBPWD = "123456";

	public static void open() {
		try {
			Class.forName(DBDRIVER);
			conn=DriverManager.getConnection(DBURL,DBUID,DBPWD);
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
				e.printStackTrace();
		}
	}
	public static void close() {
		try {
			if(stmt!=null)
					stmt.close();
			if(conn!=null && !conn.isClosed())
				conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static Connection getConnection() {
		try {
			if(conn==null ||conn.isClosed())
				open();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return conn;
	}

	public static ResultSet executeQuery(String sql) {
		try {
			open();
			stmt = conn.createStatement();
			return stmt.executeQuery(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public static int executeUpdate(String sql) {
		int result = 0;
		try {
			open();
			stmt = conn.createStatement();
			result = stmt.executeUpdate(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close();
		}
		return result;
	}

	public static Object execute(String sql) {
		boolean b=false;
		try {
			open();
			stmt = conn.createStatement();
			b = stmt.execute(sql);
			if(b){
				return stmt.getResultSet();
			}
			else {
				return stmt.getUpdateCount();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if(!b) {
				close();
			}
		}
		return null;
	}

	public static ResultSet executeQuery(String sql,Object[] in) {
		try {
			open();
			PreparedStatement pst = conn.prepareStatement(sql);
			for(int i=0;i<in.length;i++)
				pst.setObject(i+1, in[i]);
			stmt = pst;
			return pst.executeQuery();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public static int executeUpdate(String sql,Object[] in) {
		try {
			open();
			PreparedStatement pst = conn.prepareStatement(sql);
			for(int i=0;i<in.length;i++)
				pst.setObject(i+1, in[i]);
			stmt = pst;
			return pst.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			close();
		}
		return 0;
	}
	public static Object execute(String sql,Object[] in) {
		boolean b=false;
		try {
			open();
			PreparedStatement pst = conn.prepareStatement(sql);
			for(int i=0;i<in.length;i++)
				pst.setObject(i+1, in[i]);
			b = pst.execute();
			if(b){
				System.out.println("----");
				return pst.getResultSet();
			}
			else {
				System.out.println("****");
				List<Integer> list = new ArrayList<Integer>();
				list.add(pst.getUpdateCount());
				while(pst.getMoreResults()) {
					list.add(pst.getUpdateCount());
				}
				return list;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if(!b) {
				System.out.println("====");
				close();
			}
		}
		return null;
	}

	public static Object executeProcedure(String procName,Object[] in) {
		open();
		try {
			procName = "{call "+procName+"(";
			String link="";
			for(int i=0;i<in.length;i++) {
				procName+=link+"?";
				link=",";
			}
			procName+=")}";
			CallableStatement cstmt = conn.prepareCall(procName);
			for(int i=0;i<in.length;i++) {
				cstmt.setObject(i+1, in[i]);
			}
			if(cstmt.execute())
			{
				return cstmt.getResultSet();
			}
			else {
				return cstmt.getUpdateCount();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}
	


	public static Object executeOutputProcedure(String procName,
			Object[] in,Object[] output,int[] type){
		Object result = null;
		try {
			CallableStatement cstmt = conn.prepareCall("{call "+procName+"}");

			int i=0;
			for(;i<in.length;i++){
				cstmt.setObject(i+1, in[i]);
				//print(i+1);
			}
			int len = output.length+i;
			for(;i<len;i++){
				cstmt.registerOutParameter(i+1,type[i-in.length]);
				//print(i+1);
			}
			boolean b = cstmt.execute();
			for(i=in.length;i<output.length+in.length;i++)
				output[i-in.length] = cstmt.getObject(i+1);
			if(b) {
				result = cstmt.getResultSet();
			}
			else {
				result = cstmt.getUpdateCount();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
}


