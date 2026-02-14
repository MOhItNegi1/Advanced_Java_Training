package org.company.EntityOneToOne;

import jakarta.persistence.*;

@Entity
@Table(name = "passport")
public class Passport {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "passport_seq")
    @SequenceGenerator(
            name = "passport_seq",
            sequenceName = "passport_id_sequence",
            initialValue = 100,
            allocationSize = 1
    )
    private int passportId;

    private int passportNum;
    private String country;

    // Inverse Side
    @OneToOne(mappedBy = "passport")
    private Person person;

    public Passport(){}

    public Passport(int passportNum, String country){
        this.passportNum = passportNum;
        this.country = country;
    }

    // Getters & Setters
    public int getPassportId(){
        return passportId;
    }

    public int getPassportNum() {
        return passportNum;
    }

    public void setPassportNum(int passportNum){
        this.passportNum = passportNum;
    }

    public String getCountry(){
        return country;
    }

    public void setCountry(String country){
        this.country = country;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    @Override
    public String toString(){
        return "PassportId: " + passportId +
                " PassportNumber: " + passportNum +
                " Country: " + country;
    }
}
