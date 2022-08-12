package bankamat.uz.bankamat.Service;

import bankamat.uz.bankamat.repository.ATMManagerHistoryRepository;
import bankamat.uz.bankamat.repository.ATMRepository;
import bankamat.uz.bankamat.repository.BankRepository;
import bankamat.uz.bankamat.repository.UserRepository;
import bankamat.uz.bankamat.entity.ATM;
import bankamat.uz.bankamat.entity.ATMManagerOperationsHistory;
import bankamat.uz.bankamat.entity.Bank;
import bankamat.uz.bankamat.entity.enums.ATMOperationType;
import bankamat.uz.bankamat.entity.enums.USDBankNoteType;
import bankamat.uz.bankamat.entity.enums.UZSBankNoteType;
import bankamat.uz.bankamat.payload.types.ApiResponse;
import bankamat.uz.bankamat.payload.AtmAddDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import java.util.*;

@Service
public class ATMservice {
    @Autowired
    ATMRepository atmRepository;
    @Autowired
    BankRepository bankRepository;
    @Autowired
    ATMManagerHistoryRepository historyRepository;
    @Autowired
    UserRepository userRepository;

    public List<ATM> getList(){
        return atmRepository.findAll();
    }


//    Bankomatda mavjud bo’lgan kupyuralar ro’yxati va miqdori
//    (Bunda bankomat bo’yicha ko’riladi).
    public ApiResponse getBanknotes(Integer id) {
        Optional<ATM> byId = atmRepository.findById(id);
        if (!byId.isPresent()) return new ApiResponse("ATM not found", false);
        ATM atm = byId.get();
        List<Object> banknotes = new ArrayList<>();
        banknotes.add(atm.getBanknoteCountUSD());
        banknotes.add(atm.getBanknoteCountUZS());
        return new ApiResponse("Banknotes", true, banknotes);
    }

    public ApiResponse addAtm(@Valid AtmAddDTO atmDTO) {

        Optional<Bank> byId = bankRepository.findById(atmDTO.getBankID());
        if (!byId.isPresent()) {
            return new ApiResponse("bunday bank yoq", false);
        }

        ATM atm = new ATM();
        atm.setBank(byId.get());
        atm.setAddress(atmDTO.getAddress());
        atm.setAlertAmount(atmDTO.getAlertAmount());
        atm.setMaxWithdrawal(atmDTO.getMaxWithdrawal());
        atm.setCardTypes(atmDTO.getCardTypes());
        atm.setComission(atmDTO.getComission());
        ATMManagerOperationsHistory operationsHistory = new ATMManagerOperationsHistory();

        operationsHistory.setUser(atmDTO.getUser());
        double balance = 0;
        switch (atm.getCardTypes()) {
            case HUMO:
            case UZCARD:
                Map<UZSBankNoteType, Integer> banknoteCountUZS = atmDTO.getBanknoteCountUZS();
                for (Map.Entry<UZSBankNoteType, Integer> uzsB : banknoteCountUZS.entrySet()) {
                    balance += uzsB.getValue() * uzsB.getKey().getValue();
                }
                atm.setBanknoteCountUZS(atmDTO.getBanknoteCountUZS());
                operationsHistory.setBanknoteCountUZS(atmDTO.getBanknoteCountUZS());
                break;
            case VISA:
                Map<USDBankNoteType, Integer> banknoteCountUSD = atmDTO.getBanknoteCountUSD();
                for (Map.Entry<USDBankNoteType, Integer> uzsB : banknoteCountUSD.entrySet()) {
                    balance += uzsB.getValue() * uzsB.getKey().getValue();
                }
                atm.setBanknoteCountUSD(atmDTO.getBanknoteCountUSD());
                operationsHistory.setBanknoteCountUSD(atmDTO.getBanknoteCountUSD());
                break;
            default:
                return new ApiResponse("EURO bn iwlamaymiz", false);
        }

        atm.setBalance(balance);
        operationsHistory.setOperationAmount(balance);
        if (!userRepository.findById(atmDTO.getUser().getId()).isPresent()) {
            return new ApiResponse("begona odam !!", false);
        }
        operationsHistory.setUser(atmDTO.getUser());
        operationsHistory.setDate(new Date(System.currentTimeMillis()));
        operationsHistory.setOperationType(ATMOperationType.MANAGERTOLDIRAWAL);
        ATM save = atmRepository.save(atm);
        operationsHistory.setAtm(save);
        historyRepository.save(operationsHistory);

        return new ApiResponse("added", true);
    }

    public ApiResponse editAtm(@Valid AtmAddDTO atmDTO, Integer id) {
        Optional<ATM> atmID = atmRepository.findById(id);
        if (!atmID.isPresent()) {
            return new ApiResponse("This id not found", false);
        }

        Optional<Bank> byId = bankRepository.findById(atmDTO.getBankID());
        if (!byId.isPresent()) {
            return new ApiResponse("bunday bank yoq", false);
        }

        ATM atm = atmID.get();
        atm.setBank(byId.get());
        atm.setAddress(atmDTO.getAddress());
        atm.setAlertAmount(atmDTO.getAlertAmount());
        atm.setMaxWithdrawal(atmDTO.getMaxWithdrawal());
        atm.setCardTypes(atmDTO.getCardTypes());

        ATMManagerOperationsHistory operationsHistory = new ATMManagerOperationsHistory();

        operationsHistory.setUser(atmDTO.getUser());
        double balance = 0;
        switch (atm.getCardTypes()) {
            case HUMO:
            case UZCARD:
                Map<UZSBankNoteType, Integer> banknoteCountUZS = atmDTO.getBanknoteCountUZS();
                for (Map.Entry<UZSBankNoteType, Integer> uzsB : banknoteCountUZS.entrySet()) {
                    balance += uzsB.getValue() * uzsB.getKey().getValue();
                }
                atm.setBanknoteCountUZS(atmDTO.getBanknoteCountUZS());
                operationsHistory.setBanknoteCountUZS(atmDTO.getBanknoteCountUZS());
                break;
            case VISA:
                Map<USDBankNoteType, Integer> banknoteCountUSD = atmDTO.getBanknoteCountUSD();
                for (Map.Entry<USDBankNoteType, Integer> uzsB : banknoteCountUSD.entrySet()) {
                    balance += uzsB.getValue() * uzsB.getKey().getValue();
                }
                atm.setBanknoteCountUSD(atmDTO.getBanknoteCountUSD());
                operationsHistory.setBanknoteCountUSD(atmDTO.getBanknoteCountUSD());
                break;
            default:
                return new ApiResponse("EURO bn iwlamaymiz", false);
        }

        atm.setBalance(balance);
        operationsHistory.setOperationAmount(balance);
        if (!userRepository.findById(atmDTO.getUser().getId()).isPresent()) {
            return new ApiResponse("begona odam !!", false);
        }
        operationsHistory.setUser(atmDTO.getUser());
        operationsHistory.setDate(new Date(System.currentTimeMillis()));
        operationsHistory.setOperationType(ATMOperationType.MANAGERTOLDIRAWAL);
        ATM save = atmRepository.save(atm);
        operationsHistory.setAtm(save);
        historyRepository.save(operationsHistory);

        return new ApiResponse("edited success", true);
    }

    //delete ATM
    public ApiResponse delete(Integer id) {
        Optional<ATM> byId = atmRepository.findById(id);
        if (!byId.isPresent()) return  new ApiResponse("This id not found",false);
        atmRepository.deleteById(id);
        return new ApiResponse("success", true);
    }


}
