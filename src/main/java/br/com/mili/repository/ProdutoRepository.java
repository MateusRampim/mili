package br.com.mili.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import br.com.mili.model.Produto;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {
    
    @Query(value = "SELECT * FROM produto WHERE preco = (SELECT MAX(preco) FROM produto)", nativeQuery = true)
    Produto findProdutoMaisCaro();

    @Query("SELECT AVG(p.preco) FROM Produto p")
    Double calcularMediaPrecos();
}
