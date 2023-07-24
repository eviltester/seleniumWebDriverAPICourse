package com.seleniumsimplified.junit;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.hamcrest.Matchers.both;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNot.not;
import static org.hamcrest.core.IsNull.nullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.*;



public class JUnitExercisesTest {

    private String iSetThisBefore = "set as field";
    private static String iSetThisBeforeClass="default";

    @Test
    public void usingAssertTrue(){
        assertTrue("true is true", true);

        assertTrue("i know what 3+3 is", (3+3)==6);

        String theAnswer = "The Answer";
        assertTrue("The answer is true",
                 "the answer".equalsIgnoreCase(theAnswer));
    }

    @Test
    public void usingAssertFalse(){
        assertFalse("false is not true", !true);

        assertFalse("I always forget half of seven", (7/2) == 4);

        String anAnswer = "An Answer";
        assertFalse("An Answer does not contain The Answer",
                anAnswer.contains("The"));
    }

    @Test
    public void usingAssertEquals(){
        // remember syntax is message, expected, actual
        assertEquals("true == true", true, true);

        String myAnswer = "My Answer";
        assertEquals("String compare", "My Answer", myAnswer );

        assertEquals("3+3", 6, 3+3);
    }

    @Before
    public void setSomethingBeforeToUseLater(){
        iSetThisBefore = "set before";
    }

    @Test
    public void checkISetSomethingBefore(){
        assertFalse("iSetThisBefore should not equal default value",
                "set as field".equals(iSetThisBefore));

        assertEquals("iSetThisBefore Should have changed",
                    "set before", iSetThisBefore);
    }

    @BeforeClass
    public static void setSomethingBeforeClass(){
        iSetThisBeforeClass = "for all class methods";
    }

    @Test
    public void checkBeforeClassRan(){
        assertFalse("iSetThisBeforeClass should not equal default value",
                "default".equals(iSetThisBeforeClass));

        assertEquals("iSetThisBeforeClass Should have changed",
                "for all class methods", iSetThisBeforeClass);
    }

    @Test
    public void assertThatBonusQuestion(){
        //assertThat uses Hamcrest Matchers
        assertThat("The Answer", is(not("An Answer")));
        assertThat("The Answer", is(not(nullValue())));
        assertThat("The Answer",
                both(containsString("The")).
                and(containsString("Answer"))
        );
    }
}
