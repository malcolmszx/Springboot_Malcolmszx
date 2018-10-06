package com.kingdee.abc.control;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kingdee.abc.annotation.Encrypt;
import com.kingdee.abc.dao.UserMapper;
import com.kingdee.abc.entity.User;

@RestController
@RequestMapping("/user")
public class UserControl {
	
	@Autowired
	private UserMapper UserMapper;
	
	@GetMapping("/{id}")
	@Encrypt
	public User list(@PathVariable("id") int id) {
		return UserMapper.selectByPrimaryKey(id);
	}

}
