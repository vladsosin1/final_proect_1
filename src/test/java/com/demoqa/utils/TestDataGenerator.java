package com.demoqa.utils;

import java.util.Random;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class TestDataGenerator {

    private static final Random random = new Random();

    // Константа с фиксированным email для всех тестов
    public static final String FIXED_EMAIL = "test1@mail.ru";

    /**
     * Генерация случайного имени
     */
    public static String generateFirstName() {
        String[] names = {
                "Александр", "Дмитрий", "Максим", "Сергей", "Андрей",
                "Алексей", "Артём", "Илья", "Кирилл", "Никита",
                "Елена", "Анна", "Мария", "Ольга", "Наталья",
                "Екатерина", "Татьяна", "Ирина", "Светлана", "Юлия"
        };
        return names[random.nextInt(names.length)];
    }

    /**
     * Генерация случайной фамилии
     */
    public static String generateLastName() {
        String[] lastNames = {
                "Иванов", "Смирнов", "Кузнецов", "Попов", "Васильев",
                "Петров", "Соколов", "Михайлов", "Новиков", "Федоров",
                "Морозов", "Волков", "Алексеев", "Лебедев", "Семенов",
                "Егорова", "Павлова", "Козлова", "Степанова", "Николаева"
        };
        return lastNames[random.nextInt(lastNames.length)];
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
        String[] genders = {"Male", "Female", "Other"}; // API сайта ожидает английские значения
        return genders[random.nextInt(genders.length)];
    }

    /**
     * Генерация адреса
     */
    public static String generateAddress() {
        String[] streets = {
                "ул. Ленина", "ул. Пушкина", "ул. Гагарина", "ул. Мира",
                "пр. Победы", "ул. Советская", "ул. Кирова", "ул. Садовая",
                "бульвар Космонавтов", "набережная Реки", "ул. Лесная", "пр. Строителей"
        };
        return "д. " + (random.nextInt(100) + 1) + ", " + streets[random.nextInt(streets.length)];
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