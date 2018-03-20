package com.symphony.springbootdata.demo.repository;

import com.symphony.springbootdata.demo.domain.Girl;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * Created by ch on 2017-12-08
 * 带有分页跟排序功能的接口
 */
public interface GirlPagingAndSortingRepository extends PagingAndSortingRepository<Girl, Integer> {


}
