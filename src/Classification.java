public enum Classification{
    General("G"), Parental_Guidance("PG"), Mature("M15+"), Mature_Accompanied("MA15+");

    private final String name;

    Classification(String str){
        this.name = str;
    }

    /**
     * iterate over all the classfications
     */
    public void displayAllClassification(){
        int counter = 1;
        for (Classification classification : Classification.values()){
            System.out.println(Integer.toString(counter)+ ". " + classification);
            counter++;
        }
    }

    /**
     * return a single classification by the given index
     * @param index the index given
     * @return the right classification
     */
    public Classification getClassificationByIndex(int index){
        return Classification.values()[index - 1];
    }
}


