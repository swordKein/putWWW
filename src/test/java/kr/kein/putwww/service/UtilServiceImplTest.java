package kr.kein.putwww.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

//@RunWith(SpringRunner.class)
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
		//(classes = UtilServiceImpl.class)
//@WebAppConfiguration
//@AutoConfigureTestDatabase(replace= AutoConfigureTestDatabase.Replace.NONE)
//@AutoConfigureTestDatabase
//@EnableAutoConfiguration
//@ComponentScan(basePackageClasses=TestServiceImpl.class)
//@EntityScan(basePackageClasses=TestDao.class)
//@MybatisTest
public class UtilServiceImplTest {

	//@MockBean
	@Autowired
	private UtilService utilService;

	@Test
	public void testUtilServiceTest() throws Exception{
		String result = utilService.getStr();
		System.out.println("#Result:"+result);
	}
}
