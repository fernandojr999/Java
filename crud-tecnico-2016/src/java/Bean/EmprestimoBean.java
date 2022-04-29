/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Bean;


import Dao.EmprestimoDao;
import Entidade.Emprestimo;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;

@ManagedBean(name = "emprestimoBean")
@SessionScoped
public class EmprestimoBean {
    private Emprestimo emprestimo;

    public Emprestimo getEmprestimo() {
        return emprestimo;
    }

    public void setEmprestomo(Emprestimo emprestimo) {
        this.emprestimo = emprestimo;
    }
    private EmprestimoDao dao;
    private List<Emprestimo> emprestimos;
    private DataModel<Emprestimo> emprestimosModel;

    public EmprestimoBean() {
        emprestimo = new Emprestimo();
        dao = new EmprestimoDao();
        emprestimos = new ArrayList<Emprestimo>();
        emprestimosModel = new ListDataModel<Emprestimo>(emprestimos);
    }

    @PostConstruct
    public void carregaEmprestimos() {
        emprestimos.clear();
        List<Emprestimo> emps = dao.listarEmprestimos();
        for (Emprestimo e : emps) {
            emprestimos.add(e);
        }
    }

    public String listarEmprestimo() {
        return "listar";
    }

    public String cadastrarEmprestimo() {
        return "cadastro";
    }

    public String editarEmprestimo() {
        if (dao.editar(emprestimo)) {
            FacesMessage fm = new FacesMessage("editado com sucesso");
            FacesContext.getCurrentInstance().addMessage("mensagem", fm);
            
            return "listarEmprestimos";
        } else {
            FacesMessage fm = new FacesMessage("erro ao editar");
            FacesContext.getCurrentInstance().addMessage("mensagem", fm);
            return "";
        }
    }

    public String edicaoEmprestimo() {
        this.emprestimo = this.emprestimosModel.getRowData();
        return "edicao";
    }

    public String excluirEmprestimo() {
        this.emprestimo = this.emprestimosModel.getRowData();
        if (dao.excluir(emprestimo)) {
            FacesMessage fm = new FacesMessage("excluido com sucesso");
            FacesContext.getCurrentInstance().addMessage("mensagem", fm);
            return "listarEmprestimos";
        } else {
            FacesMessage fm = new FacesMessage("erro ao excluir");
            FacesContext.getCurrentInstance().addMessage("mensagem", fm);
            return "";

        }
    }
}

