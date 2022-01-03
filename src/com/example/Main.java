package com.example;

import java.util.*;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) {
        // write your code here
        List<Person> people = getPeople();

        //imperative

        //filter
    //    List<Person> females = new ArrayList<>();
    //
    //    for (Person person: people){
    //        if (person.getGender().equals(Gender.FEMALE)){
    //            females.add(person);
    //        }
    //    }
    //
    //    females.forEach(System.out::println);

        //declarative

        //filter
            List<Person> females = people.stream()
                    .filter(person -> person.getGender().equals(Gender.FEMALE))
                    .collect(Collectors.toList());

            //females.forEach(System.out::println);

        //sort
        List<Person> sorted = people.stream()
                .sorted(Comparator.comparing(Person::getAge).reversed().thenComparing(Person::getGender))
                .collect(Collectors.toList());

//        sorted.forEach(System.out::println);

        //all match
        boolean allMatch = people.stream()
                .allMatch(person -> person.getAge() > 20);

//        System.out.println("allMatch = " + allMatch);

        //any match
        boolean anyMatch = people.stream()
                .anyMatch(person -> person.getAge() > 120);

//        System.out.println("anyMatch = " + anyMatch);

        //none match

        //max
        people.stream()
                .min(Comparator.comparing(Person::getAge));
//                .ifPresent(System.out::println);

        //min
        people.stream()
                .max(Comparator.comparing(Person::getAge))
                .map(Person::getName);
//                .ifPresent(System.out::println);

        //group
        Map<Gender, List<Person>> groupByGender = people.stream()
                .collect(Collectors.groupingBy(Person::getGender));

        groupByGender.forEach((gender, people1) -> {
            System.out.println(gender);
            people1.forEach(System.out::println);
            System.out.println();
        });

        Optional<String> oldestFemale = people.stream()
                .filter(person -> person.getGender().equals(Gender.FEMALE))
                .max(Comparator.comparing(Person::getAge))
                .map(Person::getName);

        oldestFemale.ifPresent(System.out::println);

    }

    private static List<Person> getPeople(){
        return List.of(
                new Person("Tobi", 20, Gender.MALE),
                new Person("Ade", 33, Gender.MALE),
                new Person("Laide", 19, Gender.FEMALE),
                new Person("Steve", 120, Gender.MALE),
                new Person("Jeffery", 45, Gender.MALE),
                new Person("Glory", 67, Gender.FEMALE),
                new Person("Yemisi", 54, Gender.FEMALE)
        );
    }
}
