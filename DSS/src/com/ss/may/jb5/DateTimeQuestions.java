/**
 * 
 */
package com.ss.may.jb5;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Month;
import java.time.Year;
import java.time.temporal.TemporalAdjusters;

/**
 * @author lukej
 *
 */
/*
 * 1. LocalDateTime Class to store your birthday in years, months, days, seconds, and nanosecond
 * 2. Use TemporalAdjuster method previous(DayOfWeek.THURDSDAY)
 * 3. Zoneoffset is absolute difference from GMT 0 while zonid accounts for regional variance
 * 4. Use Instant.now().atZone(ZoneId.systemDefault)); for instant -> ZoneDateTime
 *    Use ZoneDateTime.now().toInstant(); for  ZoneDateTime -> instant
 * 
 */
public class DateTimeQuestions {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		//List Mondays in a given month for the current year
		Month month = Month.AUGUST;
		System.out.printf("For the month of %s:%n", month);
        LocalDate date = Year.now().atMonth(month).atDay(1).
              with(TemporalAdjusters.firstInMonth(DayOfWeek.MONDAY));
        Month mi = date.getMonth();
        while (mi == month) {
            System.out.printf("%s%n", date);
            date = date.with(TemporalAdjusters.next(DayOfWeek.MONDAY));
            mi = date.getMonth();
        }

	}

}
