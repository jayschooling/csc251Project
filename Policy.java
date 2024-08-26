public class Policy {
    private String policyNumber;
    private String providerName;
    private String firstName;
    private String lastName;
    private int age;
    private String smokingStatus; // "smoker" or "non-smoker"
    private int height; // in inches
    private int weight; // in pounds

    // No-arg constructor with default values
    public Policy() {
        this.policyNumber = "Unknown";
        this.providerName = "Unknown";
        this.firstName = "John";
        this.lastName = "Doe";
        this.age = 30;
        this.smokingStatus = "non-smoker";
        this.height = 60;
        this.weight = 150;
    }

    // Constructor with parameters
    public Policy(String policyNumber, String providerName, String firstName, String lastName, 
                  int age, String smokingStatus, int height, int weight) {
        this.policyNumber = policyNumber;
        this.providerName = providerName;
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.smokingStatus = smokingStatus;
        this.height = height;
        this.weight = weight;
    }

    // Getters and setters
    public String getPolicyNumber() { return policyNumber; }
    public void setPolicyNumber(String policyNumber) { this.policyNumber = policyNumber; }

    public String getProviderName() { return providerName; }
    public void setProviderName(String providerName) { this.providerName = providerName; }

    public String getFirstName() { return firstName; }
    public void setFirstName(String firstName) { this.firstName = firstName; }

    public String getLastName() { return lastName; }
    public void setLastName(String lastName) { this.lastName = lastName; }

    public int getAge() { return age; }
    public void setAge(int age) { this.age = age; }

    public String getSmokingStatus() { return smokingStatus; }
    public void setSmokingStatus(String smokingStatus) { this.smokingStatus = smokingStatus; }

    public int getHeight() { return height; }
    public void setHeight(int height) { this.height = height; }

    public int getWeight() { return weight; }
    public void setWeight(int weight) { this.weight = weight; }

    // Method to calculate BMI
    public double calculateBMI() {
        if (height == 0) {
            // Mistake: Should throw an exception or handle this case properly
            return 0; // Dividing by zero will lead to incorrect results
        }
        return (weight * 703.0) / (height * height);
    }

    // Method to calculate policy price
    public double calculatePolicyPrice() {
        double baseFee = 600;
        double additionalFee = 0;

        if (age > 50) {
            additionalFee += 75;
        }

        if (smokingStatus.equalsIgnoreCase("smoker")) {
            additionalFee += 100;
        }

        double bmi = calculateBMI();
        if (bmi > 35) {
            additionalFee += (bmi - 35) * 20;
        }

        return baseFee + additionalFee;
    }

    public static void main(String[] args) {
        Policy policy = new Policy();
        System.out.println("Policy Price: $" + policy.calculatePolicyPrice());
    }
}
