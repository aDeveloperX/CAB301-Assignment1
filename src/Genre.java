public enum Genre {
    Drama, Adventure, Family, Action, Sci_Fi, Comedy, Animated, Thriller, Other;

    public void displayAllGenre(){
        int counter = 1;
        for (Genre genre : Genre.values()){
            System.out.println(Integer.toString(counter)+ ". " + genre);
            counter++;
        }
    }

    public Genre getGenreByIndex(int index){
        return Genre.values()[index - 1];
    }
}