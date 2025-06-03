package cl.proyecto.proyecto.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import cl.proyecto.proyecto.model.user;

@Repository
public interface userRepository extends JpaRepository<user, Integer> {

}
