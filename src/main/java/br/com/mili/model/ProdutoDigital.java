package br.com.mili.model;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("DIGITAL")
public class ProdutoDigital extends Produto {
    private double tamanho;

    public ProdutoDigital() {}

    public ProdutoDigital(String nome, double preco, double tamanho, boolean emPromocao) {
        super(nome, preco, emPromocao);
        this.tamanho = tamanho;
    }

    public double getTamanho() { return tamanho; }
    public void setTamanho(double tamanho) { this.tamanho = tamanho; }

    @Override
    public double calcularFrete() {
        return 0;
    }
}
