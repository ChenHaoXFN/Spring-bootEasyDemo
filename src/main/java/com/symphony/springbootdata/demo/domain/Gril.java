package com.symphony.springbootdata.demo.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import lombok.Data;

/**
 * 实体类、自动建表.
 */
@Entity
@Data
public class Gril {

  @Override
  public String toString() {
    return "Gril{" +
        "id=" + id +
        ", name='" + name + '\'' +
        ", age=" + age +
        ", phoneNum='" + phoneNum + '\'' +
        '}';
  }

  @Id //主键
  @GeneratedValue //自增策略
  private Integer id;
  private String name;
  @Min(value = 18, message = "未成年") //
  private Integer age;

  @Max(value = 10)
  private String phoneNum;


  public Gril(String name, Integer age) {
    this.name = name;
    this.age = age;
  }

  public Gril() {
  }

  public Gril(Integer id, String name, Integer age) {
    this.id = id;
    this.name = name;
    this.age = age;
  }


}
