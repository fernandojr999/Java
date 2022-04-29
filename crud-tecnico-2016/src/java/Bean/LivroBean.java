/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Bean;

import Dao.LivroDao;
import Entidade.Livro;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;

@ManagedBean(name = "livroBean")
@SessionScoped
public class LivroBean {

    private Livro livro;

    public Livro getLivro() {
        return livro;
    }

    public void setLivro(Livro livro) {
        this.livro = livro;
    }
    private LivroDao dao;
    private List<Livro> livros;
    private DataModel<Livro> livrosModel;

    public LivroBean() {
        livro = new Livro();
        dao = new LivroDao();
        livros = new ArrayList<Livro>();
        livrosModel = new ListDataModel<Livro>(livros);
    }

    @PostConstruct
    public void carregaLivros() {
        livros.clear();
        List<Livro> livs = dao.listarLivros();
        for (Livro l : livs) {
            livros.add(l);
        }
    }

    public String listarEmprestimo() {
        return "listar";
    }

    public String cadastrarEmprestimo() {
        return "cadastro";
    }

    public String editarEmprestimo() {
        if (dao.editar(livro)) {
            FacesMessage fm = new FacesMessage("editado com sucesso");
            FacesContext.getCurrentInstance().addMessage("mensagem", fm);
            
            return "listarLivros";
        } else {
            FacesMessage fm = new FacesMessage("erro ao editar");
            FacesContext.getCurrentInstance().addMessage("mensagem", fm);
            return "";
        }
    }

    public String edicaoEmprestimo() {
        this.livro = this.livrosModel.getRowData();
        return "edicao";
    }

    public String excluirEmprestimo() {
        this.livro = this.livrosModel.getRowData();
        if (dao.excluir(livro)) {
            FacesMessage fm = new FacesMessage("excluido com sucesso");
            FacesContext.getCurrentInstance().addMessage("mensagem", fm);
            return "listarLivros";
        } else {
            FacesMessage fm = new FacesMessage("erro ao excluir");
            FacesContext.getCurrentInstance().addMessage("mensagem", fm);
            return "";

        }
    }
}
