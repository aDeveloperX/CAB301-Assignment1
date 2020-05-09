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

    public Movie(String title, String starring, String director, String duration, Genre genre, Classification classification, String date, int numberOfCopies) {
        this.title = title;
        this.starring = starring;
        this.director = director;
        this.duration = duration;
        this.genre = genre;
        this.classification = classification;
        this.date = date;
        this.numberOfCopies = numberOfCopies;
    }

    public String getTitle() {
        return title;
    }

    public int getNumberOfCopies() {
        return numberOfCopies;
    }

    public void borrowedCount() {
        this.borrowedCounter++;
    }

    public void borrow(){
        this.numberOfCopies--;
    }

    public void returnCopy(){
        this.numberOfCopies++;
    }
}
