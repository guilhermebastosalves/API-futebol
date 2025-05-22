package dw.mensalidade.model;

import java.time.LocalDate;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "jogador")
public class Jogador {

    // Atributos da entidade jogador

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int cod_jogador;

    @Column(nullable = false, length = 60)
    private String nome;

    @Column(nullable = false, length = 60)
    private String email;

    @Column(nullable = false)
    private LocalDate datanasc;

    @OneToMany(mappedBy = "jogador", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<Pagamento> pagamentos;

    // Construtor

    public Jogador(String nome, String email, LocalDate datanasc, List<Pagamento> pagamentos) {
        this.nome = nome;
        this.email = email;
        this.datanasc = datanasc;
        this.pagamentos = pagamentos;
    }

    public Jogador() {

    }

    // Métodos GET e SET

    public int getCodjogador() {
        return this.cod_jogador;
    }

    public void setCodjogador(int id) {
        this.cod_jogador = id;
    }

    public String getNome() {
        return this.nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDate getDatanascimento() {
        return this.datanasc;
    }

    public void setDatanascimento(LocalDate datanasc) {
        this.datanasc = datanasc;
    }

    public List<Pagamento> getPagamentos() {
        return pagamentos;
    }

    public void setPagamentos(List<Pagamento> pagamentos) {
        this.pagamentos = pagamentos;
    }

    // @Override
    // public String toString() {
    // return "Jogador [cod_jogador=" + cod_jogador + ", nome=" + nome + ", email="
    // + email + ", data_nascimento="
    // + datanasc + "]";
    // }

}
