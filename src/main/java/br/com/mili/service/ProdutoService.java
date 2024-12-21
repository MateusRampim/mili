package br.com.mili.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.mili.model.Produto;
import br.com.mili.model.ProdutoDigital;
import br.com.mili.model.ProdutoFisico;
import br.com.mili.repository.ProdutoRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProdutoService {

    @Autowired
    private ProdutoRepository produtoRepository;

    public Produto adicionarProduto(Produto produto) {
        if (!(produto instanceof ProdutoFisico || produto instanceof ProdutoDigital)) {
            throw new IllegalArgumentException("Tipo de produto inválido");
        }
        return produtoRepository.save(produto);
    }

    public List<Produto> listarProdutos() {
        return produtoRepository.findAll();
    }

    public Produto atualizarProduto(Long id, Produto produtoAtualizado) {
        Produto produtoExistente = buscarProdutoPorId(id);
        
        // Mantém o mesmo ID
        if (produtoExistente.getClass() != produtoAtualizado.getClass()) {
            throw new IllegalArgumentException("Não é possível mudar o tipo do produto");
        }
        
        // Atualiza os dados básicos
        produtoExistente.setNome(produtoAtualizado.getNome());
        produtoExistente.setPreco(produtoAtualizado.getPreco());
        produtoExistente.setEmPromocao(produtoAtualizado.isEmPromocao());
        
        // Atualiza campos específicos baseado no tipo
        if (produtoExistente instanceof ProdutoFisico) {
            ((ProdutoFisico) produtoExistente).setPeso(((ProdutoFisico) produtoAtualizado).getPeso());
        } else if (produtoExistente instanceof ProdutoDigital) {
            ((ProdutoDigital) produtoExistente).setTamanho(((ProdutoDigital) produtoAtualizado).getTamanho());
        }
        
        return produtoRepository.save(produtoExistente);
    }

    public void removerProduto(Long id) {
        if (!produtoRepository.existsById(id)) {
            throw new RuntimeException("Produto não encontrado");
        }
        produtoRepository.deleteById(id);
    }

    public Produto buscarProdutoMaisCaro() {
        Produto produtoMaisCaro = produtoRepository.findProdutoMaisCaro();
        if (produtoMaisCaro == null) {
            throw new RuntimeException("Nenhum produto encontrado");
        }
        return produtoMaisCaro;
    }

    public double calcularMediaPrecos() {
        Double media = produtoRepository.calcularMediaPrecos();
        return media != null ? media : 0.0;
    }

    private Produto buscarProdutoPorId(Long id) {
        return produtoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Produto não encontrado"));
    }
}
