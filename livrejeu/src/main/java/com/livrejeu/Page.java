package com.livrejeu;

import java.util.HashSet;
import java.util.Set;

import java.util.Set;
import java.util.Objects;

public class Page {
    private int id;
    private Enigme enigme;
    private Set<Objet> objets;

    public Page(int id, Enigme enigme, Set<Objet> objets) {
        this.id = id;
        this.enigme = enigme;
        this.objets = objets;
    }

    public int getId() {
        return this.id;
    }

    public Enigme getEnigme() {
        return this.enigme;
    }

    public Set<Objet> getObjets() {
        return this.objets;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Page page = (Page) o;
        return id == page.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Page{" + "id=" + id + '}';
    }
}