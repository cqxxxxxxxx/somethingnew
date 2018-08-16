package com.cqx;

import com.cqx.dao.UserRepository;
import com.cqx.model.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * Created by BG307435 on 2017/8/14.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class JpaTest {


    @Autowired
    UserRepository userRepository;

    @Test
    public void saveWithTransient(){
        User user = new User();
        for (int i = 0; i < 50; i++) {
            user.setName("name:" + i);
            user.setAvatar("sadfsafdsa");
            user.setGender(i);
            user.setId(null);
            userRepository.save(user);
        }
    }

    @Test
    public void save(){
        User user = new User();
        for (int i = 0; i < 50; i++) {
            user.setName("name:" + i);
            user.setGender(i);
            user.setId(null);
            userRepository.save(user);
        }
    }
    @EntityGraph
    @Test
    public void query(){
        List<User> userList = userRepository.findAll();
        userList.stream().forEach(x -> System.out.println(x.getName()));
        Pageable pageable = new PageRequest(1, 10, new Sort(Sort.Direction.ASC, "id"));
        Page<User> page = userRepository.findAll(pageable);
        userList = page.getContent();
        userList.stream().forEach(x -> System.out.println(x.getName()));

    }


    @Test
    public void update(){
        userRepository.modifyById1("三大发射点发a", 20);
        User user = new User();
        user.setName("xxxxxx");
        user.setId(20);
        userRepository.save(user);
    }

    @Test
//    @Transactional
    public void updateWithTransaction(){
        userRepository.modifyById2("我是二二二二", 21);
    }


    @Test
    public void queryHints(){

    }

}
