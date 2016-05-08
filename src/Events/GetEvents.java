package Events;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.RandomAccessFile;

public class GetEvents {

	private int year, month, day;

	public GetEvents(int y, int m, int d) {
		this.year = y;
		this.month = m;
		this.day = d;

	}

	public boolean getAppt() throws IOException {
		String name = getLastLogin();

		File Folder = new File(
				"C:\\Users\\Jason\\Desktop\\CompSciCulm\\User\\"
						+ name + "\\" + year + "\\" + month + "\\" + day
						+ "\\Appointments");
		File[] FolderList = Folder.listFiles();// file array for list of files
												// in the folder

		// System.out.println(FolderList.length);
		if (FolderList != null) {
			if (FolderList.length == 0) {
				return false;
			} else {
				return true;
			}
		}
		return false;

	}

	public boolean getBirth() throws IOException {
		String name = getLastLogin();

		File Folder = new File(
				"C:\\Users\\Jason\\Desktop\\CompSciCulm\\User\\"
						+ name + "\\" + year + "\\" + month + "\\" + day
						+ "\\Birthdays");
		File[] FolderList = Folder.listFiles();// file array for list of files
												// in the folder

		// System.out.println(FolderList.length);
		if (FolderList != null) {
			if (FolderList.length == 0) {
				return false;
			} else {
				return true;
			}
		}
		return false;
	}

	public boolean getHol() throws IOException {
		String name = getLastLogin();

		File Folder = new File(
				"C:\\Users\\Jason\\Desktop\\CompSciCulm\\User\\"
						+ name + "\\" + year + "\\" + month + "\\" + day
						+ "\\Holiday");
		File[] FolderList = Folder.listFiles();// file array for list of files
												// in the folder
		if (FolderList != null) {
			if (FolderList.length == 0) {
				return false;
			} else {
				return true;
			}
		}
		return false;

	}

	public boolean getTravel() throws IOException {
		String name = getLastLogin();

		File Folder = new File(
				"C:\\Users\\Jason\\Desktop\\CompSciCulm\\User\\"
						+ name + "\\" + year + "\\" + month + "\\" + day
						+ "\\Travel");
		File[] FolderList = Folder.listFiles();// file array for list of files
												// in the folder
		if (FolderList != null) {
			if (FolderList.length == 0) {
				return false;
			} else {
				return true;
			}
		}
		return false;
	}

	private String getLastLogin() throws IOException {
		RandomAccessFile raf = new RandomAccessFile(
				"C:\\Users\\Jason\\Desktop\\CompSciCulm\\Logins\\LastLogin.bin",
				"rw");
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		byte[] nameBytes = new byte[20];
		String name;

		raf.seek(0);
		raf.read(nameBytes);

		name = new String(nameBytes, 0);

		name = name.trim();

		// System.out.println(name);

		raf.close();

		return name;
	}

}
