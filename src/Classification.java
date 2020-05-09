public enum Classification{
    General("G"), Parental_Guidance("PG"), Mature("M15+"), Mature_Accompanied("MA15+");

    private final String name;

    Classification(String str){
        this.name = str;
    }

    public String getName(){
        return this.name;
    }

    public void displayAllClassification(){
        int counter = 1;
        for (Classification classification : Classification.values()){
            System.out.println(Integer.toString(counter)+ ". " + classification);
            counter++;
        }
    }

    public Classification getClassificationByIndex(int index){
        return Classification.values()[index - 1];
    }
}


