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

    /**
     * simulate the movie is borrowed, remove 1 copy and add 1 to the borrowed counter
     */
    public void borrow(){
        this.numberOfCopies--;
        this.borrowedCounter++;
    }

    public void returnCopy(){
        this.numberOfCopies++;
    }

    public int getBorrowedCounter() {
        return borrowedCounter;
    }

    @Override
    public String toString(){
        return "Title: " + this.title +
                "\nStarring: " + this.starring +
                "\nDirector: " + director +
                "\nDuration: " + duration +
                "\nGenre: " + genre +
                "\nClassification: " + classification +
                "\nDate: " + date +
                "\nCopies: " + numberOfCopies +
                "\n";
    }
}
