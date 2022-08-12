package bankamat.uz.bankamat.payload;

import bankamat.uz.bankamat.entity.ATM;
import bankamat.uz.bankamat.entity.Card;
import lombok.Data;

@Data
public class ATMWithdrawalDTO {
 private Card card;
 private ATM atm;

 private double amountMoneyExit;
}

