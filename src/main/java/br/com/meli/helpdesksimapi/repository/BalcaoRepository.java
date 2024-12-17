package br.com.meli.helpdesksimapi.repository;

import br.com.meli.helpdesksimapi.model.Balcao;
import org.springframework.data.domain.Limit;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BalcaoRepository extends JpaRepository<Balcao, Long> {
    List<Balcao> buscarPorUsuarioCustomerId(String nome);
}
