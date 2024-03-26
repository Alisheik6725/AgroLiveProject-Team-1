package com.jsp.agro.util;
import java.time.Duration;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.Period;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.temporal.ChronoField;

public class TimeCalculator {
	



	    static final int MINUTES_PER_HOUR = 60;
	    static final int SECONDS_PER_MINUTE = 60;
	    static final int SECONDS_PER_HOUR = SECONDS_PER_MINUTE * MINUTES_PER_HOUR;

	    public static long[] getDuration(LocalDateTime fromDateTime,LocalDateTime toDateTime) {
	    	
	    	System.out.println(fromDateTime);
	    	System.out.println(toDateTime);
	        Period period = getPeriod(fromDateTime, toDateTime);
	        long time[] = getTime(fromDateTime, toDateTime);

	        System.out.println(period.getYears() + " years " + 
	                period.getMonths() + " months " + 
	                period.getDays() + " days " +
	                time[0] + " hours " +
	                time[1] + " minutes " +
	                time[2] + " seconds.");
	        long arr[]=new long[4];
	        arr[0]=period.getYears();
	        arr[1]=period.getMonths();
	        arr[2]=period.getDays();
	      arr[3] = time[0];
	      return arr;


	    }

	    private static Period getPeriod(LocalDateTime dob, LocalDateTime now) {
	        return Period.between(dob.toLocalDate(), now.toLocalDate());
	    }

	    private static long[] getTime(LocalDateTime dob, LocalDateTime now) {
	        LocalDateTime today = LocalDateTime.of(now.getYear(),
	                now.getMonthValue(), now.getDayOfMonth(), dob.getHour(), dob.getMinute(), dob.getSecond());
	        Duration duration = Duration.between(today, now);

	        long seconds = duration.getSeconds();

	        long hours = seconds / SECONDS_PER_HOUR;
	        long minutes = ((seconds % SECONDS_PER_HOUR) / SECONDS_PER_MINUTE);
	        long secs = (seconds % SECONDS_PER_MINUTE);

	        return new long[]{hours, minutes, secs};
	    }
	}


