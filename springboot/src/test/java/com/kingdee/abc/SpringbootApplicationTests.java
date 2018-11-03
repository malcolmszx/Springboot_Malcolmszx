package com.kingdee.abc;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.BeanUtils;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.alibaba.fastjson.JSON;

import lombok.extern.slf4j.Slf4j;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class SpringbootApplicationTests {

	@Test
	public void contextLoads() {
		
		ABCOpenAccount abcOpenAccount = new ABCOpenAccount();
		abcOpenAccount.setBankName("中国农业银行");
		abcOpenAccount.setMobilePhone("23hjhioK08");	
		this.argConvert(abcOpenAccount);
	}
	
	
	public void argConvert(OpenAccount openAccount) {
		ABCOpenAccount abcOpen = new ABCOpenAccount();
		abcOpen = (ABCOpenAccount) openAccount;
		log.info("【=======】 ABCOpenAccount: {} ", JSON.toJSON(abcOpen));
		BeanUtils.copyProperties(openAccount, abcOpen);
		abcOpen.setBankName(null);
		log.info("【=======】abcOpen : {} ", JSON.toJSON(abcOpen));
		
	}

}
