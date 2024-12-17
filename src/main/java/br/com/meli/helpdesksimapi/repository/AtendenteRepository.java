package br.com.meli.helpdesksimapi.repository;

import br.com.meli.helpdesksimapi.model.Atendente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AtendenteRepository extends JpaRepository<Atendente, Long> {
}
