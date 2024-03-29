package com.springbootrabbitmqexample.model;

import java.io.Serializable;

public class Person implements Serializable {

	public Person(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    private Long id;

    public Person() {
    }

    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    private String name;

}
