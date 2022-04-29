package Dao;

import Entidade.Emprestimo;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class EmprestimoDao {

    EntityManager em;
    EntityManagerFactory emf;

    public EmprestimoDao() {
        emf = Persistence.createEntityManagerFactory("BibliotecaPU");
        em = emf.createEntityManager();
    }

    public List<Emprestimo> listarEmprestimos() {
        List<Emprestimo> emprestimos = em.createQuery("select e from Emprestimo").getResultList();
        return emprestimos;
    }

    public boolean gravarEmprestimos(Emprestimo emprestimo) {
        try {
            em.getTransaction().begin();
            em.persist(emprestimo);
            em.getTransaction().commit();
            return true;
        } catch (Exception e) {
            em.getTransaction().rollback();
            return false;
        }
    }

    public boolean editar(Emprestimo emprestimo) {
        try {

            em.getTransaction().begin();
            em.merge(emprestimo);
            em.getTransaction().commit();
            return true;

        } catch (Exception e) {

            em.getTransaction().rollback();
            return false;
        }
    }

    public boolean excluir(Emprestimo emprestimo) {
        try {
            em.getTransaction().begin();
            em.remove(emprestimo);
            em.getTransaction().commit();
            return true;

        } catch (Exception e) {
            em.getTransaction().rollback();
            return false;

        }
    }
}

