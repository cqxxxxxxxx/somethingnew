package com.cqx;

import com.fasterxml.jackson.databind.ObjectMapper;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Unit test for simple App.
 */
public class AppTest
        extends TestCase {
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public AppTest( String testName )
    {
        super( testName );
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite()
    {
        return new TestSuite( AppTest.class );
    }

    /**
     * Rigourous Test :-)
     */
    public void testApp() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        Person person = new Person();
        List<String> list = new ArrayList<>();
        list.add("1");
        list.add("2");
        list.add("3");
        List<List<String>> lists = new ArrayList<>();
        lists.add(list);
        lists.add(list);
        lists.add(list);
        String[] array = new String[]{"a", "b", "c"};
        person.setDatas(lists);
        person.setDatas1(array);
        String s = objectMapper.writeValueAsString(person);
        System.out.println(s);
        Person person1 = objectMapper.readValue(s, Person.class);
        assertTrue(true);
    }

    public static class Person {
        private List<List<String>> datas;
        private String[] datas1;

        public List<List<String>> getDatas() {
            return datas;
        }

        public void setDatas(List<List<String>> datas) {
            this.datas = datas;
        }

        public String[] getDatas1() {
            return datas1;
        }

        public void setDatas1(String[] datas1) {
            this.datas1 = datas1;
        }
    }
}
