class Animal {
    public int age;
    public String gender;

    public boolean isMammal() {
        //default implementation (can be overridden if needed)
        return this instanceof Zebra;
    }

    public void mate() {
        System.out.println("This animal is mating.");
    }
}

class Duck extends Animal {
    public String beakColor = "yellow";

    public void swim() {
        System.out.println("Duck is swimming");
    }

    public void quack() {
        System.out.println("Duck is quacking");
    }
}

class Fish extends Animal {
    private int sizeInFt;
    private boolean canEat;

    //constructor
    public Fish(int sizeInFt, boolean canEat) {
        this.sizeInFt=sizeInFt;
        this.canEat=canEat;
    }

    public void swim() {
        System.out.println("Fish is swimming.");
    }

    //getters
    public int getSizeInFt() {
        return sizeInFt;
    }

    public boolean canEat() {
        return canEat;
    }
}

class Zebra extends Animal {
    public boolean is_wild;

    //constructor
    public Zebra(boolean is_wild) {
        this.is_wild = is_wild;
    }

    public void run() {
        System.out.println("Zebra is running");
    }
}

public class UmlDiagram2 {
    public static void main(String[] args) {
        Duck duck = new Duck();
        duck.age = 2;
        duck.gender = "Female";
        System.out.println("Duck beak color: " + duck.beakColor);
        duck.quack();
        duck.swim();
        duck.mate();

        Fish fish = new Fish(3, true);
        fish.age = 1;
        fish.gender = "Male";
        System.out.println("Fish size: " + fish.getSizeInFt() + " ft");
        fish.swim();
        fish.mate();

        Zebra zebra = new Zebra(true);
        zebra.age = 5;
        zebra.gender = "Male";
        System.out.println("Is Zebra wild? " + zebra.is_wild);
        zebra.run();
        System.out.println("Is Zebra a mammal? " + zebra.isMammal());
    }
}
