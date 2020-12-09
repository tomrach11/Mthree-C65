package com.tr.BirthdayCalculator;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        System.out.println("Welcome to the 100% Scientifically Accurate Birthday Calculator!");
        Scanner sc = new Scanner(System.in);
        LocalDate birthday;
        boolean inputOk = false;

        try {
            System.out.println("What's your birthday? (MM-DD-YYYY)");
            String str = sc.nextLine();
            birthday = LocalDate.parse(str, DateTimeFormatter.ofPattern("MM-dd-yyyy"));

        } catch (Exception e) {
            birthday = getCorrectBirthday();
        }

        LocalDate thisYearBirthday = birthday.withYear(LocalDate.now().getYear());

        LocalDate today = LocalDate.now();
        String todayStr =  today.format(DateTimeFormatter.ofPattern("MM-dd-yyyy")).toString();

        int nextYear = LocalDate.now().plusYears(1).getYear();


        long daysToBirthday;
        long yearsOld;

        if (thisYearBirthday.isBefore(today) || thisYearBirthday.isEqual(today)) {
            daysToBirthday = ChronoUnit.DAYS.between(today, thisYearBirthday.withYear(nextYear));
            yearsOld = ChronoUnit.YEARS.between(birthday, thisYearBirthday.plusYears(1));
        } else {
            daysToBirthday = ChronoUnit.DAYS.between(today, thisYearBirthday);
            yearsOld = ChronoUnit.YEARS.between(birthday, thisYearBirthday);
            System.out.println(birthday);
            System.out.println(thisYearBirthday);
        }

        System.out.println("That means you were born on a " + birthday.getDayOfWeek() + "!");
        System.out.println("This year it falls on a " + thisYearBirthday.getDayOfWeek() + "...");
        System.out.println("And since today is " + today.format(DateTimeFormatter.ofPattern("MM-dd-yyyy")) + ",");
        System.out.println("there's only " + daysToBirthday + " more days until the next one when you turn " + yearsOld + "!");
    }

    public static LocalDate getCorrectBirthday() {
        Scanner sc = new Scanner(System.in);
        LocalDate birthday;

        System.out.println("Please enter your Birthday in this format (MM-DD-YYYY)");
        String str = sc.nextLine();
        try {
            birthday = LocalDate.parse(str, DateTimeFormatter.ofPattern("MM-dd-yyyy"));
        } catch (Exception e) {
            return getCorrectBirthday();
        }
        return birthday;
    }

}
