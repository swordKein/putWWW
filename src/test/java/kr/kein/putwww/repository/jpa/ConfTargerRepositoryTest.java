package kr.kein.putwww.repository.jpa;

import kr.kein.putwww.entity.ConfTarget;
import kr.kein.putwww.util.DateUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
//@SpringBootTest(classes = TestServiceImpl.class)
@WebAppConfiguration
//@MybatisTest
@AutoConfigureTestDatabase(replace= AutoConfigureTestDatabase.Replace.NONE)
@EntityScan(basePackages="kr.kein.putwww")
@EnableJpaRepositories(basePackages="kr.kein.putwww",  entityManagerFactoryRef="emf")
//@EnableAutoConfiguration(exclude = {DataSourceAutoConfiguration.class,DataSourceTransactionManagerAutoConfiguration.class})
//@MapperScan(value={"kr.kein.putwww"})
public class ConfTargerRepositoryTest {

	@Autowired
	private ConfTargetRepository confTargetRepository;
	//@Autowired
	//private TestServiceImpl testService;

	@Test
	public void test1() throws Exception{
		confTargetRepository.save(new ConfTarget("test1", "test1_desc", "test_url", DateUtils.getCurrTimestamp(), "regid"));

		Iterable<ConfTarget> result = confTargetRepository.findAll();

		System.out.println("#Result:"+result.toString());
	}
}
