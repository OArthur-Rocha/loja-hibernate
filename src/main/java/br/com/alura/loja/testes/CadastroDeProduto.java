package br.com.alura.loja.testes;

import br.com.alura.loja.dao.CategoriaDAO;
import br.com.alura.loja.dao.ProdutoDAO;
import br.com.alura.loja.modelo.Categoria;
import br.com.alura.loja.modelo.Produto;
import br.com.alura.loja.util.JPAUtil;

import javax.persistence.EntityManager;
import java.math.BigDecimal;
import java.util.List;

public class CadastroDeProduto {

    public static void main(String[] args) {
        //cadastrarProduto();

        EntityManager em = JPAUtil.getEntityManager();
        ProdutoDAO produtoDAO = new ProdutoDAO(em);

        Produto p =  produtoDAO.buscarPorId(1l);
        System.out.println(p.getPreco());

        List<Produto> todos = produtoDAO.buscarPorNomeDaCategoria("CELULARES");
        for (Produto p2 : todos) {
            System.out.println(p2.getNome());
        }

        BigDecimal precoDoProduto = produtoDAO.buscarPrecoDoProdutoComNome("RedMi Note 12");
        System.out.println("Preço do produto: R$" + precoDoProduto);
    }

    private static void cadastrarProduto() {
        Categoria celulares = new Categoria("CELULARES");
        Produto celular = new Produto("RedMi Note 12", "Muito legal", new BigDecimal("800"),
                celulares);

        EntityManager em = JPAUtil.getEntityManager();
        ProdutoDAO produtoDAO = new ProdutoDAO(em);
        CategoriaDAO categoriaDAO = new CategoriaDAO(em);

        em.getTransaction().begin();
        categoriaDAO.cadastrar(celulares);
        produtoDAO.cadastrar(celular);
        em.getTransaction().commit();
        em.close();
    }
}
