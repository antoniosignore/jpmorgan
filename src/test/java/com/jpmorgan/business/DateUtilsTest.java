package com.jpmorgan.business;

import com.jpmorgan.utils.DateUtils;
import jpmorgan.JpMorganTest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Date;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = JpMorganTest.class)
public class DateUtilsTest {

    @Test
    public void shuold_subtract_n_minutes_to_date() {

        Date now = DateUtils.now();
        System.out.println("now = " + now);
        Date newDate = DateUtils.subtractMinutes(now, 5);
        System.out.println("newDate = " + newDate);

    }
}
