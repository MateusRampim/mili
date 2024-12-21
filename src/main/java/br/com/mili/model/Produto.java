package br.com.mili.model;

import jakarta.persistence.*;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonSubTypes.Type;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "tipo")
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "tipo")
@JsonSubTypes({
    @Type(value = ProdutoFisico.class, name = "FISICO"),
    @Type(value = ProdutoDigital.class, name = "DIGITAL")
})
public abstract class Produto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private double preco;
    private boolean emPromocao;

    public Produto() {}

    public Produto(String nome, double preco, boolean emPromocao) {
        this.nome = nome;
        this.preco = preco;
        this.emPromocao = emPromocao;
    }
    
    public abstract double calcularFrete();

    // Getters e Setters
    public Long getId() { return id; }
    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }
    public double getPreco() { return preco; }
    public void setPreco(double preco) { this.preco = preco; }
    public boolean isEmPromocao() { return emPromocao; }
    public void setEmPromocao(boolean emPromocao) { this.emPromocao = emPromocao; }

    public double getPrecoComDesconto() {
        return emPromocao ? preco * 0.9 : preco;
    }

    public double getValorTotal() {
        return getPrecoComDesconto() + calcularFrete();
    }
}
