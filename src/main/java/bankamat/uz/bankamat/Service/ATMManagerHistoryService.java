package bankamat.uz.bankamat.Service;

import bankamat.uz.bankamat.repository.ATMManagerHistoryRepository;
import bankamat.uz.bankamat.repository.ATMRepository;
import bankamat.uz.bankamat.entity.ATM;
import bankamat.uz.bankamat.payload.types.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ATMManagerHistoryService {
    @Autowired
    ATMManagerHistoryRepository atmManagerHistoryRepository;
    @Autowired
    ATMRepository atmRepository;

//    Bankomatga biriktirilgan mas’ul tomonidan to’ldirilganlik ro’yxati
//    (Bunda bankomat bo’yicha ko’riladi. ).
    public ApiResponse getHistory(Integer id){
        Optional<ATM> byId = atmRepository.findById(id);
        if (!byId.isPresent()) return new ApiResponse("ATM not found",false);
        ATM atm = byId.get();
        List<ATMManagerHistoryRepository> allByAtmId = atmManagerHistoryRepository.findAllByAtmId(atm.getId());
        return new ApiResponse("Filled list",true,allByAtmId);
    }
}
