package com.desolatetimelines.acct.common.utils;

/**
 * Builds cron expressions for use with Spring's @Scheduled annotation trigger recurrent runs
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
     * Returns a {@link SpringRecurrentCronExpressionBuilder builder} that facilitates the
     * creation of Spring cron expressions that triggers recurrent runs once every x hours,
     * minutes and seconds.
     */
    public static SpringRecurrentCronExpressionBuilder recurrently() {
        return new SpringRecurrentCronExpressionBuilder();
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

    /**
     * Facilitates the creation of a Spring cron expression that triggers recurrent runs
     * once every x hours, minutes and seconds.
     */
    public static class SpringRecurrentCronExpressionBuilder {
        private int everyXHours = 0;
        private int everyXMinutes = 0;
        private int everyXSeconds = 0;

        /**
         * Specifies the amount of hours that have to pass between two consecutive ticks.
         */
        public SpringRecurrentCronExpressionBuilder everyXHours(int x) {
            this.everyXHours = x;
            return this;
        }

        /**
         * Specifies the amount of minutes that have to pass between two consecutive ticks.
         */
        public SpringRecurrentCronExpressionBuilder everyXMinutes(int x) {
            this.everyXMinutes = x;
            return this;
        }

        /**
         * Specifies the amount of seconds that have to pass between two consecutive ticks.
         */
        public SpringRecurrentCronExpressionBuilder everyXSeconds(int x) {
            this.everyXSeconds = x;
            return this;
        }

        public String build() {
            return "*/" + everyXHours + " */" + everyXMinutes + " */" + everyXSeconds + " ? * *";
        }
    }
}
