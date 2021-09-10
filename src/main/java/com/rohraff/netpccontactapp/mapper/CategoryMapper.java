package com.rohraff.netpccontactapp.mapper;

import com.rohraff.netpccontactapp.model.Category;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface CategoryMapper {

    @Insert("INSERT INTO CATEGORY (name)  VALUES(#{name})")
    @Options(useGeneratedKeys = true, keyProperty = "categoryId")
    int insertCategory(Category category);

    @Select("SELECT * FROM CATEGORY")
    List<Category> getCategories();
}
