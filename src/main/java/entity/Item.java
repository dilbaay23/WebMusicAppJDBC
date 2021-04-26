package entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by Moon on 25/04/2021
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Item {
    private Long  id;
    private Long  orderId;
    private Long  songId;
    private int amount;

    public Item(Long orderId, Long songId, int amount) {
        this.orderId = orderId;
        this.songId = songId;
        this.amount = amount;
    }
}
