import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;
import java.util.Scanner;

class Patient {

  private static int idGenerate = 0;
  private int id;
  private String name;
  private int age;
  private String gender;
  private String aadharCardNo;
  private long contactNumber;
  private String city;
  private String address;
  private String guardiansName;
  private String guardiansAddress;
  private long guardiansContactNumber;
  private boolean recovered;
  private String dateOfAdmission;

  public Patient(String name, int age, String gender, String aadharCardNo, long contactNumber, String city,
      String address, String guardiansName, String guardiansAddress, long guardiansContactNumber,
      String dateOfAdmission) {
    this.name = name;
    this.age = age;
    this.gender = gender;
    this.aadharCardNo = aadharCardNo;
    this.contactNumber = contactNumber;
    this.city = city;
    this.address = address;
    this.guardiansName = guardiansName;
    this.guardiansAddress = guardiansAddress;
    this.guardiansContactNumber = guardiansContactNumber;
    this.dateOfAdmission = dateOfAdmission;
    this.id = generateId();
    this.recovered = false;

  }

  public int getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public int getAge() {
    return age;
  }

  public String getGender() {
    return gender;
  }

  public String getAadharCardNo() {
    return aadharCardNo;
  }

  public long getContactNumber() {
    return contactNumber;
  }

  public String getCity() {
    return city;
  }

  public String getAddress() {
    return address;
  }

  public String getGuardiansName() {
    return guardiansName;
  }

  public long getGuardiansContactNumber() {
    return guardiansContactNumber;
  }

  public String getGuardiansAddress() {
    return guardiansAddress;
  }

  public String getDateOfAdmission() {
    return dateOfAdmission;
  }

  public static int generateId() {
    return ++idGenerate;
  }

  public boolean getRecoverd() {
    return recovered;
  }

  public void marksAsRecoverd() {
    this.recovered = true;
  }

  public void printDetails() {
    System.out.println("Patient details:");
    System.out.println("Name: " + getName());
    System.out.println("Age: " + getAge());
    System.out.println("Gender: " + getGender());
    System.out.println("Aadhar Card Number: " + getAadharCardNo());
    System.out.println("Contact Number: " + getContactNumber());
    System.out.println("City: " + getCity());
    System.out.println("Address: " + getAddress());
    System.out.println("Guardian's Name: " + getGuardiansName());
    System.out.println("Guardian's Contact Number: " + getGuardiansContactNumber());
    System.out.println("Guardian's Address: " + getGuardiansAddress());
    System.out.println("Date of admission: " + getDateOfAdmission());
  }

}

class AgeException extends Exception {
  AgeException(String emsg) {
    super(emsg);
  }
}

class Hospital {
  private HashMap<Integer, Patient> patients;

  public Hospital() {
    patients = new HashMap<>();
  }

  // for adding the patients
  public void addPatient(Patient patient) {
    patients.put(patient.getId(), patient);
    System.out.println("Patient added successfully");
  }

  // retreving the patient details by id
  public Patient getPatientById(int id) {
    return patients.get(id);
  }

  // retreving the patient details by city
  public ArrayList<Patient> getPatientByCity(String city) {
    ArrayList<Patient> al = new ArrayList<Patient>();

    for (Patient p : patients.values()) {
      if (p.getCity().equalsIgnoreCase(city)) {
        al.add(p);
      }
    }
    return al;
  }

  // retreving the patient details by age
  public ArrayList<Patient> getPatientByAgeGroup(int minAge, int maxAge) {
    ArrayList<Patient> al1 = new ArrayList<Patient>();
    for (Patient p : patients.values()) {
      if (p.getAge() >= minAge && p.getAge() <= maxAge) {
        al1.add(p);
      }
    }
    return al1;
  }

  // to delete the patient

  public void deletePatient(int id) {
    if (patients.containsKey(id)) {
      patients.remove(id);
    } else {
      System.out.println("no patient is found ");
    }
  }

  // to get the list of all patients in the hospital

  /*
   * public ArrayList<Patient> getAllPatients() {
   * ArrayList<Patient> al2 = new ArrayList<Patient>();
   * for(Patient p : patients.values()) {
   * al2.add(p);
   * }
   * return al2;
   * }
   */

  public void getAllPatients() {

    boolean b = patients.isEmpty();

    if (b == true) {
      System.out.println("no patient found ");
    }

    else {
      Set key = patients.keySet();
      for (Object o : key) {
        Patient k = patients.get(o);
        k.printDetails();
      }
    }
  }

  // Get id with patient name

  public ArrayList<Integer> getPatientByName(String name) {

    ArrayList<Integer> patientsId = new ArrayList<>();

    for (Patient patient : patients.values()) {
      if (patient.getName().equalsIgnoreCase(name)) {
        patientsId.add(patient.getId());
      }
    }
    return patientsId;

  }

  public void checkStatus(int id) {
    if (patients.containsKey(id)) {

      Patient p = patients.get(id);

      if (p.getRecoverd() == true) {
        System.out.println("patient is recovered");
      } else {
        System.out.println("patient is not recovered");
      }

    }
  }

  public boolean checkContactNo(long contactNo) {

    String contactString = String.valueOf(contactNo);

    if (contactString.length() == 10) {
      return true;
    } else {
      return false;
    }
  }

