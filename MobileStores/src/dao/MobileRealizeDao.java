package dao;

import entity.Mobile;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class MobileRealizeDao implements MobileDao{
    @Override
    public int stock(int mid) {
        String sql = "select sum(amount) from stock where mid = ?";
        ResultSet rs = (ResultSet) DBUtil.execute(sql,new Object[]{mid});
        int retry=0;
        try{
            while (rs.next()){
                retry = rs.getInt(1);
            }
        }catch (Exception e) {
            e.printStackTrace();
        }
        return  retry;
    }

    @Override
    public List<Mobile> Mobilelist() {
        String sql = "select * from mobile";
        List<Mobile> mobiles = new ArrayList<>();
        Mobile mobile = null;
        ResultSet rs = DBUtil.executeQuery(sql);
        try{
            while (rs.next()){
                mobile = new Mobile(rs.getInt(1),rs.getString(2),rs.getFloat(3),rs.getString(4),rs.getString(5),rs.getString(6),new MobileRealizeDao().stock(rs.getInt(1)));
                mobiles.add(mobile);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return mobiles;
    }

    @Override
    public Mobile getMobileById(int id) {
        String sql = "select * from mobile where mid = ?";
        Mobile mobile = null;
        ResultSet rs = (ResultSet) DBUtil.execute(sql, new Object[]{id});
        try{
            if (rs.next()){
                mobile = new Mobile(rs.getInt(1),rs.getString(2),rs.getFloat(3),rs.getString(4),rs.getString(5),rs.getString(6),new MobileRealizeDao().stock(rs.getInt(1)));
                return mobile;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
    @Override
    public List<Mobile> getMobileByName(String name) {
        MobileDao mobileDao = new MobileRealizeDao();
        String n = "%"+name+"%";
        String sql="select * from mobile where mname like ?";
        List<Mobile> mobiles = new ArrayList<>();
        Mobile mobile = null;
        ResultSet rs = (ResultSet) DBUtil.executeQuery(sql,new Object[]{n});
        try{
            while (rs.next()){
                mobile = new Mobile(rs.getInt(1),rs.getString(2),rs.getFloat(3),rs.getString(4),rs.getString(5),rs.getString(6),mobileDao.stock(rs.getInt(1)));
                mobiles.add(mobile);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return mobiles;
    }
    @Override
    public boolean update(int mid, String mname, float mprice,String ram, String brief) {
        String sql = "update mobile set mname=?,mprice=?,ram=?,brief=? where mid=?";
        if(DBUtil.executeUpdate(sql, new Object[]{mname,mprice,ram,brief,mid})>0){
            return true;
        } else{
            return false;
        }
    }

    @Override
    public boolean del(int id) {
        String sql ="delete from mobile where mid=?";
        if(DBUtil.executeUpdate(sql,new Object[]{id})>0){
            return true;
        }
        return false;
    }

    @Override
    public boolean add(String mname, float mprice, String ram, String brief) {
        String sql = "insert into mobile(mname,mprice,ram,brief) values(?,?,?,?)";
        if(DBUtil.executeUpdate(sql, new Object[]{mname,mprice,ram,brief})>0){
             return true;
        }
        else {
            return false;
        }
    }
}
