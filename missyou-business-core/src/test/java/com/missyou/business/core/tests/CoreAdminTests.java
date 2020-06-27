package com.missyou.business.core.tests;

import com.missyou.business.core.CoreBusinessApplication;
import com.missyou.business.core.service.ICoreAdminService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = CoreBusinessApplication.class)
public class CoreAdminTests {

	@Resource
	private ICoreAdminService coreAdminService;

	@Test
	public void testConnection() {
		coreAdminService.count();
	}

}
