package bankamat.uz.bankamat.payload;

import bankamat.uz.bankamat.entity.Bank;
import bankamat.uz.bankamat.entity.User;
import bankamat.uz.bankamat.entity.enums.CardType;
import lombok.Data;

import java.sql.Date;

@Data
public class CardDto {
    private String number;
    private String pinCode;
    private String cvv;
    private Bank bank;
    private User user;
    private String fullName;
    private Date expireDate;
    private CardType cardType;
    private boolean active = false; // xodim kimgadr cardni bermagancha active bo'lmaydi
    private boolean blocked = false;
}
