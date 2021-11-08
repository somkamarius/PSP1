package mif.projektavimas.lab3.repository;

import mif.projektavimas.lab3.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
