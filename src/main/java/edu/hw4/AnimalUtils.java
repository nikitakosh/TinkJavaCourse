package edu.hw4;

import java.util.*;
import java.util.stream.Collectors;

public class AnimalUtils {
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

    public static Animal getLongestName(List<Animal> animals){
        return animals.stream().max(Comparator.comparingInt(animal -> animal.name().length())).orElse(null);
    }

    public static Animal.Sex getWhichSexMoreCommon(List<Animal> animals) {
        Map<Animal.Sex, Long> map =  animals.stream()
                .collect(Collectors.groupingBy(Animal::sex, Collectors.counting()));
        return map.get(Animal.Sex.M) > map.get(Animal.Sex.F) ? Animal.Sex.M : Animal.Sex.F;
    }
    public static Map<Animal.Type, Animal> getHardestAnimalOfEachType(List<Animal> animals){
        return animals.stream()
                .collect(Collectors.toMap(
                        Animal::type,
                        animal -> animal,
                        (hardestAnimal, curAnimal) -> hardestAnimal.weight() > curAnimal.weight() ? hardestAnimal : curAnimal)
                );
    }
    public static Animal getKOldestAnimal(List<Animal> animals, int k){
        return animals.stream().sorted((animal1, animal2) -> animal2.age()-animal1.age()).limit(k).toList().get(k-1);
    }
    public static Optional<Animal> getHardestAnimalAmongBelowK(List<Animal> animals, int k) {
        return animals.stream().filter(animal -> animal.height() < k).max(Comparator.comparingInt(Animal::weight));
    }
    public static Integer getSumPawsAnimals(List<Animal> animals) {
        return animals.stream().mapToInt(Animal::paws).sum();
    }
    public static List<Animal> getAnimalsAgeNotEqualsPaws(List<Animal> animals) {
        return animals.stream().filter(animal -> animal.age() != animal.paws()).toList();
    }

    public static List<Animal> getAnimalBitesAndHigher100cm(List<Animal> animals) {
        return animals.stream()
                .filter(animal -> (animal.bites() == null || !animal.bites()) && animal.height() > 100)
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
    public static Boolean isAnimalHigherKcm(List<Animal> animals, int k) {
        return animals.stream()
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
                .sorted(Comparator.comparing(Animal::type))
                .sorted(Comparator.comparing(Animal::sex))
                .sorted(Comparator.comparing(Animal::name))
                .toList();
    }

    public static Boolean isSpiderBitesMoreOftenThanDog(List<Animal> animals) {
        Map<Animal.Type, Integer> map =  animals.stream()
                .filter(animal -> animal.type()== Animal.Type.SPIDER || animal.type() == Animal.Type.DOG)
                .collect(Collectors.groupingBy(
                   Animal::type,
                   Collectors.summingInt(animal -> animal.bites() ? 1 : 0)
                ));
        return map.get(Animal.Type.SPIDER) > map.get(Animal.Type.DOG);
    }
//    @SafeVarargs
//    public static Animal getHardestFishAtLists(List<Animal> ... animals) {
//    }
    public static void main(String[] args) {
        Animal animal1 = new Animal(
                "qq",
                Animal.Type.SPIDER,
                Animal.Sex.M,
                20,
                20,
                40,
                true
        );
        Animal animal2 = new Animal(
                "dddd",
                Animal.Type.DOG,
                Animal.Sex.M,
                20,
                20,
                30,
                true
        );
        Animal animal3 = new Animal(
                "ccccccccccccccccccc",
                Animal.Type.DOG,
                Animal.Sex.M,
                20,
                20,
                20,
                true
        );
        Animal animal4 = new Animal(
                "dddd",
                Animal.Type.BIRD,
                Animal.Sex.M,
                20,
                20,
                20,
                false
        );
        List<Animal> animals = new ArrayList<>(List.of(animal1, animal2, animal3, animal4));
        System.out.println(countAnimalsType(animals));
    }
}
