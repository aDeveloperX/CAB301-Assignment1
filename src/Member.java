public class Member {
    private String name;
    private String address;
    private String phoneNumber;
    private String password;
    private MovieCollection movieCollection;

    public Member(String surname, String givenname, String address, String phoneNumber, String password) {
        this.name = surname+givenname;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.password = password;
        movieCollection = new MovieCollection();
    }

    public String getName(){
        return this.name;
    }

    /**
     * check if the password is correct
     * @param password the attempted password
     * @return true if the password is correct
     */
    public boolean isPasswordCorrect(String password){
        return password.equals(this.password);
    }

    public String getPhoneNumber(){
        return this.phoneNumber;
    }

    /**
     * the user borrows a movie
     * @param movie the movie that the user borrowed
     * @return true if the movie is borrowed successfully
     */
    public boolean borrowMovie(Movie movie){
        return movieCollection.insert(new Node(movie));
    }

    /**
     * the user returns a movie
     * @param movie the given movie
     * @return true if the movie is returned successfully
     */
    public boolean returnMovie(Movie movie){
        return movieCollection.remove(movie.getTitle());
    }

    /**
     * check if the user has already borrowed the movie
     * @param title the title of the movie
     * @return return true if the user has the movie already
     */
    public boolean hasMovie(String title){
        return movieCollection.search(title) != null;
    }

    /**
     * show all movies borrowed by the user
     */
    public void displayAllBorrowedMovies(){
        movieCollection.iterateOverForMember(movieCollection.getRoot());
    }

    public MovieCollection getMovieCollection() {
        return movieCollection;
    }
}
