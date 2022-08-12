package bankamat.uz.bankamat.component;

import bankamat.uz.bankamat.repository.BankRepository;
import bankamat.uz.bankamat.repository.CardRepository;
import bankamat.uz.bankamat.repository.RoleRepository;
import bankamat.uz.bankamat.repository.UserRepository;
import bankamat.uz.bankamat.entity.Bank;
import bankamat.uz.bankamat.entity.Card;
import bankamat.uz.bankamat.entity.Role;
import bankamat.uz.bankamat.entity.User;
import bankamat.uz.bankamat.entity.enums.CardType;
import bankamat.uz.bankamat.entity.enums.RoleName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Component
public class DataLoader implements CommandLineRunner {

    @Value("${spring.datasource.initialization-mode}")
    private String initialMode;


    @Autowired
    RoleRepository roleRepository;

    @Autowired
    UserRepository userRepository;

//    @Autowired
//    PasswordEncoder passwordEncoder;

    @Autowired
    BankRepository bankRepository;

    @Autowired
    CardRepository cardRepository;

    @Override
    public void run(String... args) throws Exception {
        if (initialMode.equals("always")) {


            Role director = roleRepository.save(
                    new Role(1, RoleName.DIRECTOR));

            Role manager = roleRepository.save(
                    new Role(2, RoleName.MANAGER));

            Role user = roleRepository.save(
                    new Role(3, RoleName.USER));


            Bank universalBank = bankRepository.save(new Bank(1, "UniversalBank"));

            List<Card> cardList = new ArrayList<>();
            cardList.add(new Card(1, "8600", "111", universalBank, "TJU", "1234",
                    Date.valueOf("2022-08-25"), CardType.HUMO, false, false, 0));
            cardList.add(new Card(2, "8601", "111", universalBank, "AAA", "1111",
                    Date.valueOf("2022-08-25"), CardType.UZCARD, false, false, 0));
            cardList.add(new Card(3, "8602", "111", universalBank, "JJJ", "1111",
                    Date.valueOf("2022-08-25"), CardType.HUMO, false, false, 0));

            cardRepository.saveAll(cardList);
            userRepository.save(new User(1, "ZZZ", director));
            userRepository.save(new User(2, "QQQ", manager));
        }
    }
}
