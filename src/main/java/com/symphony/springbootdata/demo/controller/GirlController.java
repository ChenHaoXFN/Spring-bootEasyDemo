package com.symphony.springbootdata.demo.controller;

import com.symphony.springbootdata.demo.GrilPro;
import com.symphony.springbootdata.demo.domain.Girl;
import com.symphony.springbootdata.demo.repository.GirlRepository;
import com.symphony.springbootdata.demo.Result.GirlResult;
import com.symphony.springbootdata.demo.service.GirlService;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.criteria.Predicate;
import javax.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GirlController {

  //使用log日志打印注意导报类型.
  private final static Logger logger = LoggerFactory.getLogger(GirlController.class);

  @Autowired
  private GirlRepository girlRepository;

  @Autowired
  private GrilPro grilPro;

  @Autowired
  private GirlService girlService;

  // 在yml中配置的属性，在java代码中可以用@value注解进行使用.
  @Value("${commont}")
  private String commont;

  @RequestMapping(value = "hello", method = RequestMethod.GET)
  public String sayHello() {
    //    return "hello" + grilPro.getCupSize() + grilPro.getAge();
    return commont;
  }

  @RequestMapping(value = {"test"}, method = RequestMethod.GET)
  public String H(@RequestParam(value = "id", required = false, defaultValue = "0") Integer id) {
    return "ID: " + id;
  }

  @RequestMapping(value = {"/{id}/test"}, method = RequestMethod.GET)
  public String He(@PathVariable(value = "id", required = true) Integer id) {
    return "ID: " + id;
  }

  /**
   * 动态查询分页.
   */
  @GetMapping("/grils")
  public Page<Girl> findUser(Pageable pageable, @RequestParam("id") String id,@RequestParam("name") String name) {
    Specification<Girl> specification = (root, criteriaQuery, criteriaBuilder) -> {
      List<Predicate> predicates = new ArrayList<>();
      if (!StringUtils.isEmpty(id)) {
        predicates.add(criteriaBuilder.equal(root.get("id"), id));
      }
      if(!StringUtils.isEmpty(name)){
        predicates.add(criteriaBuilder.like(root.get("name"), "%"+name+"%"));
      }
      Predicate[] p = new Predicate[predicates.size()];
      return criteriaBuilder.and(predicates.toArray(p));
    };
    long count = girlRepository.count(specification); // 返回对应查询条件的总条数

    return girlRepository.findAll(specification, pageable); // 查询条件，分页条件
  }

  /**
   * 添加女生.(返回成功错误信息data)
   */
  @PostMapping("/grils")
  public GirlResult addUser(@Valid Girl girl, BindingResult bindingResult) {
    GirlResult result = new GirlResult();
    if (bindingResult.hasErrors()) {
      result.setCode(1);
      result.setMsg(bindingResult.getFieldError().getDefaultMessage());
      return result;
    }
    result.setCode(0);
    result.setMsg("成功");
    result.setData(girlRepository.save(girl));
    return result;
  }


  /**
   * 根据id查找女生.
   */
  @GetMapping("/grils/{id}")
  public Girl finOneGril(@PathVariable(value = "id") Integer id) {
    girlService.findGrilId(id);
    return girlService.findGrilById(id);
  }

  /**
   * 添加女生.
   */
  @PutMapping("grils/{id}")
  public Girl updataGril(@PathVariable(value = "id") Integer id,
      @RequestParam("name") String name,
      @RequestParam("age") Integer age) {
    return girlRepository.save(new Girl(id, name, age));
  }

  /**
   * 根据id删除女生.
   */
  @DeleteMapping("/grils/{id}")
  public void delGril(@PathVariable("id") Integer id) {
    girlRepository.delete(id);
  }

  /**
   * 根据年龄搜索女生.
   */
  @GetMapping("grils/agee/{age}")
  public List<Girl> findByAge(@PathVariable("age") Integer age) {
    return girlRepository.findByAge(age);
  }

  /**
   * 新增一个测试方法.
   */

  public Girl findOne(Integer id) {
    return girlRepository.findOne(id);
  }


}
