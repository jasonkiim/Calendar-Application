// The "CulminatingMain" class.
import java.awt.*;
import java.nio.channels.*;
//import java.nio.file.*;
import hsa.Console;
import Users.User;
import Users.BinFile;
import Users.LinkList;
import Calendar.Years;
import Calendar.Months;
import Calendar.LinkYears;
import Events.GetEvents;
import Events.EventOptions;
import java.text.*;
import java.util.*;
import java.io.*;
import java.awt.Color;

public class CulminatingMain
{
    static Console c; // The output console

    public static void main (String[] args) throws IOException, InterruptedException
    {
	BufferedReader frr = new BufferedReader (new FileReader ("Months.txt")); 
	// Connecting to text file Months, which has all the months, in it's int & string value;
	//As well as the number of days for each month
	BufferedReader fr = new BufferedReader (new FileReader ("Years.txt")); 
	// Connecting to text file Years, which has a list of years the user has access to
	BufferedReader fd = new BufferedReader (new FileReader ("FirstDay.txt"));
	// Connecting to text file FirstDay, which contains all the number that determine
	//On what box the calendar starts

	
	c = new Console (40,150);
	char option = ' '; // this char will determine what the user will press
	// in the main screen
	User ID; // this helps to make a user
	String month; // String value of month
	Months months[] [] = new Months [5] [12]; // Two dimensional array for 5
	// years and each months
	// associated with each year
	int intvaluemonth; // integer value of the month
	int determinenumofdays = 0; // The numofdays is initially set as 0 days
	int determinefirstday = 0; // The firstday is initially set as 0 day
	Years yearss; // This variable is needed to determine what year it is
	// and link it with years
	int year; // Integer value of year
	boolean checkleapyear = false;
	BinFile bf = new BinFile ();
	LinkYears years = new LinkYears (); // LinkYears

	int yearvalue = 0; //The yearvalue of the calendar
	int monthvalue = 0;
	int dayvalue = 0;
	int firstday = 0;
	int numofdays = 0;
	Color BackGround = new Color (0, 191, 191);
	Font notee = new Font ("Comic Sans MS", Font.PLAIN, 20);
	


	// Determines all the years, months and link each year with the one
	// following itFr
	// The outermost for loop goes until it reaches 5(months.length)
	for (int a = 0 ; a < months.length ; a++)
	{
	    frr = new BufferedReader (new FileReader ("Months.txt")); // Calls
	    // Months
	    year = Integer.parseInt (fr.readLine ()); // Takes in the integers
	    // listed in Years.txt
	    if (year % 4 == 0) // Boolean leap year becomes true every 4 years
	    {
		checkleapyear = true;
	    }

	    // The innermost for loop goes until it reaches 12 (12 months in a
	    // year)
	    for (int b = 0 ; b < months [a].length ; b++)
	    {
		// All variables are declared from Months.txt and FirstDay.txt
		month = frr.readLine ();
		intvaluemonth = Integer.parseInt (frr.readLine ());
		determinenumofdays = Integer.parseInt (frr.readLine ());
		determinefirstday = Integer.parseInt (fd.readLine ());
		// If it's February and leap year is true, then make the number
		// of days 29 instead of 28
		if (intvaluemonth == 2 && checkleapyear == true)
		{
		    determinenumofdays = 29;
		}
		// Declare months [a][b] a being the year and b being the months
		// associated with each year
		months [a] [b] = new Months (month, intvaluemonth,
			determinenumofdays, determinefirstday);
		// If b goes through 12 times, then close the textfile and open
		// it again in the beginning
		if (b == 11)
		{
		    frr.close ();
		}
	    }
	    // link each year.
	    yearss = new Years (year, months);
	    years.addNode (yearss);
	    // Make leapyear equal false
	    checkleapyear = false;
	}

	// Calling the mainscreen
	MainScreen ();

	// Error checking to make sure that the user only presses 'Enter' or
	// 'Space' in the Mainscreen
	do
	{

	    option = c.getChar ();

	    if (option == ((char) 10)) // if the user presses 'Enter', then
		// proceed on to the login screen
		{
		    ID = login ();
		    c.clear ();
		    bf.Login (ID);
		    break;
		    // connect to calendar
		}
	    else if (option == ((char) 32))   // if the user presses 'Space',
		// then proceed on to the signup
		// screen
		{
		    ID = create ();
		    c.clear ();
		    bf.Login (ID);
		    break;
		    // connect to calendar
		}
	}
	while (option != ((char) 10) && option != ((char) 32));
    boolean played = false;

	// infinite loop
	for (;;)
	{
	    PresstoContinue();

		if (c.isCharAvail())
		{
	    int[] [] MonthMap =  // Month Map
		{{1, 2, 3, 4}, {5, 6, 7, 8}, {9, 10, 11, 12}};

	    int[] [] Layout =  // Create the Calendar Map
		{{0, 0, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 0, 0, 0},
		    {0, 0, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 0, 0, 0},
		    {0, 0, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 0, 0, 0}};
	    // The x,y coordinates are initial coordinates of the Year Map
	    int x = 100;
	    int y = 100;

	    // The x2,y2 coordinates are initial coordinates of the Month Map
	    int x2 = 0;
	    int y2 = 0;

	    // The x3,y3 coordinates are initial coordinates of the Calendar Map
	    int x3 = 0;
	    int y3 = 0;

	    if (monthvalue == 0 && dayvalue == 0) // This is to initially launch
		// the calendar without any
		// backspace
		{
		    YearMap (x, y, years.getNode (2015).getYear (), years
			    .getNode (2016).getYear (),
			    years.getNode (2017).getYear (), years.getNode (2018)
			    .getYear (), years.getNode (2019).getYear ());
		    yearvalue = YearMapMove (x, y);
		    Monthmap (MonthMap, yearvalue);
		    monthvalue = MonthMapmove (MonthMap, x2, y2);
		}

	    // There are two different situations that can happen when the user
	    // presses 'Backspace'

	    else if (monthvalue == 5000) // If the user presses backspace in the
		// Months screen, they go back to
		// the Years screen
		{
		    YearMap (x, y, years.getNode (2015).getYear (), years
			    .getNode (2016).getYear (),
			    years.getNode (2017).getYear (), years.getNode (2018)
			    .getYear (), years.getNode (2019).getYear ());
		    yearvalue = YearMapMove (x, y);
		    Monthmap (MonthMap, yearvalue);
		    monthvalue = MonthMapmove (MonthMap, x2, y2);

		}
	    else if (dayvalue == 5000)   // If the user presses backspace in the
		// Calendar screen, they go back to
		// the Months screen
		{ // The way this works is that when the backspace is pressed, the
		    // calendar move method returns it's position value as 5000
		    Monthmap (MonthMap, yearvalue);
		    monthvalue = MonthMapmove (MonthMap, x2, y2);
		}

	    if (monthvalue == 5000) // This is so that when the backspace is
		// pressed in the Months sreen, it goes to
		// the beginning of the for loop to
		// determine which situation to load
		{
		    c.clear ();
		    
		    continue;
		}
	    else
	    {
		c.clear ();
		// Int value firstday becomes set based on what year and what
		// month it is
		firstday = years.getNode (yearvalue).months [yearvalue - 2015] [monthvalue - 1]
		    .getFirstday ();
		// These following if statements are used to determine where the
		// cursor should lie for each month
		if (firstday == 0)
		{
		    x3 = 0;
		}
		else if (firstday == 1)
		{
		    x3 = 100;
		}
		else if (firstday == 2)
		{
		    x3 = 200;
		}
		else if (firstday == 3)
		{
		    x3 = 300;
		}
		else if (firstday == 4)
		{
		    x3 = 400;
		}
		else if (firstday == 5)
		{
		    x3 = 500;
		}
		else if (firstday == 6)
		{
		    x3 = 600;
		}
		else if (firstday == 7)
		{
		    x3 = 0;
		    y3 = 100;
		}
		// Calling the calendar map and the move for calendar. The
		// calendar is created based on the two dimensional array
		// Created to initialize the year, month, the first day and it's
		// number of days. Each month's calendar is modified through
		// calendarmodify method
		numofdays = years.getNode (yearvalue).months [yearvalue - 2015] [monthvalue - 1]
		    .getMonthdays ();
		CalendarModify (
			yearvalue,
			monthvalue,
			Layout,
			firstday,
			years.getNode (yearvalue).months [yearvalue - 2015] [monthvalue - 1]
			.getMonthdays ());
			CalendarMaker (Layout, yearvalue, monthvalue);
			dayvalue = CalendarMove (Layout, x3, y3, yearvalue, monthvalue);
			if (dayvalue == 5000) // This is so that when the backspace is
		    // pressed in the Months sreen, it goes
		    // to the beginning of the for loop to
		    // determine which situation to load
		    {
			continue;
		    }

		    char event = EventsMain (yearvalue, monthvalue, dayvalue);
	    }
	//boolean played;

		}
		else
		{
			DateFormat df = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");//formats the date to be read from the system
	    Calendar calobj = Calendar.getInstance();//creates the object from the system
	    //System.out.println(df.format(calobj.getTime()));
	    String date = df.format(calobj.getTime());//creates a string of the date format
	    String name = getLastLogin();
	    String desc;
	    int eHour, eMin;
	    Font titlee = new Font ("Comic Sans MS", Font.PLAIN, 20);
		Font bankgothic2 = new Font ("Berlin Sans FB Demi", Font.BOLD, 50);
		Font bankgothic3 = new Font ("Berlin Sans FB Demi", Font.PLAIN, 30);
		Font title = new Font ("Berlin Sans FB Demi", Font.PLAIN, 20);
	    Font writeit = new Font ("Comic Sans MS", Font.PLAIN, 20);
	    
	    int actualday, actualmonth, actualyear, hour, min;
	    
	    actualday = Integer.parseInt(date.substring(0, 2));//creates ints for the date it just read
	    actualmonth = Integer.parseInt(date.substring(3, 5));
	    actualyear = Integer.parseInt(date.substring(6, 10));
	    
	    hour = Integer.parseInt(date.substring(11, 13));
	    min = Integer.parseInt(date.substring(14, 16));
	    
	    GetEvents ge = new GetEvents(actualyear,actualmonth,actualday);
	    
	    boolean appt = false, birth = false, hol = false, travel = false;
	    
	    appt = ge.getAppt();//checks which folder has events in them to save on memory later
	    birth = ge.getBirth();
	    hol = ge.getHol();
	    travel = ge.getTravel();
	    
	    
	    if(appt == true)//if theres a file in the appointment folder then read them
	    {
		    File Folder = new File("C:\\Users\\Jason\\Desktop\\CompSciCulm\\User\\" + name + "\\" + actualyear + "\\" + actualmonth
				    + "\\" + actualday+"\\Appointments");
		    File[] FolderList = Folder.listFiles();//file array for list of files in the folder
		    File Chosen = new File("");
		    String Stype = "";
		    String event = "", type = "";

		    for (int i = 0; i < FolderList.length; i++)//loops for amount of files in folder 
		    {

			    if (FolderList[i].isFile())//if the file being read is a type of file then
			    {

				    RandomAccessFile raf = new RandomAccessFile("C:\\Users\\Jason\\Desktop\\CompSciCulm\\User\\" + name + "\\" + actualyear + "\\" + actualmonth
						    + "\\" + actualday+"\\Appointments\\"+FolderList[i].getName(),"rw");
				    raf.seek(0);
				    eHour = raf.readInt();//reads the info
				    eMin = raf.readInt();
				    
				    byte[] nameBytes = new byte[50];
				    raf.read(nameBytes);
				    desc = new String(nameBytes,0);
				    desc = desc.trim();
				    
				    raf.close();
				    
				    if((eHour==hour)&&(eMin==min))//the system time = the event time then
				    {
					    EventOptions eo = new EventOptions(name,FolderList[i].getName(),"Appointments",actualyear,actualmonth,actualday);
					    c.clear();
					    //graphics to show the user their event on the right time

						 // Title Box
					    int x2 = 160;
					    int y2 = 350;
					    // Hour Box
					    int x3 = 160;
					    int y3 = 400;
					    // Minute box
					    int x4 = 180;
					    int y4 = 450;
					    // Description box
					    int x5 = 220;
					    int y5 = 500;
					    c.setColor (BackGround);
					    c.fillRect(0,0,1000,1000);
					    c.setColor (Color.lightGray);
					    c.fillRect (80, 150, 680, 480);

					    c.setFont (bankgothic2);
					    c.setColor (Color.yellow);
					    c.drawString ("View the Event", 240, 220);

					    c.setColor (Color.black);
					    c.setFont (titlee);
					    c.drawString ("Title: ", 100, 370);
					    c.setColor (Color.white);
					    c.fillRect (x2, y2, 300, 30);

					    c.setColor (Color.black);
					    c.setFont (titlee);
					    c.drawString ("Hour: ", 100, 420);
					    c.setColor (Color.white);
					    c.fillRect (x3, y3, 300, 30);

					    c.setColor (Color.black);
					    c.setFont (titlee);
					    c.drawString ("Minute: ", 100, 470);
					    c.setColor (Color.white);
					    c.fillRect (x4, y4, 300, 30);

					    c.setColor (Color.black);
					    c.setFont (titlee);
					    c.drawString ("Description: ", 100, 520);
					    c.setColor (Color.white);
					    c.fillRect (x5, y5, 520, 30);

					    c.setFont (writeit);
					    // c.println((eo.toString()));
					    c.setColor (Color.black);
					    c.drawString (eo.getEvent (), x2 + 10, y2 + 20);
					    c.drawString ((eo.getHour () + ""), x3 + 10, y3 + 20);
					    c.drawString ((eo.getMin () + ""), x4 + 10, y4 + 20);
					    c.drawString (eo.getDesc (), x5 + 10, y5 + 20);    
					    
											c.setFont (notee);
					    c.setColor (Color.blue);
					    c.drawString ("Press Anything to continue!",100, 600);
					    Thread play = new Thread(new Audio());//thread for playing sounds
					    Thread end = new Thread(new Terminate());//thread to end play-back
					    
					    play.start();//start playing thread
					    end.start();//start the thread to end playback
					    
					    while(true)
					    {
						    if(!end.isAlive())//once the user chooses to end the music the end thread is dead so we can end play back
						    {
							File abc = new File ("C:\\Users\\Jason\\Desktop\\CompSciCulm\\User\\" + name + "\\" + actualyear + "\\" + actualmonth
								+ "\\" + actualday+"\\Appointments\\"+FolderList[i].getName());
							    played = true;//boolean to say that its been played
							    Audio a = new Audio();
							    a.Terminate();//kill the playback
							    new FileOutputStream (abc).close ();//clears the file for deletion
							    abc.delete ();//deletes the file
							    break;
						    }
						    
					    }
					    if (played == true)
					    {
							break;
					    }
				    }
				    if (played == true)
				    {
						break;
				    }
			    }
			    if (played == true)
			    {
					break;
			    }

		    }

	    }
	    if(birth == true)
	    {
		    File Folder = new File("C:\\Users\\Jason\\Desktop\\CompSciCulm\\User\\" + name + "\\" + actualyear + "\\" + actualmonth
				    + "\\" + actualday+"\\Birthdays");
		    File[] FolderList = Folder.listFiles();//file array for list of files in the folder
		    File Chosen = new File("");
		    String Stype = "";
		    String event = "", type = "";

		    for (int i = 0; i < FolderList.length; i++)//loops for amount of files in folder 
		    {

			    if (FolderList[i].isFile()) {

				    RandomAccessFile raf = new RandomAccessFile("C:\\Users\\Jason\\Desktop\\CompSciCulm\\User\\" + name + "\\" + actualyear + "\\" + actualmonth
						    + "\\" + actualday+"\\Birthdays\\"+FolderList[i].getName(),"rw");
				    eHour = raf.readInt();//reads event info
				    eMin = raf.readInt();
				    
				    byte[] nameBytes = new byte[50];
				    raf.read(nameBytes);
				    desc = new String(nameBytes,0);
				    desc = desc.trim();
				    
				    if((eHour==hour)&&(eMin==min))
				    {
					    EventOptions eo = new EventOptions(name,FolderList[i].getName(),"Birthdays",actualyear,actualmonth,actualday);

					    //graphics to show the user their event on the right time
						 // Title Box
					    int x2 = 160;
					    int y2 = 350;
					    // Hour Box
					    int x3 = 160;
					    int y3 = 400;
					    // Minute box
					    int x4 = 180;
					    int y4 = 450;
					    // Description box
					    int x5 = 220;
					    int y5 = 500;

					    c.setColor (BackGround);
					    c.fillRect(0,0,1000,1000);
					    c.setColor (Color.lightGray);
					    c.fillRect (80, 150, 680, 480);

					    c.setFont (bankgothic2);
					    c.setColor (Color.yellow);
					    c.drawString ("View the Event", 240, 220);

					    c.setColor (Color.black);
					    c.setFont (titlee);
					    c.drawString ("Title: ", 100, 370);
					    c.setColor (Color.white);
					    c.fillRect (x2, y2, 300, 30);

					    c.setColor (Color.black);
					    c.setFont (titlee);
					    c.drawString ("Hour: ", 100, 420);
					    c.setColor (Color.white);
					    c.fillRect (x3, y3, 300, 30);

					    c.setColor (Color.black);
					    c.setFont (titlee);
					    c.drawString ("Minute: ", 100, 470);
					    c.setColor (Color.white);
					    c.fillRect (x4, y4, 300, 30);

					    c.setColor (Color.black);
					    c.setFont (titlee);
					    c.drawString ("Description: ", 100, 520);
					    c.setColor (Color.white);
					    c.fillRect (x5, y5, 520, 30);

					    c.setFont (writeit);
					    c.setColor (Color.black);
					    c.drawString (eo.getEvent (), x2 + 10, y2 + 20);
					    c.drawString ((eo.getHour () + ""), x3 + 10, y3 + 20);
					    c.drawString ((eo.getMin () + ""), x4 + 10, y4 + 20);
					    c.drawString (eo.getDesc (), x5 + 10, y5 + 20);   
					    
											c.setFont (notee);
					    c.setColor (Color.blue);
					    c.drawString ("Press Anything to continue!",100, 600);

					    Thread play = new Thread(new Audio());//thread for playing sounds
					    Thread end = new Thread(new Terminate());//thread to end play-back
					    
					    play.start();//start playing thread
					    end.start();//start the thread to end playback
					    
					    while(true)
					    {
						    if(!end.isAlive())//once the user chooses to end the music the end thread is dead so we can end play back
						    {
								File abc = new File ("C:\\Users\\Jason\\Desktop\\CompSciCulm\\User\\" + name + "\\" + actualyear + "\\" + actualmonth
						    + "\\" + actualday+"\\Birthdays\\"+FolderList[i].getName());
							    played = true;//boolean to check if its been played
							    Audio a = new Audio();
							    a.Terminate();//kill playback
							    new FileOutputStream (abc).close ();//clears file for deletion
							    abc.delete ();//deletes file
							    break;
						    }
						    
					    }
					    if (played == true)
					    {
							break;
					    }
				    }
				    if (played == true)
				    {
						break;
				    }
			    }
			    if (played == true)
			    {
					break;
			    }
		    }
	    }
	    if(hol == true)
	    {
		    File Folder = new File("C:\\Users\\Jason\\Desktop\\CompSciCulm\\User\\" + name + "\\" + actualyear + "\\" + actualmonth
				    + "\\" + actualday+"\\Holiday");
		    File[] FolderList = Folder.listFiles();//file array for list of files in the folder
		    File Chosen = new File("");
		    String Stype = "";
		    String event = "", type = "";

		    for (int i = 0; i < FolderList.length; i++)//loops for amount of files in folder 
		    {

			    if (FolderList[i].isFile()) {

				    RandomAccessFile raf = new RandomAccessFile("C:\\Users\\Jason\\Desktop\\CompSciCulm\\User\\" + name + "\\" + actualyear + "\\" + actualmonth
						    + "\\" + actualday+"\\Holiday\\"+FolderList[i].getName(),"rw");
				    eHour = raf.readInt();//reads event info
				    eMin = raf.readInt();
				    
				    byte[] nameBytes = new byte[50];
				    raf.read(nameBytes);
				    desc = new String(nameBytes,0);
				    desc = desc.trim();
				    
				    if((eHour==hour)&&(eMin==min))//if event time = real time then
				    {
					    EventOptions eo = new EventOptions(name,FolderList[i].getName(),"Holiday",actualyear,actualmonth,actualday);

					    //graphics to show the user their event on the right time

						 // Title Box
					    int x2 = 160;
					    int y2 = 350;
					    // Hour Box
					    int x3 = 160;
					    int y3 = 400;
					    // Minute box
					    int x4 = 180;
					    int y4 = 450;
					    // Description box
					    int x5 = 220;
					    int y5 = 500;

					    c.setColor (BackGround);
					    c.fillRect(0,0,1000,1000);
					    c.setColor (Color.lightGray);
					    c.fillRect (80, 150, 680, 480);

					    c.setFont (bankgothic2);
					    c.setColor (Color.yellow);
					    c.drawString ("View the Event", 240, 220);

					    c.setColor (Color.black);
					    c.setFont (titlee);
					    c.drawString ("Title: ", 100, 370);
					    c.setColor (Color.white);
					    c.fillRect (x2, y2, 300, 30);

					    c.setColor (Color.black);
					    c.setFont (titlee);
					    c.drawString ("Hour: ", 100, 420);
					    c.setColor (Color.white);
					    c.fillRect (x3, y3, 300, 30);

					    c.setColor (Color.black);
					    c.setFont (titlee);
					    c.drawString ("Minute: ", 100, 470);
					    c.setColor (Color.white);
					    c.fillRect (x4, y4, 300, 30);

					    c.setColor (Color.black);
					    c.setFont (titlee);
					    c.drawString ("Description: ", 100, 520);
					    c.setColor (Color.white);
					    c.fillRect (x5, y5, 520, 30);

					    c.setFont (writeit);
					    c.setColor (Color.black);
					    c.drawString (eo.getEvent (), x2 + 10, y2 + 20);
					    c.drawString ((eo.getHour () + ""), x3 + 10, y3 + 20);
					    c.drawString ((eo.getMin () + ""), x4 + 10, y4 + 20);
					    c.drawString (eo.getDesc (), x5 + 10, y5 + 20); 
					    
											c.setFont (notee);
					    c.setColor (Color.blue);
					    c.drawString ("Press Anything to continue!",100, 600);
					    Thread play = new Thread(new Audio());//thread for playing sounds
					    Thread end = new Thread();//thread to end play-back
					    
					    play.start();//start playing thread
					    end.start();//start the thread to end playback
					    
					    while(true)
					    {
						    if(!end.isAlive())//once the user chooses to end the music the end thread is dead so we can end play back
						    {
							File abc = new File ("C:\\Users\\Jason\\Desktop\\CompSciCulm\\User\\" + name + "\\" + actualyear + "\\" + actualmonth
								+ "\\" + actualday+"\\Holiday\\"+FolderList[i].getName());
							    played = true;//boolean to check if its been played
							    Audio a = new Audio();
							    a.Terminate();//kill playback
							    new FileOutputStream (abc).close ();//clear file for deletion
							    abc.delete ();//delete file
							    break;
						    }
						    
					    }
					    if (played == true)
					    {
							break;
					    }
				    }
				    if (played == true)
				    {
						break;
				    }
			    }
			    if (played == true)
			    {
					break;
			    }
		    }
	    }
	    if(travel == true)
	    {
		    File Folder = new File("C:\\Users\\Jason\\Desktop\\CompSciCulm\\User\\" + name + "\\" + actualyear + "\\" + actualmonth
				    + "\\" + actualday+"\\Travel");
		    File[] FolderList = Folder.listFiles();//file array for list of files in the folder
		    File Chosen = new File("");
		    String Stype = "";
		    String event = "", type = "";

		    for (int i = 0; i < FolderList.length; i++)//loops for amount of files in folder 
		    {

			    if (FolderList[i].isFile()) {

				    RandomAccessFile raf = new RandomAccessFile("C:\\Users\\Jason\\Desktop\\CompSciCulm\\User\\" + name + "\\" + actualyear + "\\" + actualmonth
						    + "\\" + actualday+"\\Travel\\"+FolderList[i].getName(),"rw");
				    eHour = raf.readInt();//read info for event
				    eMin = raf.readInt();
				    
				    byte[] nameBytes = new byte[50];
				    raf.read(nameBytes);
				    desc = new String(nameBytes,0);
				    desc = desc.trim();
				    
				    if((eHour==hour)&&(eMin==min))//if event time = real time
				    {
					    EventOptions eo = new EventOptions(name,FolderList[i].getName(),"Travel",actualyear,actualmonth,actualday);

					    //graphics to show the user their event on the right time

						 // Title Box
					    int x2 = 160;
					    int y2 = 350;
					    // Hour Box
					    int x3 = 160;
					    int y3 = 400;
					    // Minute box
					    int x4 = 180;
					    int y4 = 450;
					    // Description box
					    int x5 = 220;
					    int y5 = 500;

					    c.setColor (BackGround);
					    c.fillRect(0,0,1000,1000);
					    c.setColor (Color.lightGray);
					    c.fillRect (80, 150, 680, 480);

					    c.setFont (bankgothic2);
					    c.setColor (Color.yellow);
					    c.drawString ("View the Event", 240, 220);

					    c.setColor (Color.black);
					    c.setFont (titlee);
					    c.drawString ("Title: ", 100, 370);
					    c.setColor (Color.white);
					    c.fillRect (x2, y2, 300, 30);

					    c.setColor (Color.black);
					    c.setFont (titlee);
					    c.drawString ("Hour: ", 100, 420);
					    c.setColor (Color.white);
					    c.fillRect (x3, y3, 300, 30);

					    c.setColor (Color.black);
					    c.setFont (titlee);
					    c.drawString ("Minute: ", 100, 470);
					    c.setColor (Color.white);
					    c.fillRect (x4, y4, 300, 30);

					    c.setColor (Color.black);
					    c.setFont (titlee);
					    c.drawString ("Description: ", 100, 520);
					    c.setColor (Color.white);
					    c.fillRect (x5, y5, 520, 30);

					    c.setFont (writeit);
					    c.setColor (Color.black);
					    c.drawString (eo.getEvent (), x2 + 10, y2 + 20);
					    c.drawString ((eo.getHour () + ""), x3 + 10, y3 + 20);
					    c.drawString ((eo.getMin () + ""), x4 + 10, y4 + 20);
					    c.drawString (eo.getDesc (), x5 + 10, y5 + 20); 
					    
											c.setFont (notee);
					    c.setColor (Color.blue);
					    c.drawString ("Press Anything to continue!",100, 600);
					    Thread play = new Thread(new Audio());//thread for playing sounds
					    Thread end = new Thread(new Terminate());//thread to end play-back
					    
					    play.start();//start playing thread
					    end.start();//start the thread to end playback
					    
					    while(true)
					    {
						    if(!end.isAlive())//once the user chooses to end the music the end thread is dead so we can end play back
						    {
							File abc = new File ("C:\\Users\\Jason\\Desktop\\CompSciCulm\\User\\" + name + "\\" + actualyear + "\\" + actualmonth
								+ "\\" + actualday+"\\Holiday\\"+FolderList[i].getName());
							    played = true;//boolean to check if its been played
							    Audio a = new Audio();
							    a.Terminate();//kill playback
							    new FileOutputStream (abc).close ();//clear file for deleting
							    abc.delete ();//delete
							    break;
						    }
						    
					    }
					    if (played == true)
					    {
							break;
					    }
				    }
				    if (played == true)
				    {
						break;
				    }
			    }
			    if (played == true)
			    {
					break;
			    }
		    }
	    }
	    
			
			
			
		}
	}

    }

    
    
    
    

