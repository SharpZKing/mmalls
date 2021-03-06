package com.mmall.service;

import com.mmall.common.ServerResponse;
import com.mmall.pojo.Category;

import java.util.List;

/**
 * Created by zjfsharp on 2017/5/6.
 */
public interface ICategoryService {

    ServerResponse addCategory(String categoryName, Integer parentId);

    ServerResponse updateCategoryName(Integer categoryId, String categoryName);

    ServerResponse<List<Category>> getChildenPalallelCategory(Integer categoryId);

    ServerResponse<List<Integer>> selectCategoryAndChildenById(Integer categoryId);
}
