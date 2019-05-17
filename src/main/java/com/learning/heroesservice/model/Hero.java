package com.learning.heroesservice.model;


import javax.persistence.*;

@Entity
@Table(name = "hero")
public class Hero implements Comparable<Hero> {

    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private Integer id;

    private String name;

    public Hero() {
    }

    //TODO: Remove constructor
    public Hero(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public int compareTo(Hero o) {
        return this.getId().compareTo(o.getId());
    }
}
