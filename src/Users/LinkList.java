package Users;
public class LinkList
{
    protected User top;

    // start a null list

    public LinkList ()
    {
	top = null;
    }


    // add a User object to the end of the list

    public void addNode (User lpr)
    {
	if (top == null)
	{
	    top = lpr;
	}
	else
	{
	    User current = null;
	    current = top;
	    
	    while (current.getNext () != null)
	    {
		current = current.getNext ();
	    }
	    current.setNext (lpr);
	}
    }

    
    public User getTop()
    {
	return top;
    }
    

    // display the linked list

    public void showList ()
    {
	if (top == null)
	{
	    System.out.println ("Empty list ");
	}
	else
	{
	    System.out.println ("The current list:");

	    User current;
	    current = top;

	    while (current.getNext () != null)
	    {
		System.out.println (current.getUName () + " " + current.getPass());
		current = current.getNext ();
	    }
	    System.out.println (current.getUName () + " " + current.getPass ());
	    System.out.println ("*******");
	}
    }
    
    public User getNode(String name)
    {
	if (top == null)
	{
	    return null;
	}
	else
	{
	    User current;
	    current = top;

	    while (current.getNext () != null)
	    {
			if(name.equals(current.getUName()))
			{
				return current;
			}
			
		current = current.getNext ();
	    }
	    if(current.getNext()==null&&name.equals(current.getUName()))
		return  current;
	    return null;            
	}       
    }
    
    public void deleteNode(String name)
    {
	User current;
	User prev;
	
	if(top.getUName().equals(name))
	{
		top = top.getNext();
	}
	prev = top;
	    current = top.getNext();
	    
	    
	while(current.getNext() != null)
	{
		if(name.equals(current.getUName()))
			{
			prev.setNext(current.getNext());
			}
		prev = prev.getNext();
		current=current.getNext();
		
	}
	if( current.getNext()== null && current.getUName().equals(name))
	{
		prev.setNext(null);
	}
	
    
    }
}



