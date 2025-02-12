package com.rentalcar.pojos;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Payment {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @OneToOne
    @JoinColumn(name = "booking_id", nullable = false)
    private Booking booking;
    
    private double amount;
    
    private LocalDate paymentDate;
   
    @Enumerated(EnumType.STRING)
    private PaymentMethod paymentMethod;  // Corrected to PaymentMethod enum
    
    public enum PaymentMethod {
        CREDIT_CARD, DEBIT_CARD, PAYPAL, CASH;
    }
}
