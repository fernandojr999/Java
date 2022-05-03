/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao.bd;

import com.oracle.wls.shaded.org.apache.bcel.generic.INSTANCEOF;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import utils.Constantes;
/**
 *
 * @author sankhya
 */
public class DAO {
    public final String URL = Constantes.URL_BANCO;
    
    public ResultSet executaQuery(String query) throws ClassNotFoundException{
        Class.forName("org.sqlite.JDBC");
        
        Connection con = null;
        Statement st;
        ResultSet result = null;
        try {
            DriverManager.getConnection(URL).close();
            
            con = DriverManager.getConnection(URL);
            st = con.createStatement();
            result = st.executeQuery(query);
        } catch (SQLException ex) {
            System.out.print("Erro no SQL: " + ex.getMessage());
        } 
        
        return result;
    }
    
    public void command(String command) throws SQLException, ClassNotFoundException {
        Class.forName("org.sqlite.JDBC");
        Connection con;
        Statement st;
        try {
            con = DriverManager.getConnection(URL);
            st = con.createStatement();
            
            //PreparedStatement.executeUpdate();
            con.prepareStatement(command).executeUpdate();
        } catch (SQLException ex) {
            System.out.print("Erro no SQL: " + ex.getMessage());
        }
    }
}
