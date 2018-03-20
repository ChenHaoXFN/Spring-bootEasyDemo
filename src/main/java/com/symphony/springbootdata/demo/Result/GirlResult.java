package com.symphony.springbootdata.demo.Result;

import lombok.Data;

/**
 * Created by ch on 2017-11-27
 */
@Data
public class GirlResult<T> {

  private Integer code;
  private String msg;
  private T data;

}
