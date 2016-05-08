package Events;
import java.io.*;


public class EventOptions {

	private int hour, min;
	private String desc;
	private String name,event,type;
	private int year,month,day;
	public EventOptions (String n, String e,String t,int y, int m, int d)throws IOException
    {
		this.name = n;
		this.event = e;
		this.type = t;
		this.year = y;
		this.month = m;
		this.day = d;
		
		RandomAccessFile raf = new RandomAccessFile("C:\\Users\\Jason\\Desktop\\CompSciCulm\\User\\" + name + "\\" + year + "\\" + month
				+ "\\" + day+"\\"+type+"\\"+event,"rw");
		
		hour = raf.readInt();
		min = raf.readInt();
		
		byte[] nameBytes = new byte[50];
		raf.read(nameBytes);
		desc = new String(nameBytes,0);
		desc = desc.trim();
		
		raf.close();//reads all the info for the event then closes the binary file
		

    }
	public String alert()
	{
		return (event+", description: "+desc);
	}
	
	public boolean setEvent (String newName)throws IOException
    {
		 File old = new File("C:\\Users\\Jason\\Desktop\\CompSciCulm\\User\\" + name + "\\" + year + "\\" + month
					+ "\\" + day+"\\"+type+"\\"+event);

		    // File with new name
		    File file2 = new File("C:\\Users\\Jason\\Desktop\\CompSciCulm\\User\\" + name + "\\" + year + "\\" + month
					+ "\\" + day+"\\"+type+"\\"+newName+".bin");
		    if(file2.exists()) throw new java.io.IOException("file exists");

		    event = newName;
		    
		    boolean success = old.renameTo(file2);
		    if (!success) {
			return false;
		    }
		    else
			return true;
		    
		    
    } // renames the file 


    //
    public String getEvent ()throws IOException
    {   
	return event;
    } // returns event name


    
    public void setHour (int h)throws IOException
    {
	RandomAccessFile raf = new RandomAccessFile("C:\\Users\\Jason\\Desktop\\CompSciCulm\\User\\" + name + "\\" + year + "\\" + month
					+ "\\" + day+"\\"+type+"\\"+event,"rw");
	raf.seek(0);
	raf.writeInt(h);
	raf.close();
	hour = h;
    } //sets the hour to the new hour the user inputed


    
    public int getHour()
    {
	return hour;
    } //gets hour


    
    public void setMin (int m)throws IOException
    {
	RandomAccessFile raf = new RandomAccessFile("C:\\Users\\Jason\\Desktop\\CompSciCulm\\User\\" + name + "\\" + year + "\\" + month
					+ "\\" + day+"\\"+type+"\\"+event,"rw");
	int h = raf.readInt();
	raf.seek(0);
	raf.writeInt(h);
	raf.writeInt(m);
	raf.close();
	min = m;        
    } //sets the mintue to the new minute the user inputed


    public int getMin ()
    {
	return min;
    } //returns minute
    
    public void setDesc(String d)throws IOException
    {
	RandomAccessFile raf = new RandomAccessFile("C:\\Users\\Jason\\Desktop\\CompSciCulm\\User\\" + name + "\\" + year + "\\" + month
					+ "\\" + day+"\\"+type+"\\"+event,"rw");

		byte[] descBytes = new byte[50];
		d.getBytes(0,d.length(),descBytes,0);
		int h = raf.readInt();
	int m = raf.readInt();
		raf.seek(0);
	raf.writeInt(h);
	raf.writeInt(m);
	raf.write(descBytes);
	raf.close();
	desc = d;
    }// sets the description to what the user inputed
    public String getDesc()
    {
	return desc;
    }//returns the description
    
    public String toString()
    {
	String dishour = "0",dismin = "0";
	
	if(hour < 10)
	{
		dishour = dishour+Integer.toString(hour);
	}
	if(min < 10)
	{
		dismin = dismin+Integer.toString(min);
	}
	return "sup";
	/*if(min<10&&hour<10)
		return(getevent.replace(".bin", "")+" is at "+dishour+":"+dismin+". notes: "+desc);
	else if(min<10)
		return(event.replace(".bin", "")+" is at "+hour+":"+dismin+". notes: "+desc);
	if(hour<10)
		return(event.replace(".bin", "")+" is at "+dishour+":"+min+". notes: "+desc);   
	else
		return(event.replace(".bin", "")+" is at "+hour+":"+min+". notes: "+desc);*/
    }//returns all the info for the event in the correct formatting
    
}
