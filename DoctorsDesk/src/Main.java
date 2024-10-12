import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.text.SimpleDateFormat;
import java.util.Scanner;
import java.util.Date;

class Doctors{
    String FName;
    String LName;
    String Qualification;
    String Email;
    String PWD;
    String UName;
    static String PName;
    static File file;
    static FileWriter fw;
    static Scanner input = new Scanner(System.in);
    static Patient pat;

    Doctors() throws IOException {
        System.out.print("Enter User Name : ");
        String Uname=input.nextLine();
        if(Files.notExists(Path.of(Uname))){
            this.UName = Uname;
            System.out.print("Enter First Name : ");
            this.FName = input.nextLine();
            System.out.print("Enter Last Name : ");
            this.LName = input.nextLine();
            System.out.print("Enter Qualification : ");
            this.Qualification = input.nextLine();
            System.out.print("Enter Email : ");
            this.Email = input.nextLine();
            System.out.print("Enter Password : ");
            this.PWD = input.nextLine();
            file = new File(Uname);
            file.mkdir();
            fw = new FileWriter(Uname + "/docInfo.txt");
            fw.write(UName+"\n"+PWD+"\n"+"First Name: "+FName+"\n"+"Last Name: "+LName+"\n"+"Qualification: "+Qualification+"\n"+"EmailID: " + Email);
            fw.close();
            file = new File(Uname + "/Patients");
            file.mkdir();
        }
        else
        {
            System.out.println("Username Already Taken!");
            UName = Uname;
        }
    }

    public void writePres() throws IOException
    {
        pat = new Patient();
        System.out.print("Patient Full Name : ");
        PName = input.nextLine();
        PName = PName.replaceAll("\\s", "");
        System.out.print("Date of Birth : ");
        PName += input.nextLine();
        System.out.println(PName);
        pat.writePrescription(PName, UName);
    }

    public void CreateNewPat() throws IOException {
        pat = new Patient(UName);
    }
}

class Patient{
    String FName;
    String LName;
    String DOB;
    int Age;
    String Email;
    static String date;
    static String pr;
    static File file;
    static FileWriter fpw;
    static SimpleDateFormat ft = new SimpleDateFormat("dd/MM/yyyy");
    static Scanner input = new Scanner(System.in);

    Patient(){}

    Patient(String dUname) throws IOException {
        System.out.print("Enter First Name : ");
        this.FName = input.nextLine();
        System.out.print("Enter Last Name : ");
        this.LName = input.nextLine();
        System.out.print("Enter Enter Date of Birth : ");
        this.DOB = input.nextLine();
        System.out.print("Enter Email : ");
        this.Email = input.nextLine();
        System.out.print("Enter Age : ");
        this.Age = input.nextInt();
        input.nextLine();
        file = new File(dUname+ "/Patients/" + FName+LName+DOB);
        file.mkdir();
        fpw = new FileWriter(dUname+ "/Patients/" +FName+LName+DOB + "/patInfo.txt");
        fpw.write("First Name: "+FName+"\n"+"Last Name: "+LName+"\n"+"Date of Birth: "+DOB+"\n"+"Age: "+Age
                +"\n" + "EmailID: " + Email);
        fpw.close();
        fpw = new FileWriter(dUname+ "/Patients/" +FName+LName+DOB + "/patPrescript.txt");
        fpw.write("First Name: "+FName+"\n"+"Last Name: "+LName+"\n"+"Date of Birth: "+DOB+"\n"+"Age: "+Age
                +"\n" + "EmailID: " + Email);
        fpw.close();
    }

    void writePrescription(String Pname, String Dname) throws IOException {

        date = ft.format(new Date());
        fpw = new FileWriter(Dname + "/Patients/" + Pname + "/patPrescript.txt", true);
        System.out.print("Prescription : ");
        pr = "Y";
        fpw.write("\n\n"+date);
        while(pr.equals("Y"))
        {
            System.out.print("Medicine Name : ");
            pr = input.nextLine();
            fpw.write("\n" + pr +"\t\t");
            System.out.print("Days : ");
            pr = input.nextLine();
            fpw.write(pr +"\t\t");
            System.out.print("Frequency (per day) : ");
            pr = input.nextLine();
            fpw.write(pr +"\t\t");
            System.out.print("\nDo you wish to continue? Y/N : ");
            pr = input.nextLine();
        }
        fpw.close();
    }
}

public class Main {
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        Doctors d;
        d = new Doctors();
        int num;
        System.out.print("\n\tMENU\n1.New Patient\n2. Prescription\nChoice : ");
        num = sc.nextInt();
        if(num == 1)
            d.CreateNewPat();
        else if(num == 2)
            d.writePres();
        else
        {
            System.out.println("\nERROR! TRY AGAIN!!!");
            return;
        }
    }
}