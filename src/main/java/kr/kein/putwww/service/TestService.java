package kr.kein.putwww.service;

import kr.kein.putwww.mapper.TestMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TestService implements TestServiceImpl {
    @Autowired
    private TestMapper testMapper;

    @Override
    public String getTest() {
        String r3 = testMapper.getCurrentDateTime();
        System.out.println("#Test Service result:"+r3);

        return "2";
    }
}
