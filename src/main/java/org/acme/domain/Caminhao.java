package org.acme.domain;

public class Caminhao {
    private Long id;
    private String placa;
    private String modelo;
    private Integer anoFabricacao;
    private Double capacidadeCarga;
    private Long empresaId;

    public Caminhao() {}

    public Caminhao(String placa, String modelo, Integer anoFabricacao, Double capacidadeCarga, Long empresaId) {
        this.placa = placa;
        this.modelo = modelo;
        this.anoFabricacao = anoFabricacao;
        this.capacidadeCarga = capacidadeCarga;
        this.empresaId = empresaId;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getPlaca() { return placa; }
    public void setPlaca(String placa) { this.placa = placa; }

    public String getModelo() { return modelo; }
    public void setModelo(String modelo) { this.modelo = modelo; }

    public Integer getAnoFabricacao() { return anoFabricacao; }
    public void setAnoFabricacao(Integer anoFabricacao) { this.anoFabricacao = anoFabricacao; }

    public Double getCapacidadeCarga() { return capacidadeCarga; }
    public void setCapacidadeCarga(Double capacidadeCarga) { this.capacidadeCarga = capacidadeCarga; }

    public Long getEmpresaId() { return empresaId; }
    public void setEmpresaId(Long empresaId) { this.empresaId = empresaId; }
}
