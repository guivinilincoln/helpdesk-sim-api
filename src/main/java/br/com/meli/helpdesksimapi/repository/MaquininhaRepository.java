package br.com.meli.helpdesksimapi.repository;

import br.com.meli.helpdesksimapi.model.Maquininha;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MaquininhaRepository extends JpaRepository<Maquininha, Long> {
}
