package br.com.mili.model;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@DiscriminatorValue("FISICO")
public class ProdutoFisico extends Produto {
    private double peso;

    public ProdutoFisico() {}

    public ProdutoFisico(String nome, double preco, double peso, boolean emPromocao) {
        super(nome, preco, emPromocao);
        this.peso = peso;
    }

    public double getPeso() { return peso; }
    public void setPeso(double peso) { this.peso = peso; }

    @JsonProperty("valorFrete")
    @Override
    public double calcularFrete() {
        return peso * 10;
    }
}
