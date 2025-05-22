package dw.mensalidade.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import dw.mensalidade.model.Pagamento;

public interface PagamentoReposiroty extends JpaRepository<Pagamento, Integer> {

    List<Pagamento> findByValor(float valor);
}
