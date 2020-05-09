import java.util.Scanner;

public class Main {
    //staff control
    private static boolean isLoggedInStaff = false;
    private static boolean isInStaffMenu = false;

    //member control
    private static MemberCollection memberCollection = new MemberCollection();
    private static MovieCollection movieCollection = new MovieCollection();


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

    public static boolean isNumeric(String str){
        for (char c : str.toCharArray())
        {
            if (!Character.isDigit(c)) return false;
        }
        return true;
    }

    private static void registerNewMember(){
        System.out.println("=============================");
         System.out.println("Please enter the member's surname");
         String surname = scanner.next();
        System.out.println("=============================");
         System.out.println("Please enter the member's givenname");
         String givenname = scanner.next();
         while(memberCollection.containsMember(surname+givenname)){
             System.out.println("=============================");
             System.out.println("Username already exists");
             System.out.println("=============================");
             System.out.println("Please enter the member's surname");
             surname = scanner.next();
             System.out.println("=============================");
             System.out.println("Please enter the member's givenname");
             givenname = scanner.next();

         }
        System.out.println("=============================");
         System.out.println("Please enter the member's address");
         //prevent reading empty string
         scanner.nextLine();
         String address = scanner.nextLine();
        System.out.println("=============================");
         System.out.println("Please enter the member's phone number");
         String phonenumber = scanner.next();
        System.out.println("=============================");
         System.out.println("Please enter the member's password");
         String password = scanner.next();
         while(password.length() != 4 || !isNumeric(password)){
             System.out.println("=============================");
             System.out.println("The password should be 4 digits integer");
             password = scanner.next();
         }
         Member member = new Member(surname, givenname, address, phonenumber, password);
         memberCollection.addNewMember(member);
        System.out.println("=============================");
         System.out.println("User Registered!");
    }

    private static void findMembersNumber(){
        System.out.println("=============================");
        System.out.println("Please enter the username");
        String name = scanner.next();
        System.out.println("The result is: " + memberCollection.getPhoneNumberByName(name));
    }

    private static void staffRemoveDVD(){
        System.out.println("=============================");
        System.out.println("Please enter the DVD name");
        scanner.nextLine();
        String name = scanner.nextLine();
        System.out.println(name);
        System.out.println("=============================");
        if(movieCollection.remove(name)){
            System.out.println("DVD " + name + " has been removed!");
        }else{
            System.out.println("Failed to remove DVD");
        }
    }

    private static void staffAddDVD(){
        System.out.println("=============================");
        System.out.println("Please enter the DVD name");
        scanner.nextLine();
        String name = scanner.nextLine();
        System.out.println("=============================");
        System.out.println("Please enter the DVD starring");
        String starring = scanner.next();
        System.out.println("=============================");
        System.out.println("Please enter the director name");
        scanner.nextLine();
        String director = scanner.nextLine();
        System.out.println("=============================");
        System.out.println("Please enter the duration");
        String duration = scanner.next();
        System.out.println("=============================");
        System.out.println("Please select a genre using number");
        Genre.Other.displayAllGenre();
        int index = scanner.nextInt();
        Genre genre = Genre.Other.getGenreByIndex(index);
        System.out.println("=============================");
        System.out.println("Please select classification using number");
        Classification.General.displayAllClassification();
        index = scanner.nextInt();
        Classification classification = Classification.General.getClassificationByIndex(index);
        System.out.println("=============================");
        System.out.println("Please enter the date");
        String date = scanner.next();

        Movie movie = new Movie(name, starring, director, duration, genre, classification, date);
        if(movieCollection.insert(new Node(movie))){
            System.out.println("=============================");
            System.out.println("Movie " + name + " has been added!");
        }else{
            System.out.println("=============================");
            System.out.println("Failed to add movie");
        }



    }

    private static void staffMenuChoiceHandler(int index){
        switch (index){
            case 1: staffAddDVD(); break;
            case 2: staffRemoveDVD(); break;
            case 3: registerNewMember(); break;
            case 4: findMembersNumber(); break;
            case 0: isInStaffMenu = false;
        }
    }

    private static void loginStaff(){
        while(!isLoggedInStaff){
            System.out.println("=============================");
            System.out.println("Please enter username");
            String username = scanner.next();
            System.out.println("=============================");
            System.out.println("Please enter password");
            String password = scanner.next();
            if(username.equals("staff") && password.equals("today123")){
                isLoggedInStaff = true;
                System.out.println("=============================");
                System.out.println("Login Successed");
            }else{
                System.out.println("=============================");
                System.out.println("Can't find matched staff account");
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

        } while (true);
//        movieCollection.insert(new Node(new Movie("basaaaa", "asd", "asd", "asd", Genre.Other, Classification.Mature, "asd")));
//        movieCollection.insert(new Node(new Movie("c", "asd", "asd", "asd", Genre.Other, Classification.Mature, "asd")));
//        movieCollection.insert(new Node(new Movie("a", "asd", "asd", "asd", Genre.Other, Classification.Mature, "asd")));
//        System.out.println(movieCollection.root.left.movie.getTitle());
//        System.out.println(movieCollection.remove("basaaaa"));
    }
}
