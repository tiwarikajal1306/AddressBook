package addressBook;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;


public class AddressBookManager implements InterfaceAddressBookManager {



    //create new address book;
	@Override
    public  boolean newBook(String name) throws IOException{
        File file = new File("D:\\Addressbook\\"+name+".csv");
        boolean result=file.createNewFile();   //if file not exist it created else return false

        FileWriter fw=new FileWriter(file);
        fw.append("FirstName,LastName,PhoneNumber,City,State,Zip");
        fw.append("\n");
        fw.flush();
        fw.close();
        return result;



    }
	@Override
    public  void showFiles () {


        File f = new File("D:\\Addressbook");

        File pathnames[] = f.listFiles();

        // For each pathname in the pathnames array
        for (File pathname : pathnames) {
            String name=pathname.getName();
            System.out.println(name);
        }
    }
	@Override
    public File openFile(String fileName) {
        File file = new File("D:\\Addressbook\\"+fileName);
        if(file.exists()) {
            System.out.println("file open");
        }
        else {
            System.out.println("no such file exist");
            file=null;
        }
        return file;
    }
    @Override
    public void addPersonInAddressBook(Person p,File f ) throws IOException {

        FileWriter csvWriter = new FileWriter(f,true);

        char splitChar=',';
        csvWriter.append(p.getFirstName() );
        csvWriter.append(splitChar);
        csvWriter.append(p.getLastName() );
        csvWriter.append(splitChar);
        csvWriter.append(p.getPhoneNumber() );
        csvWriter.append(splitChar);
        csvWriter.append(p.getAddress().getCity());
        csvWriter.append(splitChar);
        csvWriter.append(p.getAddress().getState());
        csvWriter.append(splitChar);
        csvWriter.append(Integer.toString(p.getAddress().getZip()));

        csvWriter.append('\n');
        csvWriter.flush();
        csvWriter.close();


    }
    @Override
    public File viewrecords(String fname) {
        File file=new File ("D:\\Addressbook\\"+fname);
        String line = null;
        try
        {
            FileReader fileReader = new FileReader(file);


            BufferedReader bufferedReader = new BufferedReader(fileReader);

            while((line = bufferedReader.readLine()) != null)
            {
                System.out.println(line);
            }


            bufferedReader.close();

        }

        catch(IOException ex)
        {

            file=null;
        }

        return file;
    }
    @Override
    public  void sortByLastName(File file) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(file));
        Map<String, List<String>> map = new TreeMap<>();
        String line = reader.readLine();

        while ((line = reader.readLine()) != null) {
            String key = line.split(",")[ 1 ];
            // System.out.println(key);
            List<String> l = new LinkedList<>();
            // System.out.println(l);
            l.add(line);
            // System.out.println(l);
            map.put(key, l);
        }
        System.out.println(map);
        reader.close();
    }
    @Override
    public void sortByZip(File file) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(file));
        Map<String, List<String>> map = new TreeMap<>();
        String line = reader.readLine();

        while ((line = reader.readLine()) != null) {
            String key = line.split(",")[ 5 ];
            // System.out.println(key);
            List<String> l = new LinkedList<>();
            //System.out.println(l);
            l.add(line);
            //  System.out.println(l);
            map.put(key, l);
        }
        System.out.println(map);
        reader.close();
    }
    @Override
    public  File deleteRecord(File file,String name) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(file));
        Map<String, List<String>> map = new HashMap<>();
        String line = reader.readLine();

        while ((line = reader.readLine()) != null) {
            String key = line.split(",")[ 0 ];
            // System.out.println(key);
            List<String> l = new LinkedList<>();
            //System.out.println(l);
            l.add(line);
            //  System.out.println(l);
            map.put(key, l);
        }
        reader.close();
        System.out.println(map);
        if(map.containsKey(name)==true) {
            map.remove(name);
            System.out.println("\nRecord removed successfully !");
            FileWriter fw = new FileWriter(file);
            fw.append("FirstName,LastName,PhoneNumber,City,State,Zip\n");
            fw.flush();
            fw.close();
            FileWriter bw = new FileWriter(file,true);
            for(List<String> records:map.values()) {
                for (String val : records) {
                    bw.append(val + "\n");
                }
            }
            bw.flush();
            bw.close();
        }

        else {
            System.out.println("\nRecord does not exists !");
        }
        return file;
    }
    @Override
    public void updateRecord(String fileName,String name) throws IOException {
        ArrayList<Person> ListOfPeople = null;
        File file=new File("D:\\Addressbook\\"+fileName);
        if (file.exists()) {
            BufferedReader br = new BufferedReader(new FileReader(file));
            ListOfPeople = new ArrayList<>();

            String line = br.readLine();
            while ((line = br.readLine()) != null) {
            if(line=="First Name,Last Name,Phone Number,City,State,Zip")
            	continue;
                System.out.println(line);
                Person p = new Person();
                Address a = new Address();
                a.setCity(line.split(",")[ 3 ]);
                a.setState(line.split(",")[ 4 ]);
                a.setZip(Integer.parseInt(line.split(",")[ 5 ]));
                p.setFirstName(line.split(",")[ 0 ]);
                p.setLastName(line.split(",")[ 1 ]);
                p.setPhoneNumber(line.split(",")[ 2 ]);
                p.setAddress(a);

                ListOfPeople.add(p);
            }

            br.close();

            Map<String, Person> map = new HashMap<>();
            for (Person temp : ListOfPeople) {
                String key = temp.getFirstName();
                map.put(key, temp);
            }
            System.out.println(map);

            if (map.containsKey(name) == true) {
                Scanner input = new Scanner(System.in);
                int flag = 0;
                Person per = map.get(name);
                Address add = per.getAddress();
                while (flag == 0) {
                    System.out.println("Enter Updation Choice:" +
                            "\n1. Last Name" +
                            "\n2. Phone Number" +
                            "\n3. City" +
                            "\n4. State" +
                            "\n5. Zip" +
                            "\n6. Exit Updation");
                    System.out.print("Choice: ");
                    int choice = input.nextInt();

                    switch (choice) {
                        case 1:
                            System.out.print("\nEnter updated last name: ");
                            String lname = input.next();
                            per.setLastName(lname);
                            break;
                        case 2:
                            System.out.print("\nEnter updated phone number: ");
                            String phoneNum = input.next();
                            per.setPhoneNumber(phoneNum);
                            break;
                        case 3:
                            System.out.print("Enter updated city: ");
                            String city = input.next();
                            add.setCity(city);
                            per.setAddress(add);
                            break;
                        case 4:
                            System.out.print("Enter updated state: ");
                            String state = input.next();
                            add.setState(state);
                            per.setAddress(add);
                            break;
                        case 5:
                            System.out.print("Enter updated zip: ");
                            int zip = input.nextInt();
                            add.setZip(zip);
                            per.setAddress(add);
                        case 6:
                            flag = 1;
                            break;
                        default:
                            System.out.println("Invalid choice !!!");
                    }
                    map.computeIfPresent(name, (K, V) -> V = per);
                }

                String CSV_HEADER = "First Name,Last Name,Phone Number,City,State,Zip\n";
                FileWriter newWriter = new FileWriter(file);
                newWriter.append(CSV_HEADER);
                newWriter.flush();
                newWriter.close();
                FileWriter appWriter = new FileWriter(file, true);
                for (Person person : map.values()) {
                    appWriter.append(person.getFirstName() + ",");
                    appWriter.append(person.getLastName() + ",");
                    appWriter.append(person.getPhoneNumber() + ",");
                    appWriter.append(person.getAddress().getCity() + ",");
                    appWriter.append(person.getAddress().getState() + ",");
                    appWriter.append(person.getAddress().getZip() + "\n");
                }
                appWriter.flush();
                appWriter.close();
                System.out.println("Record updated successfully !");
            } else {
                System.out.println("Record not present !");
            }
        } else {
            System.out.println("No such file exist!");
        }

    }
}



