package com.tomcat.Loans.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor @NoArgsConstructor
@Entity
@Schema(
        name = "Loans",
        description = "schema to hold loans related information"
)
public class Loans {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long loanId;

    @Schema(
            description = "customer mobile number",
            example = "91234567891"
    )
    private String mobileNumber;

    @Schema(
            description = "type of loan taken from bank",
            example = "personal loan"
    )
    private String loanType;

    @Schema(
            description = "total loan amount disbursed to customer",
            example = "200000"
    )
    private int totalLoan;

    @Schema(
            description = "loan amount paid by customer",
            example = "10000"
    )
    private String amountPaid;

    @Schema(
            description = "active loan outstanding amount",
            example = "190000"
    )
    private int outstandingAmount;
}
