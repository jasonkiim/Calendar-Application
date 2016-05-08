package Calendar;
public class Months
{
    protected String monthname;
    private int month;
    private int monthdays;
    private int firstday;

    public Months (String monthname, int month, int monthdays, int firstday)
    {
	this.monthname = monthname;
	this.month = month;
	this.monthdays = monthdays;
	this.firstday = firstday;
    }

    //returns the string value of the current month
    public String getMonth ()
    {
	return monthname;
    }

    //sets the string value of the current month
    public void setMonth (String monthname)
    {
	this.monthname = monthname;
    }

    //returns the integer value of the month
    public int getMonthnum ()
    {
	return month;
    }

    //sets the integer value of the month
    public void setMonth (int month)
    {
	this.month = month;
    }

    //gets the number of days for each month
    public int getMonthdays ()
    {
	return monthdays;
    }

    //sets the number of days for each month
    public void setMonthdays (int monthdays)
    {
	this.monthdays = monthdays;
    }

    //returns the value of the first day
    //which determines what box the calendar should
    //start on
    public int getFirstday ()
    {
	return firstday;
    }

    //sets the value of the first day
    //which determines what box the calendar should
    //start on
    public void setFirstday (int firstday)
    {
	this.firstday = firstday;
    }
}




