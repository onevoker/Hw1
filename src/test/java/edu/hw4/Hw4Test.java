package edu.hw4;

import edu.hw4.Errors.AgeError;
import edu.hw4.Errors.HeightError;
import edu.hw4.Errors.ValidationError;
import edu.hw4.Errors.WeightError;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

public class Hw4Test {
    private final Animal catMisa = new Animal("Misa", Animal.Type.CAT, Animal.Sex.F, 1, 16, 4, true);
    private final Animal catMasik = new Animal("Masik", Animal.Type.CAT, Animal.Sex.M, 11, 21, 7, false);
    private final Animal catMorty = new Animal("Morty", Animal.Type.CAT, Animal.Sex.F, 3, 18, 5, true);
    private final Animal dogDolly = new Animal("Dolly", Animal.Type.DOG, Animal.Sex.F, 4, 44, 26, true);
    private final Animal dogNaida = new Animal("Naida", Animal.Type.DOG, Animal.Sex.F, 13, 33, 15, false);
    private final Animal birdKesha = new Animal("Kesha", Animal.Type.BIRD, Animal.Sex.M, 2, 17, 1, false);
    private final Animal fishDory = new Animal("Dory", Animal.Type.FISH, Animal.Sex.F, 5, 20, 2, false);
    // spider from Harry Potter
    private final Animal spiderAragog = new Animal("Aragog", Animal.Type.SPIDER, Animal.Sex.M, 16, 300, 40, true);
    private List<Animal> animals = List.of(
        catMisa,
        catMasik,
        catMorty,
        dogDolly,
        dogNaida,
        birdKesha,
        fishDory,
        spiderAragog
    );

    @Nested
    class Task1Test {
        @Test
        void testGetAnimalsSortedByHeight() {
            List<Animal> result = Hw4.getAnimalsSortedByHeight(animals);

            assertThat(result).isEqualTo(List.of(
                catMisa,
                birdKesha,
                catMorty,
                fishDory,
                catMasik,
                dogNaida,
                dogDolly,
                spiderAragog
            ));
        }
    }

    @Nested
    class Task2Test {
        @Test
        void testGetTopByWeightAnimals() {
            List<Animal> resultOfOneAnimals = Hw4.getTopByWeightAnimals(animals, 1);
            List<Animal> resultOf8Animals = Hw4.getTopByWeightAnimals(animals, 8);
            List<Animal> resultOf12Animals = Hw4.getTopByWeightAnimals(animals, 12);
            List<Animal> resultOfZeroAnimals = Hw4.getTopByWeightAnimals(animals, 0);

            assertAll(
                () -> assertThat(resultOfOneAnimals).isEqualTo(List.of(spiderAragog)),
                () -> assertThat(resultOf8Animals).isEqualTo(List.of(
                        spiderAragog,
                        dogDolly,
                        dogNaida,
                        catMasik,
                        catMorty,
                        catMisa,
                        fishDory,
                        birdKesha
                    )
                ),
                () -> assertThat(resultOf12Animals).isEqualTo(resultOf8Animals),
                () -> assertThat(resultOfZeroAnimals).isEqualTo(List.of())
            );
        }
    }

    @Nested
    class Task3Test {
        @Test
        void testGetAnimalsByType() {
            Map<Animal.Type, Integer> result = Hw4.getAnimalsByType(animals);
            Map<Animal.Type, Integer> expected = Map.of(
                Animal.Type.SPIDER, 1,
                Animal.Type.CAT, 3,
                Animal.Type.DOG, 2,
                Animal.Type.BIRD, 1,
                Animal.Type.FISH, 1
            );

            assertThat(result).isEqualTo(expected);
        }
    }

    @Nested
    class Task4Test {
        @Test
        void getTheLongestName() {
            Animal longestNameAnimal = Hw4.getTheLongestName(animals);
            List<Animal> emptyListOfAnimals = List.of();
            Animal noAnimal = Hw4.getTheLongestName(emptyListOfAnimals);

            assertAll(
                () -> assertThat(noAnimal).isNull(),
                () -> assertThat(longestNameAnimal).isEqualTo(spiderAragog)
            );
        }
    }

    @Nested
    class Task5Test {
        @Test
        void TestWhichMoreMOrF() {
            Animal.Sex result = Hw4.whichMoreMOrF(animals);

            assertThat(result).isEqualTo(Animal.Sex.F);
        }
    }

    @Nested
    class Task6Test {
        @Test
        void testGetAnimalsByWeight() {
            Map<Animal.Type, Animal> result = Hw4.getAnimalsByWeight(animals);
            Map<Animal.Type, Animal> expected = Map.of(
                Animal.Type.SPIDER, spiderAragog,
                Animal.Type.BIRD, birdKesha,
                Animal.Type.CAT, catMasik,
                Animal.Type.DOG, dogDolly,
                Animal.Type.FISH, fishDory
            );

            assertThat(result).isEqualTo(expected);
        }
    }

