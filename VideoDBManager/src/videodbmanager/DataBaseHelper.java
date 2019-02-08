/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package videodbmanager;

import java.sql.*;
import java.util.ArrayList;

/**
 *
 * @author Christian
 */
public class DataBaseHelper {
    
    private String server;
    private String port;    
    private String database;    
    private String usr;
    private String password;

    public DataBaseHelper(String server, String port,String database, String usr, String password) {
        this.server = server;
        this.port = port;
        this.database = database;
        this.usr = usr;
        this.password = password;
    }    
    
    public boolean login(String usr, String pw){
        boolean loginWorked = false;
        String x = "";
        String y = "";
        ResultSet rs = connect("Select name,password from usr WHERE name = '" + usr + "' AND password = '" + pw + "'");
        try{
            while(rs.next()){
                x = rs.getString(1);
                y = rs.getString(2);
                if(("peter".equals(x)) &&  ("123".equals(y))){
                    loginWorked = true;
                }
            }
        }catch(Exception e){
            System.out.println(e.getMessage());
        }        
        return loginWorked;
    }
    
    public ArrayList<String> getPath(String name){
        
        ResultSet rs = this.connect("Select path from movie WHERE name like '%" +  name + "%'");
        ArrayList<String> returnValuePath = new ArrayList<String>();
        
        try{
            while(rs.next()){
               returnValuePath.add(rs.getString(1));
            }
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
        return returnValuePath;
    }
    
    public ResultSet connect(String sqlStatement){     
        ResultSet rs = null;
        try{            
            Class.forName("org.postgresql.Driver");
            Connection con = DriverManager.getConnection("jdbc:postgresql://" 
                    + this.server 
                    +":"
                    + this.port 
                    + "/"
                    + this.database, this.usr,this.password);
            
            PreparedStatement pst = con.prepareStatement(sqlStatement);
            rs = pst.executeQuery();
            /*
            while(rs.next()){
               System.out.println(rs.getInt(1) + ";" + rs.getString(2) + ";" + rs.getString(3));
            }
            */
            
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
        return rs;
    }
    public String getServer() {

        return server;
    }

    public void setServer(String server) {
        this.server = server;
    }

    
    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
    }
    public String getDatabase() {
        return database;
    }

    public void setDatabase(String database) {
        this.database = database;
    }

    public String getUsr() {
        return usr;
    }

    public void setUsr(String usr) {
        this.usr = usr;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    
   
}  