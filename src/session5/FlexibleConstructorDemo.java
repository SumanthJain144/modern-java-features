package session5;

//Java 25 

abstract class User {
    protected  String username;
    protected  int age;

    protected User(String username, int age) {
        this.username = username;
        this.age = age;
    }
}

class PremiumUser extends User {
    private double subscriptionFee;

    public PremiumUser(String username, int age, double subscriptionFee) {
        if (age < 0) throw new IllegalArgumentException("Age cannot be negative");
        if (subscriptionFee <= 0)
            throw new IllegalArgumentException("Subscription fee must be positive");
        
      
        super(username, age);
        this.subscriptionFee = subscriptionFee;
    }
}

class FlexibleConstructorDemo{
    public static void main(String[] args) {
        PremiumUser user = new PremiumUser("john_doe", 25, 9.99);
    }
}


