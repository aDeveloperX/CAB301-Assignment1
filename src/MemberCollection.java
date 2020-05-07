import java.util.ArrayList;

public class MemberCollection{
    Member[] arr;
    int index = 0;

    public MemberCollection() {
        arr = new Member[20];
    }

    public void addNewMember(Member member){
        arr[index] = member;
        index++;
    }

    public boolean containsMember(String username){
        for (int i = 0; i < arr.length; i++) {
            if(arr[i].getName() == username){
                return true;
            }
        }
        return false;
    }

    public String getPhoneNumberByName(String username){
        for (int i = 0; i < arr.length; i++) {
            if(arr[i].getName() == username){
                return arr[i].getPhoneNumber();
            }
        }
        return "User not found";
    }



}
