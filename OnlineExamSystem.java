import java.util.*;

class User {
    String username;
    String password;
    String name;

    User(String username, String password, String name) {
        this.username = username;
        this.password = password;
        this.name = name;
    }

    boolean login(String u, String p) {
        return username.equals(u) && password.equals(p);
    }

    void updateProfile(String newName) {
        name = newName;
        System.out.println("Profile updated successfully.");
    }

    void updatePassword(String newPass) {
        password = newPass;
        System.out.println("Password updated successfully.");
    }
}

class Question {
    String question;
    String[] options;
    int correctAnswer;

    Question(String q, String[] opt, int ans) {
        question = q;
        options = opt;
        correctAnswer = ans;
    }

    void display() {
        System.out.println(question);
        for(int i=0;i<options.length;i++) {
            System.out.println((i+1) + ". " + options[i]);
        }
    }
}

public class OnlineExamSystem {

    static Scanner sc = new Scanner(System.in);
    static User user = new User("nandhini","1234","Nandhini");

    static Question[] questions = {
        new Question(
                "Which language is used for Android development?",
                new String[]{"Java","Python","C++","PHP"},1),

        new Question(
                "Which company developed Java?",
                new String[]{"Microsoft","Sun Microsystems","Google","IBM"},2),

        new Question(
                "Which keyword is used to inherit a class in Java?",
                new String[]{"this","import","extends","implements"},3)
    };

    public static void main(String[] args) {

        System.out.println("===== ONLINE EXAM SYSTEM =====");

        System.out.print("Enter Username: ");
        String u = sc.next();

        System.out.print("Enter Password: ");
        String p = sc.next();

        if(!user.login(u,p)) {
            System.out.println("Invalid Login");
            return;
        }

        int choice;

        do {
            System.out.println("\n1. Start Exam");
            System.out.println("2. Update Profile");
            System.out.println("3. Change Password");
            System.out.println("4. Logout");

            System.out.print("Enter choice: ");
            choice = sc.nextInt();

            switch(choice) {

                case 1:
                    startExam();
                    break;

                case 2:
                    System.out.print("Enter new name: ");
                    String newName = sc.next();
                    user.updateProfile(newName);
                    break;

                case 3:
                    System.out.print("Enter new password: ");
                    String newPass = sc.next();
                    user.updatePassword(newPass);
                    break;

                case 4:
                    System.out.println("Logged out successfully.");
                    break;

                default:
                    System.out.println("Invalid choice");
            }

        } while(choice != 4);

    }

    static void startExam() {

        int score = 0;

        long startTime = System.currentTimeMillis();
        long examTime = 60000; // 60 seconds

        for(int i=0;i<questions.length;i++) {

            if(System.currentTimeMillis() - startTime > examTime) {
                System.out.println("\nTime Up! Auto submitting exam...");
                break;
            }

            System.out.println("\nQuestion " + (i+1));
            questions[i].display();

            System.out.print("Enter answer: ");
            int ans = sc.nextInt();

            if(ans == questions[i].correctAnswer)
                score++;
        }

        System.out.println("\nExam Submitted.");
        System.out.println("Your Score: " + score + "/" + questions.length);
    }
}