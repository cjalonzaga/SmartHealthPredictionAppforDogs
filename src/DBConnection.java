//
//import com.mysql.jdbc.Connection;
//import java.sql.DriverManager;
//import java.sql.SQLException;
//import java.util.logging.Level;
//import java.util.logging.Logger;
//
///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//
///**
// *
// * @author Cris John Alonzaga
// */
//public class DBConnection {
//    
//    private static final String DB_NAME = "shpda_dogs";
//    private static final String USER_NAME = "root";
//    private static final String PASSWORD = "";
//    
//    private static Connection connect = null;
//    
//    public static Connection getConnection(){
//        //if(connect != null) return connect;
//        return getConnection(DB_NAME,USER_NAME,PASSWORD);
//    }
//    
//    private static Connection getConnection(String db_name, String user_name, String password){
//        
//        try{
//            Class.forName("com.mysql.jdbc.Driver");
//            connect = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/"+db_name, user_name, password);
//        }catch(ClassNotFoundException | SQLException e){
//            Logger.getLogger(DBConnection.class.getName()).log(Level.SEVERE, null, e);
//            System.out.println(e);
//        }
//        
//        return connect;
//    }
//}
