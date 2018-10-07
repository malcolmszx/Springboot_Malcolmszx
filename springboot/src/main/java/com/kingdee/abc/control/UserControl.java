package com.kingdee.abc.control;

import static org.hamcrest.CoreMatchers.nullValue;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.kingdee.abc.annotation.Encrypt;
import com.kingdee.abc.dao.UserMapper;
import com.kingdee.abc.entity.User;
import com.kingdee.abc.entity.UserExample;

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
	
	
	@ApiOperation(value="获取用户详情列表")
	@GetMapping("/{page}/{rows}")
	public Object page(@PathVariable("page") int page,@PathVariable("rows") int rows) {
		UserExample userExample = new UserExample();
		UserExample.Criteria criteria = userExample.createCriteria();
		criteria.andIdBetween(1, 100);
		PageHelper.startPage(page, rows);//page：当前页 rows:每页显示多少条
		List<User> lists = UserMapper.selectByExample(userExample);
		PageInfo<User> pageInfo = new PageInfo<User>(lists);
		return pageInfo;
	}


}
