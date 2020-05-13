
public class MemberCollection{
    private Member[] arr;
    private int index = 0;

    public MemberCollection() {
        arr = new Member[10];
    }

    public void addNewMember(Member member){
        arr[index] = member;
        index++;
    }

    public boolean containsMember(String username){
        for (int i = 0; i < index; i++) {
            if(arr[i].getName().equals(username)){
                return true;
            }
        }
        return false;
    }

    public Member getMember(String username){
        for (int i = 0; i < index; i++) {
            if(arr[i].getName().equals(username)){
                return arr[i];
            }
        }
        return null;
    }

    public int getLength(){
        return this.index;
    }

    public String getPhoneNumberByName(String username){
        for (int i = 0; i < index; i++) {
            if(arr[i].getName().equals(username)){
                return arr[i].getPhoneNumber();
            }
        }
        return "User not found";
    }
}
