package edu.hw4;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

public class AnimalUtils {

    private AnimalUtils() {
    }

    public static Map<Animal.Type, Long> countAnimalsType(List<Animal> animals) {
        return animals.stream()
                .collect(Collectors.groupingBy(Animal::type, Collectors.counting()));
    }

    public static List<Animal> sortAnimalsByHeight(List<Animal> animals) {
        return animals.stream().sorted((Comparator.comparingInt(Animal::height))).toList();
    }

    public static List<Animal> sortAnimalsByWeight(List<Animal> animals, int k) {
        return animals.stream().sorted((animal1, animal2) -> animal2.weight() - animal1.weight()).limit(k).toList();
    }

    public static Animal getLongestName(List<Animal> animals) {
        return animals.stream().max(Comparator.comparingInt(animal -> animal.name().length())).orElse(null);
    }

    public static Animal.Sex getWhichSexMoreCommon(List<Animal> animals) {
        Map<Animal.Sex, Long> map = animals.stream()
                .collect(Collectors.groupingBy(Animal::sex, Collectors.counting()));
        return map.get(Animal.Sex.M) > map.get(Animal.Sex.F) ? Animal.Sex.M : Animal.Sex.F;
    }

    public static Map<Animal.Type, Animal> getHardestAnimalOfEachType(List<Animal> animals) {
        return animals.stream()
            .collect(Collectors.toMap(
                    Animal::type,
                    animal -> animal,
                    (hardestAnimal, curAnimal) ->
                            hardestAnimal.weight() > curAnimal.weight() ? hardestAnimal : curAnimal)
            );
    }

    public static Animal getKOldestAnimal(List<Animal> animals, int k) {
        return animals.stream()
                .sorted((animal1, animal2) -> animal2.age() - animal1.age()).limit(k).toList().get(k - 1);
    }

    public static Optional<Animal> getHardestAnimalAmongBelowKcm(List<Animal> animals, int k) {
        return animals.stream().filter(animal -> animal.height() < k).max(Comparator.comparingInt(Animal::weight));
    }

    public static Integer getSumPawsAnimals(List<Animal> animals) {
        return animals.stream().mapToInt(Animal::paws).sum();
    }

    public static List<Animal> getAnimalsAgeNotEqualsPaws(List<Animal> animals) {
        return animals.stream().filter(animal -> animal.age() != animal.paws()).toList();
    }

    public static List<Animal> getAnimalBitesAndHigher100cm(List<Animal> animals) {
        final int MIN_HEIGHT = 100;
        return animals.stream()
                .filter(animal -> (animal.bites() == null || animal.bites()) && animal.height() > MIN_HEIGHT)
                .toList();
    }

    public static Integer getCountAnimalsWeightExceedsHeight(List<Animal> animals) {
        return Math.toIntExact(
                animals.stream()
                        .filter(animal -> animal.weight() > animal.height())
                        .count()
        );
    }

    public static List<Animal> getAnimalsNameLongerTwoWords(List<Animal> animals) {
        return animals.stream()
                .filter(animal -> animal.name().split(" ").length > 2)
                .toList();
    }

    public static Boolean isDogHigherKcm(List<Animal> animals, int k) {
        return animals.stream()
                .filter(animal -> animal.type() == Animal.Type.DOG)
                .anyMatch(animal -> animal.height() > k);
    }

    public static Integer getSumWeightAnimalsHeightFromKcmToLcm(List<Animal> animals, int k, int l) {
        return animals.stream()
                .filter(animal -> animal.height() >= k && animal.height() <= l)
                .mapToInt(Animal::weight)
                .sum();
    }

    public static List<Animal> sortAnimalsByTypeSexName(List<Animal> animals) {
        return animals.stream()
                .sorted(Comparator.comparing(Animal::name))
                .sorted(Comparator.comparing(Animal::sex))
                .sorted(Comparator.comparing(Animal::type))


                .toList();
    }

    public static Boolean isSpiderBitesMoreOftenThanDog(List<Animal> animals) {
        Map<Animal.Type, Integer> map = animals.stream()
                .filter(animal -> animal.type() == Animal.Type.SPIDER || animal.type() == Animal.Type.DOG)
                .collect(Collectors.groupingBy(
                        Animal::type,
                        Collectors.summingInt(animal -> animal.bites() ? 1 : 0)
                ));
        return map.get(Animal.Type.SPIDER) > map.get(Animal.Type.DOG);
    }

    @SafeVarargs
    public static Animal getHardestFishAtLists(List<Animal>... animals) {
        return Arrays.stream(animals)
                .flatMap(List::stream)
                .filter(animal -> animal.type() == Animal.Type.FISH)
                .max(Comparator.comparingInt(Animal::weight))
                .orElse(null);
    }

    private static Set<ValidationError> validateAnimal(Animal animal) {
        List<ValidationError> validationErrors = new ArrayList<>(List.of(
                new ValidateAge(),
                new ValidateBites(),
                new ValidateHeight(),
                new ValidateName(),
                new ValidateWeight()
        ));
        return validationErrors.stream()
                .filter(validationError -> validationError.isValid(animal))
                .collect(Collectors.toSet());
    }

    private static Set<String> validateAnimalShowInvalidField(Animal animal) {
        List<ValidationError> validationErrors = new ArrayList<>(List.of(
                new ValidateAge(),
                new ValidateBites(),
                new ValidateHeight(),
                new ValidateName(),
                new ValidateWeight()
        ));
        return validationErrors.stream()
                .filter(validationError -> validationError.isValid(animal))
                .map(ValidationError::getValidationField)
                .collect(Collectors.toSet());
    }

    public static Map<String, Set<ValidationError>> validateAnimals(List<Animal> animals) {
        return animals.stream()
                .collect(Collectors.toMap(
                        Animal::name,
                        AnimalUtils::validateAnimal
                ));
    }

    public static Map<String, Set<String>> validateAnimalsShowInvalidField(List<Animal> animals) {
        // тут я немного не понял задание: написано вернуть имя и название невалидных полей,
        // но в задании просят вернуть Map<String, String>
        // решил, что это ошибка формулировки и возвращаю Map<String, Set<String>>.
        // Если я не прав, разъясните формулировку, пожалуйста
        return animals.stream()
                .collect(Collectors.toMap(
                        Animal::name,
                        AnimalUtils::validateAnimalShowInvalidField
                ));
    }
}
