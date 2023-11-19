package edu.hw5;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.List;

public class Task2 {
    private static final int THIRTEEN = 13;

    public List<LocalDate> getAllFridaysThirteenInYear(int year) {
        LocalDate date = LocalDate.of(year, 1, 1);
        List<LocalDate> fridaysThirteen = new ArrayList<>();

        while (true) {
            date = getNextFridayThirteen(date);

            if (date.getYear() != year + 1) {
                fridaysThirteen.add(date);
            } else {
                return fridaysThirteen;
            }
        }
    }

    public LocalDate getNextFridayThirteen(LocalDate date) {
        LocalDate currentDate = date;

        while (true) {
            currentDate = currentDate.with(TemporalAdjusters.next(DayOfWeek.FRIDAY));

            if (currentDate.getDayOfMonth() == THIRTEEN) {
                return currentDate;
            }
        }
    }
}
