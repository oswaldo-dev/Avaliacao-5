package br.com.compass.site.repository;

import br.com.compass.site.model.Cartao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartaoRepository extends JpaRepository<Cartao, Long> {

    boolean existsByNumero(String numero);
}
