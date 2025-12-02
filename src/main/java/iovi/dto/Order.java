package iovi.dto;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class Order {

    private Long id;

    private Long userId;

    private String productName;

    private Integer quantity;
}
