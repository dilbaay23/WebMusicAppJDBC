package entity;



import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

/**
 * Created by Moon on 23/04/2021
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Customer {
    private Long id;
    private String firstName;
    private String lastName;
    private LocalDate birthDate;

    //TODO: make this unique.
    // used as username
    private String phoneNumber;

    private String address;
    private String city;
    private String country;
    private String password;

    //registration
    public Customer(String firstName, String lastName, LocalDate birthDate,String phoneNumber, String address, String city, String country,String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDate = birthDate;
        this.phoneNumber= phoneNumber;
        this.address = address;
        this.city = city;
        this.country = country;
        this.password = password;
    }

    //get Customers without passwords
    public Customer(Long id, String firstName, String lastName, LocalDate birthDate,String phoneNumber, String address, String city, String country) {
        this.id =id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDate = birthDate;
        this.phoneNumber= phoneNumber;
        this.address = address;
        this.city = city;
        this.country = country;

    }


}
