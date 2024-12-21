package br.com.mili.service;

import br.com.mili.model.ProdutoDigital;
import br.com.mili.model.ProdutoFisico;
import br.com.mili.repository.ProdutoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ProdutoServiceTest {

    @InjectMocks
    private ProdutoService produtoService;

    @Mock
    private ProdutoRepository produtoRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void deveAdicionarProdutoFisico() {
        ProdutoFisico produto = new ProdutoFisico("Livro", 100.0, 0.5, false);
        when(produtoRepository.save(any())).thenReturn(produto);

        var resultado = produtoService.adicionarProduto(produto);

        assertNotNull(resultado);
        assertEquals("Livro", resultado.getNome());
        verify(produtoRepository).save(produto);
    }

    @Test
    void deveAdicionarProdutoDigital() {
        ProdutoDigital produto = new ProdutoDigital("Ebook", 50.0, 10.0, true);
        when(produtoRepository.save(any())).thenReturn(produto);

        var resultado = produtoService.adicionarProduto(produto);

        assertNotNull(resultado);
        assertEquals("Ebook", resultado.getNome());
        verify(produtoRepository).save(produto);
    }

    @Test
    void deveCalcularMediaPrecos() {
        when(produtoRepository.calcularMediaPrecos()).thenReturn(75.0);

        double media = produtoService.calcularMediaPrecos();

        assertEquals(75.0, media);
        verify(produtoRepository).calcularMediaPrecos();
    }

    @Test
    void deveAtualizarProdutoFisico() {
        Long id = 1L;
        ProdutoFisico existente = new ProdutoFisico("Livro", 100.0, 0.5, false);
        ProdutoFisico atualizado = new ProdutoFisico("Livro Atualizado", 150.0, 0.7, true);

        when(produtoRepository.findById(id)).thenReturn(Optional.of(existente));
        when(produtoRepository.save(any())).thenReturn(existente);

        var resultado = produtoService.atualizarProduto(id, atualizado);

        assertNotNull(resultado);
        assertEquals("Livro Atualizado", resultado.getNome());
        assertEquals(150.0, resultado.getPreco());
        assertTrue(resultado.isEmPromocao());
    }
}
