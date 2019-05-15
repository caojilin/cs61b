
public class GregorianDate extends Date {

    private static final int[] MONTH_LENGTHS = { 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31
    };

    public GregorianDate(int year, int month, int dayOfMonth) {
        super(year, month, dayOfMonth);
    }

    @Override
    public Date nextDate() {
        int newDay = dayOfMonth, newMonth = month, newYear = year;

        if (dayOfMonth == 31 && month == 12) {
            newYear = year + 1;
            newDay = 1;
            newMonth = 1;
        } else if (month == 2 && dayOfMonth == 28) {
            newDay = 1;
            newMonth = month + 1;
        } else if (dayOfMonth == 31 && (month == 1 || month == 3 || month == 5
                || month == 7 || month == 8 || month == 10)) {
            newDay = 1;
            newMonth = month + 1;
        } else if (dayOfMonth == 30 && (month == 4 || month == 6 || month == 9 || month == 11)) {
            newDay = 1;
            newMonth = month + 1;
        } else {
            newDay += 1;
        }
        return new GregorianDate(newYear, newMonth, newDay);
    }


    @Override
    public int dayOfYear() {
        int precedingMonthDays = 0;
        for (int m = 1; m < month; m += 1) {
            precedingMonthDays += getMonthLength(m);
        }
        return precedingMonthDays + dayOfMonth;
    }

    private static int getMonthLength(int m) {
        return MONTH_LENGTHS[m - 1];
    }

    public static void main(String[] args) {
        GregorianDate d = new GregorianDate(2018, 12, 31);
        System.out.printf("" + d.nextDate());
        //System.out.println(d);
    }
}
