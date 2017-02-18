package com.cqx.hv.demo;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

/**
 14      * Bean Validation 中内置的 constraint
 15      * @Null   被注释的元素必须为 null
 16      * @NotNull    被注释的元素必须不为 null
 17      * @AssertTrue     被注释的元素必须为 true
 18      * @AssertFalse    被注释的元素必须为 false
 19      * @Min(value)     被注释的元素必须是一个数字，其值必须大于等于指定的最小值
 20      * @Max(value)     被注释的元素必须是一个数字，其值必须小于等于指定的最大值
 21      * @DecimalMin(value)  被注释的元素必须是一个数字，其值必须大于等于指定的最小值
 22      * @DecimalMax(value)  被注释的元素必须是一个数字，其值必须小于等于指定的最大值
 23      * @Size(max=, min=)   被注释的元素的大小必须在指定的范围内
 24      * @Digits (integer, fraction)     被注释的元素必须是一个数字，其值必须在可接受的范围内
 25      * @Past   被注释的元素必须是一个过去的日期
 26      * @Future     被注释的元素必须是一个将来的日期
 27      * @Pattern(regex=,flag=)  被注释的元素必须符合指定的正则表达式
 28      * Hibernate Validator 附加的 constraint
 29      * @NotBlank(message =)   验证字符串非null，且长度必须大于0
 30      * @Email  被注释的元素必须是电子邮箱地址
 31      * @Length(min=,max=)  被注释的字符串的大小必须在指定的范围内
 32      * @NotEmpty   被注释的字符串的必须非空
 33      * @Range(min=,max=,message=)  被注释的元素必须在合适的范围内
 34      */
public class Person {

    @NotBlank
    private String name;

    @Min(1)
    @Max(50)
    private int age;

    @Length(min = 11, max = 11)
    private String phone;

    @Email
    private String email;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
