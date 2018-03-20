package com.symphony.springbootdata.demo.Utils;

import com.symphony.springbootdata.demo.Result.GirlResult;
import com.symphony.springbootdata.demo.domain.Girl;

/**
 * Created by ch on 2017-11-27
 */
public class ResultUtil {

  public static GirlResult<Girl> error(Integer code, String msg) {
    GirlResult result = new GirlResult();
    result.setCode(code);
    result.setMsg(msg);
    return result;
  }


  public static GirlResult<Girl> success(Object o) {
    GirlResult result = new GirlResult();
    result.setData(o);
    result.setCode(0);
    result.setMsg("成功");
    return result;
  }

  public static GirlResult<Girl> success() {
    return success(null);
  }

}
