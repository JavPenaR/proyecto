package cl.proyecto.proyecto.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import cl.proyecto.proyecto.dto.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    public Optional<User> findByMail(String email);


}
