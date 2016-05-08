package Calendar;
//method used to link all the years
public class LinkYears
{
    protected Years top;
    //the constructor of this method, top is currently null
    public LinkYears ()
    {
	top = null;
    }

    //adds the next year to the current year
    public void addNode (Years lpr)
    {
	if (top == null)
	{
	    top = lpr;
	}
	else
	{
	    Years current = null;
	    current = top;

	    while (current.getNext () != null)
	    {
		current = current.getNext ();
	    }
	    current.setNext (lpr);

	}
    }
    //gets the node that contains the integer value of year
    public Years getNode (int year)
    {
	if (top == null)
	{
	    return null;
	    }
	else
	{
	    Years current;
	    current = top;

	    while (current.getNext () != null)
	    {
		if (current.getYear() == year)
		{
		    return current;
		}
		current = current.getNext ();
	    }

	    if (current.getNext () == null && year == current.getYear())
	    {
		return current;
	    }
	    return null;
	}
    }
    
    //show the list of the years
    public void showList ()
    {
	if (top == null)
	{
	    System.out.println ("Empty list ");
	}


	else
	{
	    System.out.println ("The current list:");

	    Years current;
	    current = top;

	    while (current.getNext () != null)
	    {
		System.out.println (current.getYear ());
		current = current.getNext ();
	    }

	    System.out.println (current.getYear ());
	    System.out.println ("*******");

	}
    }
}