    public static User login () throws IOException
    {
	Color BackGround = new Color (0, 191, 191);
	Font bankgothic = new Font ("Berlin Sans FB Demi", Font.BOLD, 90);
	Font impact = new Font ("Comic Sans MS", Font.PLAIN, 30);

	// The x,y are coordinates for the username box
	int x = 250;
	int y = 320;
	// The x2,y2 are coordinates for the password box
	int x2 = 250;
	int y2 = 450;
	c.setFont (bankgothic);
	c.setColor (BackGround);
	c.fillRect (0, 0, 1000, 1000);
	c.setColor (Color.gray);
	c.drawString ("Memory Bound", 100, 180);
	c.setColor (Color.yellow);
	c.drawString ("Memory Bound", 96, 178);
	// creating the boxes for username and password
	c.setColor (Color.white);
	c.fillRect (x, y, 450, 60);
	c.fillRect (x2, y2, 450, 60);

	c.setFont (impact);
	c.setColor (Color.black);
	c.drawString ("Username: ", 97, 360);
	c.drawString ("Password: ", 100, 490);

	// To indicate that the user is initially in the username box
	c.setColor (Color.black);
	c.drawRect (x, y, 450, 60);
	// The loged in boolean is initially set to false
	boolean logedin = false;
	while (logedin == false)
	{
	    // Declare the username by calling in the LoginScreenUsername
	    // method, which returns a sting value of the username the user
	    // inputs
	    String Uname = LoginScreenUsername (x, y);
	    // Once done, then move the cursor into the password box, and
	    // indicate with the drawRect that user is now on the password box
	    c.setColor (Color.white);
	    c.drawRect (x, y, 450, 60);
	    c.setColor (Color.black);
	    c.drawRect (x2, y2, 450, 60);
	    // Declare the password by calling in thhe LoginScreenPassword
	    // method, which returns a sting value of the username the user
	    // inputs
	    String Password = LoginScreenPassword (x2, y2);

	    // The following is to put the username into a binary file
	    RandomAccessFile raf = new RandomAccessFile (
		    "C:\\Users\\Jason\\Desktop\\CompSciCulm\\Logins\\Data.bin",
		    "rw"); // Opens File that has all the login info

	    long numRec = raf.length () / 40; // gets the number of logins

	    LinkList list = new LinkList (); // creating a linked list

	    byte[] nameBytes = new byte [20]; // creates byte arrays for username
	    // and password
	    byte[] passBytes = new byte [20];
	    String name;
	    String password;

	    for (int i = 0 ; i < numRec ; i++) // reads all the logins to a linked
		// list
		{
		    raf.seek (i * 40);
		    raf.read (nameBytes);
		    raf.read (passBytes);
		    name = new String (nameBytes, 0);
		    password = new String (passBytes, 0);

		    User lpr = new User (name.trim (), password.trim ());

		    list.addNode (lpr);

		    name = "";
		    password = "";

		}

	    raf.close (); // closes bin file

	    User loginID = list.getNode (Uname); // gets the info for the
	    // username

	    if (loginID == null) // if the username was not found in our records
	    {
		c.setColor (BackGround);
		c.fillRect (30, 530, 800, 100);
		c.setColor (Color.pink);
		c.drawRect (x, y, 450, 60);
		c.drawRect (x2, y2, 450, 60);
		c.setColor (Color.red);
		c.setFont (impact);
		c.drawString (
			("Sorry, Your file was not found! Please try again"),
			40, 600);
		c.setColor (Color.white);
		c.fillRect (x, y, 450, 60);
		c.fillRect (x2, y2, 450, 60);
		c.setColor (Color.pink);
		c.drawRect (x, y, 450, 60);
		continue;
	    }
	    else
	    {
		if (loginID.getUName ().equals (Uname)) // if username equals the
		    // found file
		    {
			if (loginID.getPass ().equals (Password)) // if password
			    // matches the
			    // username's
			    // password
			    {
				logedin = true;
				return loginID;
			    }
			else   // if the password does not match the password in
			    // file
			    {
				c.setColor (BackGround);
				c.fillRect (30, 530, 800, 100);
				c.setColor (Color.pink);
				c.drawRect (x, y, 450, 60);
				c.drawRect (x2, y2, 450, 60);
				c.setColor (Color.red);
				c.drawString (
					("Your username and/or password does not match"),
					40, 600);
				c.setColor (Color.white);
				c.fillRect (x, y, 450, 60);
				c.fillRect (x2, y2, 450, 60);
				c.setColor (Color.pink);
				c.drawRect (x, y, 450, 60);
				continue;
			    }
		    }
		//Reassurance that the username/password does not match
		else
		{
		    c.setColor (BackGround);
		    c.fillRect (30, 530, 800, 100);
		    c.setColor (Color.pink);
		    c.drawRect (x, y, 450, 60);
		    c.drawRect (x2, y2, 450, 60);
		    c.setColor (Color.red);
		    c.drawString (
			    ("Your username and/or password does not match"),
			    40, 600);
		    c.setColor (Color.white);
		    c.fillRect (x, y, 450, 60);
		    c.fillRect (x2, y2, 450, 60);
		    c.setColor (Color.pink);
		    c.drawRect (x, y, 450, 60);
		    continue;
		}
	    }
	}
	return null;

    }


