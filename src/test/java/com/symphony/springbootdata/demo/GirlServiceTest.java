package com.symphony.springbootdata.demo;

import com.symphony.springbootdata.demo.domain.Girl;
import com.symphony.springbootdata.demo.repository.GirlRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Created by ch on 2017-12-06
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class GirlServiceTest {

  @Autowired
  private GirlRepository girlRepository;

  @Test
  public void findOne() {
    Girl girl = girlRepository.findOne(1);
    Assert.assertEquals(new Integer(24), girl.getAge());
    System.out.println(girl.toString());
  }

}
