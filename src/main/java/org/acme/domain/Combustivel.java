package org.acme.domain;

public class Combustivel {
    private Long id;
    private String nome;
    private Double fatorEmissaoCarbono;

    public Combustivel() {}

    public Combustivel(String nome, Double fatorEmissaoCarbono) {
        this.nome = nome;
        this.fatorEmissaoCarbono = fatorEmissaoCarbono;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public Double getFatorEmissaoCarbono() { return fatorEmissaoCarbono; }
    public void setFatorEmissaoCarbono(Double fatorEmissaoCarbono) { this.fatorEmissaoCarbono = fatorEmissaoCarbono; }
}