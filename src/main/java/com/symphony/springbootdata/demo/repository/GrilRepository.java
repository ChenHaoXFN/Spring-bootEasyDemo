package com.symphony.springbootdata.demo.repository;

import com.symphony.springbootdata.demo.domain.Gril;
import java.util.List;
import org.omg.PortableInterceptor.INACTIVE;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 * @User: ch
 * @Date: 2017-11-24
 * @Time: 下午1:50
 */
public interface GrilRepository extends JpaRepository<Gril, Integer> ,
    JpaSpecificationExecutor<Gril>{

  //根据年龄来查女生
  public List<Gril> findByAge(Integer age);

  //
  public List<Gril> findByNameStartingWithAndAgeLessThan(String name, Integer age);

  //模糊查找电话号码  随便输 都给你找出来
  List<Gril> findByPhoneNumIsLike(String phoneNum);

  //查找姓名跟年龄等于..的
  @Query("select o from Gril o where o.name=?1 and o.age=?2")
  List<Gril> queryParams1(String name, Integer age);

  //占位符的两种使用方式
  @Query("select o from Gril o where o.name=:name and o.age=:age")
  List<Gril> queryParams2(@Param(value = "name") String name, @Param(value = "age") Integer age);

  //查找年龄最大的
  @Query("select o from Gril o where o.age = (select max(age) from Gril ) ")
  List<Gril> queryMaxAge();

  //使用原生sql语句查询记录总条数
  @Query(nativeQuery = true, value = "SELECT COUNT(1) FROM gril")
  long queryCont();

  //修改时候需要加上  @Modifying 注解
  @Modifying
  @Query(value = "update gril o set o.age = ?1 where o.id = ?2", nativeQuery = true)
  void updataGril(Integer age, Integer id);

  //删除时候需要加上  @Modifying 注解
  @Modifying
  @Query(nativeQuery = true, value = "DELETE FROM gril WHERE id = ?1")
  void deleteGril(Integer id);


}
