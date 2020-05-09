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

    public boolean isPasswordCorrect(String password){
        return password.equals(this.password);
    }

    public String getPhoneNumber(){
        return this.phoneNumber;
    }

    public boolean borrowMovie(Movie movie){
       if(movieCollection.insert(new Node(movie))){
           return true;
       }
       return false;
    }

    public boolean returnMovie(Movie movie){
       if(movieCollection.remove(movie.getTitle())){

           return true;
       }
       return false;
    }

    public boolean hasMovie(String title){
        return movieCollection.search(title) != null;
    }

    public void displayAllBorrowedMovies(){
        movieCollection.iterateOver(movieCollection.getRoot());
    }

    public MovieCollection getMovieCollection() {
        return movieCollection;
    }
}
