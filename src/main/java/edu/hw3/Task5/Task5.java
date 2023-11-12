package edu.hw3.Task5;

import java.util.Arrays;
import java.util.Objects;

public class Task5 {
    private final static String DESC = "DESC";
    private final static String ASC = "ASC";

    public String[] parseContacts(String[] contacts, String sort) {
        if (contacts == null || contacts.length == 0) {
            return new String[] {};
        }

        if (!Objects.equals(sort, DESC) && !Objects.equals(sort, ASC)) {
            throw new IllegalArgumentException("You cant sort like that");
        }

        if (sort.equals(DESC)) {
            Arrays.sort(contacts, Task5::comparatorDESC);
        } else {
            Arrays.sort(contacts, Task5::comparatorASC);
        }

        return contacts;
    }

    private static int comparatorASC(String contact1, String contact2) {
        String[] contacts1 = contact1.split(" ");
        String[] contacts2 = contact2.split(" ");
        String lastName1 = contacts1.length > 1 ? contacts1[1] : contacts1[0];
        String lastName2 = contacts2.length > 1 ? contacts2[1] : contacts2[0];

        return lastName1.compareTo(lastName2);
    }

    private static int comparatorDESC(String contact1, String contact2) {
        return comparatorASC(contact2, contact1);
    }
}
