import java.util.ArrayList;

public class MemberCollection{
    Member[] arr;

    public MemberCollection() {
    }

    public void addNewMember(Member member){
        arr[arr.length] = member;
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
