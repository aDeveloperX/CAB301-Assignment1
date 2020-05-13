import java.util.Scanner;

public class Main {
    //staff control
    private static boolean isLoggedInStaff = false;
    private static boolean isInStaffMenu = false;
    private static boolean isLoggedInMember =false;
    private static boolean isInMemberMenu = false;
    private static String currentMemberName = null;

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
                isInMemberMenu = true;
                memberMenuHandler();
                break;
            case 0:
                System.exit(0);
        }
    }

    private static void staffMenuHandler(){
        do{
            loginStaff();
            while(isLoggedInStaff){
                displayStaffMenu();
                int staffMenuChoice = scanner.nextInt();
                staffMenuChoiceHandler(staffMenuChoice);
            }
        }while (isInStaffMenu);
    }

    private static void memberMenuHandler(){
        do{
            loginMember();
            while(isLoggedInMember){
               displayMemberMenu();
               int memberMenuChoice = scanner.nextInt();
               memberMenuChoiceHandler(memberMenuChoice);
            }
        }while (isInMemberMenu);
    }

    private static void loginMember(){
        System.out.println("=============================");
        System.out.println("Please enter the member's fullname");
        String name = scanner.next();
        System.out.println("=============================");
        System.out.println("Please enter the password");
        String password = scanner.next();
        if(memberCollection.containsMember(name)){
            if(memberCollection.getMember(name).isPasswordCorrect(password)){
                System.out.println("Login successed!");
                isLoggedInMember = true;
                currentMemberName = name;
            }else{
                System.out.println("Incorrect password");
                System.out.println("=============================");
                isInMemberMenu = false;
            }
        }else{
            System.out.println("Username doesn't exist!");
            System.out.println("=============================");
            isInMemberMenu = false;
        }
    }

    private static void displayAllMovies(){
        System.out.println("=========All Movies=========");
        movieCollection.iterateOver(movieCollection.getRoot());
        System.out.println("=============================");
    }

    private static void borrowAMovieDVD(){
        System.out.println("=============================");
        System.out.println("Please enter the name of the movie you want to borrow");
        scanner.nextLine();
        String name = scanner.nextLine();
        if (movieCollection.search(name) == null){
            System.out.println("Can't find the movie with name: " + name);
            System.out.println("=============================");
        }else{
            Movie movie = movieCollection.search(name).movie;
            // avoid if the user has borrowed this movie already
            if(memberCollection.getMember(currentMemberName).hasMovie(movie.getTitle())){
                System.out.println("You've already borrowed this movie!");
                System.out.println("=============================");
                //avoid there's no copies left
            }else{
                System.out.println("You borrowed this movie!");
                System.out.println("=============================");
                memberCollection.getMember(currentMemberName).borrowMovie(movie);
                movie.borrow();
                //debug
                System.out.println(movie.getNumberOfCopies());
            }
        }

    }

    private static void listBorrowedMovies(){
        System.out.println("========Movies You have Borrowed========");
        memberCollection.getMember(currentMemberName).displayAllBorrowedMovies();
        System.out.println("========================================");
    }

    private static void returnAMovieDVD(){
        System.out.println("======================");
        System.out.println("Please enter the name of the movie that you want to return");
        scanner.nextLine();
        String name = scanner.nextLine();
        Member member =  memberCollection.getMember(currentMemberName);
        if(member.hasMovie(name)){
           member.returnMovie(member.getMovieCollection().search(name).movie);
           movieCollection.search(name).movie.returnCopy();
            System.out.println("You have returned this movie");
            System.out.println("======================");
            System.out.println(movieCollection.search(name).movie.getNumberOfCopies());
        }else{
            System.out.println("Can't find the movie name from your borrowed movies");
            System.out.println("======================");
        }
    }

    //prob bugged out
    private static void displayTopTenMovies(){
        Movie[] arr = bubbleSort(movieCollection.toArray(movieCollection.getRoot()), movieCollection.getIndex());
        System.out.println("==================Top10 Popular Movies===================");
        for (int i = 0; i < movieCollection.getIndex() ; i++) {
              System.out.println(Integer.toString(i + 1) + ". "+ arr[i].getTitle());
        }
        movieCollection.resetArr();
    }

    private static void memberMenuChoiceHandler(int index){
        switch (index){
            case 1: displayAllMovies(); break;
            case 2: borrowAMovieDVD(); break;
            case 3: returnAMovieDVD(); break;
            case 4: listBorrowedMovies(); break;
            case 5: displayTopTenMovies(); break;
            case 0: isInMemberMenu = false; isLoggedInMember=false; currentMemberName = null;
        }
    }

    private static boolean isNumeric(String str){
        for (char c : str.toCharArray()){
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
            //
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
        System.out.println("=============================");
        System.out.println("Please enter the number of copies");
        int copies = scanner.nextInt();
        ///
        ///
        //

        Movie movie = new Movie(name, starring, director, duration, genre, classification, date, copies);
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
            case 0: isInStaffMenu = false; isLoggedInStaff = false;
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
                   System.out.println("=============================");
                   isInStaffMenu = false;
                   break;
               }
           }
        }
    public static Movie[] bubbleSort(Movie[] movies, int length) {

        for (int i = 0; i < length - 1; i++) {
            for (int j = 0; j < length - i - 1; j++) {

                if (movies[j].getBorrowedCounter() < movies[j + 1].getBorrowedCounter()) {
                    Movie temp = movies[j];
                    movies[j] = movies[j + 1];
                    movies[j + 1] = temp;
                }
            }
        }
     return movies;
    }

    public static void main(String[] args) {

        do {
            scanner = new Scanner(System.in);
            displayMainMenu();
            int menuChoice = scanner.nextInt();
            displaySelection(menuChoice);
        } while (true);
//        movieCollection.insert(new Node(new Movie("b", "a", "a", "hj", Genre.Other, Classification.General, "a", 1, 3)));
//        movieCollection.insert(new Node(new Movie("c", "a", "a", "hj", Genre.Other, Classification.General, "a", 1, 3)));
//        movieCollection.insert(new Node(new Movie("a", "a", "a", "hj", Genre.Other, Classification.General, "a", 1, 4 )));
//        movieCollection.insert(new Node(new Movie("cc", "a", "a", "hj", Genre.Other, Classification.General, "a", 1, 2)));
//        movieCollection.insert(new Node(new Movie("d", "a", "a", "hj", Genre.Other, Classification.General, "a", 1, 2)));
//        movieCollection.insert(new Node(new Movie("dcccc", "a", "a", "hj", Genre.Other, Classification.General, "a", 1, 7)));
//        movieCollection.insert(new Node(new Movie("dbbbb", "a", "a", "hj", Genre.Other, Classification.General, "a", 1, 19)));

        //movieCollection.iterateOver(movieCollection.getRoot());
        //movieCollection.toArray(movieCollection.getRoot());
        //displayTopTenMovies();


    }
}
