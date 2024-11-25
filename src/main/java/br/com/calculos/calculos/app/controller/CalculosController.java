package br.com.calculos.calculos.app.controller;

import br.com.calculos.calculos.app.entity.Entrada;
import br.com.calculos.calculos.app.entity.Resultado;
import br.com.calculos.calculos.app.service.CalculosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/calculos")
public class CalculosController {

    @Autowired
    private CalculosService calculosService;

    @GetMapping("/somar")
    public ResponseEntity<Resultado> somar(@RequestBody Entrada entrada){

        try{

           Resultado resultado = this.calculosService.somar(entrada);
           return new  ResponseEntity<Resultado>(resultado,HttpStatus.OK);

        } catch (Exception e ) {

            return new ResponseEntity<>(null,HttpStatus.BAD_REQUEST);

        }
    }
}

