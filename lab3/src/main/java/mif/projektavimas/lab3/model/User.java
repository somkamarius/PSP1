package mif.projektavimas.lab3.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class User {
    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private String lastName;
    private String phoneNumber;
    private String emailAddress;
    private String homeAddress;
    private String password;

    public User(String name, String lastName, String phoneNumber, String emailAddress, String homeAddress, String password) {
        this.name = name;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.emailAddress = emailAddress;
        this.homeAddress = homeAddress;
        this.password = password;
    }
}
