package br.com.calculos.calculos.app.service;


import br.com.calculos.calculos.app.entity.Carro;
import br.com.calculos.calculos.app.entity.Resultado;
import br.com.calculos.calculos.app.repository.CarroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.w3c.dom.ls.LSException;

import java.util.List;

@Service
public class CarroService {

    @Autowired
    private CarroRepository carroRepository;

    public String save(Carro carro) {
        this.carroRepository.save(carro);
        return  "Carro salvo com sucesso!";

    }

    public String update (Carro carro, long id) {
        carro.setId(id);
        this.carroRepository.save(carro);
        return "Carro atualizado com sucesso!";
    }

    public String delete(Long id){
        this.carroRepository.deleteById(id);
        return "Carro deletado com sucesso!";
    }

    public List<Carro> findAll(){
        List<Carro> lista = this.carroRepository.findAll();
    return lista;
    }

    public Carro findById(Long id){
        Carro carro = this.carroRepository.findById(id).get();
        return carro;
    }

}
