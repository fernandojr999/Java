/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controlador;

import dao.bd.CompaniaDAO;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import modelo.Compania;


/**
 *
 * @author sankhya
 */
@WebServlet(name = "Compania", urlPatterns = {"/compania"})
public class CompaniaControl extends Master {
     CompaniaDAO pdao = new CompaniaDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        if (!verificaSessao(request, response)) {
           getServletContext().getRequestDispatcher("/telaLogin.jsp").forward(request, response);
        }
        
        // ações get:
        // sem parâmetro: recuperar todos os elementos
        // com parâmetro r: recuperar elemento individual
        // com parâmetro d: excluir elemento
        // com parâmetro f: abrir formulário de inclusão
        String op = request.getParameter("op");

        //String retrieve = request.getParameter("r");
        //String delete = request.getParameter("d");
        //String form = request.getParameter("f");
        if (op == null) { // exibe todos
            
            int pagina = Integer.parseInt(request.getParameter("page"));
            ArrayList<Compania> registros = pdao.getAll(pagina);
            request.setAttribute("registros", registros);
            
            int qtdPaginas = pdao.qtdPaginas();
            request.setAttribute("qtdPaginas", qtdPaginas);
            
            getServletContext().getRequestDispatcher("/Compania/compania-listar.jsp").forward(request, response);
        }  else if (op.equals("f")) {
            getServletContext().getRequestDispatcher("/Compania/compania-inserir.jsp").forward(request, response);
        } 

    }
    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
       
        if (!verificaSessao(request, response)) {
           getServletContext().getRequestDispatcher("/telaLogin.jsp").forward(request, response);
        }
        // configuração para corrigir questões de acento
        request.setCharacterEncoding("utf8");
        
        Compania comp = new Compania();
        comp.setId(request.getParameter("id"));
        comp.setNome(request.getParameter("nome"));
        comp.setDominio(request.getParameter("dominio"));
        comp.setAno(request.getParameter("ano"));
        comp.setIndustria(request.getParameter("industria"));
        comp.setTamanho(request.getParameter("tamanho"));
        comp.setLocalizacao(request.getParameter("localizacao"));
        comp.setPais(request.getParameter("pais"));        
        comp.setLinkedin(request.getParameter("linkedin"));
        comp.setEmpregados_atual(Integer.parseInt(request.getParameter("empregados_atual")));
        comp.setEmpregados_total(Integer.parseInt(request.getParameter("empregados_total")));

        pdao.inserir(comp);
        
        request.setAttribute("msg", "Compania inserida com sucesso");
        getServletContext().getRequestDispatcher("/mensagem.jsp").forward(request, response);
    }
    
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
