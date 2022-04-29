package Bean;

import Dao.AlunoDao;
import Entidade.Aluno;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;

@ManagedBean (name = "alunoBean")
@SessionScoped
public class AlunoBean {
    private Aluno aluno;

    public Aluno getAluno() {
        return aluno;
    }

    public void setAluno(Aluno aluno) {
        this.aluno = aluno;
    }
    private AlunoDao dao;
    private List<Aluno> alunos;
    private DataModel<Aluno> alunosModel;

    public AlunoBean() {
        aluno = new Aluno();
        dao = new AlunoDao();
        alunos = new ArrayList<Aluno>();
        alunosModel = new ListDataModel<Aluno>(alunos);
    }

    @PostConstruct
    public void trazAlunos() {
        alunos.clear();
        List<Aluno> livs = dao.listarAlunos();
        for (Aluno l : livs) {
            alunos.add(l);
        }
    }

    public String listarAluno() {
        return "listar";
    }

    public String cadastrarAluno() {
        return "cadastro";
    }

    public String editarAluno() {
        if (dao.editarAlunos(aluno)) {
            FacesMessage fm = new FacesMessage("editado com sucesso");
            FacesContext.getCurrentInstance().addMessage("mensagem", fm);
            
            return "listarAlunos";
        } else {
            FacesMessage fm = new FacesMessage("erro ao editar");
            FacesContext.getCurrentInstance().addMessage("mensagem", fm);
            return "";
        }
    }

    public String editaAluno() {
        this.aluno = this.alunosModel.getRowData();
        return "edicao";
    }

    public String excluirAluno() {
        this.aluno = this.alunosModel.getRowData();
        if (dao.excluirAlunos(aluno)) {
            FacesMessage fm = new FacesMessage("excluido com sucesso");
            FacesContext.getCurrentInstance().addMessage("mensagem", fm);
            return "listarAlunos";
        } else {
            FacesMessage fm = new FacesMessage("erro ao excluir");
            FacesContext.getCurrentInstance().addMessage("mensagem", fm);
            return "";

        }
    }
}


