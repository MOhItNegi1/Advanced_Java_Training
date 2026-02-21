package org.example.Entity;

import jakarta.persistence.*;


    @Entity(name="IDCard")
    public class IDCard {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private int id;
        private String cardNumber;

        @OneToOne(mappedBy = "idCard")
        private Student student;

        public IDCard() {
        }
        public IDCard(int id, String cardNumber, Student student) {
            this.id = id;
            this.cardNumber = cardNumber;
            this.student = student;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getCardNumber() {
            return cardNumber;
        }

        public void setCardNumber(String cardNumber) {
            this.cardNumber = cardNumber;
        }

        public Student getStudent() {
            return student;
        }

        public void setStudent(Student student) {
            this.student = student;
        }
    }
