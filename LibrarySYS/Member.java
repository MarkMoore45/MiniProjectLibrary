package LibrarySYS;

public class Member {

    private int MemberID;
    private String forename;
    private String surname;
    private String password;
    private String email;
    private String address;
    private int phone;
    private static int count = 1;

    public  Member(){
        this("NA","NA","NA","NA","NA",0);
    }

    public Member(String forename, String surname, String password, String email, String address, int phone) {
        setMemberID();
        setForename(forename);
        setSurname(surname);
        setPassword(password);
        setEmail(email);
        setAddress(address);
        setPhone(phone);
    }

    public int getMemberID() {
        return MemberID;
    }

    public void setMemberID() {
        this.MemberID = count++;
    }

    public String getForename() {
        return forename;
    }

    public void setForename(String forename) {
        this.forename = forename;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getPhone() {
        return phone;
    }

    public void setPhone(int phone) {
        this.phone = phone;
    }

    @Override
    public String toString() {
        return "\nMemberID: " + getMemberID() +
                "\nForename: " + getForename() +
                "\nSurname: " + getSurname() +
                "\nPassword: " + getPassword() +
                "\nE-mail: " + getEmail() +
                "\nAddress: " + getAddress() +
                "\nPhone Number: " + getPhone();
    }
}
