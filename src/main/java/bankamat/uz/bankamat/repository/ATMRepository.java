package bankamat.uz.bankamat.repository;

import bankamat.uz.bankamat.entity.ATM;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ATMRepository extends JpaRepository<ATM, Integer> {
}
