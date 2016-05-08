package Users;
import java.io.*;
import hsa.Console;
//import java.nio.file.Files;

public class BinFile
{
    static Console c;
    public void Create (LinkList list) throws IOException
    {     
	User current = list.getTop ();

	RandomAccessFile raf = new RandomAccessFile ("C:\\Users\\Jason\\Desktop\\CompSciCulm\\Logins\\Data.bin", "rw");

	byte[] nameBytes = new byte [20];
	byte[] passBytes = new byte [20];

	while (current != null)
	{
	    current.getUName ().getBytes (0, current.getUName ().length (), nameBytes, 0);
	    current.getPass ().getBytes (0, current.getPass ().length (), passBytes, 0);

	    raf.write (nameBytes);
	    raf.write (passBytes);

	    nameBytes = new byte [20];
	    passBytes = new byte [20];

	    current = current.getNext ();
	}

	raf.close ();


    }


    public void Login (User lpr) throws IOException
    {
	File lastlogin = new File ("C:\\Users\\Jason\\Desktop\\CompSciCulm\\Logins\\LastLogin.bin");

	try
	{
	    lastlogin.delete ();
	}
	catch (Exception e)
	{
	}


	User current = lpr;

	RandomAccessFile raf = new RandomAccessFile ("C:\\Users\\Jason\\Desktop\\CompSciCulm\\Logins\\LastLogin.bin", "rw");

	byte[] nameBytes = new byte [20];


	current.getUName ().getBytes (0, current.getUName ().length (), nameBytes, 0);


	raf.write (nameBytes);


	nameBytes = new byte [20];


	raf.close ();


    }
}
