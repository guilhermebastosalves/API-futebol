package dw.mensalidade.controller;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dw.mensalidade.model.Pagamento;
import dw.mensalidade.repository.JogadorRepository;
import dw.mensalidade.repository.PagamentoReposiroty;

@RestController
@RequestMapping("/pagamentos")
public class PagamentoController {

    @Autowired
    PagamentoReposiroty pagamentoRep;

    @Autowired
    JogadorRepository jogadorRep;

    // GET: listar todos os pagamentos

    @GetMapping("/")
    public ResponseEntity<List<Pagamento>> getAllPagamentos() {
        try {
            List<Pagamento> list = new ArrayList<Pagamento>();

            pagamentoRep.findAll().forEach(list::add);

            if (list.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(list, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // GET: listar pagamento de acordo com o id

    @GetMapping("/{id}")
    public ResponseEntity<Pagamento> getPagagamentoById(@PathVariable("id") int id) {
        Optional<Pagamento> data = pagamentoRep.findById(id);

        if (data.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(data.get(), HttpStatus.OK);

    }

    // POST : cadastrar um pagamento

    @PostMapping("/")
    public ResponseEntity<Pagamento> cadastrarPagamento(@RequestBody Pagamento pagamento) {
        try {
            if (pagamento.getAno() > 2025 || pagamento.getMes() > 12 || pagamento.getMes() <= 0) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
            if (pagamento.getValor().compareTo(BigDecimal.ZERO) == -1) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
            if (jogadorRep.findById(pagamento.getJogador().getCodjogador()).isEmpty()) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
            Pagamento pg = pagamentoRep.save(new Pagamento(pagamento.getAno(), pagamento.getMes(), pagamento.getValor(),
                    pagamento.getJogador()));
            return new ResponseEntity<>(pg, HttpStatus.CREATED);

        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // PUT /:id : atualizar dados de um pagamento dado o id

    @PutMapping("/{id}")
    public ResponseEntity<Pagamento> updatePagamento(@PathVariable("id") int id, @RequestBody Pagamento pagamento) {
        Optional<Pagamento> data = pagamentoRep.findById(id);

        if (data.isPresent()) {
            if (pagamento.getAno() > 2025 || pagamento.getMes() > 12 || pagamento.getMes() < 0) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
            if (pagamento.getValor().compareTo(BigDecimal.ZERO) == -1) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
            Pagamento jg = data.get();
            jg.setAno(pagamento.getAno());
            jg.setMes(pagamento.getMes());
            jg.setValor(pagamento.getValor());

            return new ResponseEntity<>(pagamentoRep.save(jg), HttpStatus.OK);
        } else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }

    // DEL /:id : remover um pagamento dado o id

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deletePagamento(@PathVariable("id") int id) {
        try {
            Optional<Pagamento> data = pagamentoRep.findById(id);

            if (data.isPresent()) {
                pagamentoRep.deleteById(id);
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

}
