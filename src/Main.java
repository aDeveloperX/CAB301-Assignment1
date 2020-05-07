import java.util.Scanner;

public class Main {
    //staff control
    private static boolean isLoggedInStaff = false;
    private static boolean isInStaffMenu = false;

    //member control
    private static MemberCollection memberCollection = new MemberCollection();


    private static Scanner scanner;

    private static void displayMainMenu(){
        System.out.println("Welcome to the Commuity Library\n==========Main Menu==========");
        System.out.println("1. Staff Login\n2. Member Login\n0. Exit");
        System.out.println("=============================\n\nPlease make a selection (1-2. or 0 to exit)");
    }

    private static void displayStaffMenu(){
        System.out.println("==========Staff Menu==========");
        System.out.println("1. Add a new movie DVD\n2. Remove a movie DVD\n3. Register a new Member\n4. Find a registered member's phone number");
        System.out.println("0. Return to main menu");
        System.out.println("==============================\nPlease make a selection (1-4. or 0 to return to main menu):");
    }

    private static void displayMemberMenu(){
        System.out.println("==========Member Menu==========");
        System.out.println("1. Display all movies\n2. Borrow a movie DVD\n3. Return a movie DVD\n4. List current borrowed movie DVDs \n5. Display top 10 most popular movies");
        System.out.println("0. Return to main menu");
        System.out.println("===============================\nPlease make a selection (1-5. or 0 to return to main menu):");
    }

    private static void displaySelection(int index){
        switch (index){
            case 1:
                isInStaffMenu = true;
                staffMenuHandler();
                break;
            case 2:
                displayMemberMenu();
                break;
            case 0:
                System.exit(0);
        }
    }

    private static void staffMenuHandler(){

        do{
            loginStaff();
            if(isLoggedInStaff){
                displayStaffMenu();
                int staffMenuChoice = scanner.nextInt();
                staffMenuChoiceHandler(staffMenuChoice);
            }
        }while (isInStaffMenu);
    }

    public static boolean isNumeric(String str)
    {
        for (char c : str.toCharArray())
        {
            if (!Character.isDigit(c)) return false;
        }
        return true;
    }

    private static void registerNewMember(){
         System.out.println("Please enter the member's surname");
         String surname = scanner.next();
         System.out.println("Please enter the member's givenname");
         String givenname = scanner.next();
         System.out.println("Please enter the member's address");
         //prevent reading empty string
         scanner.nextLine();
         String address = scanner.nextLine();
         System.out.println("Please enter the member's phone number");
         String phonenumber = scanner.next();
         System.out.println("Please enter the member's password");
         String password = scanner.next();
         while(password.length() != 4 || !isNumeric(password)){
             System.out.println("The password should be 4 digits integer");
             password = scanner.next();
         }
         Member member = new Member(surname, givenname, address, phonenumber, password);
         memberCollection.addNewMember(member);
         System.out.println("User Registered!");
    }

    private static void staffMenuChoiceHandler(int index){
        switch (index){
            case 1:
            case 2:
            case 3: registerNewMember(); break;
            case 0: isInStaffMenu = false;
        }
    }

    private static void loginStaff(){
        while(!isLoggedInStaff){
            System.out.println("Please enter username");
            String username = scanner.next();
            System.out.println("Please enter password");
            String password = scanner.next();
            if(username.equals("staff") && password.equals("today123")){
                isLoggedInStaff = true;
                System.out.println("Login Successed");
            }else{
                System.out.println("Can't find matched user");
                break;
            }
        }
    }

    public static void main(String[] args) {
       do {
           scanner = new Scanner(System.in);
           displayMainMenu();
           int menuChoice = scanner.nextInt();
           displaySelection(menuChoice);

       }while (true);
    }
}
