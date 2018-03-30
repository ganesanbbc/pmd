package com.cts.pmt;

import com.cts.pmt.annotation.Car;
import com.cts.pmt.annotation.MyAnnotation;
import org.hamcrest.CoreMatchers;
import org.junit.Test;

import java.lang.reflect.Method;

import static org.hamcrest.MatcherAssert.assertThat;

public class TestAnnotationTest {


    @Test
    public void thatCustomAnnotation() {
        long actual = 0;
        long expected = 1;

        Class<MyAnnotation> classObj = MyAnnotation.class;
        for (Method method : classObj.getDeclaredMethods()) {
            if (method.isAnnotationPresent(Car.class)) {
                actual++;
            }
        }


        assertThat(actual, CoreMatchers.is(expected));

    }
}
