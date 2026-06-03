package org.acme.domain;

import java.time.LocalDate;

public class Motorista {
    private Long id;
    private String nome;
    private String cpf;
    private String numeroCnh;
    private LocalDate validadeCnh;
    private Long empresaId;

    public Motorista() {}

    public Motorista(String nome, String cpf, String numeroCnh, LocalDate validadeCnh, Long empresaId) {
        this.nome = nome;
        this.cpf = cpf;
        this.numeroCnh = numeroCnh;
        this.validadeCnh = validadeCnh;
        this.empresaId = empresaId;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public String getCpf() { return cpf; }
    public void setCpf(String cpf) { this.cpf = cpf; }

    public String getNumeroCnh() { return numeroCnh; }
    public void setNumeroCnh(String numeroCnh) { this.numeroCnh = numeroCnh; }

    public LocalDate getValidadeCnh() { return validadeCnh; }
    public void setValidadeCnh(LocalDate validadeCnh) { this.validadeCnh = validadeCnh; }

    public Long getEmpresaId() { return empresaId; }
    public void setEmpresaId(Long empresaId) { this.empresaId = empresaId; }
}