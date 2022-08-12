package bankamat.uz.bankamat.repository;

import bankamat.uz.bankamat.entity.User;
import bankamat.uz.bankamat.entity.enums.RoleName;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    Optional<User> findByRole(RoleName role);
}
