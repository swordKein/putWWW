package kr.kein.putwww.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * Created by Ice on 11/4/2016.
 */
@Service
public class SomeService {

    private static final Logger log = LoggerFactory.getLogger(SomeService.class);

    public void writeDataToLog(String dataToWrite) {
        log.info("The data is : " + dataToWrite);
    }
}
