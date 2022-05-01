/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao.bd;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import modelo.Compania;
import utils.Constantes;

/**
 *
 * @author sankhya
 */
public class CompaniaDAO extends DAO{
    
    private ArrayList<Compania> registros = new ArrayList();
    
    public ArrayList<Compania> getAll(int pagina) {
        int ini = pagina * Constantes.REGISTROS_POR_PAGINAS;
        registros.clear();
        
        ResultSet result;
        try {
            result = executaQuery("select * from compania limit "+ini+", "+Constantes.REGISTROS_POR_PAGINAS+"");
            while (result.next()) {
                Compania comp = new Compania();
                comp.setId(result.getString(1));
                comp.setNome(result.getString(2));
                comp.setDominio(result.getString(3));
                comp.setAno(result.getString(4));
                comp.setIndustria(result.getString(5));
                comp.setTamanho(result.getString(6));
                comp.setLocalizacao(result.getString(7));
                comp.setPais(result.getString(8));
                comp.setLinkedin(result.getString(9));
                comp.setEmpregados_atual(result.getInt(10));
                comp.setEmpregados_total(result.getInt(11));
                
                registros.add(comp);
            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(CompaniaDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(CompaniaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return registros;
    }
    
    public void inserir(Compania comp){
        ResultSet result;
        try {
            String insert;
            insert = "INSERT INTO compania "+
                     "VALUES "+
                     "('"+comp.getId()+"', '"+
                         comp.getNome()+"', '"+
                         comp.getDominio()+"', '"+
                         comp.getAno()+"', '"+
                         comp.getIndustria()+"', '"+
                         comp.getTamanho()+"', '"+
                         comp.getLocalizacao()+"', '"+
                         comp.getPais()+"', '"+
                         comp.getLinkedin()+"', "+
                         comp.getEmpregados_atual()+", "+
                         comp.getEmpregados_total()+" "+
                    ") RETURNING 1";
            
            result = executaQuery(insert);
            
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(CompaniaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public int qtdPaginas(){
        ResultSet result;
        int qtd = 0;
        try {
            result = executaQuery("select count(*) from compania");
            while (result.next()) {
               qtd = result.getInt(1); 
            }
        } catch (SQLException ex) {
            Logger.getLogger(CompaniaDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(CompaniaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        if (qtd % Constantes.REGISTROS_POR_PAGINAS == 0){
            return qtd / Constantes.REGISTROS_POR_PAGINAS;
        }else{
            return (qtd / Constantes.REGISTROS_POR_PAGINAS) + 1;
        }
    }
    
}
