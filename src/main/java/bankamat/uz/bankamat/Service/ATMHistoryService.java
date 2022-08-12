package bankamat.uz.bankamat.Service;

import bankamat.uz.bankamat.payload.types.ApiResponse;
import bankamat.uz.bankamat.repository.ATMHistoryRepository;
import bankamat.uz.bankamat.repository.ATMRepository;
import bankamat.uz.bankamat.entity.ATM;
import bankamat.uz.bankamat.entity.ATMHistory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static bankamat.uz.bankamat.entity.enums.ATMOperationType.PULKIRITAWAL;
import static bankamat.uz.bankamat.entity.enums.ATMOperationType.WITHDRAWAL;

@Service
public class ATMHistoryService {
    @Autowired
    ATMHistoryRepository atmHistoryRepository;
    @Autowired
    ATMRepository atmRepository;

    //Kirim-chiqimlar ro’yxati (Mijozlar tomonidan yechilgan va cardga
    // solingan pullar. Bunda bankomat bo’yicha ko’riladi);
    public ApiResponse getAtmHistory(Integer id) {
        Optional<ATM> byId = atmRepository.findById(id);
        if (!byId.isPresent()) return new ApiResponse("ATM not found", false);
        ATM atm = byId.get();
        List<ATMHistory> allByAtmId = atmHistoryRepository.findAllByAtmId(atm.getId());
        return new ApiResponse("ATM buyicha kirim chiqim", true, allByAtmId);
    }

    //Kunlik kirim miqdori (cardga solingan pullar. Bunda bankomat bo’yicha ko’riladi);
    public ApiResponse getDailyIncome(Integer id) {
        Optional<ATM> byId = atmRepository.findById(id);
        if (!byId.isPresent()) return new ApiResponse("ATM not found", false);
        ATM atm = byId.get();
        LocalDate localDate = LocalDate.now();
        List<ATMHistory> allByAtmIdAndDateStartsWith = atmHistoryRepository.findAllByAtmIdAndDateAndOperationType(id, localDate, PULKIRITAWAL);
        return new ApiResponse("ATM buyicha kunlik kirim", true, allByAtmIdAndDateStartsWith);
    }

    //    Kunlik chiqim miqdori (Mijozlar tomonidan yechilgan. Bunda bankomat bo’yicha ko’riladi) ;
    public ApiResponse getDailyOutgoings(Integer id) {
        Optional<ATM> byId = atmRepository.findById(id);
        if (!byId.isPresent()) return new ApiResponse("ATM not found", false);
        ATM atm = byId.get();
        LocalDate localDate = LocalDate.now();
        List<ATMHistory> allByAtmIdAndDateStartsWith = atmHistoryRepository.findAllByAtmIdAndDateAndOperationType(id, localDate, WITHDRAWAL);
        return new ApiResponse("ATM buyicha kunlik chiqim", true, allByAtmIdAndDateStartsWith);
    }

}
