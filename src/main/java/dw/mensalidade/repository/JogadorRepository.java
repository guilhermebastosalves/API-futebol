package dw.mensalidade.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import dw.mensalidade.model.Jogador;

public interface JogadorRepository extends JpaRepository<Jogador, Integer> {
    // Aqui você pode adicionar métodos personalizados, se necessário
    // Exemplo: List<Artigo> findByPublicado(boolean publicado);

    List<Jogador> findByNomeContainingIgnoreCase(String nome);

    List<Jogador> findByEmailContainingIgnoreCase(String email);

}