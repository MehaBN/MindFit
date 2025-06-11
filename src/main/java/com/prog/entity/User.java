package com.prog.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;

@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // Auto-generated ID
    
    private String username;
    private String email;
    private String password; // Make sure this is hashed before storing in the DB
    private int age;
    private String bio;
    private String gender;
    private double height; // Height in cm or meters as per your requirement
    private double weight; // Weight in kg
    private String role; // New field for role (USER or ADMIN)

    // Default constructor (JPA requirement)
    public User() {
    }

    // Constructor to easily create User instances
    public User(String username, String email, String password, int age, String bio, String gender, double height, double weight, String role) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.age = age;
        this.bio = bio;
        this.gender = gender;
        this.height = height;
        this.weight = weight;
        this.role = role;
    }

    // Getters and setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }
    
    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    // Override toString() for better debugging
    @Override
    public String toString() {
        return "User [id=" + id + ", username=" + username + ", email=" + email + 
               ", age=" + age + ", bio=" + bio + ", gender=" + gender + 
               ", height=" + height + ", weight=" + weight + "]";
    }
}
