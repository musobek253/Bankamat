package bankamat.uz.bankamat.payload;

import bankamat.uz.bankamat.entity.User;
import bankamat.uz.bankamat.entity.enums.CardType;
import bankamat.uz.bankamat.entity.enums.USDBankNoteType;
import bankamat.uz.bankamat.entity.enums.UZSBankNoteType;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.Map;

@Data
public class AtmAddDTO {
    @NotNull
    private CardType cardTypes;

    private Map<UZSBankNoteType, Integer> banknoteCountUZS; //null

    private Map<USDBankNoteType, Integer> banknoteCountUSD;

    @NotNull
    private double maxWithdrawal;

    @NotNull
    private double alertAmount;

    @NotNull
    private String address;

    @NotNull
    private Integer bankID;
    private int comission;
    private User user;
}
