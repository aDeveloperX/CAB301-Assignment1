public class Member {
    private String name;
    private String address;
    private String phoneNumber;
    private String password;

    public Member(String surname, String givenname, String address, String phoneNumber, String password) {
        this.name = surname+givenname;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.password = password;
    }

    public String getName(){
        return this.name;
    }

    public String getPhoneNumber(){
        return this.phoneNumber;
    }


}