    @Nested
    class Task7Test {
        @Test
        void testGetKTheOldestAnimal() {
            Animal theOldestAnimal = Hw4.getKTheOldestAnimal(animals, 1);
            Animal theMediumAgedAnimal = Hw4.getKTheOldestAnimal(animals, 4);
            Animal theYoungestAnimal = Hw4.getKTheOldestAnimal(animals, 8);

            assertAll(
                () -> assertThat(theOldestAnimal).isEqualTo(spiderAragog),
                () -> assertThat(theMediumAgedAnimal).isEqualTo(fishDory),
                () -> assertThat(theYoungestAnimal).isEqualTo(catMisa)
            );
        }
    }

    @Nested
    class Task8Test {
        @Test
        void testGetTheHeaviestAnimalWhichHeightLessThenKK() {
            Optional<Animal> animal1 = Hw4.getTheHeaviestAnimalWhichHeightLessThenK(animals, 5);
            Optional<Animal> animal2 = Hw4.getTheHeaviestAnimalWhichHeightLessThenK(animals, 301);
            Optional<Animal> animal3 = Hw4.getTheHeaviestAnimalWhichHeightLessThenK(animals, 50);

            assertAll(
                () -> assertThat(animal1).isEmpty(),
                () -> assertThat(animal2.orElse(null)).isEqualTo(spiderAragog),
                () -> assertThat(animal3.orElse(null)).isEqualTo(dogDolly)
            );
        }
    }

    @Nested
    class Task9Test {
        @Test
        void testGetSumOfPaws() {
            Integer result = Hw4.getSumOfPaws(animals);
            Integer expected = 30;

            assertThat(result).isEqualTo(expected);
        }
    }

    @Nested
    class Task10Test {
        @Test
        void testGetAnimalsWhichAgeNotEqualToPaws() {
            List<Animal> result = Hw4.getAnimalsWhichAgeNotEqualToPaws(animals);
            List<Animal> expected = List.of(
                catMisa,
                catMasik,
                catMorty,
                dogNaida,
                fishDory,
                spiderAragog
            );

            assertThat(result).isEqualTo(expected);
        }
    }

    @Nested
    class Task11Test {
        @Test
        void testGetAnimalsWhichCanBitesWithHeightMoreThenMeter() {
            Animal fishKarasik = new Animal("Mutant", Animal.Type.FISH, Animal.Sex.M, 7, 150, 10, true);
            List<Animal> newAnimals = new ArrayList<>(animals);
            newAnimals.add(fishKarasik);

            List<Animal> result = Hw4.getAnimalsWhichCanBitesWithHeightMoreThenMeter(newAnimals);
            List<Animal> expected = List.of(spiderAragog, fishKarasik);
            List<Animal> result1 = Hw4.getAnimalsWhichCanBitesWithHeightMoreThenMeter(animals);
            List<Animal> expected1 = List.of(spiderAragog);

            assertAll(
                () -> assertThat(result).isEqualTo(expected),
                () -> assertThat(result1).isEqualTo(expected1)
            );
        }
    }

    @Nested
    class Task12Test {
        @Test
        void testCountAnimalsWhichWeightMoreThenHeight() {
            Animal catPushok = new Animal("Pushok", Animal.Type.CAT, Animal.Sex.M, 7, 21, 23, false);
            List<Animal> newAnimals = new ArrayList<>(animals);
            newAnimals.add(catPushok);

            Integer result = Hw4.countAnimalsWhichWeightMoreThenHeight(newAnimals);
            Integer result1 = Hw4.countAnimalsWhichWeightMoreThenHeight(animals);

            assertAll(
                () -> assertThat(result).isEqualTo(1),
                () -> assertThat(result1).isEqualTo(0)
            );
        }
    }

    @Nested
    class Task13Test {
        @Test
        void testGetAnimalsWhichNameHaveMoreThenTwoWords() {
            Animal birdVasiliy =
                new Animal("Ivanov Vasiliy Viktorovich", Animal.Type.BIRD, Animal.Sex.M, 2, 20, 1, false);
            List<Animal> newAnimals = new ArrayList<>(animals);
            newAnimals.add(birdVasiliy);

            List<Animal> result = Hw4.getAnimalsWhichNameHaveMoreThenTwoWords(animals);
            List<Animal> result1 = Hw4.getAnimalsWhichNameHaveMoreThenTwoWords(newAnimals);

            assertAll(
                () -> assertThat(result).isEmpty(),
                () -> assertThat(result1).isEqualTo(List.of(birdVasiliy))
            );
        }
    }

