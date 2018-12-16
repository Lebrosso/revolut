package domain;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class TransactionDto {
    private Long id ;
    private String title ;
    private Long amount;
    private Long sourceAc ;
    private Long destinationAcc;
    private Long accountId;
}
