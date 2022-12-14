package bankamat.uz.bankamat.controller;

import bankamat.uz.bankamat.Service.BankService;
import bankamat.uz.bankamat.entity.Bank;
import bankamat.uz.bankamat.payload.types.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/bank")
public class BankController {

    @Autowired
    BankService bankService;

    @GetMapping
    public HttpEntity<?> getAll(){
        List<Bank> list = bankService.getList();
        return ResponseEntity.ok(list);
    }

    @GetMapping("/{id}")
    public HttpEntity<?> getById(@PathVariable Integer id ){
        ApiResponse id1 = bankService.getId(id);
        return ResponseEntity.status(id1.isSuccess()?202:409).body(id1);
    }

    @PostMapping
    public HttpEntity<?> cardAddToClient(@RequestBody Bank bank) {
        ApiResponse apiResponse = bankService.addBank(bank);
        return ResponseEntity.status(apiResponse.isSuccess()?202:409).body(apiResponse);
    }

    @PutMapping("/{id}")
    public HttpEntity<?> edit(@PathVariable Integer id,@RequestBody Bank bank){
        ApiResponse apiResponse = bankService.editBank(id, bank);
        return ResponseEntity.status(apiResponse.isSuccess()?202:409).body(apiResponse);
    }

    @DeleteMapping("/{id}")
    public HttpEntity<?> delete(@PathVariable Integer id){
        ApiResponse delete = bankService.delete(id);
        return ResponseEntity.status(delete.isSuccess()?202:409).body(delete);
    }

}
