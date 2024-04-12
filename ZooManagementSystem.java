package zoomanagementsystem;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

abstract class Animal {
    String name;
    int age;
    double weight;
    Habitat habitat;

    public Animal(String name, int age, double weight) {
        this.name = name;
        this.age = age;
        this.weight = weight;
    }

    abstract void makeSound();
    abstract void eat();
    abstract void sleep();
}

interface Flyable {
    void fly();
}

interface Swimmable {
    void swim();
}

class Mammal extends Animal {
    public Mammal(String name, int age, double weight) {
        super(name, age, weight);
    }

    @Override
    void makeSound() {
        System.out.println(name + " is making a sound");
    }

    @Override
    void eat() {
        System.out.println(name + " is eating");
    }

    @Override
    void sleep() {
        System.out.println(name + " is sleeping");
    }
}

class Bird extends Animal implements Flyable {
    public Bird(String name, int age, double weight) {
        super(name, age, weight);
    }

    @Override
    void makeSound() {
        System.out.println(name + " tweets");
    }

    @Override
    void eat() {
        System.out.println(name + " is eating");
    }

    @Override
    void sleep() {
        System.out.println(name + " is sleeping");
    }

    @Override
    public void fly() {
        System.out.println(name + " is flying");
    }
}

class Fish extends Animal implements Swimmable {
    public Fish(String name, int age, double weight) {
        super(name, age, weight);
    }

    @Override
    void makeSound() {
        System.out.println(name + " makes a sound");
    }

    @Override
    void eat() {
        System.out.println(name + " is eating");
    }

    @Override
    void sleep() {
        System.out.println(name + " is sleeping");
    }

    @Override
    public void swim() {
        System.out.println(name + " is swimming");
    }
}

class Reptile extends Animal {
    public Reptile(String name, int age, double weight) {
        super(name, age, weight);
    }

    @Override
    void makeSound() {
        System.out.println(name + " makes a sound");
    }

    @Override
    void eat() {
        System.out.println(name + " is eating");
    }

    @Override
    void sleep() {
        System.out.println(name + " is sleeping");
    }
}

class Primate extends Mammal {
    public Primate(String name, int age, double weight) {
        super(name, age, weight);
    }
}

class Ape extends Primate implements Swimmable {
    public Ape(String name, int age, double weight) {
        super(name, age, weight);
    }

    @Override
    public void swim() {
        System.out.println(name + " is swimming");
    }
}

class Monkey extends Primate implements Swimmable, Flyable {
    public Monkey(String name, int age, double weight) {
        super(name, age, weight);
    }

    @Override
    public void swim() {
        System.out.println(name + " is swimming");
    }

    @Override
    public void fly() {
        System.out.println(name + " is flying");
    }
}

interface Climber {
    void climb();
}

class ClimbingMonkey extends Monkey implements Climber {
    public ClimbingMonkey(String name, int age, double weight) {
        super(name, age, weight);
    }

    @Override
    public void climb() {
        System.out.println(name + " is climbing");
    }
}

class Habitat {
    String name;
    List<Animal> animalsInHabitat;
    private static List<Habitat> allHabitats = new ArrayList<>();

    public Habitat(String name) {
        this.name = name;
        this.animalsInHabitat = new ArrayList<>();
        allHabitats.add(this);
    }

    public void addAnimal(Animal animal) {
        animalsInHabitat.add(animal);
        animal.habitat = this;
    }

    public static List<Habitat> getAllHabitats() {
        return allHabitats;
    }

    public List<Animal> getAnimalsInHabitat() {
        return animalsInHabitat;
    }
}


public class ZooManagementSystem {
private List<Animal> animals = new ArrayList<>();
private List<Habitat> habitats = new ArrayList<>();
   