    // Method to create the main screen
    public static void MainScreen ()
    {
	Color BackGround = new Color (0, 191, 191);
	Font bankgothic = new Font ("Berlin Sans FB Demi", Font.BOLD, 90);
	Font impact = new Font ("Impact", Font.PLAIN, 30);

	c.setFont (bankgothic);
	c.setColor (BackGround);
	c.fillRect (0, 0, 1000, 1000);
	c.setColor (Color.gray);
	c.drawString ("Memory Bound", 100, 180);
	c.setColor (Color.yellow);
	c.drawString ("Memory Bound", 96, 178);
	c.setColor (Color.black);
	c.fillRoundRect (160, 320, 500, 75, 40, 40);
	c.fillRoundRect (160, 440, 500, 75, 40, 40);
	c.setColor (Color.yellow);
	c.fillRoundRect (165, 325, 490, 65, 40, 40);
	c.fillRoundRect (165, 445, 490, 65, 40, 40);
	c.setColor (Color.black);
	c.setFont (impact);
	c.drawString ("Press (Enter) to Log In!", 260, 368);
	c.drawString ("Press (Space) to Sign up!", 260, 488);
    }


    // THE FOLLOWING METHODS ARE FOR USERS PART
    // This method is to call the sign-up screen
    public static User create () throws IOException
    {
	// String name = System.getProperty("user.name");
	RandomAccessFile raf = new RandomAccessFile (
		"C:\\Users\\Jason\\Desktop\\CompSciCulm\\Logins\\Data.bin",
		"rw");

	LinkList list = new LinkList ();

	Color BackGround = new Color (0, 191, 191);
	Color menu = new Color (128, 255, 255);
	Font title = new Font ("Berlin Sans FB Demi", Font.BOLD, 45);
	Font text = new Font ("MV Boli", Font.BOLD, 25);
	Font note = new Font ("MV Boli", Font.BOLD, 20);
	Font alert = new Font ("Comic Sans MS", Font.BOLD, 15);

	// The x,y coordinates of the username box
	int x = 200;
	int y = 180;
	// The x2,y2 coordinates are the box for password
	int x2 = 200;
	int y2 = 280;
	// The x3,y3 coordinates are the box for confirm password box
	int x3 = 200;
	int y3 = 410;
	// The username, password, and confirmpasssword variables are initially
	// set as nothing
	String UserName = "", Pass = "", ConPass = "";
	// Boolean variables to check if the password contains one uppercased
	// letter and one number
	boolean upper = false, num = false;
	// The invalid characters the user cannot have in their username
	boolean bcksl = false, fwdsl = false, colon = false, astericks = false, question = false, quotations = false;
	boolean grtthn = false, lsthn = false, stick = false, restricted = false;
	// Taken is a boolean variable is to see if the username is taken.
	// passconditions is to check the user met all the conditions
	boolean taken = true, PassCondtions = false;
	byte[] nameBytes = new byte [20]; // byte arrays for the username and
	// password
	byte[] passBytes = new byte [20];
	String name;
	String password;
	long numRec = raf.length () / 40; // gets the number of logins

	for (int i = 0 ; i < numRec ; i++) // for the number of logins, read them
	    // to a linked list
	    {
		raf.seek (i * 40);
		raf.read (nameBytes);
		raf.read (passBytes);
		name = new String (nameBytes, 0);
		password = new String (passBytes, 0);

		User lpr = new User (name.trim (), password.trim ());

		list.addNode (lpr);
		c.println (name.trim () + ": " + password.trim ());

		name = "";
		password = "";

	    }

	c.clear ();
	c.setColor (BackGround);
	c.fillRect (0, 0, 1000, 1000);
	c.setColor (menu);
	c.fillRect (50, 150, 730, 450);
	c.setFont (title);
	c.setColor (Color.black);
	c.drawString (("Sign Up for Memory Bound"), 130, 100);
	c.setColor (Color.yellow);
	c.drawString (("Sign Up for Memory Bound"), 132, 100);
	c.setColor (Color.black);
	c.setFont (text);
	c.drawString (("Username: "), 60, 200);
	c.drawString (("Password: "), 60, 300);
	c.drawString (("Confirm "), 60, 400);
	c.drawString (("Password: "), 60, 430);
	c.setFont (alert);
	c.drawString ("(16 char max.)", 460, 200);
	c.drawString ("(16 char max.)", 460, 300);
	c.setColor (Color.blue);
	c.drawString (
		"*Cannot contain special characters. Please use only numbers and letters",
		60, 230);
	c.drawString (
		"*Must contain at least one upper case letter and one number",
		60, 330);
	c.setColor (Color.white);
	c.fillRect (x, y, 250, 30);
	c.fillRect (x2, y2, 250, 30);
	c.fillRect (x3, y3, 250, 30);

	c.setColor (Color.black);
	c.drawRect (x, y, 250, 30);

	while (taken == true || restricted == false)
	{
	    UserName = SignUpScreenUsername (x, y);

	    // This for loop goes through the username to check if it has any
	    // restricted characters
	    for (int i = 0 ; i < UserName.length () ; i++)
	    {
		if (UserName.charAt (i) == 92)
		    bcksl = true;
		if (UserName.charAt (i) == 47)
		    fwdsl = true;
		if (UserName.charAt (i) == 58)
		    colon = true;
		if (UserName.charAt (i) == 42)
		    astericks = true;
		if (UserName.charAt (i) == 63)
		    question = true;
		if (UserName.charAt (i) == 34)
		    quotations = true;
		if (UserName.charAt (i) == 60)
		    lsthn = true;
		if (UserName.charAt (i) == 62)
		    grtthn = true;
		if (UserName.charAt (i) == 124)
		    stick = true;
	    }

	    // if the preceding condition is all met, then the while loop is
	    // closed
	    if (bcksl == false && fwdsl == false && colon == false
		    && astericks == false && question == false
		    && quotations == false && lsthn == false && grtthn == false
		    && stick == false)
	    {
		restricted = true;
	    }
	    // Or else, remind the user that there is a restricted character in
	    // the username
	    else
	    {
		c.setFont (alert);
		c.setColor (Color.red);
		c.drawString (
			"ALERT! Username cannot contain restricted characters!",
			60, 260);
		c.setColor (Color.white);
		c.fillRect (x, y, 250, 30);
		c.setColor (Color.pink);
		c.drawRect (x, y, 250, 30);
		bcksl = false;
		fwdsl = false;
		colon = false;
		astericks = false;
		question = false;
		quotations = false;
		grtthn = false;
		lsthn = false;
		stick = false;
		continue;
	    }
	    // Goes through the file to see if the username is taken
	    User found = list.getNode (UserName);
	    // if it's not found, then taken becomes false and it continues.
	    if (found == null)
	    {
		taken = false;
		c.setColor (Color.blue);
		c.drawRect (x, y, 250, 30);
		continue;
	    }
	    // Or else, it reminds the user that the username is taken and tell
	    // them to try another one.
	    else
	    {
		c.setFont (alert);

		c.setColor (Color.red);
		c.drawString ("This username is taken. Please try another one!",
			60, 260);
		c.setColor (Color.white);
		c.fillRect (x, y, 250, 30);
		c.setColor (Color.pink);
		c.drawRect (x, y, 250, 30);
	    }
	}
	c.setColor (menu);
	c.fillRect (60, 240, 700, 40);
	c.setColor (Color.black);
	c.drawRect (x2, y2, 250, 30);

	// Do while loop for password, while password matches the password
	// entered again
	do
	{
	    do
	    {
		Pass = SignUpScreenPassword (x2, y2);
		for (int i = 0 ; i < Pass.length () ; i++)
		{
		    if (Pass.charAt (i) >= 65 && Pass.charAt (i) <= 90)
		    {
			upper = true;
		    }
		    if (Pass.charAt (i) >= 48 && Pass.charAt (i) <= 57)
		    {
			num = true;
		    }
		}

		if (upper == true && num == true)//all the password conditions are met
		{
		    c.setColor (menu);
		    c.fillRect (60, 340, 650, 40);
		    PassCondtions = true;
		}
		else
		{
		    num = false;
		    upper = false;
		    c.setColor (Color.red);
		    c.setFont (alert);

		    c.drawString (
			    "ALERT! This password does not contain one number and one uppercased letter!",
			    60, 360);
		    c.setColor (Color.white);
		    c.fillRect (x2, y2, 250, 30);
		    c.setColor (Color.pink);
		    c.drawRect (x2, y2, 250, 30);
		    continue;

		}
	    }
	    while (PassCondtions == false);

	    c.setColor (Color.blue);
	    c.drawRect (x2, y2, 250, 30);
	    c.setColor (Color.black);
	    c.drawRect (x3, y3, 250, 30);
	    ConPass = SignUpScreenPassword (x3, y3);
	    // If the password does not match the confirm password, then remake
	    // the password

	    do
	    {
		c.setColor (Color.white);
		c.fillRect (x2, y2, 250, 30);
		c.fillRect (x3, y3, 250, 30);
		c.setColor (Color.pink);
		c.drawRect (x2, y2, 250, 30);
		c.drawRect (x3, y3, 250, 30);
		c.setColor (Color.red);
		c.setFont (alert);

		c.drawString ("ALERT! Password does not match!", 60, 460);
		Pass = SignUpScreenPassword (x2, y2);
		c.setColor (Color.blue);
		c.drawRect (x2, y2, 250, 30);
		c.setColor (Color.black);
		c.drawRect (x3, y3, 250, 30);
		ConPass = SignUpScreenPassword (x3, y3);
	    }
	    while (!Pass.equals (ConPass));

	}
	while (!Pass.equals (ConPass));

	User lpr = new User (UserName, Pass);

	list.addNode (lpr);

	BinFile bf = new BinFile (); // writes the new user name and password to
	// the login records

	bf.Create (list);

	boolean success = (new File (
		    "C:\\Users\\Jason\\Desktop\\CompSciCulm\\User\\"
		    + UserName)).mkdirs ();
	if (!success)
	{
	    c.print ("Error, creation failed.");
	    // Folder creation failed
	}

	File dest = new File (
		"C:\\Users\\Jason\\Desktop\\CompSciCulm\\User\\"
		+ UserName + "\\");
	c.clear ();
	LoadingScreen (); // Due to d
	AddFolders (dest); // creates the file tree

	return lpr;
    }


    private static void AddFolders (File dest) throws IOException
    {
	for (int i = 2015 ; i <= 2019 ; i++)
	    // creates the years folders
	    new File (dest + "\\" + i).mkdirs ();

	for (int y = 2015 ; y <= 2019 ; y++)
	{
	    for (int m = 1 ; m <= 12 ; m++)
	    {
		new File (dest + "\\" + y + "\\" + m).mkdirs (); // creates the
		// months
		// folders
	    }
	}

	for (int y = 2015 ; y <= 2019 ; y++)
	{
	    for (int m = 1 ; m <= 12 ; m++)
	    {
		for (int d = 1 ; d <= 31 ; d++)
		    new File (dest + "\\" + y + "\\" + m + "\\" + d).mkdirs (); // creates
		// the
		// day
		// folders
	    }
	}

	for (int y = 2015 ; y <= 2019 ; y++)
	{
	    for (int m = 1 ; m <= 12 ; m++)
	    {
		for (int d = 1 ; d <= 31 ; d++) // creates the event type folders
		{
		    new File (dest + "\\" + y + "\\" + m + "\\" + d
			    + "\\Appointments").mkdirs ();
		    new File (dest + "\\" + y + "\\" + m + "\\" + d
			    + "\\Birthdays").mkdirs ();
		    new File (dest + "\\" + y + "\\" + m + "\\" + d
			    + "\\Holiday").mkdirs ();
		    new File (dest + "\\" + y + "\\" + m + "\\" + d + "\\Travel")
			.mkdirs ();
		}
	    }
	}
    }


    // In the SignupScreen, this method is used so the user can type in their
    // username. Returns a string value of the username to the main method.
    public static String SignUpScreenUsername (int x, int y)
    {
	Font note = new Font ("Comic Sans MS", Font.PLAIN, 20);
	c.setFont (note);
	// Create two string variables, username being the actual string value
	// of username and prevusername referring to the username with one less
	// character
	String username = "";
	String prevusername = "";
	char usernamechar;
	do
	{
	    c.setColor (Color.black);
	    usernamechar = c.getChar ();
	    // if the char the user presses is backspace
	    if (usernamechar == ((char) 8))
	    {
		// Setting the if statement so that backspace only occurs within
		// the box range
		for (int b = 0 ; b < username.length () - 1 ; b++) // declares
		    // previous
		    // username to
		    // equal one
		    // less
		    // character
		    // than username
		    {
			prevusername = prevusername + username.charAt (b);
		    }
		c.setColor (Color.white); // When user presses backspace, the
		// character originally there is
		// 'erased'
		c.fillRect (x, y, 250, 30);
		c.setColor (Color.black);
		c.drawRect (x, y, 250, 30);
		c.drawString (prevusername, x + 10, y + 20);

		username = prevusername; // username equals prevusername, which
		// is one character less than what's
		// in the username value
		prevusername = ""; // reset the prevusername to equal nothing
		continue;

	    }
	    // if the user presses enter, proceed on to next box
	    if (usernamechar == ((char) 10))
	    {
		break;
	    }
	    // if user presses anything other than backspace or enter, then draw
	    // the character on the box
	    if (usernamechar != ((char) 8) && usernamechar != ((char) 10)
		    && username.length () < 16)
	    {
		username += usernamechar + "";
		c.drawString ("" + username, x + 10, y + 20);
	    }
	}
	while (usernamechar != ((char) 10));
	return (username);
    }


    // In the SignupScreen, this method is used so the user can type in their
    // password. Returns a string value of the password to the main method.
    public static String SignUpScreenPassword (int x2, int y2)
    {
	Font note = new Font ("Comic Sans MS", Font.PLAIN, 20);
	c.setFont (note);
	// Create two string variables, password being the actual string value
	// of password and prevpassword referring to the password with one less
	// character
	String password = "";
	String prevpassword = "";
	// char passwordchar equals the actual character the user inputs
	char passwordchar;
	do
	{
	    c.setColor (Color.black);
	    passwordchar = c.getChar ();
	    // if the char the user presses is backspace
	    if (passwordchar == ((char) 8))
	    {
		// Setting the if statement so that backspace only occurs within
		// the box range
		if (x2 >= 202 && x2 <= 440)
		{
		    for (int b = 0 ; b < password.length () - 1 ; b++) // declares
			// the
			// previous
			// password
			// equal one
			// character
			// less than
			// the
			// actual
			// password
			{
			    prevpassword = prevpassword + password.charAt (b);
			}
		    // 'erase' the character if the user presses backspace
		    c.setColor (Color.white);
		    c.fillRect (x2 - 5, y2 + 9, 15, 15);
		    // move the cursor back
		    x2 -= 15;
		    // make password equal previous password and reset the
		    // previouspassword to nothing
		    password = prevpassword;
		    prevpassword = "";
		    continue;
		}
	    }
	    // if the user presses enter, then break the loop
	    if (passwordchar == ((char) 10))
	    {
		break;
	    }
	    // only display the character user inputs while it's within range
	    // and while user doesn't press enter or backspace
	    if (passwordchar != ((char) 8) && passwordchar != ((char) 10)
		    && x2 <= 430)
	    {
		c.drawString ("*", x2 + 10, y2 + 25);
		password += passwordchar + "";
		x2 += 15;
	    }
	}
	while (passwordchar != ((char) 10));

	return password;

    }

    //This method will be used for username box of the log in screen
    //returns a string value of the username the user inputs on keyboard
    //displays each character on the screen
    public static String LoginScreenUsername (int x, int y)
    {
	Font note = new Font ("Comic Sans MS", Font.PLAIN, 35);
	c.setFont (note);
	String username = "";
	String prevusername = "";
	char usernamechar;
	//do the following until the user presses 'Enter'
	do
	{
	    c.setColor (Color.black);
	    usernamechar = c.getChar ();

	    if (usernamechar == ((char) 8))
	    {
	    //setting a local pointer called previous username which is always
	    //one character lesser than the actual username
		for (int b = 0 ; b < username.length () - 1 ; b++)
		{
		    prevusername = prevusername + username.charAt (b);
		}
		c.setColor (Color.white);
		c.fillRect (x, y, 450, 60);
		c.setColor (Color.black);
		c.drawRect (x, y, 450, 60);
		c.drawString (prevusername, x + 10, y + 40);
		username = prevusername;
		prevusername = "";
		continue;

	    }
	    //break the loop if the user presses enter
	    if (usernamechar == ((char) 10))
	    {
		break;
	    }
	    //only draw the characters when the user puts less than 16 characters
	    //and if they do not press space or enter
	    if (usernamechar != ((char) 8) && usernamechar != ((char) 10)
		    && username.length () < 16)
	    {
		username += usernamechar + "";
		c.drawString (username, x + 10, y + 40);
	    }
	}
	while (usernamechar != ((char) 10));
	return (username);
    }

