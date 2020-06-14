package br.renato.dao;

import java.util.List;

import javax.persistence.EntityManager;

import br.renato.connection.ConnectionFactory;
import br.renato.domain.Categoria;

public class CategoriaDAO {
	
	
	public void inserir(Categoria c) {
		
		
		EntityManager em = new ConnectionFactory().getConnection();
		
		try {
			em.getTransaction().begin();
			if(c.getId()==null)
				em.persist(c);
			else
				em.merge(c);
			em.getTransaction().commit();
		}catch (Exception e) {
			System.err.println(e);
			em.getTransaction().rollback();
		}finally{
			em.close();
		}
	}
	
	public void editar(Categoria c) {
		inserir(c);
	}
	
	public Categoria buscarPorId(Integer id) {
		Categoria categoria = null;
		EntityManager em = new ConnectionFactory().getConnection();
		
		try {
			categoria = em.find(Categoria.class, id);
		} catch (Exception e) {
			System.err.println(e);
		}finally {
			em.close();
		}
		
		return categoria;
	}
	
	public List<Categoria> listar(){
		EntityManager em = new ConnectionFactory().getConnection();
		List<Categoria> categorias = null;
		try {
			categorias = em.createQuery("from Categoria c").getResultList(); //jpql
		} catch (Exception e) {
			System.err.println(e);
		}finally {
			em.close();
		}
		return categorias;
	}
	
	public Categoria remover(Integer id) {
		EntityManager em = new ConnectionFactory().getConnection();
		Categoria categoria = null;
		try {
			categoria = em.find(Categoria.class, id);
			em.getTransaction().begin();;
			em.remove(categoria);
			em.getTransaction().commit();
		} catch (Exception e) {
			System.err.println(e);
			em.getTransaction().rollback();
		}finally {
			em.close();
		}
		return categoria;
	}
}
