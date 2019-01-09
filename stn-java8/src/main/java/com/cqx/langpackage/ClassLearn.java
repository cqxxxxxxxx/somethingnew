package com.cqx.langpackage;

import org.junit.Test;

public class ClassLearn {

    @Test
    public void whatIsClass() {
        Class<Person> personClass = Person.class;
        Person person = new Person();
        Class personClass1 = person.getClass();

        @SuppressWarnings("rawtypes") Class sexClass = Sex.BOY.getClass();

    }

    private enum Sex {
        BOY,
        GIRL
    }

    private class Person {
        private String name;

        public void setName(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }
    }
}