    @Nested
    class Task14Test {
        @Test
        void testIsDogWhichHeightMoreThenKInList() {
            Boolean result = Hw4.isDogWhichHeightMoreThenKInList(animals, 40);
            Boolean result1 = Hw4.isDogWhichHeightMoreThenKInList(animals, 50);

            assertAll(
                () -> assertThat(result).isTrue(),
                () -> assertThat(result1).isFalse()
            );
        }
    }

    @Nested
    class Task15Test {
        @Test
        void testGetSumWeightOfTypeInAgeRange() {
            Map<Animal.Type, Integer> result = Hw4.getSumWeightOfTypeInAgeRange(animals, 1, 10);
            Map<Animal.Type, Integer> result1 = Hw4.getSumWeightOfTypeInAgeRange(animals, 1, 20);
            Map<Animal.Type, Integer> result2 = Hw4.getSumWeightOfTypeInAgeRange(animals, 1, 4);

            Map<Animal.Type, Integer> expected = Map.of(
                Animal.Type.DOG, 26,
                Animal.Type.CAT, 9,
                Animal.Type.FISH, 2,
                Animal.Type.BIRD, 1
            );
            Map<Animal.Type, Integer> expected1 = Map.of(
                Animal.Type.DOG, 41,
                Animal.Type.CAT, 16,
                Animal.Type.SPIDER, 40,
                Animal.Type.FISH, 2,
                Animal.Type.BIRD, 1
            );
            Map<Animal.Type, Integer> expected2 = Map.of(
                Animal.Type.DOG, 26,
                Animal.Type.CAT, 9,
                Animal.Type.BIRD, 1
            );

            assertAll(
                () -> assertThat(result).isEqualTo(expected),
                () -> assertThat(result1).isEqualTo(expected1),
                () -> assertThat(result2).isEqualTo(expected2)
            );
        }
    }

    @Nested
    class Task16Test {
        @Test
        void testGetAnimalsSortedByTypeSexName() {
            List<Animal> result = Hw4.getAnimalsSortedByTypeSexName(animals);

            assertThat(result).isEqualTo(List.of(
                    catMasik,
                    catMisa,
                    catMorty,
                    dogDolly,
                    dogNaida,
                    birdKesha,
                    fishDory,
                    spiderAragog
                )
            );
        }
    }

    @Nested
    class Task17Test {
        @Test
        void testIsSpiderBitesMoreOftenThenDogs() {
            Animal spiderPavuk =
                new Animal("Pavuk", Animal.Type.SPIDER, Animal.Sex.F, 1, 10, 1, true);
            Animal dogSobaka =
                new Animal("Sobaka", Animal.Type.DOG, Animal.Sex.M, 2, 30, 20, true);

            List<Animal> newAnimals = new ArrayList<>(animals);
            newAnimals.add(spiderPavuk);

            List<Animal> newAnimals1 = new ArrayList<>(animals);
            newAnimals1.add(dogSobaka);

            Boolean result = Hw4.isSpiderBitesMoreOftenThenDogs(animals);
            Boolean result1 = Hw4.isSpiderBitesMoreOftenThenDogs(newAnimals);
            Boolean result2 = Hw4.isSpiderBitesMoreOftenThenDogs(newAnimals1);

            assertAll(
                () -> assertThat(result).isFalse(),
                () -> assertThat(result1).isTrue(),
                () -> assertThat(result2).isFalse()
            );
        }
    }

    @Nested
    class Task18Test {
        @Test
        void testGetTheHeaviestFish() {
            List<List<Animal>> animalsLists = List.of(
                animals,
                List.of(
                    new Animal("Nemo", Animal.Type.FISH, Animal.Sex.M, 3, 40, 1, false),
                    new Animal("Bobik", Animal.Type.DOG, Animal.Sex.M, 2, 30, 18, true)
                ),
                List.of(
                    new Animal("Krutaya Riba", Animal.Type.FISH, Animal.Sex.F, 5, 150, 31, true),
                    new Animal("Guppy", Animal.Type.FISH, Animal.Sex.F, 1, 15, 1, false)
                ),
                List.of(
                    birdKesha,
                    fishDory
                )
            );

            List<List<Animal>> noFishList = List.of(
                List.of(birdKesha, catMasik, catMorty),
                List.of(dogDolly),
                List.of(catMisa)
            );

            Animal theHeaviestFish = Hw4.getTheHeaviestFish(animalsLists);
            Animal noFish = Hw4.getTheHeaviestFish(noFishList);

            assertAll(
                () -> assertThat(theHeaviestFish).isEqualTo(new Animal(
                        "Krutaya Riba",
                        Animal.Type.FISH,
                        Animal.Sex.F,
                        5,
                        150,
                        31,
                        true
                    )
                ),
                () -> assertThat(noFish).isNull()
            );
        }
    }

