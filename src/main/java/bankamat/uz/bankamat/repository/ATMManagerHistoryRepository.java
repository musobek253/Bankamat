package bankamat.uz.bankamat.repository;

import bankamat.uz.bankamat.entity.ATMManagerOperationsHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface ATMManagerHistoryRepository extends JpaRepository<ATMManagerOperationsHistory,Integer>{
        List<ATMManagerHistoryRepository> findAllByAtmId(Integer atm_id);
}
