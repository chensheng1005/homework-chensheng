package dao;

import entity.Mobile;

import java.util.List;

public interface MobileDao {
    int stock(int mid);
    List<Mobile> Mobilelist();
    Mobile getMobileById(int id);
    List<Mobile> getMobileByName(String name);
    boolean update(int mid, String mname, float mprice, String ram, String brief);
    boolean del(int id);
    boolean add(String mname, float mprice, String ram, String brief);
}
