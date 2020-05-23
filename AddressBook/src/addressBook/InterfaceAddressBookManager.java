package addressBook;

import java.io.File;
import java.io.IOException;

public interface InterfaceAddressBookManager {
	
	public  boolean newBook(String name) throws IOException;
	public  void showFiles ();
	public  File openFile(String fileName);
    public  void addPersonInAddressBook(Person p,File f ) throws IOException;
    public  File viewrecords(String fname);
    public  void sortByLastName(File file) throws IOException;
    public  void sortByZip(File file) throws IOException;
    public  File deleteRecord(File file,String name) throws IOException ;
    public void updateRecord(String fileName,String name) throws IOException;
}