    //This method will be used for password box of the log in screen
    //returns a string value of the password the user inputs on keyboard
    //displays each character on the screen
    public static String LoginScreenPassword (int x2, int y2)
    {
	Font note = new Font ("Comic Sans MS", Font.PLAIN, 35);
	c.setFont (note);
	String password = "";
	String prevpassword = "";
	char passwordchar;
	//do the following until the user presses 'Enter'
	do
	{
	    c.setColor (Color.black);
	    passwordchar = c.getChar ();

	    if (passwordchar == ((char) 8))
	    {
		if (x2 >= 260 && x2 <= 570)
		{
		    //setting a local pointer called previous password which is always
		    //one character lesser than the actual password
		    for (int b = 0 ; b < password.length () - 1 ; b++)
		    {
			prevpassword = prevpassword + password.charAt (b);
		    }
		    c.setColor (Color.white);
		    c.fillRect (x2 - 15, y2 + 9, 30, 40);
		    x2 -= 20;
		    password = prevpassword;
		    prevpassword = "";
		    continue;
		}
	    }
	    //break the loop if the user presses enter
	    if (passwordchar == ((char) 10))
	    {
		break;
	    }
	    //draws stars instead of drawing the actual char value of the password
	    if (passwordchar != ((char) 8) && passwordchar != ((char) 10)
		    && x2 <= 550)
	    {
		c.drawString ("*", x2 + 10, y2 + 50);
		password += passwordchar + "";
		x2 += 20;
	    }
	}
	while (passwordchar != ((char) 10));
	return password;
    }


    
    
    
      
    
    
    
    
    
    
    
    // THE FOLLOWING METHODS ARE FOR CALENDAR PART
    // Method used to modify the numbers on the calendar
    //Takes in the first day, the last day, and does a for loop to 
    //start creating the calendar for each month
    public static void CalendarModify (int yearvalue, int monthvalue,
	    int Layout[] [], int firstday, int lastday)
    {
	int counter = 1;
	for (int a = 0 ; a < 6 ; a++)
	{
	    for (int b = 0 ; b < 7 ; b++)
	    {
		//break the loop if the counter is greater than the last day of the month
		if (counter > lastday)
		{
		    break;
		}
		//if the squares are less than the firstday, then the boxes will be 'invalid' to enter into
		if (a == 0 && b < firstday)
		{
		    Layout [a] [b] = 0;
		}
		//or make it a valid box and count up
		else
		{
		    Layout [a] [b] = counter;
		    counter++;
		}

	    }
	}
    }

    //This method is used to create the year map
    public static void YearMap (int x, int y, int year1, int year2, int year3,
	    int year4, int year5)
    {

	c.clear ();

	Font year = new Font ("Magneto", Font.BOLD, 50);
	Color BackGround = new Color (147, 201, 255);
	Color year2015 = new Color (0, 101, 204);
	Color year2016 = new Color (0, 115, 230);
	Color year2017 = new Color (13, 134, 255);
	Color years = new Color (0, 45, 89);
	Color cursor = new Color (225, 240, 255);

	c.setColor (BackGround);
	c.fillRect (0, 0, 1000, 1000);

	// years
	c.setColor (year2015);
	c.fillRect (x, y, 200, 200); // 2015
	c.setColor (year2016);
	c.fillRect (x + 200, y, 200, 200); // 2016
	c.setColor (year2017);
	c.fillRect (x + 400, y, 200, 200); // 2017
	c.setColor (year2015);
	c.fillRect (x + 100, y + 200, 200, 200); // 2018
	c.setColor (year2016);
	c.fillRect (x + 300, y + 200, 200, 200); // 2019

	c.setColor (Color.black);
	c.drawRect (x, y, 200, 200); // 2015
	c.drawRect (x + 200, y, 200, 200); // 2016
	c.drawRect (x + 400, y, 200, 200); // 2017
	c.drawRect (x + 100, y + 200, 200, 200); // 2018
	c.drawRect (x + 300, y + 200, 200, 200); // 2019

	// First year is marked by red to begin with
	c.setColor (cursor);
	c.drawRect (x, y, 200, 200); // 2015
	c.setColor (years);
	// Prints out the first 3 rows
	c.setFont (year);
	c.drawString (year1 + "", x + 30, y + 120);
	c.drawString ((year2) + "", x + 230, y + 120);
	c.drawString ((year3) + "", x + 430, y + 120);
	// Prints out the 2 rows on second column
	c.drawString ((year4) + "", x + 130, y + 300);
	c.drawString ((year5) + "", x + 330, y + 300);

    }

    //This method is used to move the cursor and returns the integer value
    //of the year the user chooses
    public static int YearMapMove (int x, int y)
    {
	Color cursor = new Color (225, 240, 255);

	int yearvalue = 0;
	//infinite loop so that user can move around forever until 'Enter' is pressed
	for (;;)
	{
	    char move = c.getChar ();
	    //the user can only press w,a,s,d (ERROR CHECKING)
	    if (move == ((char) 10))
	    {
		break;
	    }
	    while (move != 'w' && move != 'W' && move != 's' && move != 'S'
		    && move != 'a' && move != 'A' && move != 'd' && move != 'D'
		    && move != ((char) 10))
	    {
		move = c.getChar ();
	    }
	    //moves the cursor up if the user presses w, only move the cursor in the given range
	    if (move == 'w' || move == 'W')
	    {
		if (y >= 300)
		{
		    if (y == 100)
		    {
			c.setColor (Color.black);
			c.drawRect (x, y, 200, 200);
			c.setColor (cursor);
			y -= 200;
			c.drawRect (x, y, 200, 200);
		    }
		    else if (y == 300)
		    {
			c.setColor (Color.black);
			c.drawRect (x, y, 200, 200);
			c.setColor (cursor);
			x -= 100;
			y -= 200;
			c.drawRect (x, y, 200, 200);
		    }
		}
	    }
	  //moves the cursor right if the user presses d, only move the cursor in the given range
	    else if (move == 'd' || move == 'D')
	    {
		if (x <= 300)
		{
		    c.setColor (Color.black);
		    c.drawRect (x, y, 200, 200);
		    c.setColor (cursor);
		    x += 200;
		    c.drawRect (x, y, 200, 200);

		}

	    }
	  //moves the cursor left if the user presses a, only move the cursor in the given range
	    else if (move == 'a' || move == 'A')
	    {
		if (x >= 300)
		{
		    c.setColor (Color.black);
		    c.drawRect (x, y, 200, 200);
		    c.setColor (cursor);
		    x -= 200;
		    c.drawRect (x, y, 200, 200);

		}
	    }
	  //moves the cursor down if the user presses s, only move the cursor in the given range
	    else if (move == 's' || move == 's')
	    {
		if (y <= 100 && x <= 300)
		{
		    if (y == 100)
		    {
			c.setColor (Color.black);
			c.drawRect (x, y, 200, 200);
			c.setColor (cursor);
			x += 100;

			y += 200;
			c.drawRect (x, y, 200, 200);
		    }
		    else if (y == 300)
		    {
			c.setColor (Color.black);
			c.drawRect (x, y, 200, 200);
			c.setColor (cursor);
			x += 100;
			y += 200;
			c.drawRect (x, y, 200, 200);
		    }
		}
	    }
	}

	// Because we didn't use hitboxes for the main screen, we had to
	// determine the value of the year by the x-coordinate of the box(all
	// different for 5 boxes)
	// based on which x coordinate the box was on, the yearvalue changes
	// from 2015 to 2019

	if (x == 100)
	{
	    yearvalue = 2015;
	}

	else if (x == 300)
	{
	    yearvalue = 2016;
	}

	else if (x == 500)
	{
	    yearvalue = 2017;
	}

	else if (x == 200)
	{
	    yearvalue = 2018;
	}

	else if (x == 400)
	{
	    yearvalue = 2019;
	}

	return yearvalue;
    }

    //this method creates the map of the months of the corresponding year
    public static void Monthmap (int MonthMap[] [], int yearvalue)
    {
	c.clear ();

	Font months = new Font ("Magneto", Font.BOLD, 50);
	Font year = new Font ("Cooper Black", Font.PLAIN, 40);
	Color monthss = new Color (0, 45, 89);
	Color BackGround = new Color (206, 206, 255);
	Color month1 = new Color (110, 110, 255);
	Color month2 = new Color (140, 140, 255);
	Color month3 = new Color (164, 164, 255);
	Color month4 = new Color (196, 196, 255);
	Color cursor = new Color (225, 240, 255);

	c.setColor (BackGround);
	c.fillRect (0, 0, 1000, 1000);

	c.setFont (year);
	c.setColor (monthss);
	c.drawString (("Year   " + yearvalue + ""), 300, 35);

	Font note = new Font ("Comic Sans MS", Font.BOLD, 15);
	c.setFont (note);
	c.drawString ("Press 'Back Space' to go back!", 50, 35);

	//creating 4 rows and 3 columns for the month map
	for (int y = 0 ; y <= 2 ; y++)
	{

	    for (int x = 0 ; x <= 3 ; x++)
	    {
		c.setColor (cursor); // the blue outline covered the cursor when
		// we had this cursor initialization in the
		// first if statement, so we had to put it
		// here
		c.drawRect (20, 50, 200, 200); // to make sure that the cursor
		// color is shown properly
		if (MonthMap [y] [x] == 1)
		{

		    c.setColor (month1);
		    c.fillRect (x * 200 + 20, y * 200 + 50, 200, 200);
		    c.setColor (cursor);
		    c.drawRect (x * 200 + 20, y * 200 + 50, 200, 200);
		    c.setColor (monthss);
		    c.setFont (months);
		    c.drawString ("Jan", x * 200 + 70, y * 200 + 120);
		    c.setColor (Color.black);
		    c.drawString ((MonthMap [y] [x] + ""), x * 200 + 100,
			    y * 200 + 200);
		}
		else if (MonthMap [y] [x] == 2)
		{
		    c.setColor (month4);
		    c.fillRect (x * 200 + 20, y * 200 + 50, 200, 200);
		    c.setColor (Color.blue);
		    c.drawRect (x * 200 + 20, y * 200 + 50, 200, 200);
		    c.setColor (monthss);
		    c.setFont (months);
		    c.drawString ("Feb", x * 200 + 70, y * 200 + 120);
		    c.setColor (Color.black);
		    c.drawString ((MonthMap [y] [x] + ""), x * 200 + 100,
			    y * 200 + 200);

		}
		else if (MonthMap [y] [x] == 3)
		{
		    c.setColor (month3);
		    c.fillRect (x * 200 + 20, y * 200 + 50, 200, 200);
		    c.setColor (Color.blue);
		    c.drawRect (x * 200 + 20, y * 200 + 50, 200, 200);
		    c.setColor (monthss);
		    c.setFont (months);
		    c.drawString ("Mar", x * 200 + 70, y * 200 + 120);
		    c.setColor (Color.black);
		    c.drawString ((MonthMap [y] [x] + ""), x * 200 + 100,
			    y * 200 + 200);

		}
		else if (MonthMap [y] [x] == 4)
		{
		    c.setColor (month4);
		    c.fillRect (x * 200 + 20, y * 200 + 50, 200, 200);
		    c.setColor (Color.blue);
		    c.drawRect (x * 200 + 20, y * 200 + 50, 200, 200);
		    c.setColor (monthss);
		    c.setFont (months);
		    c.drawString ("April", x * 200 + 40, y * 200 + 120);
		    c.setColor (Color.black);
		    c.drawString ((MonthMap [y] [x] + ""), x * 200 + 100,
			    y * 200 + 200);

		}
		else if (MonthMap [y] [x] == 5)
		{
		    c.setColor (month3);
		    c.fillRect (x * 200 + 20, y * 200 + 50, 200, 200);
		    c.setColor (Color.blue);
		    c.drawRect (x * 200 + 20, y * 200 + 50, 200, 200);
		    c.setColor (monthss);
		    c.setFont (months);
		    c.drawString ("May", x * 200 + 60, y * 200 + 120);
		    c.setColor (Color.black);
		    c.drawString ((MonthMap [y] [x] + ""), x * 200 + 100,
			    y * 200 + 200);

		}
		else if (MonthMap [y] [x] == 6)
		{
		    c.setColor (month2);
		    c.fillRect (x * 200 + 20, y * 200 + 50, 200, 200);
		    c.setColor (Color.blue);
		    c.drawRect (x * 200 + 20, y * 200 + 50, 200, 200);
		    c.setColor (monthss);
		    c.setFont (months);
		    c.drawString ("June", x * 200 + 60, y * 200 + 120);
		    c.setColor (Color.black);
		    c.drawString ((MonthMap [y] [x] + ""), x * 200 + 100,
			    y * 200 + 200);

		}
		else if (MonthMap [y] [x] == 7)
		{
		    c.setColor (month4);
		    c.fillRect (x * 200 + 20, y * 200 + 50, 200, 200);
		    c.setColor (Color.blue);
		    c.drawRect (x * 200 + 20, y * 200 + 50, 200, 200);
		    c.setColor (monthss);
		    c.setFont (months);
		    c.drawString ("July", x * 200 + 60, y * 200 + 120);
		    c.setColor (Color.black);
		    c.drawString ((MonthMap [y] [x] + ""), x * 200 + 100,
			    y * 200 + 200);

		}
		else if (MonthMap [y] [x] == 8)
		{
		    c.setColor (month1);
		    c.fillRect (x * 200 + 20, y * 200 + 50, 200, 200);
		    c.setColor (Color.blue);
		    c.drawRect (x * 200 + 20, y * 200 + 50, 200, 200);
		    c.setColor (monthss);
		    c.setFont (months);
		    c.drawString ("Aug", x * 200 + 60, y * 200 + 120);
		    c.setColor (Color.black);
		    c.drawString ((MonthMap [y] [x] + ""), x * 200 + 100,
			    y * 200 + 200);

		}
		else if (MonthMap [y] [x] == 9)
		{
		    c.setColor (month2);
		    c.fillRect (x * 200 + 20, y * 200 + 50, 200, 200);
		    c.setColor (Color.blue);
		    c.drawRect (x * 200 + 20, y * 200 + 50, 200, 200);
		    c.setColor (monthss);
		    c.setFont (months);
		    c.drawString ("Sept", x * 200 + 50, y * 200 + 120);
		    c.setColor (Color.black);
		    c.drawString ((MonthMap [y] [x] + ""), x * 200 + 100,
			    y * 200 + 200);

		}
		else if (MonthMap [y] [x] == 10)
		{
		    c.setColor (month1);
		    c.fillRect (x * 200 + 20, y * 200 + 50, 200, 200);
		    c.setColor (Color.blue);
		    c.drawRect (x * 200 + 20, y * 200 + 50, 200, 200);
		    c.setColor (monthss);
		    c.setFont (months);
		    c.drawString ("Oct", x * 200 + 70, y * 200 + 120);
		    c.setColor (Color.black);
		    c.drawString ((MonthMap [y] [x] + ""), x * 200 + 100,
			    y * 200 + 200);

		}
		else if (MonthMap [y] [x] == 11)
		{
		    c.setColor (month3);
		    c.fillRect (x * 200 + 20, y * 200 + 50, 200, 200);
		    c.setColor (Color.blue);
		    c.drawRect (x * 200 + 20, y * 200 + 50, 200, 200);
		    c.setColor (monthss);
		    c.setFont (months);
		    c.drawString ("Nov", x * 200 + 70, y * 200 + 120);
		    c.setColor (Color.black);
		    c.drawString ((MonthMap [y] [x] + ""), x * 200 + 100,
			    y * 200 + 200);

		}
		else if (MonthMap [y] [x] == 12)
		{
		    c.setColor (month4);
		    c.fillRect (x * 200 + 20, y * 200 + 50, 200, 200);
		    c.setColor (Color.blue);
		    c.drawRect (x * 200 + 20, y * 200 + 50, 200, 200);
		    c.setColor (monthss);
		    c.setFont (months);
		    c.drawString ("Dec", x * 200 + 70, y * 200 + 120);
		    c.setColor (Color.black);
		    c.drawString ((MonthMap [y] [x] + ""), x * 200 + 100,
			    y * 200 + 200);

		}
	    }
	}
    }

