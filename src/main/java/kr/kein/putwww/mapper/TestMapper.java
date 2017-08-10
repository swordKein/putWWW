package kr.kein.putwww.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
//@Qualifier("TestDao")
public interface TestMapper {
    //@Select("SELECT NOW()")
    public String getCurrentDateTime();
}
