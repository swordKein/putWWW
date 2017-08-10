package kr.kein.putwww.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

//@RunWith(SpringRunner.class)
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
		//(classes = TestServiceImpl.class)
//@WebAppConfiguration
//@AutoConfigureTestDatabase(replace= AutoConfigureTestDatabase.Replace.NONE)
//@AutoConfigureTestDatabase
//@EnableAutoConfiguration
//@ComponentScan(basePackageClasses=TestServiceImpl.class)
//@EntityScan(basePackageClasses=TestDao.class)
//@MybatisTest
public class TestServiceTestImpl {

	//@MockBean
	@Autowired
	private TestService testService;

	@Test
	public void testServiceTest() throws Exception{
		String result = testService.getTest();
		System.out.println("#Result:"+result);
	}
}