    //allows the user to move the cursor to pick which month to go into
    public static int MonthMapmove (int MonthMap[] [], int x, int y)
    {
	Color cursor = new Color (225, 240, 255);

	char move;
	//infinite loop so that user can move around forever until 'Enter' is pressed
	for (;;)
	{
		//
	    move = c.getChar ();
	    if (move == ((char) 10))
	    {
		break;
	    }
	    if (move == ((char) 8))
	    {
		break;
	    }
	    while (move != 'w' && move != 'W' && move != 's' && move != 'S'
		    && move != 'a' && move != 'A' && move != 'd' && move != 'D'
		    && move != ((char) 10) && move != ((char) 8))
	    {
		move = c.getChar ();
	    }
	    //if the user presses a, then the cursor will move left
	    if (move == 'a' || move == 'a')
	    {
		if (x >= 180)
		{
		    c.setColor (Color.blue);
		    c.drawRect (x + 20, y + 50, 200, 200);
		    x -= 200;
		    c.setColor (cursor);
		    c.drawRect (x + 20, y + 50, 200, 200);

		}
	    }
	  //if the user presses d, then the cursor will move to right
	    if (move == 'd' || move == 'D')
	    {
		if (x <= 420)
		{
		    c.setColor (Color.blue);
		    c.drawRect (x + 20, y + 50, 200, 200);
		    x += 200;
		    c.setColor (cursor);
		    c.drawRect (x + 20, y + 50, 200, 200);
		}
	    }
	  //if the user presses w, then the cursor will move up
	    if (move == 'w' || move == 'W')
	    {
		if (y >= 200)
		{
		    c.setColor (Color.blue);
		    c.drawRect (x + 20, y + 50, 200, 200);
		    y -= 200;
		    c.setColor (cursor);
		    c.drawRect (x + 20, y + 50, 200, 200);
		}
	    }
	  //if the user presses s, then the cursor will move down
	    if (move == 's' || move == 'S')
	    {
		if (y <= 200)
		{
		    c.setColor (Color.blue);
		    c.drawRect (x + 20, y + 50, 200, 200);
		    y += 200;
		    c.setColor (cursor);
		    c.drawRect (x + 20, y + 50, 200, 200);
		}
	    }
	}
	//return the monthmap value to be 5000 when the user presses backspace
	//this allows the program to detect in the mainscreen to go back to the year map
	if (move == ((char) 8))
	{
	    MonthMap [y / 200] [x / 200] = 5000;
	    c.clear ();
	}
	//returns the value of the month
	return (MonthMap [y / 200] [x / 200]);
    }

    //method used to create the calendar layout
    public static void CalendarMaker (int Layout[] [], int yearvalue, int monthvalue) throws IOException
    {
	c.clear ();
	Font dayletter = new Font ("Arial Black", Font.PLAIN, 15);
	Font daynumber = new Font ("Segoe Script", Font.BOLD, 50);
	Font title = new Font ("Segoe Script", Font.BOLD, 40);
	Color titlee = new Color (0, 0, 160);
	Color BackGround = new Color (147, 201, 255);
	Color invalid = new Color (15, 135, 255);
	Color cursor = new Color (94, 174, 255);
	c.setColor (BackGround);
	c.fillRect (0, 0, 1000, 1000);

	c.setColor (titlee);
	Font note = new Font ("Comic Sans MS", Font.BOLD, 15);
	c.setFont (note);
	c.drawString ("Press 'Back Space' to go back!", 10, 35);

	c.setFont (title);
	c.setColor (titlee);
	//determining what month to print out depending on what month it is
	if (monthvalue == 1)
	{
	    c.drawString ("January  " + yearvalue, 250, 35);
	}

	else if (monthvalue == 2)
	{
	    c.drawString ("February " + yearvalue, 250, 35);
	}

	else if (monthvalue == 3)
	{
	    c.drawString ("March    " + yearvalue, 250, 35);
	}

	else if (monthvalue == 4)
	{
	    c.drawString ("April    " + yearvalue, 250, 35);
	}

	else if (monthvalue == 5)
	{
	    c.drawString ("May      " + yearvalue, 250, 35);
	}

	else if (monthvalue == 6)
	{
	    c.drawString ("June     " + yearvalue, 250, 35);
	}

	else if (monthvalue == 7)
	{
	    c.drawString ("July     " + yearvalue, 250, 35);
	}

	else if (monthvalue == 8)
	{
	    c.drawString ("August  " + yearvalue, 250, 35);
	}

	else if (monthvalue == 9)
	{
	    c.drawString ("September  " + yearvalue, 250, 35);
	}

	else if (monthvalue == 10)
	{
	    c.drawString ("Octoboer  " + yearvalue, 250, 35);
	}

	else if (monthvalue == 11)
	{
	    c.drawString ("November  " + yearvalue, 250, 35);
	}

	else if (monthvalue == 12)
	{
	    c.drawString ("December  " + yearvalue, 250, 35);
	}

	c.setFont (daynumber);
	// 7 rows and 6 columns of boxes created
	for (int y = 0 ; y < 6 ; y++)
	{
	    for (int x = 0 ; x < 7 ; x++)
	    {
		//special case in which the box will be 'invalid' if the box value is 0
		if (Layout [y] [x] == 0)
		{
		    c.setColor (invalid);
		    c.fillRect (x * 100 + 70, y * 100 + 70, 100, 80);
			c.setColor (Color.black);
			c.drawRect (x * 100 + 70, y * 100+50, 100, 20);
		    c.setColor (Color.black);
		    c.drawRect (x * 100 + 70, y * 100 + 50, 100, 100);
		}
		// 1 is the cursor, which is why the drawRect is in cursor color
		else if (Layout [y] [x] == 1)
		{
		    c.setColor (Color.white);
		    c.fillRect (x * 100 + 70, y * 100 + 70, 100, 80);
			c.setColor (Color.black);
			c.drawRect (x * 100 + 70, y * 100+50, 100, 20);
		    c.setColor (cursor);
		    c.drawRect (x * 100 + 70, y * 100 + 70, 100, 80);
		    c.setColor (Color.black);
		    c.drawString ((Layout [y] [x] + ""), x * 100 + 100, y * 100 + 50 + 70);

		}
		//but for the rest, they are valid boxes and will print the day value
		//I separated the days into two sections, because the numbers on map got skewed
		//when past digit 9
		for (int a = 2 ; a <= 31 ; a++)
		{
		    if (Layout [y] [x] == a && a <= 9)
		    {
			c.setColor (Color.white);
		    c.fillRect (x * 100 + 70, y * 100 + 70, 100, 80);
			c.setColor (Color.black);
			c.drawRect (x * 100 + 70, y * 100+50, 100, 20);
			c.drawRect (x * 100 + 70, y * 100 + 50, 100, 100);
			c.setColor (Color.black);
			c.drawString ((Layout [y] [x] + ""), x * 100 + 100,
				y * 100 + 50 + 70);

		    }
		    else if (Layout [y] [x] == a && a >= 10)
		    {
			c.setColor (Color.white);
		    c.fillRect (x * 100 + 70, y * 100 + 70, 100, 80);
			c.setColor (Color.black);
			c.drawRect (x * 100 + 70, y * 100+50, 100, 20);
			c.drawRect (x * 100 + 70, y * 100 + 50, 100, 100);
			c.setColor (Color.black);
			c.drawString ((Layout [y] [x] + ""), x * 100 + 85,
				y * 100 + 50 + 70);
		    }
		}

	    }
	}

	int x = 102;
	int y = 65;
	//prints out the days on the top section of each box
	for (int a = 0; a< 6; a++)
	{
		c.setFont (dayletter);
		c.setColor (Color.red);
		c.drawString ("Sun", 102, y);
		c.setColor (Color.black);
		c.drawString ("Mon", x+100, y);
		c.drawString ("Tues", x+200, y);
		c.drawString ("Wed", x+300, y);
		c.drawString ("Thurs",x+393, y);
		c.drawString ("Fri",x+503, y);
		c.drawString ("Sat",x+600, y);
		y += 100;
		x = 102;
	}
    }

    
    
    //the cursor for the calendar
    public static int CalendarMove (int Layout[] [], int x, int y, int yearvalue, int monthvalue)
    {
	Color cursor = new Color (94, 174, 255);

	char move;
	//infinite loop so that the user can move the cursor forever until enter is pressed
	for (;;)
	{
	    move = c.getChar ();
	    //error checking so that user can only press w,a,s,d,enter,or backspace
	    while (move != 'w' && move != 'W' && move != 's' && move != 'S'
		    && move != 'a' && move != 'A' && move != 'd' && move != 'D'
		    && move != ((char) 10) && move != ((char) 8))
	    {
		move = c.getChar (); // error trap to make sure the user enters
		// only specific characters
	    }
	    //break the loop if the user presses backspace or enter
	    if (move == ((char) 10))
	    {
		break;
	    }
	    if (move == ((char) 8))
	    {
		break;
	    }
	    //moves left if the user presses a
	    if (move == 'a' || move == 'a')
	    {
		//error checking so that user does not move left to the invalid boxes
		if (x >= 100 && Layout [y / 100] [(x - 100) / 100] != 0) // only move the cursor in the "valid" tiles, 0 is invalid
		    {
			c.setColor (Color.black);
			c.drawRect (x + 70, y + 70, 100, 80);
			x -= 100;
			c.setColor (cursor);
			c.drawRect (x + 70, y + 70, 100, 80);
		    }
	    }
	  //moves right if the user presses d
	    if (move == 'd' || move == 'D')
	    {
		//error checking so that user does not move right to the invalid boxes
		if (x <= 500 && Layout [y / 100] [(x + 100) / 100] != 0) // only
		    // move
		    // the
		    // cursor
		    // in
		    // the
		    // "valid"
		    // tiles,
		    // 0 is
		    // invalid
		    {
			c.setColor (Color.black);
			c.drawRect (x + 70, y + 70, 100, 80);

			x += 100;
			c.setColor (cursor);
			c.drawRect (x + 70, y + 70, 100, 80);

		    }
	    }
	  //moves up if the user presses w
	    if (move == 'w' || move == 'W')
	    {
		//error checking so that user does not move up to the invalid boxes
		if (y >= 100 && Layout [(y - 100) / 100] [x / 100] != 0) // only
		    // move
		    // the
		    // cursor
		    // in
		    // the
		    // "valid"
		    // tiles,
		    // 0 is
		    // invalid
		    {

			c.setColor (Color.black);
			c.drawRect (x + 70, y + 70, 100, 80);

			y -= 100;
			c.setColor (cursor);
			c.drawRect (x + 70, y + 70, 100, 80);

		    }
	    }
	  //moves down if the user presses s
	    if (move == 's' || move == 'S')
	    {
		//error checking so that user does not move down to the invalid boxes
		if (y <= 400 && Layout [(y + 100) / 100] [x / 100] != 0) // only
		    // move
		    // the
		    // cursor
		    // in
		    // the
		    // "valid"
		    // tiles,
		    // 0 is
		    // invalid
		    {

			c.setColor (Color.black);
			c.drawRect (x + 70, y + 70, 100, 80);
			y += 100;
			c.setColor (cursor);
			c.drawRect (x + 70, y + 70, 100, 80);
		    }
	    }
	}
	if (move == ((char) 8))
	{
	    Layout [y / 100] [x / 100] = 5000;
	    c.clear ();
	}

	return (Layout [y / 100] [x / 100]);
    }

    //method to create the loading screen when the user creates a profile
    //when creating a file, it lags due to the fact that the program is creating
    //several folders, hence I created this loadingscreen to make sure the user
    //is not confused
    public static void LoadingScreen ()
    {
	Color BackGround = new Color (0, 191, 191);
	Font dayletter = new Font ("Arial Black", Font.PLAIN, 15);
	Font daynumber = new Font ("Segoe Script", Font.BOLD, 50);
	Font impact = new Font ("Impact", Font.PLAIN, 30);
	c.setFont (impact);
	c.setColor (BackGround);
	c.fillRect (0, 0, 1000, 1000);
	//used the calendar layout to create this screen
	for (int y = 0 ; y < 6 ; y++)
	{
	    for (int x = 0 ; x < 7 ; x++)
	    {
		c.setColor (Color.white);
		c.fillRect (x * 100 + 70, y * 100 + 50, 100, 100);
		c.setColor (Color.black);
		c.drawRect (x * 100 + 70, y * 100 + 50, 100, 100);
	    }
	}

	c.setFont (dayletter);
	c.setColor (Color.red);
	c.drawString ("Sun", 102, 65);
	c.setColor (Color.black);
	c.drawString ("Mon", 202, 65);
	c.drawString ("Tues", 302, 65);
	c.drawString ("Wed", 402, 65);
	c.drawString ("Thurs", 495, 65);
	c.drawString ("Fri", 605, 65);
	c.drawString ("Sat", 702, 65);
	c.drawLine (70, 70, 770, 70);

	c.setFont (daynumber);
	c.drawString ("P", 100, 130);
	c.drawString ("L", 200, 130);
	c.drawString ("E", 300, 130);
	c.drawString ("A", 400, 130);
	c.drawString ("S", 500, 130);
	c.drawString ("E", 600, 130);

	c.drawString ("W", 100, 230);
	c.drawString ("A", 200, 230);
	c.drawString ("I", 300, 230);
	c.drawString ("T", 400, 230);

	c.drawString ("C", 100, 330);
	c.drawString ("R", 200, 330);
	c.drawString ("E", 300, 330);
	c.drawString ("A", 400, 330);
	c.drawString ("T", 500, 330);
	c.drawString ("-", 600, 330);
	c.drawString ("I", 100, 430);
	c.drawString ("N", 200, 430);
	c.drawString ("G", 300, 430);

	c.drawString ("P", 100, 530);
	c.drawString ("R", 200, 530);
	c.drawString ("O", 300, 530);
	c.drawString ("F", 400, 530);
	c.drawString ("I", 500, 530);
	c.drawString ("L", 600, 530);
	c.drawString ("E", 700, 530);
    }

    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    

    // THIS IS THE TO DO LIST PART
    //The Main Screen of the events screen
    public static char EventsMain (int year, int month, int day)
	throws IOException
    {
	//Fonts used for the Main Screen in events
	char choice;
	boolean corChoice = false;
	Font bankgothic = new Font ("Berlin Sans FB Demi", Font.BOLD, 70);
	Font bankgothic2 = new Font ("Berlin Sans FB Demi", Font.BOLD, 50);
	Font note = new Font ("Comic Sans MS", Font.PLAIN, 15);
	Font choose = new Font ("Comic Sans MS", Font.BOLD, 30);

	Color BackGround = new Color (0, 191, 191);
	c.setColor (BackGround);
	c.fillRect (0, 0, 1000, 1000);

	c.setFont (bankgothic);
	c.setColor (Color.gray);
	c.drawString ("To Do List", 250, 100);
	c.setColor (Color.yellow);
	c.drawString ("To Do List", 246, 98);
	c.setColor (Color.gray);
	c.fillRect (70, 140, 700, 500);
	c.setColor (Color.lightGray);
	c.fillRect (80, 150, 680, 480);

	c.setFont (bankgothic2);
	c.setColor (Color.yellow);
	c.drawString ("Main Screen", 290, 200);
	c.setColor (Color.gray);
	c.drawString ("Main Screen", 288, 200);

	c.setColor (Color.black);
	c.fillRoundRect (160, 220, 500, 75, 40, 40);
	c.fillRoundRect (160, 320, 500, 75, 40, 40);
	c.fillRoundRect (160, 420, 500, 75, 40, 40);
	c.fillRoundRect (160, 520, 500, 75, 40, 40);

	c.setColor (Color.yellow);
	c.fillRoundRect (165, 225, 490, 65, 40, 40);
	c.fillRoundRect (165, 325, 490, 65, 40, 40);
	c.fillRoundRect (165, 425, 490, 65, 40, 40);
	c.fillRoundRect (165, 525, 490, 65, 40, 40);
	c.setFont (choose);
	c.setColor (Color.black);
	c.drawString ("Press (c) to create an event!", 200, 270);
	c.drawString ("Press (v) to view an event!", 200, 370);
	c.drawString ("Press (d) to delete an event!", 200, 470);
	c.drawString ("Press (e) to edit an event!", 200, 570);

	c.setFont (note);
	c.drawString ("Press 'Back Space' to go back!", 300, 620);

	choice = ' ';
	//error checking so that the user can only pick to create, view, edit
	//or delete
	do {
		choice = c.getChar();
		if (choice  == ((char) 8))
		{
			corChoice = true;
			break;
		}
		if (choice == ('c') || choice == ('C')) {
			corChoice = true;
			create(year, month, day);
			break;
		} else if (choice == ('v') || choice == ('V')) {
			corChoice = true;
			view(year, month, day);
			break;
		} else if (choice == ('e') || choice == ('E')) {
			corChoice = true;
			edit(year, month, day);
			break;
		} else if (choice == ('d') || choice == ('D')) {
			corChoice = true;
			delete(year, month, day);
			break;
		} else
			corChoice = false;
	} while (corChoice == false);

	
	return choice;

    }



