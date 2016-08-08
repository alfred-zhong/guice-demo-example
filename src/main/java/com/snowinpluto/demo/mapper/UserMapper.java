package com.snowinpluto.demo.mapper;

import com.snowinpluto.demo.entity.User;
import com.snowinpluto.demo.providers.MybatisSqlProvider;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;

import java.util.List;

public interface UserMapper {

    @Insert("insert into user(name, age) values (#{name}, #{age})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    public void add(User user);

    @Select("select id, name, age from user where id = #{id}")
    public List<User> findById(@Param("id") Long id);

    @SelectProvider(type = MybatisSqlProvider.class, method = "findUserByNames")
    public List<User> findByNames(@Param("names") List<String> names);
}
