package kr.kein.putwww.repository;

import kr.kein.putwww.mapper.TestMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

@RunWith(SpringJUnit4ClassRunner.class)
//@SpringBootTest(classes = TestServiceImpl.class)
@WebAppConfiguration
@MybatisTest
@AutoConfigureTestDatabase(replace= AutoConfigureTestDatabase.Replace.NONE)
//@EnableAutoConfiguration(exclude = {DataSourceAutoConfiguration.class,DataSourceTransactionManagerAutoConfiguration.class})
//@MapperScan(value={"kr.kein.putwww"})
public class TestMapperTest {

	@Autowired
	private TestMapper testMapper;
	//@Autowired
	//private TestServiceImpl testService;

	@Test
	public void testMapperTest() throws Exception{
		String result = testMapper.getCurrentDateTime();
		System.out.println("#Result:"+result);
	}
}
