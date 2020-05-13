public enum Genre {
    Drama, Adventure, Family, Action, Sci_Fi, Comedy, Animated, Thriller, Other;


    /**
     * display all the Genres
     */
    public void displayAllGenre(){
        int counter = 1;
        for (Genre genre : Genre.values()){
            System.out.println(Integer.toString(counter)+ ". " + genre);
            counter++;
        }
    }

    /**
     * displays a single genre by the given index
     * @param index the index given
     * @return the right genre
     */
    public Genre getGenreByIndex(int index){
        return Genre.values()[index - 1];
    }
}