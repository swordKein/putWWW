package kr.kein.putwww.job.schedule;

import kr.kein.putwww.service.SomeService;
import org.quartz.*;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by Ice on 11/4/2016.
 */
@PersistJobDataAfterExecution
@DisallowConcurrentExecution
public class JobRunner implements Job {

    private String dataToWrite;

    @Autowired
    private SomeService someService;

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        someService.writeDataToLog(dataToWrite);
    }

    public void setDataToWrite(String dataToWrite) {
        this.dataToWrite = dataToWrite;
    }
}
