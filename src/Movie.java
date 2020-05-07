public class Movie {
    private String title;
    private int starring;
    private String director;
    private String duration;
    private Genre genre;
    private Classification classification;
    private String date;

    public Movie(String title, int starring, String director, String duration, Genre genre, Classification classification, String date) {
        this.title = title;
        this.starring = starring;
        this.director = director;
        this.duration = duration;
        this.genre = genre;
        this.classification = classification;
        this.date = date;
    }
}
