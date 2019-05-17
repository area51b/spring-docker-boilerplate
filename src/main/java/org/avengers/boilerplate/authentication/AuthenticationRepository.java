package org.avengers.boilerplate.authentication;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.avengers.boilerplate.common.base.BaseRepository;

@Mapper
public interface AuthenticationRepository extends BaseRepository {

	@Select("select 1 from `users` where username = #{username} and password = #{password}")
	public String login(@Param("username") String username, @Param("password") String password);
	
}
