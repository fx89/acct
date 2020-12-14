import { MonthlyExpensesReportItem } from './monthly-expenses-report-item';

export class MonthlyExpensesReportCategory {
    public categoryId : Number;
    public totalExpenses : Number;
    public items : MonthlyExpensesReportItem[];
}