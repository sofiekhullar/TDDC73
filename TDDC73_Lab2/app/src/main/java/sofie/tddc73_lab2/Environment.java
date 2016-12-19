package sofie.tddc73_lab2;

import java.util.ArrayList;

public class Environment {

    private String name;
    private ArrayList<Animal> animalList = new ArrayList<Animal>();

    public Environment(String name, ArrayList<Animal> animalList) {
        super();
        this.name = name;
        this.animalList = animalList;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public ArrayList<Animal> getAnimalList() {
        return animalList;
    }
}