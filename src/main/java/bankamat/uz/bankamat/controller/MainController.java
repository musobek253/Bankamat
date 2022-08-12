package bankamat.uz.bankamat.controller;

import bankamat.uz.bankamat.Service.MainService;
import bankamat.uz.bankamat.payload.ATMWithdrawalDTO;
import bankamat.uz.bankamat.payload.PulKiritDto;
import bankamat.uz.bankamat.payload.types.ApiResponse;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/main")
public class MainController {

    @Autowired
    MainService mainService;

   @SneakyThrows
   @PostMapping("/withdrawal")
   public HttpEntity<?> withdrawal(@RequestBody ATMWithdrawalDTO atmWithdrawalDTO){
       ApiResponse withdrawal = mainService.withdrawal(atmWithdrawalDTO);
       return ResponseEntity.status(withdrawal.isSuccess()?202:409).body(withdrawal);

   }

    @PostMapping("/cardniToldirish")
    public HttpEntity<?> cardAddToClient(@RequestBody PulKiritDto pulKiritDto) {
        ApiResponse apiResponse = mainService.cardniToldirish(pulKiritDto);
        return ResponseEntity.status(apiResponse.isSuccess()?202:409).body(apiResponse);
    }


}
