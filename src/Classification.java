public enum Classification{
    General("G"), Parental_Guidance("PG"), Mature("M15+"), Mature_Accompanied("MA15+");

    private final String name;

    Classification(String str){
        this.name = str;
    }

    public String getName(){
        return this.name;
    }
}


