package bankamat.uz.bankamat.controller;

import bankamat.uz.bankamat.Service.ATMHistoryService;
import bankamat.uz.bankamat.Service.ATMManagerHistoryService;
import bankamat.uz.bankamat.Service.ATMservice;
import bankamat.uz.bankamat.payload.types.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/director")
public class ReportDirectorController {
    @Autowired
    ATMHistoryService atmHistoryService;
    @Autowired
    ATMManagerHistoryService atMmanagerHistoryService;
    @Autowired
    ATMservice ATMservice;

    @GetMapping("/{id}")
    public HttpEntity<?> getAtmHistory(@PathVariable Integer id){
        ApiResponse atmHistory = atmHistoryService.getAtmHistory(id);
        return ResponseEntity.status(atmHistory.isSuccess()? HttpStatus.OK:HttpStatus.CONFLICT).body(atmHistory);
    }
    @GetMapping("/dailyIncome/{id}")
    public HttpEntity<?> getAtmDailyIncome(@PathVariable Integer id) {
        ApiResponse atmHistory = atmHistoryService.getDailyIncome(id);
        return ResponseEntity.status(atmHistory.isSuccess()? HttpStatus.OK:HttpStatus.CONFLICT).body(atmHistory);
    }
    @GetMapping("/dailyOngoings/{id}")
    public HttpEntity<?> getAtmDailyOutgoings(@PathVariable Integer id){
        ApiResponse atmHistory = atmHistoryService.getDailyOutgoings(id);
        return ResponseEntity.status(atmHistory.isSuccess()? HttpStatus.OK:HttpStatus.CONFLICT).body(atmHistory);
    }
    @GetMapping("/filledAtm/{id}")
    public HttpEntity<?> getFilledAtm(@PathVariable Integer id){
        ApiResponse atmHistory = atMmanagerHistoryService.getHistory(id);
        return ResponseEntity.status(atmHistory.isSuccess()? HttpStatus.OK:HttpStatus.CONFLICT).body(atmHistory);
    }
    @GetMapping("/banknotes/{id}")
    public HttpEntity<?> getBanknotesByATMid(@PathVariable Integer id){
        ApiResponse atmHistory = ATMservice.getBanknotes(id);
        return ResponseEntity.status(atmHistory.isSuccess()? HttpStatus.OK:HttpStatus.CONFLICT).body(atmHistory);
    }

}
