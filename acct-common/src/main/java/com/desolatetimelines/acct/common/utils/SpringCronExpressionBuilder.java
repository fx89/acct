package com.desolatetimelines.acct.common.utils;

/**
 * Builds cron expressions for use with Spring's @Scheduled annotation
 */
public abstract class SpringCronExpressionBuilder {

    /**
     * Returns a {@link SpringDailyCronExpressionBuilder builder} that facilitates the
     * creation of Spring cron expressions that trigger daily runs at the specified time.
     */
    public static SpringDailyCronExpressionBuilder daily() {
        return new SpringDailyCronExpressionBuilder();
    }

    /**
     * Facilitates the creation of Spring cron expressions that trigger daily runs at the specified time
     */
    public static class SpringDailyCronExpressionBuilder {
        private int hour = 0;
        private int minute = 0;
        private int second = 0;

        /**
         * Specifies the hour when the daily run is to occur. If not set, the default is 0.
         */
        public SpringDailyCronExpressionBuilder atHour(int hour) {
            this.hour = hour;
            return this;
        }

        /**
         * Specifies the minute when the daily run is to occur. If not set, the default is 0.
         */
        public SpringDailyCronExpressionBuilder atMinute(int minute) {
            this.minute = minute;
            return this;
        }

        /**
         * Specifies the second when the daily run is to occur. If not set, the default is 0.
         */
        public SpringDailyCronExpressionBuilder atSecond(int second) {
            this.second = second;
            return this;
        }

        public String build() {
            return second + " " + minute + " " + hour + " * * *";
        }
    }

}