    //method for delete screen
    public static void delete (int year, int month, int day) throws IOException
    {
	//Fonts used for delete screen
	Font bankgothic2 = new Font ("Berlin Sans FB Demi", Font.BOLD, 50);
	Font bankgothic3 = new Font ("Berlin Sans FB Demi", Font.PLAIN, 30);
	Font title = new Font ("Berlin Sans FB Demi", Font.PLAIN, 25);
	//Retrieving the string value of current user 
	String name = getLastLogin ();
	
	char convchoice;
    int choice;
    boolean cc;
    File Folder = new File (
	    "C:\\Users\\Jason\\Desktop\\CompSciCulm\\User\\"
	    + name + "\\" + year + "\\" + month + "\\" + day);
    File[] FolderList = Folder.listFiles ();
    File Chosen = new File ("");
    String Stype = "";
    String event = "", type = "";

    c.setColor (Color.lightGray);
    c.fillRect (80, 150, 680, 480);
    c.setFont (bankgothic2);
    c.setColor (Color.yellow);
    c.drawString ("Delete Event", 265, 200);
    c.setColor (Color.gray);
    c.drawString ("Delete Event", 263, 200);

    Font warning = new Font ("Comic Sans MS", Font.PLAIN, 15);
    c.setFont (warning);
    c.setColor (Color.black);
    c.drawString (
	    "* If there is nothing under the category, you will be automatically directed to the Main Screen",
	    90, 225);

    c.setColor (Color.black);
    c.fillRoundRect (240, 240, 350, 75, 40, 40);
    c.fillRoundRect (240, 340, 350, 75, 40, 40);
    c.fillRoundRect (240, 440, 350, 75, 40, 40);
    c.fillRoundRect (240, 540, 350, 75, 40, 40);

    c.setColor (Color.yellow);
    c.fillRoundRect (245, 245, 340, 65, 40, 40);
    c.fillRoundRect (245, 345, 340, 65, 40, 40);
    c.fillRoundRect (245, 445, 340, 65, 40, 40);
    c.fillRoundRect (245, 545, 340, 65, 40, 40);

    c.setFont (bankgothic3);
    c.setColor (Color.black);
    c.drawString ("Appointments (1)", 295, 285);
    c.drawString ("Birthdays (2)", 320, 385);
    c.drawString ("Holiday (3)", 330, 485);
    c.drawString ("Travel (4)", 340, 585);

    int x = 130;
    int y = 285;


    do
    {
	cc = false;
	//The user can only choose 1,2,3 or 4 from the delete screen, error checking!
	do
	{
	    convchoice = c.getChar ();
	}
	while (convchoice != '1' && convchoice != '2'
		&& convchoice != '3' && convchoice != '4');
	choice = convchoice - '0';
	for (int i = 0 ; i < FolderList.length ; i++)//loops for amount of files in the folder
	{
	    if ((i + 1) == choice)
	    {
		type = FolderList [i].getName ();
		Chosen = new File (Folder + "\\" + FolderList [i].getName ());
		Stype = FolderList [i].getName ();
		cc = true;
	    }

	}
	//They must enter the chracter again if cc is false
	if (cc == false)
	{
	    convchoice = c.getChar ();
	}
    }
    while (choice < 1 && choice > FolderList.length);

    File[] FileList = Chosen.listFiles ();
    File ChosenFolder = Chosen;
    File f = Chosen;
    //If there are no events on the list, then go back to the main screen
    if (!(f.list ().length > 0))
    {
	c.clear ();
	EventsMain (year, month, day);

    }
    //Or if there is, then proceed on to this screen
    else
    {
	do
	{
	    cc = false;
	    c.setColor (Color.lightGray);
	    c.fillRect (80, 150, 680, 480);

	    c.setFont (bankgothic2);
	    c.setColor (Color.yellow);
	    c.drawString ("Choose an event to delete", 130, 200);
	    c.setColor (Color.gray);
	    c.drawString ("Choose an event to delete", 128, 200);
	    c.setColor (Color.black);
	    c.fillRoundRect (100, 240, 300, 75, 40, 40);
	    c.fillRoundRect (100, 340, 300, 75, 40, 40);
	    c.fillRoundRect (100, 440, 300, 75, 40, 40);
	    c.fillRoundRect (100, 540, 300, 75, 40, 40);

	    c.fillRoundRect (430, 240, 300, 75, 40, 40);
	    c.fillRoundRect (430, 340, 300, 75, 40, 40);
	    c.fillRoundRect (430, 440, 300, 75, 40, 40);
	    c.fillRoundRect (430, 540, 300, 75, 40, 40);

	    c.setColor (Color.yellow);
	    c.fillRoundRect (105, 245, 290, 65, 40, 40);
	    c.fillRoundRect (105, 345, 290, 65, 40, 40);
	    c.fillRoundRect (105, 445, 290, 65, 40, 40);
	    c.fillRoundRect (105, 545, 290, 65, 40, 40);

	    c.fillRoundRect (435, 245, 290, 65, 40, 40);
	    c.fillRoundRect (435, 345, 290, 65, 40, 40);
	    c.fillRoundRect (435, 445, 290, 65, 40, 40);
	    c.fillRoundRect (435, 545, 290, 65, 40, 40);
	    //displays the event names
	    for (int i = 0 ; i < FileList.length ; i++)//loop for amount of files in the folder
	    {
		if (FileList [i].isDirectory ())
		{
		    c.setFont (title);
		    c.setColor (Color.black);
		    if (y < 700 && x <= 500 && x <= 500)
		    {
			c.drawString (
				(FileList [i].getName ().replaceAll (".bin", "")
				    + " (" + (i + 1) + ")"), x, y);
		    }
		    y += 100;
		    if (y == 685)
		    {
			x += 330;
			y = 285;
		    }
		}
		else if (FileList [i].isFile ())
		{
		    c.setFont (title);
		    c.setColor (Color.black);
		    if (y < 700 && x <= 500)
		    {
			c.drawString (
				(FileList [i].getName ().replaceAll (".bin", "")
				    + " (" + (i + 1) + ")"), x, y);
		    }
		    y += 100;
		    if (y == 685)
		    {
			x += 330;
			y = 285;
		    }
		}
	    }
	    //The user can only pick/delete out of 8 events
	    do
	    {

		convchoice = c.getChar ();
	    }
	    while (convchoice != '1' && convchoice != '2'
		    && convchoice != '3' && convchoice != '4'
		    && convchoice != '5' && convchoice != '6'
		    && convchoice != '7' && convchoice != '8');

	    choice = convchoice - '0';
	    //Checks to see if the choice user names matches with the event in their folders
	    //if true, then delete, if not true, then user has to pick again
	    for (int i = 0 ; i < FileList.length ; i++)//loops for amount of files in the folder
	    {
		if ((i + 1) == choice)
		{
		    event = FileList [i].getName ();
		    Chosen = new File (ChosenFolder + "\\"
			    + FileList [i].getName ());
		    cc = true;
		}

	    }
	    if (cc == false)
	    {
		x = 130;
		y = 285;
		convchoice = c.getChar ();
	    }
	}
	while (choice < 1 || choice > FileList.length);

	//Moves on to the ensure screen where they will be asked to delete or go back
	int decision;
	char convdecision;

	    c.setColor (Color.lightGray);
	    c.fillRect (80, 150, 680, 480);
	    c.setFont (bankgothic2);

	    c.setColor (Color.lightGray);
	    c.fillRect (80, 150, 680, 480);
	    c.setFont (bankgothic2);
	    c.setColor (Color.yellow);
	    c.drawString ("Delete Event", 265, 220);
	    c.setColor (Color.gray);
	    c.drawString ("Delete Event", 260, 220);
	    c.setFont (bankgothic3);
	    c.setColor (Color.black);
	    c.drawString ("Are you sure you want to delete this event?", 90, 300);

	    c.setColor (Color.black);
	    c.fillRoundRect (240, 400, 350, 75, 40, 40);
	    c.fillRoundRect (240, 500, 350, 75, 40, 40);

	    c.setColor (Color.yellow);
	    c.fillRoundRect (245, 405, 340, 65, 40, 40);
	    c.fillRoundRect (245, 505, 340, 65, 40, 40);
	    c.setColor (Color.black);
	    c.drawString ("Yes (1)", 350, 450);
	    c.drawString ("No (2)", 350, 550);
	    //User can only pick Yes or no (1) or (2)
	    do
	    {
		convdecision = c.getChar ();
	    }
	    while (convdecision != '1' && convdecision != '2');

	    decision = convdecision - '0';
	    //The following codes delete the event when they choose 1
	    if (decision == 1)
	    {
		new FileOutputStream (Chosen).close ();
		Chosen.delete ();
		c.setColor (Color.lightGray);
		c.fillRect (80, 150, 680, 480);
		c.setFont (bankgothic2);
		c.setColor (Color.yellow);
		c.drawString ("Delete Event", 265, 220);
		c.setColor (Color.gray);
		c.drawString ("Delete Event", 260, 220);
		c.setFont (bankgothic3);
		c.setColor (Color.black);
		c.drawString ("Successfully Deleted the event!", 90, 300);
		Font notee = new Font ("Comic Sans MS", Font.PLAIN, 20);

		c.setFont (notee);
		c.setColor (Color.blue);
		c.drawString ("Press 'BackSpace' to go back to the Main Screen!", 100, 600);
		char presstocontinue;
		do
		{
		    presstocontinue = c.getChar ();
		}
		while (presstocontinue != ((char) 8));
		EventsMain (year, month, day);
		
		//If the user presses 2, then just proceed on to the main screen
	    }
	    else if (decision == 2)
	    {
		EventsMain (year, month, day);
	    }


	}
    }
    



    //Following codes for edit method
    public static void edit (int year, int month, int day) throws IOException
    {
	//Gets the information of the current user logged in
	String name = getLastLogin ();

	int choice;
	boolean wc = false;
	boolean renamed = false;
	String newname = "";
	boolean cc;
	File Folder = new File (
		"C:\\Users\\Jason\\Desktop\\CompSciCulm\\User\\"
		+ name + "\\" + year + "\\" + month + "\\" + day);
	File[] FolderList = Folder.listFiles ();
	File Chosen = new File ("");
	String Stype = "";
	String event = "", type = "";
	int hour, min;
	String desc;
	char convchoice;
	int x = 130;
	int y = 285;
	Font bankgothic2 = new Font ("Berlin Sans FB Demi", Font.BOLD, 50);
	Font bankgothic3 = new Font ("Berlin Sans FB Demi", Font.PLAIN, 30);
	Font title = new Font ("Berlin Sans FB Demi", Font.PLAIN, 20);

	Font notee = new Font ("Comic Sans MS", Font.PLAIN, 20);
	Font note = new Font ("Comic Sans MS", Font.BOLD, 15);
	Font titlee = new Font ("Comic Sans MS", Font.PLAIN, 20);

	c.setColor (Color.lightGray);
	c.fillRect (80, 150, 680, 480);
	c.setFont (bankgothic2);
	c.setColor (Color.yellow);
	c.drawString ("Edit Event", 290, 200);
	c.setColor (Color.gray);
	c.drawString ("Edit Event", 288, 200);

	Font warning = new Font ("Comic Sans MS", Font.PLAIN, 15);
	c.setFont (warning);
	c.setColor (Color.black);
	c.drawString (
		"* If there is nothing under the category, you will be automatically directed to the Main Screen",
		90, 225);

	c.setColor (Color.black);
	c.fillRoundRect (240, 240, 350, 75, 40, 40);
	c.fillRoundRect (240, 340, 350, 75, 40, 40);
	c.fillRoundRect (240, 440, 350, 75, 40, 40);
	c.fillRoundRect (240, 540, 350, 75, 40, 40);

	c.setColor (Color.yellow);
	c.fillRoundRect (245, 245, 340, 65, 40, 40);
	c.fillRoundRect (245, 345, 340, 65, 40, 40);
	c.fillRoundRect (245, 445, 340, 65, 40, 40);
	c.fillRoundRect (245, 545, 340, 65, 40, 40);

	c.setFont (bankgothic3);
	c.setColor (Color.black);
	c.drawString ("Appointments (1)", 295, 285);
	c.drawString ("Birthdays (2)", 320, 385);
	c.drawString ("Holiday (3)", 330, 485);
	c.drawString ("Travel (4)", 340, 585);


	do
	{
	    cc = false;
	    //User can only choose between 1 to 4 error checking
	    do
	    {
		convchoice = c.getChar ();
	    }
	    while (convchoice != '1' && convchoice != '2'
		    && convchoice != '3' && convchoice != '4');

	    choice = convchoice - '0';
	    //if it matches the event in their folders, then break the loop
	    for (int i = 0 ; i < FolderList.length ; i++)
	    {
		if ((i + 1) == choice)
		{
		    type = FolderList [i].getName ();
		    Chosen = new File (Folder + "\\" + FolderList [i].getName ());
		    Stype = FolderList [i].getName ();
		    cc = true;
		}

	    }
	    if (cc == false)
	    {
		convchoice = c.getChar ();
	    }
	}
	while (choice < 1 && choice > FolderList.length);

	File[] FileList = Chosen.listFiles ();//array for the list of files
	File f = Chosen;
	//if the folder is empty in event types, then just proceed on to the main screen of events

	if (!(f.list ().length > 0))
    {
	c.clear ();
	EventsMain (year, month, day);

    }

	else
	{
	    do
	    {
		cc = false;
		c.setColor (Color.lightGray);
		c.fillRect (80, 150, 680, 480);

		c.setFont (bankgothic2);
		c.setColor (Color.yellow);
		c.drawString ("Choose an event to edit", 160, 200);
		c.setColor (Color.gray);
		c.drawString ("Choose an event to edit", 158, 200);
		c.setColor (Color.black);
		c.fillRoundRect (100, 240, 300, 75, 40, 40);
		c.fillRoundRect (100, 340, 300, 75, 40, 40);
		c.fillRoundRect (100, 440, 300, 75, 40, 40);
		c.fillRoundRect (100, 540, 300, 75, 40, 40);

		c.fillRoundRect (430, 240, 300, 75, 40, 40);
		c.fillRoundRect (430, 340, 300, 75, 40, 40);
		c.fillRoundRect (430, 440, 300, 75, 40, 40);
		c.fillRoundRect (430, 540, 300, 75, 40, 40);

		c.setColor (Color.yellow);
		c.fillRoundRect (105, 245, 290, 65, 40, 40);
		c.fillRoundRect (105, 345, 290, 65, 40, 40);
		c.fillRoundRect (105, 445, 290, 65, 40, 40);
		c.fillRoundRect (105, 545, 290, 65, 40, 40);

		c.fillRoundRect (435, 245, 290, 65, 40, 40);
		c.fillRoundRect (435, 345, 290, 65, 40, 40);
		c.fillRoundRect (435, 445, 290, 65, 40, 40);
		c.fillRoundRect (435, 545, 290, 65, 40, 40);
		c.setFont (bankgothic3);
		c.setColor (Color.black);
		//loop to display the names of the events to edit
		for (int i = 0 ; i < FileList.length ; i++)
		{
		    if (FileList [i].isDirectory ())
		    {
			c.setFont (title);
			c.setColor (Color.black);
			if (y < 700 && x <= 500 && x < 435)
			{
			    c.drawString (
				    (FileList [i].getName ().replaceAll (".bin", "")
					+ " (" + (i + 1) + ")"), x, y);
			}
			y += 100;
			if (y == 685)
			{
			    x += 330;
			    y = 285;
			}
		    }
		    else if (FileList [i].isFile ())
		    {
			c.setFont (title);
			c.setColor (Color.black);
			if (y < 700 && x <= 500)
			{
			    c.drawString (
				    (FileList [i].getName ().replaceAll (".bin", "")
					+ " (" + (i + 1) + ")"), x, y);
			}
			y += 100;
			if (y == 685)
			{
			    x += 330;
			    y = 285;
			}
		    }
		}
		//The user can only pick between 1 to 8
		do
		{
		    convchoice = c.getChar ();
		}
		while (convchoice != '1' && convchoice != '2'
			&& convchoice != '3' && convchoice != '4'
			&& convchoice != '5' && convchoice != '6'
			&& convchoice != '7' && convchoice != '8');
		choice = convchoice - '0';

		//checks to see if the event is there, if there is, then break the loop
		for (int i = 0 ; i < FileList.length ; i++)
		{
		    if ((i + 1) == choice)
		    {
			event = FileList [i].getName ();
			Chosen = new File (
				"C:\\Users\\Jason\\Desktop\\CompSciCulm\\User\\"
				+ name + "\\" + FileList [i].getName ());
			cc = true;
		    }

		}
		//If false, then user has to pick again
		if (cc == false)
		{
		    x = 130;
		    y = 285;
		    convchoice = c.getChar ();
		}
	    }
	    while (choice < 1 || choice > FileList.length);

	    EventOptions eo = new EventOptions (name, event, type, year, month,
		    day);

	    //graphics for editing screen
	    c.setColor (Color.lightGray);
	    c.fillRect (80, 150, 680, 480);
	    c.setFont (bankgothic3);
	    c.setColor (Color.black);
	    c.drawString ("What part do you want to edit?", 160, 200);
	    c.setColor (Color.black);
	    c.fillRoundRect (240, 240, 350, 75, 40, 40);
	    c.fillRoundRect (240, 340, 350, 75, 40, 40);
	    c.fillRoundRect (240, 440, 350, 75, 40, 40);
	    c.fillRoundRect (240, 540, 350, 75, 40, 40);

	    c.setColor (Color.yellow);
	    c.fillRoundRect (245, 245, 340, 65, 40, 40);
	    c.fillRoundRect (245, 345, 340, 65, 40, 40);
	    c.fillRoundRect (245, 445, 340, 65, 40, 40);
	    c.fillRoundRect (245, 545, 340, 65, 40, 40);
	    c.setFont (bankgothic3);
	    c.setColor (Color.black);
	    c.drawString ("Name (1)", 295, 285);
	    c.drawString ("Hour (2)", 320, 385);
	    c.drawString ("Minute (3)", 330, 485);
	    c.drawString ("Description (4)", 340, 585);
	    //user can only pick between 1 to 8
	    do
	    {

		do
		{
		    convchoice = c.getChar ();
		}
		while (convchoice != '1' && convchoice != '2'
			&& convchoice != '3' && convchoice != '4'
			&& convchoice != ((char) 8));
		choice = convchoice - '0';
		wc = true;
	    }
	    while (wc == false);


	    // Title Box
	    int x2 = 160;
	    int y2 = 350;
	    // Hour Box
	    int x3 = 160;
	    int y3 = 400;
	    // Minute box
	    int x4 = 180;
	    int y4 = 450;
	    // Description box
	    int x5 = 220;
	    int y5 = 500;


	    c.setColor (Color.lightGray);
	    c.fillRect (80, 150, 680, 480);

	    c.setFont (bankgothic2);
	    c.setColor (Color.yellow);
	    c.drawString ("Edit an Event", 240, 220);

	    c.setFont (note);
	    c.setColor (Color.darkGray);
	    c.drawString ("(25 char max.)", 470, 370);
	    c.drawString ("(0 to 24)", 470, 420);
	    c.drawString ("(0 to 59)", 500, 470);
	    c.drawString ("(50 char max.)", 100, 550);

	    c.setColor (Color.black);
	    c.setFont (titlee);
	    c.drawString ("Title: ", 100, 370);
	    c.setColor (Color.white);
	    c.fillRect (x2, y2, 300, 30);

	    c.setColor (Color.black);
	    c.setFont (titlee);
	    c.drawString ("Hour: ", 100, 420);
	    c.setColor (Color.white);
	    c.fillRect (x3, y3, 300, 30);

	    c.setColor (Color.black);
	    c.setFont (titlee);
	    c.drawString ("Minute: ", 100, 470);
	    c.setColor (Color.white);
	    c.fillRect (x4, y4, 300, 30);

	    c.setColor (Color.black);
	    c.setFont (titlee);
	    c.drawString ("Description: ", 100, 520);
	    c.setColor (Color.white);
	    c.fillRect (x5, y5, 520, 30);
	    //if they choose 1, then change the name of the desired event
	    if (choice == 1)
	    {
		do
		{
		    c.setColor (Color.black);
		    c.drawRect (x2, y2, 300, 30);
		    newname = User (x2, y2);
		    renamed = eo.setEvent (newname);

		    if (renamed = false)
			c.print ("You used an illegal character or event name already existed, please try again.");
		}
		while (renamed = false);
	    }
	    //if they choose 2, then change the hour of the desired event
	    else if (choice == 2)
	    {
		do
		{
		    c.setColor (Color.white);
		    c.fillRect (x3, y3, 300, 30);
		    c.setColor (Color.black);
		    c.drawRect (x3, y3, 300, 30);
		    hour = Integer.parseInt (Time (x3, y3));

		}
		while (hour < 0 || hour > 23);

		eo.setHour (hour);
	    }
	    //if they choose 3, then change the minute of the desired event
	    else if (choice == 3)
	    {
		do
		{
		    c.setColor (Color.white);
		    c.fillRect (x4, y4, 300, 30);
		    c.setColor (Color.black);
		    c.drawRect (x4, y4, 300, 30);
		    min = Integer.parseInt (Time (x4, y4));

		}
		while (min < 0 || min > 59);

		eo.setMin (min);
	    }
	    //if they choose 4, then change the description of the desired event

	    else if (choice == 4)
	    {
		do
		{
		    c.setColor (Color.white);
		    c.fillRect (x5, y5, 520, 30);
		    c.setColor (Color.black);
		    c.drawRect (x5, y5, 520, 30);
		    desc = Memo (x5, y5);
		}
		while (desc.length () > 50);

		eo.setDesc (desc);

	    }
	    //proceeed on to the main screen again
	    c.setFont (notee);
	    c.setColor (Color.blue);
	    c.drawString ("Press 'BackSpace' to go back to the Main Screen!",
		    100, 600);
	    char presstocontinue;
	    do
	    {
		presstocontinue = c.getChar ();
	    }
	    while (presstocontinue != ((char) 8));
	    EventsMain (year, month, day);
	}
    }

