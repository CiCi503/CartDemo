package DAO;

import Entity.Items;
import Util.DBHelper;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by CiCi on 2017/2/27.
 * 业务逻辑类，里面的方法是为外层的JSP调用提供支持
 */
public class ItemsDao {
	public ArrayList<Items> getAllItems() {
		
		PreparedStatement pst = null;
		ResultSet rs = null;
		ArrayList<Items> arrayList = new ArrayList<Items>();
		
		Connection conn = DBHelper.getConnection();
		String sql = "SELECT * FROM items;";
		try {
			pst = conn.prepareStatement(sql);
			rs = pst.executeQuery();
			
			while (rs.next()) {
				Items item = new Items();
				item.setId(rs.getInt("id"));
				item.setName(rs.getString("name"));
				item.setCity(rs.getString("city"));
				item.setNumber(rs.getInt("number"));
				item.setPrice(rs.getInt("price"));
				item.setPicture(rs.getString("picture"));
				arrayList.add(item);
				
			}
			return arrayList;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}finally {
			if (rs != null) {
				try{
					rs.close();
					rs = null;
				}catch (SQLException e) {
					e.printStackTrace();
				}
			}
			
			if (pst != null) {
				try{
					pst.close();
					pst = null;
				}catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		
	}
	
	public Items getItemByID(int id) {
		Connection conn = DBHelper.getConnection();
		ResultSet rs = null;
		PreparedStatement pst = null;
		
		try {
			pst = conn.prepareStatement("SELECT * FROM items WHERE id = ?");
			pst.setInt(1,id); //这是将参数设置为传进来的id
			rs = pst.executeQuery();
			if (rs.next()) {
				Items items = new Items();
				items.setId(rs.getInt("id"));
				items.setCity(rs.getString("city"));
				items.setName(rs.getString("name"));
				items.setNumber(rs.getInt("number"));
				items.setPicture(rs.getString("picture"));
				items.setPrice(rs.getInt("price"));
				return items;
			} else {
				return null;
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		} finally {
			if (rs != null) {
				try {
					rs.close();
					rs = null;
				} catch (SQLException e) {
					e.printStackTrace();
				}
				
				
			}
			if (pst != null) {
				try {
					pst.close();
					pst = null;
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}}
	
	public ArrayList<Items> getViewList(String list)
	{
//		System.out.println("list:"+list);
		ArrayList<Items> itemlist = new ArrayList<Items>();
		int iCount=5;
		if(list!=null&&list.length()>0)
		{
			String[] arr = list.split("A");
//			System.out.println("arr.length="+arr.length);
			
			if(arr.length>=5)
			{
				for(int i=arr.length-1;i>=arr.length-iCount;i--)
				{
					itemlist.add(getItemByID(Integer.parseInt(arr[i])));
				}
			}
			else
			{
				for(int i=arr.length-1;i>=0;i--)
				{
					itemlist.add(getItemByID(Integer.parseInt(arr[i])));
				}
			}
			return itemlist;
		}
		else
		{
			return null;
		}
		
	}
		
	public static void main(String[] args) {
		ItemsDao itemsDao = new ItemsDao();
		ArrayList<Items> a = itemsDao.getAllItems();
		for (int i=0; i<a.size(); i++) {
			Items item = a.get(i);
			System.out.print(item.getId() + " " + item.getName() + " " + item.getCity() + " " + item.getPrice());
			System.out.println();
			
		}
		
		Items item = itemsDao.getItemByID(5);
		System.out.print(item.getId() + " " + item.getName() + " " + item.getCity() + " " + item.getPrice());
		System.out.println();
	}
}
