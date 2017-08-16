package com.cqx.dao;

import com.cqx.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.QueryHints;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.data.repository.query.Param;

import javax.persistence.QueryHint;
import javax.transaction.Transactional;

/**
 * 注意 更新 跟删除操作必须加@Transactional和@Modifying
 * Created by BG307435 on 2017/8/14.
 */
public interface UserRepository extends JpaRepository<User, Integer>{


    User findByName(String name);

    Page<User> findByName(String name, Pageable pageable);


    /**
     * namedQuery => 看User类 @NamedQuery
     * @param phone
     * @return
     */
    User findByPhoneXXXX(String phone);

    /**
     * nativeQuery 可能就跟数据库强相关了，移植性可能就不好
     * @param name
     * @param id
     * @return
     */
    @Query(value = "select * from stn_user u where id = ?2", nativeQuery = true)
    User findById(String  name, Integer id);


    /**
     * namedquery  <= 查这个关键字
     * 默认 表名 为实体类名,如果改用stn_user 报异常映射出错，
     * 优点： 跟具体数据库无关，oracle，mysql都能用
     * @param name
     * @param id
     * @return
     */
    @Modifying
    @Transactional
    @Query(value = "update User u set u.name = ?1 where u.id = ?2")
    int modifyById2(String name, Integer id);


    /**
     * nativeQuery => Configures whether the given query is a native one.
     * 不过这样写sql会对数据库类型产生依赖，不方便数据库移植
     * @param name
     * @param id
     * @return
     */
    @Modifying
    @Transactional
    @Query(value = "update stn_user u set u.name = ?1 where u.id = ?2", nativeQuery = true)
    int modifyById1(String  name, Integer id);


    /**
     * 通过@Param 跟 :name 对应起来, 而不是参数顺序
     * @param phone
     * @param name
     * @return
     */
    @Query("select u from User u where u.name = :name or u.phone = :phone")
    User findByLastnameOrFirstname(@Param("phone") String phone,
                                   @Param("name") String name);


//  不知道干什么用的
//    @QueryHints(value = { @QueryHint(name = "name", value = "value")},
//            forCounting = false)
//    Page<User> findByLastname(String lastname, Pageable pageable);

}
