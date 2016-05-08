package Users;
// The "User" class.
public class User
{
    private String UserName,Password;
    private User nextUser;

    // Constructor.
    public User (String Un, String Pas)
    {
	this.UserName = Un;
	this.Password = Pas;
	nextUser = null;

    } // EmployeeRecord constructor


    // These methods are used to manipulate a linked list

    public User getNext ()
    {
	return nextUser;
    }


    public void setNext (User nextEmployeeRecord)
    {
	this.nextUser= nextEmployeeRecord;
    }




    // Method to change name.
    public void setUName (String newName)
    {
	UserName = newName;
    } // setName method


    // Method to get name.
    public String getUName ()
    {
	return UserName;
    } // getName method


    // Method to change Password.
    public void setPass (String newPas)
    {
	Password = newPas;
    } // setPassword method


    // Method to get Password.
    public String getPass ()
    {
	return Password;
    } // getPass method
    
    

} /* User class */
