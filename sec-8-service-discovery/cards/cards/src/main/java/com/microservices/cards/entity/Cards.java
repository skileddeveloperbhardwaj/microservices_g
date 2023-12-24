package com.microservices.cards.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.*;

@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Cards extends BaseEntity {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO,generator="native")
    private Long id;

    @NotNull
    @Pattern(regexp = "[0-9]{10}", message = "Phone number must contain at least 10 digits")
    private String mobileNumber;

    @NotNull
    private String cardNumber;

    @NotNull
    private String cardType;

    @NotNull
    private Long totalLimit;

    @NotNull
    private Long availableAmount;

    @NotNull
    private Long amountUsed;

}
