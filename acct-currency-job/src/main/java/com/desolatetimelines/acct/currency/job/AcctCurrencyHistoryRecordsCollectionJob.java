package com.desolatetimelines.acct.currency.job;

import com.desolatetimelines.acct.currency.service.AcctCurrencyCollectionService;
import com.desolatetimelines.acct.job.framework.model.AcctJobCron;
import com.desolatetimelines.acct.job.framework.service.AcctJob;
import org.springframework.stereotype.Service;

/**
 * This background job is responsible for collecting exchange rate history
 * records for the registered monitored currencies
 */
@Service
public class AcctCurrencyHistoryRecordsCollectionJob extends AcctJob {

    private final AcctCurrencyCollectionService currencyCollectionService;

    public AcctCurrencyHistoryRecordsCollectionJob(AcctCurrencyCollectionService currencyCollectionService) {
        this.currencyCollectionService = currencyCollectionService;
    }

    @Override
    protected String getJobServiceName() {
        return System.getenv("CURRENCY_APPLICATION_NAME");
    }

    @Override
    protected String getJobUUID() {
        return "550fa0fe-40bc-48ed-95cb-dda236cdb882";
    }

    @Override
    protected String getJobName() {
        return "Monitored currency records collection";
    }

    @Override
    protected String getJobDescription() {
        return "This background job is responsible for collecting exchange rate history " +
            "records for the registered monitored currencies";
    }

    @Override
    protected AcctJobCron getCron() {
        return AcctJobCron.acctJobCronWithFixedIntervalMs(5 * 60 * 1000); // Every 5 minutes
    }

    @Override
    protected long getMaxDelayMs() {
        return 5000;
    }

    @Override
    protected void internalJobLogicRunnable() {
        currencyCollectionService.handleCurrencyExchangeRatesCollection();
    }
}
