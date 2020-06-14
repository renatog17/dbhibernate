package br.renato.main;

import java.util.List;

import br.renato.dao.CategoriaDAO;
import br.renato.dao.ProdutoDAO;
import br.renato.domain.Categoria;
import br.renato.domain.Produto;

public class Main {

	static ProdutoDAO daoP = new ProdutoDAO();
	static CategoriaDAO daoC = new CategoriaDAO();
	public static void main(String[] args) {
		//inserirCategoria();
		//inserirProduto();
		Categoria c = daoC.buscarPorId(2);
		List<Produto> produtos = c.getProdutos();
		
		for (Produto produto : produtos) {
			System.out.println(produto.getDescricao());
		}
		
		
		//System.out.println(p.getCategoria().getNome());
		/*Produto produtinho = daoP.buscarPorId(1);
		System.out.println(produtinho.getDescricao());
		System.out.println(produtinho.getCategoria().getNome());
		Categoria categoria = daoC.buscarPorId(1);
		System.out.println("*********");
		System.out.println(categoria.getNome());
		try {
			
			System.out.println(categoria.getProdutos().get(0));
		} catch (Exception e) {
			e.printStackTrace();
			e.getCause();
		}
		System.out.println(categoria.getProdutos().get(1));
		*/
	}
	
	public static void inserirCategoria() {
		Categoria c = new Categoria();
		c.setNome("Escritório");
		Categoria c2 = new Categoria();
		c2.setNome("Banheiro");
		daoC.inserir(c);
		daoC.inserir(c2);
	}

	public static void inserirProduto() {		
		Categoria c = daoC.buscarPorId(1);
		
		Produto p1 = new Produto();
		p1.setCategoria(c);
		p1.setDescricao("Clipe de Papel");
		p1.setQtd(5);
		p1.setValor(5.50);

		Produto p2 = new Produto();
		p2.setCategoria(c);
		p2.setDescricao("Grampeador");
		p2.setQtd(15);
		p2.setValor(15.50);
		
		Categoria c2 = daoC.buscarPorId(2);

		Produto p3 = new Produto();
		p3.setCategoria(c2);
		p3.setDescricao("Toalha");
		p3.setQtd(17);
		p3.setValor(10.00);
		

		Produto p4 = new Produto();
		p4.setCategoria(c2);
		p4.setDescricao("Sabonete");
		p4.setQtd(25);
		p4.setValor(2.60);
		
		
		daoP.inserir(p4);
		daoP.inserir(p3);
		daoP.inserir(p2);
		daoP.inserir(p1);
	}
}
