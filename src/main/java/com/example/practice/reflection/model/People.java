package com.example.practice.reflection.model;

import java.util.Objects;

public class People {
    private String userId;
    private String name;

    public People(String userId, String name) {
        this.userId = userId;
        this.name = name;
    }

    public boolean equalsUser(People user) {
        return this.equals(user);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        People user = (People) o;
        return Objects.equals(userId, user.userId) && Objects.equals(name, user.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, name);
    }
}
