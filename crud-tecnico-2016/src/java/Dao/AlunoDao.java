/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;


import Entidade.Aluno;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;


public class AlunoDao {
    EntityManager em;
    EntityManagerFactory emf;

    public AlunoDao() {
        emf = Persistence.createEntityManagerFactory("BibliotecaPU");
        em = emf.createEntityManager();
    }

    public List<Aluno> listarAlunos() {
        List<Aluno> alunos = em.createQuery("select a from Aluno").getResultList();
        return alunos;
    }

    public boolean gravarAlunos(Aluno aluno) {
        try {
            em.getTransaction().begin();
            em.persist(aluno);
            em.getTransaction().commit();
            return true;
        } catch (Exception e) {
            em.getTransaction().rollback();
            return false;
        }
    }

    public boolean editarAlunos(Aluno aluno) {
        try {

            em.getTransaction().begin();
            em.merge(aluno);
            em.getTransaction().commit();
            return true;

        } catch (Exception e) {

            em.getTransaction().rollback();
            return false;
        }
    }

    public boolean excluirAlunos(Aluno aluno) {
        try {
            em.getTransaction().begin();
            em.remove(aluno);
            em.getTransaction().commit();
            return true;

        } catch (Exception e) {
            em.getTransaction().rollback();
            return false;

        }
    }
}

