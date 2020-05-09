public class Movie {
    private String title;
    private String starring;
    private String director;
    private String duration;
    private Genre genre;
    private Classification classification;
    private String date;
    private int borrowedCounter = 0;
    private int numberOfCopies = 0;

    public Movie(String title, String starring, String director, String duration, Genre genre, Classification classification, String date) {
        this.title = title;
        this.starring = starring;
        this.director = director;
        this.duration = duration;
        this.genre = genre;
        this.classification = classification;
        this.date = date;
    }

    public String getTitle() {
        return title;
    }

    public void setBorrowedCounter(int borrowedCounter) {
        this.borrowedCounter = borrowedCounter;
    }

    public void setNumberOfCopies(int numberOfCopies) {
        this.numberOfCopies = numberOfCopies;
    }

    public void borrow(){
        this.numberOfCopies--;
    }
}
