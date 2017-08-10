package kr.kein.putwww.service;

import org.springframework.stereotype.Service;

@Service
public class UtilService implements UtilServiceImpl {

    @Override
    public String getStr() {
        return "str";
    }
}
