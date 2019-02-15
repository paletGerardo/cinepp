package net.gerr.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import net.gerr.app.model.Usuario;

public interface UsuariosRepository extends JpaRepository<Usuario, Integer> {

}
