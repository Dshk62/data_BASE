package com.example.basedata;

import javafx.beans.property.*;

public class File {
    private final IntegerProperty id;
    private final StringProperty name;
    private final IntegerProperty age;
    private final DoubleProperty salary;

    public File(int id, String name, int age, double salary) {
        this.id = new SimpleIntegerProperty(id);
        this.name = new SimpleStringProperty(name);
        this.age = new SimpleIntegerProperty(age);
        this.salary = new SimpleDoubleProperty(salary);
    }

    public int getId() {
        return id.get();
    }

    public IntegerProperty idProperty() {
        return id;
    }

    public String getName() {
        return name.get();
    }

    public StringProperty nameProperty() {
        return name;
    }

    public int getAge() {
        return age.get();
    }

    public IntegerProperty ageProperty() {
        return age;
    }

    public double getSalary() {
        return salary.get();
    }

    public DoubleProperty salaryProperty() {
        return salary;
    }
}
