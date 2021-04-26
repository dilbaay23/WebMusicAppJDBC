package entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by Moon on 23/04/2021
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Song {
    private Long id;
    private String title;
    private String artist;
    private String genre;
    private int year;
    private double cost;

}
