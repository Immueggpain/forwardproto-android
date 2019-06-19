/*******************************************************************************
 * MIT License
 *
 * Copyright (c) 2018 Immueggpain
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 *******************************************************************************/
package com.github.immueggpain.common;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;
import java.util.function.Function;

import android.annotation.TargetApi;
import android.os.Build;
import org.apache.commons.math3.util.Precision;

/**
 * sct = shortcut for time & date
 */
public final class sct {

	/**
	 * seconds, between the current time and midnight, January 1, 1970 UTC
	 */
	public static long time_s() {
		return System.currentTimeMillis() / 1000;
	}

	/**
	 * milliseconds, between the current time and midnight, January 1, 1970 UTC
	 */
	public static long time_ms() {
		return System.currentTimeMillis();
	}

	public static long time_ms_filems_system(String str) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss-SSSXX");
		Date date = sdf.parse(str);
		return date.getTime();
	}

	/**
	 * {@link #datetime(long, String, TimeZone) datetime(now, pattern,
	 * TimeZone.getTimeZone(timeZoneID))}
	 */
	public static String datetime(String pattern, String timeZoneID) {
		return datetime(time_ms(), pattern, timeZoneID);
	}

	/**
	 * {@link #datetime(long, String, TimeZone) datetime(time_ms, pattern,
	 * TimeZone.getTimeZone(timeZoneID))}
	 */
	public static String datetime(long time_ms, String pattern, String timeZoneID) {
		return datetime(time_ms, pattern, TimeZone.getTimeZone(timeZoneID));
	}

	/**
	 * format time_ms to string
	 */
	public static String datetime(long time_ms, String pattern, TimeZone timeZone) {
		SimpleDateFormat sdf = new SimpleDateFormat(pattern);
		sdf.setTimeZone(timeZone);
		String datetime = sdf.format(new Date(time_ms));
		return datetime;
	}

	/**
	 * {@link #datetime(long, String, TimeZone) datetime(now, "yyyy-MM-dd HH:mm:ss",
	 * TimeZone.getDefault())}
	 */
	public static String datetime() {
		return datetime(time_ms(), "yyyy-MM-dd HH:mm:ss", TimeZone.getDefault());
	}

	/**
	 * {@link #datetime(long, String, TimeZone) datetime(time_ms,
	 * "yyyy-MM-dd-HH-mm-ss-SSSXX", TimeZone.getDefault())}
	 */
	public static String datetime_filems_system(long time_ms) {
		return datetime(time_ms, "yyyy-MM-dd-HH-mm-ss-SSSXX", TimeZone.getDefault());
	}

	/**
	 * {@link #datetime(String, String) datetime("yyyy-MM-dd HH-mm-ss",
	 * "Asia/Shanghai")}
	 */
	public static String datetime_file_china() {
		return datetime("yyyy-MM-dd HH-mm-ss", "Asia/Shanghai");
	}

	/**
	 * convert string to utc seconds
	 */
	public static long str2time_s(String datetime, String pattern) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat(pattern);
		Date date = sdf.parse(datetime);
		return date.getTime() / 1000;
	}

	/**
	 * "yyyy-MM-dd HH:mm:ss"
	 */
	public static long str2time_s(String datetime) throws ParseException {
		return str2time_s(datetime, "yyyy-MM-dd HH:mm:ss");
	}

	/**
	 * return the last monday date
	 */
	public static String toWeek() {
		Calendar cal = Calendar.getInstance();
		cal.setFirstDayOfWeek(Calendar.MONDAY);
		cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String datestr = sdf.format(cal.getTime());
		return datestr;
	}

	@TargetApi(Build.VERSION_CODES.N)
	public static String toString(long ms) {
		int decimals = 0;

		Function<Double, String> roundFunc = d -> {
			if (decimals > 0)
				return String.valueOf(Precision.round(d, decimals));
			else
				return String.valueOf(d.intValue());
		};

		long sec = 1000;
		long min = 60 * sec;
		long hour = 60 * min;
		long day = 24 * hour;
		long week = 7 * day;
		long year = 52 * week;
		double numd = ms;
		if (ms < sec)
			return ms + " millisecond";
		if (ms < min)
			return roundFunc.apply(numd / sec) + " second";
		if (ms < hour)
			return roundFunc.apply(numd / min) + " minute";
		if (ms < day)
			return roundFunc.apply(numd / hour) + " hour";
		if (ms < week)
			return roundFunc.apply(numd / day) + " day";
		if (ms < year)
			return roundFunc.apply(numd / week) + " week";
		else
			return roundFunc.apply(numd / year) + " year";
	}

}
