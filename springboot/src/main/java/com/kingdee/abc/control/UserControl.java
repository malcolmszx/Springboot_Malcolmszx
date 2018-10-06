package com.kingdee.abc.control;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kingdee.abc.annotation.Encrypt;
import com.kingdee.abc.dao.UserMapper;
import com.kingdee.abc.entity.User;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(tags="用户管理")
@RestController
@RequestMapping("/user")
public class UserControl {
	
	@Autowired
	private UserMapper UserMapper;
	
	@ApiOperation(value="获取用户详情")
	@GetMapping("/{id}")
	@Encrypt
	public User list(@PathVariable("id") int id) {
		return UserMapper.selectByPrimaryKey(id);
	}

}
