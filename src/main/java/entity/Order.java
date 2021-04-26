package entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * Created by Moon on 24/04/2021
 */
@Data
@NoArgsConstructor
public class Order {
    private Long id;
    private Long customerId;
    private LocalDateTime date;
    private String status;

    public Order(Long id, Long customerId, LocalDateTime date, String status) {
        this.id = id;
        this.customerId = customerId;
        //this.date = date;
        this.status = status;
    }

    public Order(Long customerId) {
        this.customerId = customerId;
        this.status = "PROCESSING";
    }
}