    //method to view the event
    public static void view (int year, int month, int day) throws IOException
    {

	String name = getLastLogin ();
	File Folder = new File (
		"C:\\Users\\Jason\\Desktop\\CompSciCulm\\User\\"
		+ name + "\\" + year + "\\" + month + "\\" + day);
	File[] FolderList = Folder.listFiles ();
	char convchoice;
	int choice;
	int x = 130;
	int y = 285;
	String Stype = "";
	String event = "", type = "";
	boolean cc;
	File Chosen = new File ("");
	Font bankgothic2 = new Font ("Berlin Sans FB Demi", Font.BOLD, 50);
	Font bankgothic3 = new Font ("Berlin Sans FB Demi", Font.PLAIN, 30);
	Font title = new Font ("Berlin Sans FB Demi", Font.PLAIN, 20);

	//graphics for the main screen of the view screen
	c.setColor (Color.lightGray);
	c.fillRect (80, 150, 680, 480);
	c.setFont (bankgothic2);
	c.setColor (Color.yellow);
	c.drawString ("View Event", 280, 200);
	c.setColor (Color.gray);
	c.drawString ("View Event", 278, 200);

	Font warning = new Font ("Comic Sans MS", Font.PLAIN, 15);
	c.setFont (warning);
	c.setColor (Color.black);
	c.drawString (
		"* If there is nothing under the category, you will be automatically directed to the Main Screen",
		90, 225);

	c.setColor (Color.black);
	c.fillRoundRect (240, 240, 350, 75, 40, 40);
	c.fillRoundRect (240, 340, 350, 75, 40, 40);
	c.fillRoundRect (240, 440, 350, 75, 40, 40);
	c.fillRoundRect (240, 540, 350, 75, 40, 40);

	c.setColor (Color.yellow);
	c.fillRoundRect (245, 245, 340, 65, 40, 40);
	c.fillRoundRect (245, 345, 340, 65, 40, 40);
	c.fillRoundRect (245, 445, 340, 65, 40, 40);
	c.fillRoundRect (245, 545, 340, 65, 40, 40);

	c.setFont (bankgothic3);
	c.setColor (Color.black);
	c.drawString ("Appointments (1)", 295, 285);
	c.drawString ("Birthdays (2)", 320, 385);
	c.drawString ("Holiday (3)", 330, 485);
	c.drawString ("Travel (4)", 340, 585);

	do
	{
	    cc = false;
	    //user can only pick between 1 to 4 
	    do
	    {
		convchoice = c.getChar ();
	    }
	    while (convchoice != '1' && convchoice != '2'
		    && convchoice != '3' && convchoice != '4');
	    choice = convchoice - '0';
	    //checks to see if the folder contains the list of events, then break the loop
	    for (int i = 0 ; i < FolderList.length ; i++)
	    {
		if ((i + 1) == choice)
		{
		    type = FolderList [i].getName ();
		    Chosen = new File (Folder + "\\" + FolderList [i].getName ());
		    Stype = FolderList [i].getName ();
		    cc = true;
		}

	    }
	    if (cc == false)
	    {
		convchoice = c.getChar ();
	    }
	}
	while (choice < 1 && choice > FolderList.length);

	File[] FileList = Chosen.listFiles ();

	File f = Chosen;
	//if the list is empty, then just proceed to events main screen
	if (!(f.list ().length > 0))
    {
	c.clear ();
	EventsMain (year, month, day);

    }

	else
	{
	    do
	    {
		cc = false;
		//graphics for user to pick which event to view
		c.setColor (Color.lightGray);
		c.fillRect (80, 150, 680, 480);

		c.setFont (bankgothic2);
		c.setColor (Color.yellow);
		c.drawString ("Choose an event to view", 150, 200);
		c.setColor (Color.gray);
		c.drawString ("Choose an event to view", 148, 200);
		c.setColor (Color.black);
		c.fillRoundRect (100, 240, 300, 75, 40, 40);
		c.fillRoundRect (100, 340, 300, 75, 40, 40);
		c.fillRoundRect (100, 440, 300, 75, 40, 40);
		c.fillRoundRect (100, 540, 300, 75, 40, 40);

		c.fillRoundRect (430, 240, 300, 75, 40, 40);
		c.fillRoundRect (430, 340, 300, 75, 40, 40);
		c.fillRoundRect (430, 440, 300, 75, 40, 40);
		c.fillRoundRect (430, 540, 300, 75, 40, 40);

		c.setColor (Color.yellow);
		c.fillRoundRect (105, 245, 290, 65, 40, 40);
		c.fillRoundRect (105, 345, 290, 65, 40, 40);
		c.fillRoundRect (105, 445, 290, 65, 40, 40);
		c.fillRoundRect (105, 545, 290, 65, 40, 40);

		c.fillRoundRect (435, 245, 290, 65, 40, 40);
		c.fillRoundRect (435, 345, 290, 65, 40, 40);
		c.fillRoundRect (435, 445, 290, 65, 40, 40);
		c.fillRoundRect (435, 545, 290, 65, 40, 40);

		//loop to see the list of the list of events in the file
		//then displays the title of events on the screen
		for (int i = 0 ; i < FileList.length ; i++)
		{
		    if (FileList [i].isDirectory ())
		    {
			c.setFont (title);
			c.setColor (Color.black);
			if (y < 700 && x <= 500 && x <= 300)
			{
			    c.drawString (
				    (FileList [i].getName ().replaceAll (".bin", "")
					+ " (" + (i + 1) + ")"), x, y);
			}
			y += 100;
			if (y == 685)
			{
			    x += 330;
			    y = 285;
			}
		    }
		    else if (FileList [i].isFile ())
		    {
			c.setFont (title);
			c.setColor (Color.black);
			if (y < 700 && x <= 500 && x <= 500)
			{
			    c.drawString (
				    (FileList [i].getName ().replaceAll (".bin", "")
					+ " (" + (i + 1) + ")"), x, y);
			}
			y += 100;
			//to move the cursor to the next row
			if (y == 685)
			{
			    x += 330;
			    y = 285;
			}
		    }
		}
		//error checkign to make sure the user can only see 8 events on the screen
		do
		{
		    convchoice = c.getChar ();
		}
		while (convchoice != '1' && convchoice != '2'
			&& convchoice != '3' && convchoice != '4'
			&& convchoice != '5' && convchoice != '6'
			&& convchoice != '7' && convchoice != '8');

		choice = convchoice - '0';

		for (int i = 0 ; i < FileList.length ; i++)
		{
		    if ((i + 1) == choice)
		    {
			event = FileList [i].getName ();
			Chosen = new File (
				"C:\\Users\\Jason\\Desktop\\CompSciCulm\\User\\"
				+ name + "\\" + FileList [i].getName ());
			cc = true;
		    }

		}
		if (cc == false)
		{
		    x = 130;
		    y = 285;
		    convchoice = c.getChar ();
		}

	    }
	    while (choice < 1 || choice > FileList.length);

	    EventOptions eo = new EventOptions (name, event, type, year, month,
		    day);

	    //displays the event 
	    Font titlee = new Font ("Comic Sans MS", Font.PLAIN, 20);
	    Font writeit = new Font ("Comic Sans MS", Font.PLAIN, 20);

	    // Title Box
	    int x2 = 160;
	    int y2 = 350;
	    // Hour Box
	    int x3 = 160;
	    int y3 = 400;
	    // Minute box
	    int x4 = 180;
	    int y4 = 450;
	    // Description box
	    int x5 = 220;
	    int y5 = 500;

	    c.setColor (Color.lightGray);
	    c.fillRect (80, 150, 680, 480);

	    c.setFont (bankgothic2);
	    c.setColor (Color.yellow);
	    c.drawString ("View the Event", 240, 220);

	    c.setColor (Color.black);
	    c.setFont (titlee);
	    c.drawString ("Title: ", 100, 370);
	    c.setColor (Color.white);
	    c.fillRect (x2, y2, 300, 30);

	    c.setColor (Color.black);
	    c.setFont (titlee);
	    c.drawString ("Hour: ", 100, 420);
	    c.setColor (Color.white);
	    c.fillRect (x3, y3, 300, 30);

	    c.setColor (Color.black);
	    c.setFont (titlee);
	    c.drawString ("Minute: ", 100, 470);
	    c.setColor (Color.white);
	    c.fillRect (x4, y4, 300, 30);

	    c.setColor (Color.black);
	    c.setFont (titlee);
	    c.drawString ("Description: ", 100, 520);
	    c.setColor (Color.white);
	    c.fillRect (x5, y5, 520, 30);

	    c.setFont (writeit);
	    c.setColor (Color.black);
	    c.drawString (eo.getEvent (), x2 + 10, y2 + 20);
	    c.drawString ((eo.getHour () + ""), x3 + 10, y3 + 20);
	    c.drawString ((eo.getMin () + ""), x4 + 10, y4 + 20);
	    c.drawString (eo.getDesc (), x5 + 10, y5 + 20);

	    Font notee = new Font ("Comic Sans MS", Font.PLAIN, 20);

	    c.setFont (notee);
	    c.setColor (Color.blue);
	    c.drawString ("Press 'BackSpace' to go back to the Main Screen!",
		    100, 600);
	    char presstocontinue;
	    do
	    {
		presstocontinue = c.getChar ();
	    }
	    while (presstocontinue != ((char) 8));
	    EventsMain (year, month, day);
	}
    }

