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

    /**
     * display the main menu
     */
    private static void displayMainMenu(){
        System.out.println("Welcome to the Commuity Library\n==========Main Menu==========");
        System.out.println("1. Staff Login\n2. Member Login\n0. Exit");
        System.out.println("==============================\n\nPlease make a selection (1-2. or 0 to exit)");
    }

    /**
     * display the staff menu
     */
    private static void displayStaffMenu(){
        System.out.println("==========Staff Menu==========");
        System.out.println("1. Add a new movie DVD\n2. Remove a movie DVD\n3. Register a new Member\n4. Find a registered member's phone number");
        System.out.println("0. Return to main menu");
        System.out.println("==============================\nPlease make a selection (1-4. or 0 to return to main menu):");
    }

    /**
     * display the member menu
     */
    private static void displayMemberMenu(){
        System.out.println("==========Member Menu==========");
        System.out.println("1. Display all movies\n2. Borrow a movie DVD\n3. Return a movie DVD\n4. List current borrowed movie DVDs \n5. Display top 10 most popular movies");
        System.out.println("0. Return to main menu");
        System.out.println("===============================\nPlease make a selection (1-5. or 0 to return to main menu):");
    }

    /**
     * display the menu based on the input
     * @param index the users choice of menu
     */
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

    /**
     * loop for staff menu
     */
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

    /**
     * loop for member menu
     */
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

    /**
     * login a member base on the memberCollection
     */
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

    /**
     * display all movies in the record
     */
    private static void displayAllMovies(){
        System.out.println("=========All Movies=========");
        movieCollection.iterateOver(movieCollection.getRoot());
    }

    /**
     * borrow a movie
     */
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
            }else if(movie.getNumberOfCopies() <= 0){
                System.out.println("There's no copy of the selected movie left!");
                System.out.println("=============================");
            }else if( memberCollection.getMember(currentMemberName).getMovieCounter() >= 10){
                System.out.println("You can only borrow up to 10 movies!");
                System.out.println("=============================");
            }
            else{
                System.out.println("You borrowed this movie!");
                memberCollection.getMember(currentMemberName).increaseMovieCounter();
                memberCollection.getMember(currentMemberName).borrowMovie(movie);
                movie.borrow();
            }
        }

    }

    /**
     * display all movies the user has borrowed
     */
    private static void listBorrowedMovies(){
        System.out.println("========Movies You have Borrowed========");
        memberCollection.getMember(currentMemberName).displayAllBorrowedMovies();
    }

    /**
     * return a movie DVD
     */
    private static void returnAMovieDVD(){
        System.out.println("======================");
        System.out.println("Please enter the name of the movie that you want to return");
        scanner.nextLine();
        String name = scanner.nextLine();
        Member member =  memberCollection.getMember(currentMemberName);
        if(member.hasMovie(name) && movieCollection.search(name) != null){
           member.returnMovie(member.getMovieCollection().search(name).movie);
           movieCollection.search(name).movie.returnCopy();
            memberCollection.getMember(currentMemberName).decreseMovieCounter();
            System.out.println("You have returned this movie");
        }else if(member.hasMovie(name) && movieCollection.search(name) == null){
            member.returnMovie(member.getMovieCollection().search(name).movie);
            System.out.println("You have returned this movie, but this movie has been removed by the staff!");
        }
        else{
            System.out.println("Can't find the movie name from your borrowed movies");
        }
    }

    /**
     * use bubble sort to sort the top10 movies
     */
    private static void displayTopTenMovies(){
        Movie[] arr = bubbleSort(movieCollection.toArray(movieCollection.getRoot()), movieCollection.getIndex());
        System.out.println("==================Top10 Popular Movies===================");
        if(movieCollection.getIndex() < 10){
            for (int i = 0; i < movieCollection.getIndex() ; i++) {
                System.out.println(i + 1 + ". "+ arr[i].getTitle() + "   Frequency: " + arr[i].getBorrowedCounter());
            }
        }else{
            for (int i = 0; i < 10 ; i++) {
                System.out.println(i + 1 + ". "+ arr[i].getTitle() + "   Frequency: " + arr[i].getBorrowedCounter());
            }
        }
        movieCollection.resetArr();
    }

    /**
     * call the right function with the given index
     * @param index the user's choice
     */
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

    /**
     * check if a string is numeric
     * @param str the given string
     * @return return true if it's numeric
     */
    private static boolean isNumeric(String str){
        for (char c : str.toCharArray()){
            if (!Character.isDigit(c)) return false;
        }
        return true;
    }

    /**
     * register a new member
     */
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

    /**
     * find a member's phone number by the given username
     */
    private static void findMembersNumber(){
        System.out.println("=============================");
        System.out.println("Please enter the username");
        String name = scanner.next();
        System.out.println("The result is: " + memberCollection.getPhoneNumberByName(name));
    }

    /**
     * staff removes a DVD by the given movieName from the collection
     */
    private static void staffRemoveDVD(){
        System.out.println("=============================");
        System.out.println("Please enter the DVD name");
        scanner.nextLine();
        String name = scanner.nextLine();
        System.out.println("=============================");
        if(movieCollection.remove(name)){
            System.out.println("DVD " + name + " has been removed!");
            //
        }else{
            System.out.println("Failed to remove DVD");
        }
    }

    /**
     * staff adds a DVD to the collection
     */
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
        System.out.println("=============================");
        int index = scanner.nextInt();
        if(index > 9){
            index = 8;
        }
        Genre genre = Genre.Other.getGenreByIndex(index);
        System.out.println("=============================");
        System.out.println("Please select classification using number");
        Classification.General.displayAllClassification();
        System.out.println("=============================");
        index = scanner.nextInt();
        if(index > 4){
            index = 4;
        }
        Classification classification = Classification.General.getClassificationByIndex(index);
        System.out.println("=============================");
        System.out.println("Please enter the date");
        String date = scanner.next();
        System.out.println("=============================");
        System.out.println("Please enter the number of copies");
        int copies = scanner.nextInt();
        if(copies < 1){
            copies = 1;
        }

        Movie movie = new Movie(name, starring, director, duration, genre, classification, date, copies);
        if(movieCollection.insert(new Node(movie))){
            System.out.println("=============================");
            System.out.println("Movie " + name + " has been added!");
        }else{
            System.out.println("=============================");
            System.out.println("Failed to add movie");
        }
    }

    /**
     * call the right function with the user's choice
     * @param index the user's input
     */
    private static void staffMenuChoiceHandler(int index){
        switch (index){
            case 1: staffAddDVD(); break;
            case 2: staffRemoveDVD(); break;
            case 3: registerNewMember(); break;
            case 4: findMembersNumber(); break;
            case 0: isInStaffMenu = false; isLoggedInStaff = false;
        }
    }

    /**
     * login a staff, check the username and password
     */
    private static void loginStaff(){

           while(!isLoggedInStaff){
               System.out.println("=============================");
               System.out.println("Please enter username");
               String username = scanner.next();
               System.out.println("=============================");
               System.out.println("Please enter password");
               String password = scanner.next();
               //username and password check
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

    /**
     * bubble sort algorithm
     * @param movies an array of movies
     * @param length the length of the array
     * @return returns a sorted array base on the times its been borrowed
     */
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
            movieCollection.insert(new Node(new Movie("a", "a", "a", "sd",Genre.Other, Classification.Mature, "a", 13)));
            movieCollection.insert(new Node(new Movie("b", "a", "a", "sd",Genre.Other, Classification.Mature, "a", 13)));
            movieCollection.insert(new Node(new Movie("c", "a", "a", "sd",Genre.Other, Classification.Mature, "a", 13)));
            movieCollection.insert(new Node(new Movie("d", "a", "a", "sd",Genre.Other, Classification.Mature, "a", 13)));
            movieCollection.insert(new Node(new Movie("e", "a", "a", "sd",Genre.Other, Classification.Mature, "a", 13)));
            movieCollection.insert(new Node(new Movie("f", "a", "a", "sd",Genre.Other, Classification.Mature, "a", 13)));
            movieCollection.insert(new Node(new Movie("g", "a", "a", "sd",Genre.Other, Classification.Mature, "a", 13)));
            movieCollection.insert(new Node(new Movie("h", "a", "a", "sd",Genre.Other, Classification.Mature, "a", 13)));
            movieCollection.insert(new Node(new Movie("i", "a", "a", "sd",Genre.Other, Classification.Mature, "a", 13)));
            movieCollection.insert(new Node(new Movie("j", "a", "a", "sd",Genre.Other, Classification.Mature, "a", 13)));
            movieCollection.insert(new Node(new Movie("k", "a", "a", "sd",Genre.Other, Classification.Mature, "a", 13)));
            int menuChoice = scanner.nextInt();
            displaySelection(menuChoice);
        } while (true);
    }
}
