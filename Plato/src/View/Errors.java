package View;

public class Errors {
    public static void showErrors(int errorID) {
        switch (errorID) {
            case 1:
                System.out.println("Wrong password! Please try again.");
                break;
            case 2:
                System.out.println("Sorry, only letters(a-z) are allowed.");
                break;
            case 3:
                System.out.println("Sorry, only digits(0-9) are allowed.");
                break;
            case 4:
                System.out.println("Sorry, only letters(a-z) and digits(0-9) are allowed.");
                break;
            case 5:
                System.out.println("Wrong format! please write in the correct format.");
                break;
        }
    }
}
