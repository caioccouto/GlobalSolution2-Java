package org.acme.domain;

public class Rota {
    private Long id;
    private String nome;
    private String origem;
    private String destino;
    private Double distanciaKm;
    private String regiao;
    private Double origemLat;
    private Double origemLon;
    private Double destinoLat;
    private Double destinoLon;

    public Rota() {}

    public Rota(String nome, String origem, String destino, Double distanciaKm, String regiao,
                Double origemLat, Double origemLon, Double destinoLat, Double destinoLon) {
        this.nome = nome;
        this.origem = origem;
        this.destino = destino;
        this.distanciaKm = distanciaKm;
        this.regiao = regiao;
        this.origemLat = origemLat;
        this.origemLon = origemLon;
        this.destinoLat = destinoLat;
        this.destinoLon = destinoLon;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public String getOrigem() { return origem; }
    public void setOrigem(String origem) { this.origem = origem; }

    public String getDestino() { return destino; }
    public void setDestino(String destino) { this.destino = destino; }

    public Double getDistanciaKm() { return distanciaKm; }
    public void setDistanciaKm(Double distanciaKm) { this.distanciaKm = distanciaKm; }

    public String getRegiao() { return regiao; }
    public void setRegiao(String regiao) { this.regiao = regiao; }

    public Double getOrigemLat() { return origemLat; }
    public void setOrigemLat(Double origemLat) { this.origemLat = origemLat; }

    public Double getOrigemLon() { return origemLon; }
    public void setOrigemLon(Double origemLon) { this.origemLon = origemLon; }

    public Double getDestinoLat() { return destinoLat; }
    public void setDestinoLat(Double destinoLat) { this.destinoLat = destinoLat; }

    public Double getDestinoLon() { return destinoLon; }
    public void setDestinoLon(Double destinoLon) { this.destinoLon = destinoLon; }
}