package com.symphony.springbootdata.demo.service;

import com.symphony.springbootdata.demo.domain.Girl;
import com.symphony.springbootdata.demo.repository.GirlRepository;
import java.util.ArrayList;
import java.util.List;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Sort.Order;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Created by ch on 2017-12-08
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class GirlServiceTests {

  @Autowired
  private GirlRepository girlRepository;
  @Autowired
  private GirlService girlService;

  private PagingAndSortingRepository pagingAndSortingRepository;


  @Test
  public void findOne() throws Exception {
    String phoneNum = "545";
    String phoneNumber = "%" + phoneNum + "%";
    List<Girl> lists = girlRepository.findByPhoneNumIsLike(phoneNumber);
    for (Girl girl : lists) {
      System.out.println(girl.toString());
    }
  }

  @Test
  public void queryParams1() throws Exception {
    List<Girl> list = girlRepository.queryParams1("陈浩", 20);
    for (Girl girl : list) {
      System.out.println(girl.toString());
    }
  }

  @Test
  public void queryMaxAge() throws Exception {
    List<Girl> list = girlRepository.queryMaxAge();
    for (Girl girl : list) {
      System.out.println(girl.toString());
    }

  }

  @Test
  public void queryParams2() throws Exception {
    List<Girl> list = girlRepository.queryParams2("陈浩", 20);
    for (Girl girl : list) {
      System.out.println(girl.toString());
    }
  }

  @Test
  public void queryCont() throws Exception {
    long l = girlRepository.queryCont();
    System.out.println(l);
  }

  @Test
  public void updataGril() throws Exception {
    girlService.updataGril(555, 2);
  }


  @Test
  public void deleteGril() throws Exception {
    girlService.deldteGril(1);
  }

  //批量添加测试数据
  @Test
  public void saveTestData() {
    List<Girl> girls = new ArrayList<>();
    Girl girl = null;
    for (int a = 0; a < 100; a++) {
      girl = new Girl();
      girl.setId(a + 10);
      girl.setAge(1000 - a);
      girl.setName("test" + a);
      girl.setPhoneNum("3213456" + a);
      girls.add(girl);
    }
    girlRepository.save(girls);
  }


  /**
   * 测试分页跟排序
   */

  /*@Test
  public void testSort(){
    Iterable sort = new ;
    pagingAndSortingRepository.findAll(sort);
  }*/
  @Test
  public void testPage() {
    //设置升续降续，以什么字段排序
    Order order = new Order(Direction.ASC, "age");
    Sort sort = new Sort(order);
    //page 0、index 以0未开始，size 每一页显示几条
    Pageable pageable = new PageRequest(0, 10, sort);
    girlService.page(pageable);
  }

}