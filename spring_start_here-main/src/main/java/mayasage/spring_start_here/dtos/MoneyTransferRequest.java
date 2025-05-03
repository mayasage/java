package mayasage.spring_start_here.dtos;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class MoneyTransferRequest {
        private int senderAccountId;
        private int receiverAccountId;
        private BigDecimal transferAmount;
}
