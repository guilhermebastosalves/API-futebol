package dw.mensalidade.controller;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import dw.mensalidade.repository.JogadorRepository;
import dw.mensalidade.model.Jogador;

@RestController
public class JogadorController {

    @Autowired
    JogadorRepository rep;

    // GET: listar todos os jogadores ou listar de acordo com o nome enviado

    @GetMapping("/")
    public ResponseEntity<List<Jogador>> getAllJogadores(@RequestParam(required = false) String nome) {
        try {
            List<Jogador> list = new ArrayList<Jogador>();

            if (nome == null) {
                rep.findAll().forEach(list::add);
            } else {
                rep.findByNomeContainingIgnoreCase(nome).forEach(list::add);
            }

            if (list.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(list, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // POST : cadastrar um jogador

    @PostMapping("/")
    public ResponseEntity<Jogador> cadastrarJogador(@RequestBody Jogador jogador) {
        try {
            Jogador jg = rep.save(
                    new Jogador(jogador.getNome(), jogador.getEmail(), jogador.getDatanascimento(),
                            jogador.getPagamentos()));
            return new ResponseEntity<>(jg, HttpStatus.CREATED);

        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // PUT /:id : atualizar dados de um jogador dado o id

    @PutMapping("/{id}")
    public ResponseEntity<Jogador> updateJogador(@PathVariable("id") int id, @RequestBody Jogador jogador) {
        Optional<Jogador> data = rep.findById(id);

        if (data.isPresent()) {
            Jogador jg = data.get();
            jg.setDatanascimento(jogador.getDatanascimento());
            jg.setNome(jogador.getNome());
            jg.setEmail(jogador.getEmail());

            return new ResponseEntity<>(rep.save(jg), HttpStatus.OK);
        } else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }

    // DEL /:id : remover um jogador dado o id

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteJogador(@PathVariable("id") int id) {
        try {
            Optional<Jogador> data = rep.findById(id);
            if (data.isPresent()) {
                rep.deleteById(id);
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
}
