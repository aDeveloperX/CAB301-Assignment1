
public class MemberCollection{
    private Member[] arr;

    //the index to keep track of the length of the array
    private int index = 0;

    public MemberCollection() {
        arr = new Member[10];
    }

    /**
     * add a member to the collection
     * @param member the member that is about to be added
     */
    public void addNewMember(Member member){
        arr[index] = member;
        index++;
    }

    /**
     * check if the member is already exist
     * @param username the given username
     * @return return true if the username is exist
     */
    public boolean containsMember(String username){
        for (int i = 0; i < index; i++) {
            if(arr[i].getName().equals(username)){
                return true;
            }
        }
        return false;
    }

    /**
     * returns a member object with the given username
     * @param username the given username
     * @return the right member
     */
    public Member getMember(String username){
        for (int i = 0; i < index; i++) {
            if(arr[i].getName().equals(username)){
                return arr[i];
            }
        }
        return null;
    }

    /**
     * returns the phone number of the given member
     * @param username the given username
     * @return the phone number of the user
     */
    public String getPhoneNumberByName(String username){
        for (int i = 0; i < index; i++) {
            if(arr[i].getName().equals(username)){
                return arr[i].getPhoneNumber();
            }
        }
        return "User not found";
    }
}
