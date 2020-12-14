import { MonthlyExpensesReportCategory } from './monthly-expenses-report-category';

export class MonthlyExpensesReportMonth {
    public monthNumber : Number;
    public totalExpenses : Number;
    public categories : MonthlyExpensesReportCategory[];
}