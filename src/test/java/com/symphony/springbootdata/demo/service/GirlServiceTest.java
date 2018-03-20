package com.symphony.springbootdata.demo.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

/**
 * Created by ch on 2017-12-06
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class GirlServiceTest {

  @Autowired
  private MockMvc mvc;

  @Test
  public void findOne() throws Exception {
    mvc.perform(MockMvcRequestBuilders.get("/grils"))
        .andExpect(MockMvcResultMatchers.status().is(200))
        .andExpect(MockMvcResultMatchers.content().string("[{\"id\":1,\"name\":\"陈浩\",\"age\":24},{\"id\":2,\"name\":\"2\",\"age\":20},{\"id\":3,\"name\":\"陈浩\",\"age\":24},{\"id\":4,\"name\":\"hello\",\"age\":20},{\"id\":5,\"name\":\"陈浩\",\"age\":24},{\"id\":6,\"name\":\"陈浩\",\"age\":20}]"));
  }

}