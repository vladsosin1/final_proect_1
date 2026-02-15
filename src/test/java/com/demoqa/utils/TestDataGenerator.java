package com.demoqa.utils;

import java.util.Random;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class TestDataGenerator {

    private static final Random random = new Random();

    /**
     * Генерация случайного имени
     */
    public static String generateFirstName() {
        String[] names = {"Alex", "John", "Michael", "David", "James", "Robert",
                "Maria", "Anna", "Elena", "Olga", "Natasha", "Ivan"};
        return names[random.nextInt(names.length)];
    }

    /**
     * Генерация случайной фамилии
     */
    public static String generateLastName() {
        String[] lastNames = {"Smith", "Johnson", "Williams", "Brown", "Jones",
                "Garcia", "Miller", "Davis", "Rodriguez", "Martinez"};
        return lastNames[random.nextInt(lastNames.length)];
    }

    /**
     * Генерация email
     */
    public static String generateEmail() {
        return generateFirstName().toLowerCase() +
                generateLastName().toLowerCase() +
                random.nextInt(1000) + "@test.com";
    }

    /**
     * Генерация мобильного телефона (10 цифр)
     */
    public static String generateMobileNumber() {
        StringBuilder mobile = new StringBuilder();
        for (int i = 0; i < 10; i++) {
            mobile.append(random.nextInt(10));
        }
        return mobile.toString();
    }

    /**
     * Генерация случайного пола
     */
    public static String generateGender() {
        String[] genders = {"Male", "Female", "Other"};
        return genders[random.nextInt(genders.length)];
    }

    /**
     * Генерация адреса
     */
    public static String generateAddress() {
        String[] streets = {"Main St", "Broadway", "Park Ave", "Oak Street",
                "Pine Street", "Maple Avenue"};
        return random.nextInt(1000) + " " + streets[random.nextInt(streets.length)];
    }

    /**
     * Генерация даты рождения (формат: dd MMM yyyy)
     */
    public static String generateDateOfBirth() {
        LocalDate date = LocalDate.now()
                .minusYears(random.nextInt(50) + 18) // от 18 до 68 лет
                .minusMonths(random.nextInt(12))
                .minusDays(random.nextInt(28));
        return date.format(DateTimeFormatter.ofPattern("dd MMM yyyy"));
    }
}
