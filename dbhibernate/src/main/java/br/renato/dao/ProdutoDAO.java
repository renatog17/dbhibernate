package br.renato.dao;

import java.util.List;

import javax.persistence.EntityManager;

import br.renato.connection.ConnectionFactory;
import br.renato.domain.Categoria;
import br.renato.domain.Produto;

public class ProdutoDAO {

	public void inserir(Produto produto) {
		EntityManager em = new ConnectionFactory().getConnection();

		try {
			em.getTransaction().begin();
			if(produto.getId()==null)
				em.persist(produto);
			else
				em.merge(produto);
			em.getTransaction().commit();
		} catch (Exception e) {
			System.err.println();
			em.getTransaction().getRollbackOnly();
		} finally {
			em.close();
		}
	}
	
	public void editar(Produto produto) {
		inserir(produto);
	}
	
	public Produto buscarPorId(Integer id) {
		Produto produto = null;
		EntityManager em = new ConnectionFactory().getConnection();
		try {
			produto = em.find(Produto.class, id);
		} catch (Exception e) {
			System.err.println(e);
		} finally {
			em.close();
		}
		return produto;
	}
	
	public List<Produto> listar(){
		List<Produto> produtos = null;
		EntityManager em = new ConnectionFactory().getConnection();
		try {
			produtos = em.createQuery("from Produto p").getResultList();
		} catch (Exception e) {
			System.err.println(e);
		}finally {
			em.close();
		}
		return produtos;
	}

	public Produto remover(Integer id) {
		EntityManager em = new ConnectionFactory().getConnection();
		Produto produto = null;
		
		try {
			produto = em.find(Produto.class, id);
			em.getTransaction().begin();;
			em.remove(produto);
			em.getTransaction().commit();
		} catch (Exception e) {
			System.err.println(e);
			em.getTransaction().rollback();
		} finally {
			em.close();
		}
		return produto;
	}
}
