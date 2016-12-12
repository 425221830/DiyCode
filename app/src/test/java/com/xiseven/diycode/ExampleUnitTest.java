package com.xiseven.diycode;

import com.xiseven.diycode.utils.DateUtils;

import org.junit.Test;

import java.text.ParseException;
import java.util.Date;

import static org.junit.Assert.assertEquals;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() throws Exception {
        assertEquals(4, 2 + 2);
    }

    @Test
    public void test() throws ParseException {
        String date = DateUtils.getTimeAgo("2016-12-04T13:54:11.593+08:00");
        System.out.println("date = " + date);
    }
}