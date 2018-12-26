package domain;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class AccountDto {
    private Long id ;
    private String owner;
    private Long credit;
}
