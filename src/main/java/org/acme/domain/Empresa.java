package org.acme.domain;

import java.time.LocalDate;

public class Empresa {
    private Long id;
    private String nome;
    private String cnpj;
    private String setor;
    private String uf;
    private String cidade;
    private String responsavel;
    private LocalDate dtCadastro;
    private Double metaConsumo;

    public Empresa() {}

    public Empresa(String nome, String cnpj, String setor, String uf, String cidade, String responsavel, LocalDate dtCadastro, Double metaConsumo) {
        this.nome = nome;
        this.cnpj = cnpj;
        this.setor = setor;
        this.uf = uf;
        this.cidade = cidade;
        this.responsavel = responsavel;
        this.dtCadastro = dtCadastro;
        this.metaConsumo = metaConsumo;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public String getCnpj() { return cnpj; }
    public void setCnpj(String cnpj) { this.cnpj = cnpj; }

    public String getSetor() { return setor; }
    public void setSetor(String setor) { this.setor = setor; }

    public String getUf() { return uf; }
    public void setUf(String uf) { this.uf = uf; }

    public String getCidade() { return cidade; }
    public void setCidade(String cidade) { this.cidade = cidade; }

    public String getResponsavel() { return responsavel; }
    public void setResponsavel(String responsavel) { this.responsavel = responsavel; }

    public LocalDate getDtCadastro() { return dtCadastro; }
    public void setDtCadastro(LocalDate dtCadastro) { this.dtCadastro = dtCadastro; }

    public Double getMetaConsumo() {
        return metaConsumo;
    }
    public void setMetaConsumo(Double metaConsumo) {
        this.metaConsumo = metaConsumo;
    }
}