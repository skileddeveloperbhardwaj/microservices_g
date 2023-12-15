package com.microservices.cards.service.impl;

import com.microservices.cards.dto.CardsDto;
import com.microservices.cards.entity.Cards;
import com.microservices.cards.exception.CardsAlreadyExistedException;
import com.microservices.cards.mapper.CardsMapper;
import com.microservices.cards.repository.CardsRepository;
import com.microservices.cards.service.ICardsService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Random;

@Service
@AllArgsConstructor
public class CardsServiceImpl implements ICardsService {
    private CardsRepository repository;
    @Override
    public void createCard(String mobileNumber) {
        Optional<Cards> cards = repository.findByMobileNumber(mobileNumber);
        if (cards.isPresent()) {
            //throw exception
            throw new CardsAlreadyExistedException("Cards already existing. Try with a new mobile numner");
        }

        repository.save(createNewCard(mobileNumber));

    }

    private Cards createNewCard(String mobileNumber) {
        Cards cards = new Cards();
        long randomCardNumber = 100000000000L + new Random().nextInt(900000000);
        cards.setCardNumber(Long.toString(randomCardNumber));
        cards.setMobileNumber(mobileNumber);
        cards.setAvailableAmount(1000000L);
        cards.setCardType("Credit");
        cards.setTotalLimit(5000000L);
        cards.setAmountUsed(0L);

        return cards;
    }

    @Override
    public CardsDto fetchCard(String mobileNumber) {
        Cards cards = repository.findByMobileNumber(mobileNumber).orElseThrow(
                () -> new RuntimeException("Cards not found")
        );

        return CardsMapper.mapToCardsDto(cards, new CardsDto());
    }

    @Override
    public void updateCard(CardsDto cardsDto) {

    }

    @Override
    public void deleteCard(String mobileNumber) {

    }
}
