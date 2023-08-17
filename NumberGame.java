import java.util.*;

class random_no {
  int user_no, int_random;
  int k = 5, count = 0;

  public void gen() {
    // Here we generated the random Number
    Random rand = new Random();

    int min = 0;
    int max = 100;
    int_random = (int) Math.floor(Math.random() * (max - min + 1) + min);
  }

  public void get() {

    // Here we have given 5 attempts to user to guess the number
    while (k > 0) {
      Scanner sc = new Scanner(System.in);
      System.out.println("Guess a Number");
      user_no = sc.nextInt();

      if (int_random == user_no) {
        System.out.println("Your Answer is correct");
        break;
      } else if (int_random > user_no) {
        System.out.println("The Guess is too Low" + " " + "Now you have" + " " + (k - 1) + " " + "attemps");
        k--;
        count++;
      } else if (int_random < user_no) {
        System.out.println("The Guess is too High" + " " + "Now you have" + " " + (k - 1) + " " + "attemps");
        k--;
        count++;
      }
    }
    System.out.println("Your Score:");
    System.out.println("You have Taken" + " " + count + " " + "attempts");
  }
}

class NumberGame {
  public static void main(String args[]) {
    System.out.println("Number Game");
    System.out.println("Instruction :");
    System.out.println("1. You Have Given 5 attempts");
    System.out.println("2. We'll give the score at last ");

    random_no ay = new random_no();
    ay.gen();
    ay.get();
    while (true) {
      Scanner se = new Scanner(System.in);
      System.out.println("If you wants to Play again type Y, if not type N ");
      String user_choice = se.nextLine();
      if (user_choice.equals("Y")) {
        random_no ae = new random_no();
        ae.gen();
        ae.get();
      } else {
        System.out.println("Game Over");
        break;
      }
    }
  }
}