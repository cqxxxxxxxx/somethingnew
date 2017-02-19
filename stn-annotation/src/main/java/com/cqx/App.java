package com.cqx;

import com.cqx.factory.BeanFactory;
import com.cqx.factory.BeanFactoryImpl;
import com.cqx.model.CallbackTest;
import com.cqx.model.Person;
import com.cqx.model.Room;
import com.cqx.starter.Starter;
import com.cqx.starter.StarterImpl;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Arrays;


/**
 * Hello world!
 */
public class App {

    public static void main(String[] args) {
        String a = System.getProperty("user.dir") + "/stn-annotation/src/main/java/com/cqx/model";
        Starter starter = new StarterImpl();
        starter.startLoad(a);
        BeanFactory beanFactory = new BeanFactoryImpl();
        Room room = (Room) beanFactory.getBean("room");
        Person p = (Person) beanFactory.getBean("person");
        room.whoisin();
        CallbackTest callbackTest = (CallbackTest) beanFactory.getBean("callbackTest");
        System.out.println(callbackTest.getFuck());
//        Class[] classes = CallbackTest.class.getInterfaces();
//        Arrays.stream(classes).forEach(aClass -> System.out.println(aClass.getName()));
//
    }


//    public static  void main(String[] args){
//        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationcontext.xml");
//
//    }
}
