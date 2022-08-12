package bankamat.uz.bankamat.payload;

import bankamat.uz.bankamat.entity.ATM;
import bankamat.uz.bankamat.entity.Card;
import bankamat.uz.bankamat.entity.enums.CardType;
import bankamat.uz.bankamat.entity.enums.USDBankNoteType;
import bankamat.uz.bankamat.entity.enums.UZSBankNoteType;
import lombok.Data;

import java.util.Map;

@Data
public class PulKiritDto {
    private Card card;
    private ATM atm;

    private Map<UZSBankNoteType, Integer> banknoteCountUZS;

    private Map<USDBankNoteType, Integer> banknoteCountUSD;
    private CardType cardType;
}

