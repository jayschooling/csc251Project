import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents an insurance policy.
 */
public class ProjectCSC251_Jahmeek_Harper {
    private String policyNumber;
    private String providerName;
    private String firstName;
    private String lastName;
    private int age;
    private String smokingStatus; // "smoker" or "non-smoker"
    private int height; // in inches
    private int weight; // in pounds

    /**
     * No-arg constructor with default values.
     */
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

    /**
     * Constructor with parameters to initialize a Policy object.
     *
     * @param policyNumber the policy number
     * @param providerName the provider name
     * @param firstName the policyholder's first name
     * @param lastName the policyholder's last name
     * @param age the policyholder's age
     * @param smokingStatus the policyholder's smoking status ("smoker" or "non-smoker")
     * @param height the policyholder's height in inches
     * @param weight the policyholder's weight in pounds
     */
    public Policy(String policyNumber, String providerName, String firstName, String lastName, 
                  int age, String smokingStatus, int height, int weight) {
        this.policyNumber = policyNumber;
        this.providerName = providerName;
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        setSmokingStatus(smokingStatus); // Use setter for validation
        this.height = height;
        this.weight = weight;
    }

    // Getters and setters

    /**
     * Gets the policy number.
     *
     * @return the policy number
     */
    public String getPolicyNumber() { return policyNumber; }
    public void setPolicyNumber(String policyNumber) { this.policyNumber = policyNumber; }

    /**
     * Gets the provider name.
     *
     * @return the provider name
     */
    public String getProviderName() { return providerName; }
    public void setProviderName(String providerName) { this.providerName = providerName; }

    /**
     * Gets the policyholder's first name.
     *
     * @return the first name
     */
    public String getFirstName() { return firstName; }
    public void setFirstName(String firstName) { this.firstName = firstName; }

    /**
     * Gets the policyholder's last name.
     *
     * @return the last name
     */
    public String getLastName() { return lastName; }
    public void setLastName(String lastName) { this.lastName = lastName; }

    /**
     * Gets the policyholder's age.
     *
     * @return the age
     */
    public int getAge() { return age; }
    public void setAge(int age) { 
        if (age > 0) {
            this.age = age;
        } else {
            throw new IllegalArgumentException("Age must be positive.");
        }
    }

    /**
     * Gets the policyholder's smoking status.
     *
     * @return the smoking status
     */
    public String getSmokingStatus() { return smokingStatus; }
    public void setSmokingStatus(String smokingStatus) { 
        if ("smoker".equalsIgnoreCase(smokingStatus) || "non-smoker".equalsIgnoreCase(smokingStatus)) {
            this.smokingStatus = smokingStatus;
        } else {
            throw new IllegalArgumentException("Smoking status must be 'smoker' or 'non-smoker'.");
        }
    }

    /**
     * Gets the policyholder's height.
     *
     * @return the height in inches
     */
    public int getHeight() { return height; }
    public void setHeight(int height) { 
        if (height > 0) {
            this.height = height;
        } else {
            throw new IllegalArgumentException("Height must be positive.");
        }
    }

    /**
     * Gets the policyholder's weight.
     *
     * @return the weight in pounds
     */
    public int getWeight() { return weight; }
    public void setWeight(int weight) { 
        if (weight > 0) {
            this.weight = weight;
        } else {
            throw new IllegalArgumentException("Weight must be positive.");
        }
    }

    /**
     * Calculates the Body Mass Index (BMI) of the policyholder.
     *
     * @return the BMI
     * @throws ArithmeticException if height is zero
     */
    public double calculateBMI() {
        if (height == 0) {
            throw new ArithmeticException("Height cannot be zero for BMI calculation.");
        }
        return (weight * 703.0) / (height * height);
    }

    /**
     * Calculates the policy price based on age, smoking status, and BMI.
     *
     * @return the policy price
     */
    public double calculatePolicyPrice() {
        double baseFee = 600;
        double additionalFee = 0;

        if (age > 50) {
            additionalFee += 75;
        }

        if ("smoker".equalsIgnoreCase(smokingStatus)) {
            additionalFee += 100;
        }

        double bmi = calculateBMI();
        if (bmi > 35) {
            additionalFee += (bmi - 35) * 20;
        }

        return baseFee + additionalFee;
    }

    /**
     * Main method to demonstrate policy price calculation.
     *
     * @param args command-line arguments
     */
    public static void main(String[] args) {
        // Demo class is embedded here for simplicity
        String filename = "PolicyInformation.txt";
        List<Policy> policies = new ArrayList<>();
        int smokerCount = 0;
        int nonSmokerCount = 0;

        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = br.readLine()) != null) {
                String policyNumber = line;
                String providerName = br.readLine();
                String firstName = br.readLine();
                String lastName = br.readLine();
                int age = Integer.parseInt(br.readLine());
                String smokingStatus = br.readLine();
                int height = Integer.parseInt(br.readLine());
                int weight = Integer.parseInt(br.readLine());

                Policy policy = new Policy(policyNumber, providerName, firstName, lastName, age, smokingStatus, height, weight);
                policies.add(policy);

                if ("smoker".equalsIgnoreCase(smokingStatus)) {
                    smokerCount++;
                } else {
                    nonSmokerCount++;
                }
            }
        } catch (IOException | NumberFormatException e) {
            System.err.println("Error reading file or parsing data: " + e.getMessage());
        }

        // Display policy details and counts
        for (Policy policy : policies) {
            System.out.printf("Policy Number: %s%n", policy.getPolicyNumber());
            System.out.printf("Provider Name: %s%n", policy.getProviderName());
            System.out.printf("Policyholder: %s %s%n", policy.getFirstName(), policy.getLastName());
            System.out.printf("Age: %d%n", policy.getAge());
            System.out.printf("Smoking Status: %s%n", policy.getSmokingStatus());
            System.out.printf("Height: %d inches%n", policy.getHeight());
            System.out.printf("Weight: %d pounds%n", policy.getWeight());
            System.out.printf("Policy Price: $%.2f%n%n", policy.calculatePolicyPrice());
        }

        System.out.printf("Number of Smokers: %d%n", smokerCount);
        System.out.printf("Number of Non-Smokers: %d%n", nonSmokerCount);
    }
}