    public static void main(String[] args) {
      
         ZooManagementSystem zooManager = new ZooManagementSystem();
        zooManager.startZooManager();
    }

        
        
        
      public void simulateZoo() {
        for (Animal animal : animals) {
            System.out.println("Simulating " + animal.name + " in habitat: " + animal.habitat.name);
            animal.makeSound();
            animal.eat();
            animal.sleep();
            if (animal instanceof Flyable) {
                ((Flyable) animal).fly();
            }
            if (animal instanceof Swimmable) {
                ((Swimmable) animal).swim();
            }
            if (animal instanceof Climber) {
                ((Climber) animal).climb();
            }
            System.out.println();
        }
    }

    public void displayZoo() {
        if (animals.isEmpty()) {
            System.out.println("The zoo is empty.");
        } else {
            System.out.println("Here are the Zoo Animals:");
            for (Animal animal : animals) {
                System.out.println("Name: " + animal.name + ", Age: " + animal.age + ", Weight: " + animal.weight + ", Habitat: " + animal.habitat.name);
            }
        }
    }

    public void displayHabitats() {
        if (Habitat.getAllHabitats().isEmpty()) {
            System.out.println("There are no habitats in the zoo.");
        } else {
            System.out.println("Here are the Habitats:");
            for (Habitat habitat : Habitat.getAllHabitats()) {
                System.out.println("- " + habitat.name);
            }
        }
    }

    public void displayAnimalsInHabitat() {
        if (Habitat.getAllHabitats().isEmpty()) {
            System.out.println("There are no habitats in the zoo.");
        } else {
            System.out.println("Here are the Animals in each Habitat:");
            for (Habitat habitat : Habitat.getAllHabitats()) {
                System.out.println("Habitat: " + habitat.name);
                List<Animal> animalsInHabitat = habitat.getAnimalsInHabitat();
                if (animalsInHabitat.isEmpty()) {
                    System.out.println("No animals in this habitat.");
                } else {
                    for (Animal animal : animalsInHabitat) {
                        System.out.println("- " + animal.name + " ("+ getAnimalType(animal)+ ")");
                    }
                }
                System.out.println();
            }
        }
    }

    private String getAnimalType(Animal animal) {
    if (animal instanceof Mammal) {
        return "Mammal";
    } else if (animal instanceof Bird) {
        return "Bird";
    } else if (animal instanceof Fish) {
        return "Fish";
    } else if (animal instanceof Reptile) {
        return "Reptile";
    } else if (animal instanceof Monkey || animal instanceof ClimbingMonkey) {
        return "Monkey";
    } else {
        return "Unknown";
    }
}

