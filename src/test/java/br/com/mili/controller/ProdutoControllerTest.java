package br.com.mili.controller;

import br.com.mili.model.ProdutoFisico;
import br.com.mili.service.ProdutoService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ProdutoController.class)
class ProdutoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProdutoService produtoService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void deveAdicionarProduto() throws Exception {
        ProdutoFisico produto = new ProdutoFisico("Livro", 100.0, 0.5, false);
        when(produtoService.adicionarProduto(any())).thenReturn(produto);

        mockMvc.perform(post("/produtos")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(produto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nome").value("Livro"))
                .andExpect(jsonPath("$.preco").value(100.0));
    }

    @Test
    void deveListarProdutos() throws Exception {
        ProdutoFisico produto = new ProdutoFisico("Livro", 100.0, 0.5, false);
        when(produtoService.listarProdutos()).thenReturn(Arrays.asList(produto));

        mockMvc.perform(get("/produtos"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].nome").value("Livro"))
                .andExpect(jsonPath("$[0].preco").value(100.0));
    }

    @Test
    void deveRetornarMediaPrecos() throws Exception {
        when(produtoService.calcularMediaPrecos()).thenReturn(75.0);

        mockMvc.perform(get("/produtos/media-precos"))
                .andExpect(status().isOk())
                .andExpect(content().string("75.0"));
    }
}
