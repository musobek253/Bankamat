package bankamat.uz.bankamat.entity;

import bankamat.uz.bankamat.entity.enums.ATMOperationType;
import bankamat.uz.bankamat.entity.enums.USDBankNoteType;
import bankamat.uz.bankamat.entity.enums.UZSBankNoteType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;
import java.util.Map;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ATMManagerOperationsHistory {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    private Date date;

    @ManyToOne
    private User user;

    @ManyToOne
    private ATM atm;

    @Enumerated(value = EnumType.STRING)
    private ATMOperationType operationType;

    @ElementCollection
    private Map<UZSBankNoteType, Integer> banknoteCountUZS; //null

    @ElementCollection
    private Map<USDBankNoteType, Integer> banknoteCountUSD;
    @Column
    private double operationAmount;

}
