package dw.mensalidade.model;

import jakarta.persistence.Id;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;

@Entity
@Table(name = "pagamento")
public class Pagamento {

    // Atributos da entidade pagamento

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int cod_pagamento;

    @Column(nullable = false)
    private Short ano;

    @Column(nullable = false)
    private Byte mes;

    @Column(nullable = false)
    private BigDecimal valor;

    @ManyToOne
    @JoinColumn(name = "cod_jogador", nullable = false)
    @JsonBackReference
    private Jogador jogador;

    // Construtor

    public Pagamento(Short ano, Byte mes, BigDecimal valor, Jogador jogador) {
        this.ano = ano;
        this.mes = mes;
        this.valor = valor;
        this.jogador = jogador;
    }

    public Pagamento() {

    }

    // Métodos GET e SET

    public int getCodpagamento() {
        return this.cod_pagamento;
    }

    public void setCodpagamento(int cod_pagamento) {
        this.cod_pagamento = cod_pagamento;
    }

    @Transient
    public Jogador getJogador() {
        return this.jogador;
    }

    @Transient
    public void setJogador(Jogador jogador) {
        this.jogador = jogador;
    }

    public Short getAno() {
        return this.ano;
    }

    public void setAno(Short ano) {
        this.ano = ano;
    }

    public Byte getMes() {
        return this.mes;
    }

    public void setMes(Byte mes) {
        this.mes = mes;
    }

    public BigDecimal getValor() {
        return this.valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    @Override
    public String toString() {
        return "Pagamento [cod_pagamento=" + cod_pagamento + ", ano=" + ano + ", mes=" + mes + ", valor=" + valor + "]";
    }

}
