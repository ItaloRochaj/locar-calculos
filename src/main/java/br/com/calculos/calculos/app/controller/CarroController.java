package br.com.calculos.calculos.app.controller;

import br.com.calculos.calculos.app.entity.Carro;
import br.com.calculos.calculos.app.service.CarroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/carro")
public class CarroController {

    @Autowired
    private CarroService carroService;

    @PostMapping("/save")
    public ResponseEntity<String> save(@RequestBody Carro carro){

        try{
            String mensagem = this.carroService.save(carro);
            return new ResponseEntity<>(mensagem,HttpStatus.CREATED);
        }catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<String> update(@RequestBody Carro carro,@PathVariable long id){
        try{
            String mensagem = this.carroService.update(carro, id);
            return new ResponseEntity<>(mensagem,HttpStatus.OK);

        }catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }

    }

    @DeleteMapping("/delete/{id}")
    public  ResponseEntity<String> delete(Carro carro, @PathVariable long id){
        try{
            String mensagem = this.carroService.delete(id);
            return new ResponseEntity<>(mensagem,HttpStatus.OK);
        }catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }

    }

    @GetMapping("/findAll")
    public ResponseEntity <List<Carro>> finAll(){
        try{
            List<Carro> lista = this.carroService.findAll();
            return new ResponseEntity<>(lista, HttpStatus.OK);

        }catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }

    }

    @GetMapping("/findByid/{id}")
    public ResponseEntity <Carro> findByid(@PathVariable Long id){
        try{
            Carro carro = this.carroService.findById(id);
            return new ResponseEntity<>(carro, HttpStatus.OK);
        }catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }

    }

}

