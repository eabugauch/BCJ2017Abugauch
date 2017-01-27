package com.abugauch.bootcamp.Persistence;

/**
 * Created by Usuario on 24/1/2017.
 */
import com.abugauch.bootcamp.Configuration.DBConnection;
import com.abugauch.bootcamp.Domain.Weather;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class WeatherDAO implements defaultDAO<Weather> {
    public static final String SQL_INSERT = "INSERT INTO db_abugauch.weather (idWeather, description) VALUES(?,?)";
    public static final String SQL_READ = "SELECT * FROM db_abugauch.weather WHERE idWeather = ?";
    public static final String SQL_READALL = "SELECT * FROM db_abugauch.weather";
    private  DBConnection dbCon;


    public boolean create(Weather a){
        PreparedStatement ps;
        try {
            ps = dbCon.getConnection().prepareStatement(SQL_INSERT);
            ps.setInt(1,a.getIdWeather());
            ps.setString(2,a.getDescr());
            if (ps.executeUpdate() >0){return true;}
        }catch(SQLException ex){
            System.out.println("Storage error"+ex);
        }finally {
            dbCon.closeCnn();
        }
        return false;
    }

    public Weather read(Object key){
        PreparedStatement ps;
        Weather a = null;
        try {
            ResultSet res;
            ps = dbCon.getConnection().prepareStatement(SQL_READ);
            ps.setInt(1,Integer.parseInt(key.toString()));
            res = ps.executeQuery();
            while (res.next()){
                a = new Weather(res.getInt(1),res.getString(2));
            }
        }catch(SQLException ex){
            System.out.println("Storage error"+ex);
        }finally {
            dbCon.closeCnn();
        }
        return a;
    }

    public ArrayList<Weather> readAll(){
        PreparedStatement ps;
        ResultSet res;
        ArrayList <Weather> weathers = new ArrayList();
        try {
            ps = dbCon.getConnection().prepareStatement(SQL_READALL);
            res = ps.executeQuery();

            while (res.next()){
                weathers.add(new Weather(res.getInt(1),res.getString(2)));
            }
        }catch(SQLException ex){
            System.out.println("Storage error"+ex);
        }finally {
            dbCon.closeCnn();
        }
        return weathers;
    }

}
