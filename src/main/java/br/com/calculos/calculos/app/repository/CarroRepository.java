package br.com.calculos.calculos.app.repository;

import br.com.calculos.calculos.app.entity.Carro;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CarroRepository extends JpaRepository <Carro, Long> {

}
