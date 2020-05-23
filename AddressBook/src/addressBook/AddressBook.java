package addressBook;

import java.io.File;
import java.util.Scanner;

public class AddressBook {

    public static void main(String[] args) throws Exception {
        String firstName,lastName,phoneNumber;
        File openBook=null;
        int repeat = 1;
        AddressBookManager am=new AddressBookManager();
        Scanner scanner = new Scanner(System.in);
        while (repeat == 1) {
            System.out.println("Enter the choice \n 1. Create New Address Book\n2. open files \n3.Edit Address Book \n4.sort Records \n5.close Address Book \n6.Exit ");
            int choice = scanner.nextInt();   // get user's choice
            switch (choice) {
                // to create address book
                case 1:
                    System.out.println("Enter address book name");
                    String addressBookName = scanner.next();
                    if (am.newBook(addressBookName))
                        System.out.println("new Address Book created");
                    else
                        System.out.println("address book already created");
                    break;

                case 2:
                    if(openBook==null) {
                      am.showFiles();
                        System.out.println("Enter the name of Address Book Which you want to open");
                        String name=scanner.next();
                        openBook = am.openFile(name);
                    }
                    else {
                        System.out.println("File already open!please close the open file");
                    }
                    break;
                // to add person in address book

                case 3:
                    System.out.println("Enter the choice \n1 add Person in address book \n2 update person info \n3 delete record");
                    int choice2=scanner.nextInt();
                    switch (choice2) {
                        case 1 :
                            Person p= new Person();
                            Address a=new Address();
                            System.out.println("Enter file Name::");
                            addressBookName = scanner.next();
                            openBook = am.openFile(addressBookName);
                            if(openBook==null) {
                                System.out.println("file can not open");
                            }
                            else {
                                System.out.println("Enter person first name: ");
                                firstName = scanner.next();
                                p.setFirstName(firstName);
                                System.out.println("Enter person last name: ");
                                lastName =scanner.next();
                                p.setLastName(lastName);
                                System.out.println("Enter Phone Number");
                                phoneNumber=scanner.next();
                                p.setPhoneNumber(phoneNumber);
                                System.out.println("Enter the city");
                                String city=scanner.next();
                                a.setCity(city);
                                System.out.println("Enter state");
                                String state=scanner.next();
                                a.setState(state);
                                System.out.println("Enter Zip");
                                int zip=scanner.nextInt();
                                a.setZip(zip);
                                p.setAddress(a);
                                am.addPersonInAddressBook(p,openBook);
                            }
                            break;

                        case 2 :
                            System.out.println("Enter the name of the file whose record you want to update");
                            String fileName= scanner.next();
                            am.viewrecords(fileName);
                            System.out.println("Enter the first Name");
                            String name=scanner.next();
                            am.updateRecord(fileName, name);

                            break;

                        case 3 :
                            System.out.println("Enter the file name whose record to be deleted");
                            fileName=scanner.next();
                            File book = am.viewrecords(fileName);
                            if(book==null)
                                System.out.println("No such file exist! '" + fileName + "'");
                            else {
                                System.out.println("Enter the Firstname of person whose record is to be deleted");
                                String personName=scanner.next();
                              am.deleteRecord(book, personName);

                            }
                            break;

                        default:
                            System.out.println("Invalid Choice");
                            break;
                    }

                    break;

                case 4:
                    System.out.println("Enter the file Name Whose record you want to sort");
                    String fileName=scanner.next();
                    File book=am.openFile(fileName);
                    if(book==null)
                        System.out.println("No such file exist! '" + fileName + "'");
                    else {
                        System.out.println("How you want to sort the records? \n1.Sort by LastName \n2.Sort By Zip");
                        System.out.println("Enter Your choice");
                        int choice3=scanner.nextInt();
                        switch(choice3) {
                            case 1:
                                System.out.println("Sort By LastName");
                               am.sortByLastName(book);
                                break;

                            case 2 :
                                System.out.println("Sort by Zip");
                             am.sortByZip(book);
                                break;
                            default:
                                System.out.println("Invalid Choice");
                                break;
                        }
                    }
                    break;

                case 5:
                    if(openBook == null){
                        System.out.println("Nothing Open to Close !\n");
                    }
                    else{
                        openBook = null;
                        System.out.println("All files closed !\n");
                    }
                    break;
                case 6 :
                    System.out.println("Thanks you!");
                    System.exit(0);

                    break;

                default:
                    System.out.println("Invalid choice");
                    break;
            }

            System.out.println("To continue press 1 \n and for exit press any number ");
            repeat = scanner.nextInt();
        }

        if (repeat != 1) {
            System.out.println("Exit");
        }
    }


}