    @Nested
    class Task19Test {
        @Test
        void firstTestGetValidationErrors() {
            Map<String, Set<ValidationError>> result = Hw4.getValidationErrors(animals);
            Map<String, Set<ValidationError>> expected = Map.of(
                catMisa.name(), Set.of(),
                catMasik.name(), Set.of(),
                catMorty.name(), Set.of(),
                dogDolly.name(), Set.of(),
                dogNaida.name(), Set.of(),
                birdKesha.name(), Set.of(),
                fishDory.name(), Set.of(),
                spiderAragog.name(), Set.of()
            );

            assertThat(result).usingRecursiveComparison().ignoringCollectionOrder().isEqualTo(expected);
        }

        @Test
        void secondTestGetValidationErrors() {
            Animal catVasya = new Animal("Vasya", Animal.Type.CAT, Animal.Sex.F, -1, 0, 0, true);

            List<Animal> newAnimals = List.of(catVasya);

            Map<String, Set<ValidationError>> result = Hw4.getValidationErrors(newAnimals);
            Map<String, Set<ValidationError>> expected = Map.of(
                catVasya.name(), Set.of(
                    new AgeError("Incorrect age"),
                    new HeightError("Incorrect height"),
                    new WeightError("Incorrect weight")
                )
            );

            assertThat(result).usingRecursiveComparison().ignoringCollectionOrder().isEqualTo(expected);
        }

        @Test
        void thirdTestGetValidationErrors() {
            Animal dogDan = new Animal("Dan", Animal.Type.DOG, Animal.Sex.M, 200, 44, 101, true);
            Animal fishSun = new Animal("Sun", Animal.Type.FISH, Animal.Sex.F, 10, 20, 102, false);
            Animal spiderGena = new Animal("Gena", Animal.Type.SPIDER, Animal.Sex.M, 4, 0, 14, true);

            List<Animal> newAnimals = List.of(dogDan, fishSun, spiderGena);

            Map<String, Set<ValidationError>> result = Hw4.getValidationErrors(newAnimals);
            Map<String, Set<ValidationError>> expected = Map.of(
                dogDan.name(), Set.of(
                    new AgeError("Incorrect age"),
                    new WeightError("Incorrect weight")
                ),
                fishSun.name(), Set.of(
                    new WeightError("Incorrect weight")
                ),
                spiderGena.name(), Set.of(
                    new HeightError("Incorrect height")
                )
            );

            assertThat(result).usingRecursiveComparison().ignoringCollectionOrder().isEqualTo(expected);
        }
    }

    @Nested
    class Task20Test {
        @Test
        void firstTestGetBeautifulValidationErrors() {
            Map<String, String> result = Hw4.getBeautifulValidationErrors(animals);
            Map<String, String> expected = Map.of(
                catMisa.name(), "",
                catMasik.name(), "",
                catMorty.name(), "",
                dogDolly.name(), "",
                dogNaida.name(), "",
                birdKesha.name(), "",
                fishDory.name(), "",
                spiderAragog.name(), ""
            );

            assertThat(result).isEqualTo(expected);
        }

        @Test
        void secondTestGetBeautifulValidationErrors() {
            Animal catVasiliy = new Animal("Vasiliy", Animal.Type.CAT, Animal.Sex.F, -1, 0, 0, true);

            List<Animal> newAnimals = List.of(catVasiliy);

            Map<String, String> result = Hw4.getBeautifulValidationErrors(newAnimals);
            Map<String, String> expected = Map.of(
                catVasiliy.name(), "Incorrect weight, ".concat("Incorrect height, ").concat("Incorrect age")
            );

            assertThat(result).usingRecursiveComparison().ignoringCollectionOrder().isEqualTo(expected);

        }

        @Test
        void thirdTestGetBeautifulValidationErrors() {
            Animal dogDan = new Animal("Dan", Animal.Type.DOG, Animal.Sex.M, 200, 44, 101, true);
            Animal fishSun = new Animal("Sun", Animal.Type.FISH, Animal.Sex.F, 10, 20, 102, false);
            Animal spiderGena = new Animal("Gena", Animal.Type.SPIDER, Animal.Sex.M, 4, 0, 14, true);

            List<Animal> newAnimals = List.of(dogDan, fishSun, spiderGena);

            Map<String, String> result = Hw4.getBeautifulValidationErrors(newAnimals);
            Map<String, String> expected = Map.of(
                dogDan.name(), "Incorrect weight, ".concat("Incorrect age"),
                fishSun.name(), "Incorrect weight",
                spiderGena.name(), "Incorrect height"
            );

            assertThat(result).usingRecursiveComparison().ignoringCollectionOrder().isEqualTo(expected);
        }
    }
}
