package edu.hw4;

import edu.hw4.Errors.ValidationError;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

public class Hw4 {
    private static final int CENTIMETRES_IN_METRE = 100;

    private Hw4() {
    }

    // Task1
    public static List<Animal> getAnimalsSortedByHeight(List<Animal> animals) {
        return animals.stream()
            .sorted(Comparator.comparingInt(Animal::height))
            .collect(Collectors.toList());
    }

    // Task2
    public static List<Animal> getTopByWeightAnimals(List<Animal> animals, int k) {
        return animals.stream()
            .sorted((animal1, animal2) -> (animal2.weight() - animal1.weight()))
            .limit(k)
            .collect(Collectors.toList());
    }

    // Task3
    public static Map<Animal.Type, Integer> getAnimalsByType(List<Animal> animals) {
        return animals.stream()
            .collect(Collectors.groupingBy(Animal::type, Collectors.summingInt(count -> 1)));
    }

    // Task4
    public static Animal getTheLongestName(List<Animal> animals) {
        return animals.stream()
            .reduce((animal1, animal2) -> animal1.name().length() >= animal2.name().length() ? animal1 : animal2)
            .orElse(null);
    }

    // Task5
    public static Animal.Sex whichMoreMOrF(List<Animal> animals) {
        double countMale = animals.stream()
            .filter(animal -> animal.sex() == Animal.Sex.M)
            .count();

        return countMale >= (double) animals.size() / 2 ? Animal.Sex.M : Animal.Sex.F;
    }

    // Task6
    public static Map<Animal.Type, Animal> getAnimalsByWeight(List<Animal> animals) {
        return animals.stream()
            .collect(
                Collectors.toMap(Animal::type, animal -> animal,
                    (animal1, animal2) -> animal1.weight() >= animal2.weight() ? animal1 : animal2
                )
            );
    }

    // Task7
    public static Animal getKTheOldestAnimal(List<Animal> animals, int k) {
        return animals.stream()
            .sorted((animal1, animal2) -> animal2.age() - animal1.age())
            .skip(k - 1)
            .findFirst()
            .orElse(null);
    }

    // Task8
    public static Optional<Animal> getTheHeaviestAnimalWhichHeightLessThenK(List<Animal> animals, int k) {
        return animals.stream()
            .filter(animal -> animal.height() < k)
            .reduce((animal1, animal2) -> animal1.height() >= animal2.height() ? animal1 : animal2);
    }

    // Task9
    public static Integer getSumOfPaws(List<Animal> animals) {
        return Math.toIntExact(animals.stream()
            .mapToInt(Animal::paws)
            .sum()
        );
    }

    // Task10
    public static List<Animal> getAnimalsWhichAgeNotEqualToPaws(List<Animal> animals) {
        return animals.stream()
            .filter(animal -> animal.age() != animal.paws())
            .collect(Collectors.toList());
    }

    // Task11
    public static List<Animal> getAnimalsWhichCanBitesWithHeightMoreThenMeter(List<Animal> animals) {
        return animals.stream()
            .filter(animal -> animal.bites() && animal.height() > CENTIMETRES_IN_METRE)
            .collect(Collectors.toList());
    }

    // Task12
    public static Integer countAnimalsWhichWeightMoreThenHeight(List<Animal> animals) {
        return Math.toIntExact(animals.stream()
            .filter(animal -> animal.weight() > animal.height())
            .count());
    }

    // Task13
    public static List<Animal> getAnimalsWhichNameHaveMoreThenTwoWords(List<Animal> animals) {
        return animals.stream()
            .filter(animal -> animal.name().split(" ").length > 2)
            .collect(Collectors.toList());
    }

    //Task14
    public static Boolean isDogWhichHeightMoreThenKInList(List<Animal> animals, int k) {
        return animals.stream()
            .anyMatch(animal -> animal.type() == Animal.Type.DOG && animal.height() > k);
    }

    // Task15
    public static Map<Animal.Type, Integer> getSumWeightOfTypeInAgeRange(List<Animal> animals, int k, int l) {
        return animals.stream()
            .filter(animal -> animal.age() >= k && animal.age() <= l)
            .collect(Collectors.groupingBy(Animal::type, Collectors.summingInt(Animal::weight)));
    }

    // Task16
    public static List<Animal> getAnimalsSortedByTypeSexName(List<Animal> animals) {
        return animals.stream()
            .sorted(Comparator.comparing(Animal::type)
                .thenComparing(Animal::sex)
                .thenComparing(Animal::name))
            .collect(Collectors.toList());
    }

    // Task17
    public static Boolean isSpiderBitesMoreOftenThenDogs(List<Animal> animals) {
        long countSpidersWhichBite = animals.stream()
            .filter(animal -> animal.type() == Animal.Type.SPIDER && animal.bites())
            .count();

        long countDogsWhichBite = animals.stream()
            .filter(animal -> animal.type() == Animal.Type.DOG && animal.bites())
            .count();

        return countSpidersWhichBite > countDogsWhichBite;
    }

    // Task18
    public static Animal getTheHeaviestFish(List<List<Animal>> animalsList) {
        return animalsList.stream()
            .flatMap(List::stream)
            .filter(animal -> animal.type() == Animal.Type.FISH)
            .reduce((animal1, animal2) -> animal1.weight() >= animal2.weight() ? animal1 : animal2)
            .orElse(null);
    }

    // Task19
    public static Map<String, Set<ValidationError>> getValidationErrors(List<Animal> animals) {
        return animals.stream()
            .collect(Collectors.toMap(
                    Animal::name,
                    ValidationError::getListOfValidationError
                )
            );
    }

    // Task20
    public static Map<String, String> getBeautifulValidationErrors(List<Animal> animals) {
        Map<String, Set<ValidationError>> errors = getValidationErrors(animals);

        return errors.entrySet().stream()
            .collect(Collectors.toMap(
                    Map.Entry::getKey,
                    map -> map.getValue().stream()
                        .map(ValidationError::getMessage)
                        .collect(Collectors.joining(", "))
                )
            );
    }
}
