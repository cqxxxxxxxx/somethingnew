package com.cqx;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;


/**
 * Hello world!
 */
public class App {

//    public static void main(String[] args) {
//        String a = System.getProperty("user.dir") + "/stn-annotation/src/main/java/com/cqx/model";
//        Starter starter = new StarterImpl();
//        starter.startLoad(a);
//        BeanFactory beanFactory = new BeanFactoryImpl();
//        Room room = (Room) beanFactory.getBean("room");
//        Person p = (Person) beanFactory.getBean("person");
//        room.whoisin();
//        CallbackTest callbackTest = (CallbackTest) beanFactory.getBean("callbackTest");
//        System.out.println(callbackTest.getFuck());
////        Class[] classes = CallbackTest.class.getInterfaces();
////        Arrays.stream(classes).forEach(aClass -> System.out.println(aClass.getName()));
////
//    }


    public static void main(String[] args) {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationcontext.xml");

    }
}
