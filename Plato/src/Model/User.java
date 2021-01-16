package Model;

public class User {
    private String firstname;
    private String lastName;
    private String username;
    private String password;
    private String email;
    private String phoneNumber;
    private int userID;

    public User() {

    }

    public User(String firstname, String lastName, String username, String password, String email, String phoneNumber, int userID) {
        this.firstname = firstname;
        this.lastName = lastName;
        this.username = username;
        this.password = password;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.userID = userID;
    }

    public int getUserID() {
        return userID;
    }

    public String getEmail() {
        return email;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getLastName() {
        return lastName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    //    public void setFirstname(String firstname) {
//        this.firstname = firstname;
//    }
//
//    public void setLastName(String lastName) {
//        this.lastName = lastName;
//    }
//
//    public void setUser(String username) {
//        this.username = username;
//    }
//
//    public void setID(int userID) {
//        this.userID = userID;
//    }
//
//    public void setPassword(String password) {
//        this.password = password;
//    }
//
//    public void setEmail(String email) {
//        this.email = email;
//    }
//
//    public void setPhoneNumber(String phoneNumber) {
//        this.phoneNumber = phoneNumber;
//    }

    public boolean changePassword(String currentPassword, String newPassword) {
        if (this.password.equals(currentPassword)) {
            this.password = newPassword;
            Database.updateFiles();
            return true;
        } else {
            //Error
            return false;
        }
    }

    public boolean editField(String field, String newValue) {
        if (field.equals("firstname")) {
            if (newValue.matches("[a-zA-Z]+")) {
                this.firstname = newValue;
                Database.updateFiles();
                return true;
            } else {
                //Error
                return false;
            }
        } else if (field.equals("lastname")) {
            if (newValue.matches("[a-zA-Z]+")) {
                this.lastName = newValue;
                Database.updateFiles();
                return true;
            } else {
                //Error
                return false;
            }
        } else if (field.equals("username")) {
            if (newValue.matches("[a-zA-Z0-9]+")) {
                if (!Tools.isUsernameExist(newValue)) {
                    this.username = newValue;
                    Database.updateFiles();
                    return true;
                } else {
                    //Error
                    return false;
                }
            } else {
                //Error
                return false;
            }
        } else if (field.equals("email")) {
            if (newValue.matches("[a-zA-Z0-9.]+[@][a-z]+[.][a-z]+")) {
                if (!Tools.isEmailExist(newValue)) {
                    this.email = newValue;
                    Database.updateFiles();
                    return true;
                } else {
                    //Error
                    return false;
                }
            } else {
                //Error
                return false;
            }
        } else if (field.equals("phoneNumber")) {
            if (newValue.matches("[0-9]+")) {
                if (!Tools.isPhoneNumberExist(newValue)) {
                    this.phoneNumber = newValue;
                    Database.updateFiles();
                    return true;
                } else {
                    //Error
                    return false;
                }
            } else {
                //Error
                return false;
            }
        } else {
            //Error
            return false;
        }
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
}
