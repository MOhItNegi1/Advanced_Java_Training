package org.company.EntityOneToOne;


import jakarta.persistence.*;


@Entity
@Table(name="Person_Table")
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "Person_seq")
    @SequenceGenerator(name="person_seq",sequenceName = "person_sequence",initialValue = 100,allocationSize = 1)


    private int personID;
    private String name;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="passportId")
    private Passport passport;


    // Default Constructor
    public Person() {
    }

    // Parameterized Constructor
    public Person(String name, Passport passport) {
        this.name = name;
        this.passport = passport;
    }

    // Getter and Setter for personID
    public int getPersonID() {
        return personID;
    }

    public void setPersonID(int personID) {
        this.personID = personID;
    }

    // Getter and Setter for name
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    // Getter and Setter for passport
    public Passport getPassport() {
        return passport;
    }

    public void setPassport(Passport passport) {
        this.passport = passport;
    }

    @Override
    public String toString() {
        return "Person{" +
                "personID=" + personID +
                ", name='" + name + '\'' +
                ", passport=" + passport +
                '}';
    }
}






