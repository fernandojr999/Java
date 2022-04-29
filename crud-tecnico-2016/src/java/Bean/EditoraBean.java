
package Bean;

import Dao.EditoraDao;
import Entidade.Editora;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;



public class EditoraBean {
    private Editora editora;

    public Editora getEditora() {
        return editora;
    }

    public void setEditora(Editora editora) {
        this.editora = editora;
    }
    private EditoraDao dao;
    private List<Editora> editoras;
    private DataModel<Editora> editorasModel;

    public EditoraBean() {
        editora = new Editora();
        dao = new EditoraDao();
        editoras = new ArrayList<Editora>();
        editorasModel = new ListDataModel<Editora>(editoras);
    }

    @PostConstruct
    public void carregaEditoras() {
        editoras.clear();
        List<Editora> livs = dao.listarEditoras();
        for (Editora l : livs) {
            editoras.add(l);
        }
    }

    public String listarEditora() {
        return "listar";
    }

    public String cadastrarEditora() {
        return "cadastro";
    }

    public String editarEditora() {
        if (dao.editarEditoras(editora)) {
            FacesMessage fm = new FacesMessage("editado com sucesso");
            FacesContext.getCurrentInstance().addMessage("mensagem", fm);
            
            return "listarEditoras";
        } else {
            FacesMessage fm = new FacesMessage("erro ao editar");
            FacesContext.getCurrentInstance().addMessage("mensagem", fm);
            return "";
        }
    }

    public String edicaoEditora() {
        this.editora = this.editorasModel.getRowData();
        return "edicao";
    }

    public String excluirEditora() {
        this.editora = this.editorasModel.getRowData();
        if (dao.excluirEditora(editora)) {
            FacesMessage fm = new FacesMessage("excluido com sucesso");
            FacesContext.getCurrentInstance().addMessage("mensagem", fm);
            return "listarEditoras";
        } else {
            FacesMessage fm = new FacesMessage("erro ao excluir");
            FacesContext.getCurrentInstance().addMessage("mensagem", fm);
            return "";

        }
    }
}

