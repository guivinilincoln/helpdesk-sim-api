package br.com.meli.helpdesksimapi.repository;

import br.com.meli.helpdesksimapi.model.Balcao;
import br.com.meli.helpdesksimapi.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    List<Usuario> buscarUsuarioCustomerId(String nome);

}
