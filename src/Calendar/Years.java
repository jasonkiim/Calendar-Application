package Calendar;
public class Years
{
    private int years;
    //Months have to be public to be used by years; cannot be used when it's set to private nor protected
    public Months months[][];
    private Years nextYear;
    public Years (int years, Months months[][])
    {
	this.months = months;
	this.years = years;
	nextYear = null;
    }

    //returns the value of the next year
    public Years getNext ()
    {
	return nextYear;
    }

    //sets the value of the next year
    public void setNext (Years nextYear)
    {
	this.nextYear = nextYear;
    }

    //returns the value of the current year
    public int getYear ()
    {
	return years;
    }

    //sets the value of the current year
    public void setYear (int year)
    {
	this.years = year;
    }

}
