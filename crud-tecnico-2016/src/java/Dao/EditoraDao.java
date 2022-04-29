
package Dao;


import Entidade.Editora;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class EditoraDao {
    EntityManager em;
    EntityManagerFactory emf;

    public EditoraDao() {
        emf = Persistence.createEntityManagerFactory("BibliotecaPU");
        em = emf.createEntityManager();
    }

    public List<Editora> listarEditoras() {
        List<Editora> editoras = em.createQuery("select e from Editora").getResultList();
        return editoras;
    }

    public boolean gravarEditoras(Editora editora) {
        try {
            em.getTransaction().begin();
            em.persist(editora);
            em.getTransaction().commit();
            return true;
        } catch (Exception e) {
            em.getTransaction().rollback();
            return false;
        }
    }

    public boolean editarEditoras(Editora editora) {
        try {

            em.getTransaction().begin();
            em.merge(editora);
            em.getTransaction().commit();
            return true;

        } catch (Exception e) {

            em.getTransaction().rollback();
            return false;
        }
    }

    public boolean excluirEditora(Editora editora) {
        try {
            em.getTransaction().begin();
            em.remove(editora);
            em.getTransaction().commit();
            return true;

        } catch (Exception e) {
            em.getTransaction().rollback();
            return false;

        }
    }
}


