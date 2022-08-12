package bankamat.uz.bankamat.Service;

import bankamat.uz.bankamat.repository.CardRepository;
import bankamat.uz.bankamat.entity.Card;
import bankamat.uz.bankamat.payload.CardDto;
import bankamat.uz.bankamat.payload.types.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CardService {
    @Autowired
    CardRepository cardRepository;

    public List<Card> getList() {
        return cardRepository.findAllByActiveIsFalse();
    }

    public ApiResponse getId(Integer id) {
        Optional<Card> byId = cardRepository.findById(id);
        return byId.map(card -> new ApiResponse("man ol senga padarka", true, card)).orElseGet(() -> new ApiResponse("this id not found", false));
    }

    public ApiResponse addCard(CardDto cardDto) {
        boolean byBankAndUserAndCardType = cardRepository.findByBankAndUserAndCardType(cardDto.getBank(),
                cardDto.getUser(), cardDto.getCardType());
        if (byBankAndUserAndCardType)
            return new ApiResponse("Biz sizga carta bundey turdagi card berolmiymiz", false);
        Optional<Card> byNumber = cardRepository.findByNumber(cardDto.getNumber());
        if (byNumber.isPresent())
            return new ApiResponse("Bunday nomerli card berilgan", false);
        Card card = new Card();
        card.setCardType(cardDto.getCardType());
        card.setBank(cardDto.getBank());
        card.setCvv(cardDto.getCvv());
        card.setExpireDate(cardDto.getExpireDate());
        card.setFullName(cardDto.getFullName());
        card.setActive(true);
        card.setBlocked(true);
        card.setNumber(cardDto.getNumber());
        card.setPinCode(cardDto.getPinCode());
        card.setUser(cardDto.getUser());
        cardRepository.save(card);
        return new ApiResponse("Added success", true, card);
    }

    public ApiResponse editCard(Integer id,CardDto cardDto) {
        Optional<Card> byId = cardRepository.findById(id);
        if (!byId.isPresent())
            return new ApiResponse("This id not found brat, qayting orqaga",false);
        boolean byBankAndUserAndCardType = cardRepository.findByBankAndUserAndCardType(cardDto.getBank(),
                cardDto.getUser(), cardDto.getCardType());
        if (byBankAndUserAndCardType)
            return new ApiResponse("Biz sizga carta bundey turdagi card berolmiymiz", false);
        Optional<Card> byNumber = cardRepository.findByNumber(cardDto.getNumber());
        if (byNumber.isPresent())
            return new ApiResponse("Bunday nomerli card berilgan", false);
        Card card = byId.get();
        card.setCardType(cardDto.getCardType());
        card.setBank(cardDto.getBank());
        card.setCvv(cardDto.getCvv());
        card.setExpireDate(cardDto.getExpireDate());
        card.setFullName(cardDto.getFullName());
        card.setActive(true);
        card.setBlocked(true);
        card.setNumber(cardDto.getNumber());
        card.setPinCode(cardDto.getPinCode());
        card.setUser(cardDto.getUser());
        cardRepository.save(card);
        return new ApiResponse("Edited success", true, card);
    }

    public ApiResponse delete(Integer id){
        Optional<Card> byId = cardRepository.findById(id);
        if (!byId.isPresent())
            return new ApiResponse("This id not found: kimmikini o'chirmoqchisiz e, qaramismi yaxshiro",false);
        cardRepository.deleteById(id);
        return new ApiResponse("Delete success",true);
    }
}