    //method used to create the event
    public static void create (int year, int month, int day) throws IOException
    {

	String name = getLastLogin ();
	//fonts used
	Font bankgothic2 = new Font ("Berlin Sans FB Demi", Font.BOLD, 50);
	Font bankgothic3 = new Font ("Berlin Sans FB Demi", Font.PLAIN, 30);
	Font note = new Font ("Comic Sans MS", Font.BOLD, 15);
	Font titlee = new Font ("Comic Sans MS", Font.PLAIN, 20);
	Font writeit = new Font ("Comic Sans MS", Font.PLAIN, 20);
	Font memo = new Font ("Comic Sans MS", Font.PLAIN, 10);

	//graphics for main screen of create screen
	// Event box
	int x1 = 240;
	int y1 = 260;
	// Title Box
	int x2 = 160;
	int y2 = 350;
	// Hour Box
	int x3 = 160;
	int y3 = 400;
	// Minute box
	int x4 = 180;
	int y4 = 450;
	// Description box
	int x5 = 220;
	int y5 = 500;

	boolean clear = false;
	boolean cc;
	String desc;
	char convchoice;
	int choice;
	File Chosen = new File ("");
	File Folder = new File (
		"C:\\Users\\Jason\\Desktop\\CompSciCulm\\User\\"
		+ name + "\\" + year + "\\" + month + "\\" + day);
	File[] FileList = Folder.listFiles ();
	String title;
	int hour = 0, min = 0;

	
	c.setColor (Color.lightGray);
	c.fillRect (80, 150, 680, 480);

	c.setFont (bankgothic2);
	c.setColor (Color.yellow);
	c.drawString ("Create Event", 250, 220);
	c.setColor (Color.gray);
	c.drawString ("Create Event", 248, 220);

	c.setFont (titlee);
	c.setColor (Color.black);
	c.drawString ("Event Type: ", 100, 280);

	c.setColor (Color.white);
	c.fillRect (x1, y1, 300, 30);

	c.setFont (note);
	c.setColor (Color.darkGray);
	c.drawString (
		"Press (1) for Appointments, (2) for Birthdays (3) for Holiday, (4) for Travel",
		90, 320);
	c.drawString ("(25 char max.)", 470, 370);
	c.drawString ("(0 to 24)", 470, 420);
	c.drawString ("(0 to 59)", 500, 470);
	c.drawString ("(50 char max.)", 100, 550);

	c.setColor (Color.black);
	c.setFont (titlee);
	c.drawString ("Title: ", 100, 370);
	c.setColor (Color.white);
	c.fillRect (x2, y2, 300, 30);

	c.setColor (Color.black);
	c.setFont (titlee);
	c.drawString ("Hour: ", 100, 420);
	c.setColor (Color.white);
	c.fillRect (x3, y3, 300, 30);

	c.setColor (Color.black);
	c.setFont (titlee);
	c.drawString ("Minute: ", 100, 470);
	c.setColor (Color.white);
	c.fillRect (x4, y4, 300, 30);

	c.setColor (Color.black);
	c.setFont (titlee);
	c.drawString ("Description: ", 100, 520);
	c.setColor (Color.white);
	c.fillRect (x5, y5, 520, 30);

	c.setColor (Color.black);
	c.drawRect (x1, y1, 300, 30);

	do
	{
	    cc = false;
	    //User only chooses between 1 to 4, to determine the type of the event they 
	    //wish to create
	    do
	    {
		convchoice = c.getChar ();
	    }
	    while (convchoice != '1' && convchoice != '2'
		    && convchoice != '3' && convchoice != '4');
	    choice = convchoice - '0';
	    for (int i = 0 ; i < FileList.length ; i++)
	    {
		if ((i + 1) == choice)
		{
		    c.drawString (FileList [i].getName () + "", x1 + 20, y1 + 22);
		    Chosen = new File (Folder + "\\" + FileList [i].getName ());
		    cc = true;
		}

	    }

	

	    if (cc == false)
	    {
		convchoice = c.getChar ();
	    }

	}
	while (choice < 1 && choice > FileList.length);
	c.setColor (Color.blue);
	c.drawRect (x1, y1, 300, 30);
	c.setColor (Color.white);
	c.fillRect (x2, y2, 300, 30);
	c.setColor (Color.black);
	c.drawRect (x2, y2, 300, 30);

	//allow the user to type in the title of the event
	//displays the characters
	do
	{

	    title = User (x2, y2);
	    clear = Restricted (title, Chosen);

	}
	while (clear == false);

	c.setColor (Color.blue);
	c.drawRect (x2, y2, 300, 30);
	c.setColor (Color.lightGray);
	c.fillRect (85, 385, 600, 13);

	//allow the user to type in the hour of the event
	//displays the characters, ONLY ACCEPTING 2 CHAR MAX
	do
	{
	    c.setColor (Color.white);
	    c.fillRect (x3, y3, 300, 30);
	    c.setColor (Color.black);
	    c.drawRect (x3, y3, 300, 30);
	    hour = Integer.parseInt (Time (x3, y3));
	}
	while (hour < 0 || hour > 23);
	c.setColor (Color.blue);
	c.drawRect (x3, y3, 300, 30);
	//allow the user to type in the minute of the event
	//displays the characters, ONLY ACCEPTING 2 CHAR MAX

	do
	{
	    c.setColor (Color.white);
	    c.fillRect (x4, y4, 300, 30);
	    c.setColor (Color.black);
	    c.drawRect (x4, y4, 300, 30);
	    min = Integer.parseInt (Time (x4, y4));
	}
	while (min < 0 || min > 59);
	
	c.setColor (Color.blue);
	c.drawRect (x4, y4, 300, 30);
	
	//allow the user to type in the description of the event
	//displays the characters (up to 50 char)
	do
	{
	    c.setColor (Color.white);
	    c.fillRect (x5, y5, 520, 30);
	    c.setColor (Color.black);
	    c.drawRect (x5, y5, 520, 30);
	    desc = Memo (x5, y5);
	}
	while (desc.length () > 50);

	RandomAccessFile ev = new RandomAccessFile (Chosen + "\\" + title
		+ ".bin", "rw");

	byte[] descBytes = new byte [50];
	desc.getBytes (0, desc.length (), descBytes, 0);


	ev.seek (0);

	ev.writeInt (hour);
	ev.writeInt (min);
	ev.write (descBytes);

	ev.close ();
	Font notee = new Font ("Comic Sans MS", Font.PLAIN, 20);

	c.setFont (notee);
	c.drawString ("Event Successfully Created!", 100, 580);
	c.drawString ("Press 'BackSpace' to go back to the Main Screen!", 100,
		600);
	char presstocontinue;
	do
	{
	    presstocontinue = c.getChar ();
	}
	while (presstocontinue != ((char) 8));
	EventsMain (year, month, day);

    }

    //method used to determine the person with the last login
    //originally had the function to display the to-do list, but we
    //got rid of the string value, therefore this method is not
    //necessary in contributing to the whole program.
    public static String getLastLogin () throws IOException
    {
	RandomAccessFile raf = new RandomAccessFile (
		"C:\\Users\\Jason\\Desktop\\CompSciCulm\\Logins\\LastLogin.bin",
		"rw");

	byte[] nameBytes = new byte [20];
	String name;

	raf.seek (0);
	raf.read (nameBytes);

	name = new String (nameBytes, 0);

	name = name.trim ();

	raf.close ();

	return name;
    }

    //method to ensure the user cannot create username that has restricted
    //characters
    public static boolean Restricted (String UserName, File Chosen)
    {
	File[] FileList = Chosen.listFiles ();
	Font alert = new Font ("MV BOLI", Font.BOLD, 13);
	int x2 = 160;
	int y2 = 350;
	boolean bcksl = false, fwdsl = false, colon = false, astericks = false, question = false, quotations = false;
	boolean grtthn = false, lsthn = false, stick = false, restricted = false;

	for (int i = 0 ; i < UserName.length () ; i++)
	{
	    if (UserName.charAt (i) == 92)
		bcksl = true;
	    if (UserName.charAt (i) == 47)
		fwdsl = true;
	    if (UserName.charAt (i) == 58)
		colon = true;
	    if (UserName.charAt (i) == 42)
		astericks = true;
	    if (UserName.charAt (i) == 63)
		question = true;
	    if (UserName.charAt (i) == 34)
		quotations = true;
	    if (UserName.charAt (i) == 60)
		lsthn = true;
	    if (UserName.charAt (i) == 62)
		grtthn = true;
	    if (UserName.charAt (i) == 124)
		stick = true;
	}


	if (bcksl == false && fwdsl == false && colon == false
		&& astericks == false && question == false
		&& quotations == false && lsthn == false && grtthn == false
		&& stick == false)
	{
	    restricted = true;
	}
	else
	{
	    c.setColor (Color.lightGray);
	    c.fillRect (85, 385, 600, 13);
	    c.setFont (alert);
	    c.setColor (Color.red);
	    c.drawString ("Your event name contains restricted characters!",
		    100, 395);
	    c.setColor (Color.white);
	    c.fillRect (x2, y2, 300, 30);
	    c.setColor (Color.pink);
	    c.drawRect (x2, y2, 300, 30);
	}

	if (FileList != null)
	{
	    for (int i = 0 ; i < FileList.length ; i++)
	    {

		if (FileList [i].isFile ())
		{

		    if (FileList [i].getName ().replaceAll (".bin", "")
			    .equals (UserName))
		    {
			c.setColor (Color.lightGray);
			c.fillRect (85, 385, 600, 13);
			c.setFont (alert);
			c.setColor (Color.red);
			c.drawString ("Event name already exists!", 100, 395);
			c.setColor (Color.white);
			c.fillRect (x2, y2, 300, 30);
			c.setColor (Color.pink);
			c.drawRect (x2, y2, 300, 30);
			restricted = false;
		    }
		}
	    }
	}

	return restricted;
    }

    //this method is used so the user can type in
    //the desired title in the create screen
    //shows the characters they put in
    public static String User (int x, int y)
    {
	Font note = new Font ("Comic Sans MS", Font.PLAIN, 20);

	c.setFont (note);
	// Create two string variables, username being the actual string value
	// of username and prevusername referring to the username with one less
	// character
	String username = "";
	String prevusername = "";
	char usernamechar;
	do
	{
	    c.setColor (Color.black);
	    usernamechar = c.getChar ();
	    // if the char the user presses is backspace
	    if (usernamechar == ((char) 8))
	    {
		// Setting the if statement so that backspace only occurs within
		// the box range
		for (int b = 0 ; b < username.length () - 1 ; b++) // declares
		    // previous
		    // username to
		    // equal one
		    // less
		    // character
		    // than username
		    {
			prevusername = prevusername + username.charAt (b);
		    }
		c.setColor (Color.white); // When user presses backspace, the
		// character originally there is
		// 'erased'
		c.fillRect (x, y, 300, 30);
		c.setColor (Color.black);
		c.drawRect (x, y, 300, 30);
		c.drawString (prevusername, x + 10, y + 20);

		username = prevusername; // username equals prevusername, which
		// is one character less than what's
		// in the username value
		prevusername = ""; // reset the prevusername to equal nothing
		continue;

	    }
	    // if the user presses enter, proceed on to next box
	    if (usernamechar == ((char) 10))
	    {
		break;
	    }
	    // if user presses anything other than backspace or enter, then draw
	    // the character on the box
	    if (usernamechar != ((char) 8) && usernamechar != ((char) 10)
		    && username.length () < 25)
	    {
		username += usernamechar + "";
		c.drawString ("" + username, x + 10, y + 20);
	    }
	}
	while (usernamechar != ((char) 10));
	return (username);
    }

    //this method is used so the user can type in
    //the desired hour, minutes in the create screen
    //shows the characters they put in
    public static String Time (int x, int y)
    {
	Font note = new Font ("Comic Sans MS", Font.PLAIN, 20);
	c.setFont (note);
	// Create two string variables, username being the actual string value
	// of username and prevusername referring to the username with one less
	// character
	String username = "";
	String prevusername = "";
	char usernamechar;
	do
	{
	    c.setColor (Color.black);
	    usernamechar = c.getChar ();
	    // if the char the user presses is backspace
	    if (usernamechar == ((char) 8))
	    {
		// Setting the if statement so that backspace only occurs within
		// the box range
		for (int b = 0 ; b < username.length () - 1 ; b++) // declares
		    // previous
		    // username to
		    // equal one
		    // less
		    // character
		    // than username
		    {
			prevusername = prevusername + username.charAt (b);
		    }
		c.setColor (Color.white); // When user presses backspace, the
		// character originally there is
		// 'erased'
		c.fillRect (x, y, 300, 30);
		c.setColor (Color.black);
		c.drawRect (x, y, 300, 30);
		c.drawString (prevusername, x + 10, y + 20);

		username = prevusername; // username equals prevusername, which
		// is one character less than what's
		// in the username value
		prevusername = ""; // reset the prevusername to equal nothing
		continue;

	    }
	    // if the user presses enter, proceed on to next box
	    if (usernamechar == ((char) 10))
	    {
		break;
	    }
	    // if user presses anything other than backspace or enter, then draw
	    // the character on the box
	    if (usernamechar != ((char) 8) && usernamechar != ((char) 10)
		    && username.length () < 2)
	    {
		username += usernamechar + "";
		c.drawString ("" + username, x + 10, y + 20);
	    }
	}
	while (usernamechar != ((char) 10));
	return (username);
    }

    //this method is used so the user can type in
    //the desired description in the create screen
    //shows the characters they put in
    public static String Memo (int x, int y)
    {
	Font note = new Font ("Comic Sans MS", Font.BOLD, 13);
	c.setFont (note);
	// Create two string variables, username being the actual string value
	// of username and prevusername referring to the username with one less
	// character
	String username = "";
	String prevusername = "";
	char usernamechar;
	do
	{
	    c.setColor (Color.black);
	    usernamechar = c.getChar ();
	    // if the char the user presses is backspace
	    if (usernamechar == ((char) 8))
	    {
		// Setting the if statement so that backspace only occurs within
		// the box range
		for (int b = 0 ; b < username.length () - 1 ; b++) // declares
		    // previous
		    // username to
		    // equal one
		    // less
		    // character
		    // than username
		    {
			prevusername = prevusername + username.charAt (b);
		    }
		c.setColor (Color.white); // When user presses backspace, the
		// character originally there is
		// 'erased'
		c.fillRect (x, y, 520, 30);
		c.setColor (Color.black);
		c.drawRect (x, y, 520, 30);
		c.drawString (prevusername, x + 10, y + 20);

		username = prevusername; // username equals prevusername, which
		// is one character less than what's
		// in the username value
		prevusername = ""; // reset the prevusername to equal nothing
		continue;

	    }
	    // if the user presses enter, proceed on to next box
	    if (usernamechar == ((char) 10))
	    {
		break;
	    }
	    // error checking so that it only displays the character if
	    // the user inputs everything but enter, backspace
	    //limited to 60 characters
	    if (usernamechar != ((char) 8) && usernamechar != ((char) 10)
		    && username.length () < 60)
	    {
		username += usernamechar + "";
		c.drawString ("" + username, x + 10, y + 20);
	    }
	}
	while (usernamechar != ((char) 10));
	//returns the string value of the description
	return (username);
    }
    
    
    
    public static void PresstoContinue ()
    {
	Color BackGround = new Color (0, 191, 191);
	Font bankgothic = new Font ("Berlin Sans FB Demi", Font.BOLD, 90);
	Font impact = new Font ("Impact", Font.PLAIN, 30);
	//The x,y are coordinates for the username box
	int x = 250;
	int y = 320;
	//The x2,y2 are coordinates for the password box
	int x2 = 250;
	int y2 = 450;
	c.setFont (bankgothic);
	c.setColor (BackGround);
	c.fillRect (0, 0, 1000, 1000);
	c.setColor (Color.gray);
	c.drawString ("Memory Bound", 100, 180);
	c.setColor (Color.yellow);
	c.drawString ("Memory Bound", 96, 178);
	//creating the boxes for username and password
	c.setFont (impact);
	c.setColor (Color.white);
	c.drawString ("Press anything to continue!", 400, 600);
    }
    public static void showEvent (String name, String event, String type, int year, int month , int day) throws IOException
    {
	Font titlee = new Font ("Comic Sans MS", Font.PLAIN, 20);
	Font bankgothic2 = new Font ("Berlin Sans FB Demi", Font.BOLD, 50);
	Font bankgothic3 = new Font ("Berlin Sans FB Demi", Font.PLAIN, 30);
	Font title = new Font ("Berlin Sans FB Demi", Font.PLAIN, 20);
	    Font writeit = new Font ("Comic Sans MS", Font.PLAIN, 20);

	    EventOptions eo = new EventOptions (name, event, type, year, month,day);

	 // Title Box
	    int x2 = 160;
	    int y2 = 350;
	    // Hour Box
	    int x3 = 160;
	    int y3 = 400;
	    // Minute box
	    int x4 = 180;
	    int y4 = 450;
	    // Description box
	    int x5 = 220;
	    int y5 = 500;

	    c.setColor (Color.lightGray);
	    c.fillRect (80, 150, 680, 480);

	    c.setFont (bankgothic2);
	    c.setColor (Color.yellow);
	    c.drawString ("View the Event", 240, 220);

	    c.setColor (Color.black);
	    c.setFont (titlee);
	    c.drawString ("Title: ", 100, 370);
	    c.setColor (Color.white);
	    c.fillRect (x2, y2, 300, 30);

	    c.setColor (Color.black);
	    c.setFont (titlee);
	    c.drawString ("Hour: ", 100, 420);
	    c.setColor (Color.white);
	    c.fillRect (x3, y3, 300, 30);

	    c.setColor (Color.black);
	    c.setFont (titlee);
	    c.drawString ("Minute: ", 100, 470);
	    c.setColor (Color.white);
	    c.fillRect (x4, y4, 300, 30);

	    c.setColor (Color.black);
	    c.setFont (titlee);
	    c.drawString ("Description: ", 100, 520);
	    c.setColor (Color.white);
	    c.fillRect (x5, y5, 520, 30);

	    c.setFont (writeit);
	    // c.println((eo.toString()));
	    c.setColor (Color.black);
	    c.drawString (eo.getEvent (), x2 + 10, y2 + 20);
	    c.drawString ((eo.getHour () + ""), x3 + 10, y3 + 20);
	    c.drawString ((eo.getMin () + ""), x4 + 10, y4 + 20);
	    c.drawString (eo.getDesc (), x5 + 10, y5 + 20);
    }
} // CulminatingMain class

