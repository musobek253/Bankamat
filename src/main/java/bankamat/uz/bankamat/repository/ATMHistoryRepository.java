package bankamat.uz.bankamat.repository;

import bankamat.uz.bankamat.entity.ATMHistory;
import bankamat.uz.bankamat.entity.enums.ATMOperationType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface ATMHistoryRepository extends JpaRepository<ATMHistory, Integer> {

    List<ATMHistory> findAllByAtmId(Integer atm_id);
    List<ATMHistory> findAllByAtmIdAndDateAndOperationType(Integer atm_id, LocalDate date, ATMOperationType operationType);
}