  public boolean checkAadharNo(String aadharNo) {
    if (aadharNo.length() == 12) {
      return true;
    } else {
      return false;
    }
  }

  public boolean checkAge(int age) {
    try {
      if (age > 0 && age <= 100) {
        return true;
      } else {
        throw new AgeException("Invalid age");
      }
    } catch (AgeException e) {
      System.out.println(e.getMessage());
      return false;
    }
  }

}

class Main {
  public static void main(String args[]) {

    Scanner sc = new Scanner(System.in);

    Hospital hos = new Hospital();

    /*
     * Patient p1 = new Patient("Bob", 18, "Male", "784529457345", 8278457845L,
     * "Vijayawada", "Singhnagar", "Steve", "Karimnagar", 9345868886L,"24-04-1999");
     * Patient p2 = new Patient("Harry", 19, "Male", "839532038485", 9292493993L,
     * "vijayawada", "New Jav Streets", "Mark", "Old Plaza",
     * 8484949488L,"23-04-1999");
     * 
     * Patient p3 = new
     * Patient("mark",20,"male","839398474732",8493849384L,"guntur","new street "
     * ,"mike","london",9303948493L,"22-04-23");
     */

    System.out.println("welcome to Arogya hospitial ");

    do {

      System.out.println(
          "Enter 1 to add the patients \nEnter 2 to get patient details by id \nEnter 3 to get list of patinet from this city \nEnter 4 to mark patient as recovered \nEnter 5 for list of patients by age group \nEnter 6 to delete the patient \nEnter 7 to get the list of all patients \nEnter 8 to check the patient status \nEnter 9 to quit the menu ");

      System.out.println("Enter the choice (1,2,3,4,5,6,7,8)");
      int ch = sc.nextInt();

      switch (ch) {
        case 1:
          System.out.println("Enter the number of patients");
          int num = sc.nextInt();
          sc.nextLine();

          for (int i = 0; i < num; i++) {
            System.out.println("Enter the name: ");
            String name = sc.nextLine();
            System.out.println("Enter the Age: ");
            int age = sc.nextInt();
            hos.checkAge(age);
            sc.nextLine();
            System.out.println("Enter the gender: ");
            String gender = sc.nextLine();
            System.out.println("Enter the Aadhar Card Number: ");
            String aadharCardNo = sc.nextLine();
            while (!hos.checkAadharNo(aadharCardNo)) {
              System.out.println("Invalid :Enter the aadhar number again");
              aadharCardNo = sc.nextLine();
            }
            System.out.println("Enter the contact number: ");
            long contactNumber = sc.nextLong();
            sc.nextLine();
            while (!hos.checkContactNo(contactNumber)) {
              System.out.println("Invalid Re enter the 10 digitcontact number again: ");
              contactNumber = sc.nextLong();
              sc.nextLine();
            }
            System.out.println("Enter the city: ");
            String city = sc.nextLine();
            System.out.println("Enter the Address: ");
            String address = sc.nextLine();
            System.out.println("Enter the Guardian's Name: ");
            String guardiansName = sc.nextLine();
            System.out.println("Enter the Guardian's Address: ");
            String guardiansAddress = sc.nextLine();
            System.out.println("Enter the Guardian's contact number: ");
            long guardiansContactNumber = sc.nextLong();
            sc.nextLine(); // Consume the newline character
            System.out.println("Enter the Date of Admission: ");
            String dateOfAdmission = sc.nextLine();

            Patient patient = new Patient(name, age, gender, aadharCardNo, contactNumber, city, address, guardiansName,
                guardiansAddress, guardiansContactNumber, dateOfAdmission);
            hos.addPatient(patient);
          }
          break;

        case 2:
          System.out.println("enter the  patient id ");
          int id = sc.nextInt();
          Patient p = hos.getPatientById(id);

          if (p != null) {
            p.printDetails();
          } else {
            System.out.println("No patient found with this id");
          }

          break;

        case 3:
          System.out.println("enter the city :");
          sc.nextLine();
          String city1 = sc.nextLine();
          ArrayList<Patient> al = hos.getPatientByCity(city1);

          for (Patient k : al) {
            k.printDetails();
          }

          break;

        case 4:
          System.out.println("enter the id");
          int id1 = sc.nextInt();
          Patient pa = hos.getPatientById(id1);
          if (pa != null) {
            pa.marksAsRecoverd();
            System.out.println("patient marked as a recovered");
          } else {
            System.out.println("no patient found with this id");
          }

          break;

        case 5:
          System.out.println("enter the min age and max age");
          int minAge = sc.nextInt();
          int maxAge = sc.nextInt();
          ArrayList<Patient> al1 = hos.getPatientByAgeGroup(minAge, maxAge);

          for (Patient o : al1) {
            o.printDetails();
          }

          break;

        case 6:
          System.out.println("enter the patient id");
          int id2 = sc.nextInt();

          hos.deletePatient(id2);
          break;

        case 7:
          hos.getAllPatients();

          break;

        case 8:

          System.out.println("enter the patient id");
          int id3 = sc.nextInt();
          hos.checkStatus(id3);
          break;
        case 9:

          System.exit(0);

        default:
          System.out.println("invalid");

      }
    } while (true);
  }
}
