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

    public String getPhoneNumber(){
        return this.phoneNumber;
    }

    public boolean borrowMovie(Movie movie){

       if(movieCollection.insert(new Node(movie))){
           movie.borrow();
           return true;
       }
       return false;
    }


}
