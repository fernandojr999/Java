/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;


import Entidade.Livro;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author informatica
 */
public class LivroDao {
    EntityManager em;
    EntityManagerFactory emf;

    public LivroDao() {
        emf = Persistence.createEntityManagerFactory("BibliotecaPU");
        em = emf.createEntityManager();
    }

    public List<Livro> listarLivros() {
        List<Livro> livros = em.createQuery("select l from Livro").getResultList();
        return livros;
    }

    public boolean gravarLivros(Livro livro) {
        try {
            em.getTransaction().begin();
            em.persist(livro);
            em.getTransaction().commit();
            return true;
        } catch (Exception e) {
            em.getTransaction().rollback();
            return false;
        }
    }

    public boolean editar(Livro livro) {
        try {

            em.getTransaction().begin();
            em.merge(livro);
            em.getTransaction().commit();
            return true;

        } catch (Exception e) {

            em.getTransaction().rollback();
            return false;
        }
    }

    public boolean excluir(Livro livro) {
        try {
            em.getTransaction().begin();
            em.remove(livro);
            em.getTransaction().commit();
            return true;

        } catch (Exception e) {
            em.getTransaction().rollback();
            return false;

        }
    }
}