    public void startZooManager() {
        Scanner scanner = new Scanner(System.in);
        int choice;
        do {
            System.out.println("\nZoo Management System Menu:");
            System.out.println("1. View all animals in the zoo");
            System.out.println("2. Add animal to the zoo");
            System.out.println("3. Simulate zoo");
            System.out.println("4. View all habitats");
            System.out.println("5. View animals in each habitat");
            System.out.println("6. Exit");
            System.out.println("Enter your choice: ");
            choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    displayZoo();
                    break;
                case 2:
                    addAnimal(scanner);
                    break;
                case 3:
                    simulateZoo();
                    break;
                case 4:
                    displayHabitats();
                    break;
                case 5:
                    displayAnimalsInHabitat();
                    break;
                case 6:
                    System.out.println("Exiting Zoo Management System. Goodbye!");
                    break;
                default:
                    System.out.println("Invalid choice. Please enter a valid option.");
            }
        } while (choice != 6);
        scanner.close();
    }

    public void addAnimal(Scanner scanner) {
    System.out.println("Enter animal type:");
    System.out.println("1. Mammal");
    System.out.println("2. Bird");
    System.out.println("3. Fish");
    System.out.println("4. Reptile");
    System.out.println("5. Monkey");
    System.out.print("Choice: ");
    int animalType = scanner.nextInt();
    
    String name, habitatName;
    int age;
    double weight;
    Animal animal;
    switch (animalType) {
        case 1:
            System.out.println("Enter mammal type:");
            System.out.println("1. Primate");
            System.out.println("2. Ape");
            System.out.print("Choice: ");
            int mammalType = scanner.nextInt();
            System.out.println("Enter animal name:");
            name = scanner.next();
            System.out.println("Enter animal age:");
            age = scanner.nextInt();
            System.out.println("Enter animal weight:");
            weight = scanner.nextDouble();
            switch (mammalType) {
                case 1:
                    animal = new Primate(name, age, weight);
                    break;
                case 2:
                    animal = new Ape(name, age, weight);
                    break;
                default:
                    System.out.println("Invalid mammal type. Defaulting to Primate.");
                    animal = new Primate(name, age, weight);
            }
            break;
        case 2:
            System.out.println("Enter animal name:");
            name = scanner.next();
            System.out.println("Enter animal age:");
            age = scanner.nextInt();
            System.out.println("Enter animal weight:");
            weight = scanner.nextDouble();
            animal = new Bird(name, age, weight);
            break;
        case 3:
            System.out.println("Enter animal name:");
            name = scanner.next();
            System.out.println("Enter animal age:");
            age = scanner.nextInt();
            System.out.println("Enter animal weight:");
            weight = scanner.nextDouble();
            animal = new Fish(name, age, weight);
            break;
        case 4:
            System.out.println("Enter animal name:");
            name = scanner.next();
            System.out.println("Enter animal age:");
            age = scanner.nextInt();
            System.out.println("Enter animal weight:");
            weight = scanner.nextDouble();
            animal = new Reptile(name, age, weight);
            break;
        case 5:
            System.out.println("Enter monkey type:");
            System.out.println("1. Monkey");
            System.out.println("2. Climbing Monkey");
            System.out.print("Choice: ");
            int monkeyType = scanner.nextInt();
            System.out.println("Enter animal name:");
            name = scanner.next();
            System.out.println("Enter animal age:");
            age = scanner.nextInt();
            System.out.println("Enter animal weight:");
            weight = scanner.nextDouble();
            switch (monkeyType) {
                case 1:
                    animal = new Monkey(name, age, weight);
                    break;
                case 2:
                    animal = new ClimbingMonkey(name, age, weight);
                    break;
                default:
                    System.out.println("Invalid monkey type. Defaulting to Monkey.");
                    animal = new Monkey(name, age, weight);
            }
            break;
        default:
            System.out.println("Invalid animal type.");
            return;
    }

    animals.add(animal);

    System.out.println("Enter habitat name to add this animal to:");
    habitatName = scanner.next();
    Habitat habitat = findOrCreateHabitat(habitatName);
    habitat.addAnimal(animal);

    // Display the selected habitat
    System.out.println("Animal added to habitat: " + habitat.name);
}

    private Animal createMammal(Scanner scanner, String name, int age, double weight) {
        System.out.println("Enter mammal type (1: Primate, 2: Ape): ");
        int mammalType = scanner.nextInt();
        switch (mammalType) {
            case 1:
                return new Primate(name, age, weight);
            case 2:
                return new Ape(name, age, weight);
            default:
                System.out.println("Invalid mammal type. Defaulting to Primate.");
                return new Primate(name, age, weight);
        }
    }

    private Animal createMonkey(Scanner scanner, String name, int age, double weight) {
        System.out.println("Enter monkey type (1: Monkey, 2: ClimbingMonkey): ");
        int monkeyType = scanner.nextInt();
        switch (monkeyType) {
            case 1:
                return new Monkey(name, age, weight);
            case 2:
                return new ClimbingMonkey(name, age, weight);
            default:
                System.out.println("Invalid monkey type. Defaulting to Monkey.");
                return new Monkey(name, age, weight);
        }
    }

    public Habitat findOrCreateHabitat(String habitatName) {
        for (Habitat habitat : habitats) {
            if (habitat.name.equalsIgnoreCase(habitatName)) {
                return habitat;
            }
        }
        Habitat newHabitat = new Habitat(habitatName);
        habitats.add(newHabitat);
        return newHabitat;
    }
}