package com.microservices.cards.controller;

import com.microservices.cards.dto.CardsDto;
import com.microservices.cards.dto.ResponseDto;
import com.microservices.cards.service.ICardsService;
import jakarta.validation.constraints.Pattern;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/api", produces = {MediaType.APPLICATION_JSON_VALUE})
@Validated
public class CardsController {

    @Autowired
    private ICardsService cardsService;
    @PostMapping("/create")
    public ResponseEntity<ResponseDto> createCards(@RequestParam String mobileNumber) {
        cardsService.createCard(mobileNumber);

        return ResponseEntity.status(HttpStatus.CREATED).body(new ResponseDto("201", "Cards created successfully"));
    }

    @GetMapping("/fetch")
    public ResponseEntity<CardsDto> fetchCards(@RequestParam
                                                   @Pattern(regexp = "[0-9]{10}", message = "Phone number should have at least 10 digits")
            String mobileNumber) {
        CardsDto cardsDto = cardsService.fetchCard(mobileNumber);
        return ResponseEntity.status(HttpStatus.OK).body(cardsDto);
    }

    @PutMapping("/update")
    public ResponseEntity<ResponseDto> updateCards(@RequestBody CardsDto cardsDto) {
        cardsService.updateCard(cardsDto);
        return ResponseEntity.status(HttpStatus.OK).body(new ResponseDto("201", "Cards details updated successfully"));
    }
}
