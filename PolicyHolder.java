import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

class PolicyHolder {
    private String firstName;
    private String lastName;
    private int age;
    private String smokingStatus;
    private int height;
    private int weight;

    public PolicyHolder(String firstName, String lastName, int age, String smokingStatus, int height, int weight) {
        this.firstName = firstName;
        this.lastName = lastName;
        setAge(age);
        setSmokingStatus(smokingStatus);
        setHeight(height);
        setWeight(weight);
    }

    public String getFirstName() { return firstName; }
    public void setFirstName(String firstName) { this.firstName = firstName; }

    public String getLastName() { return lastName; }
    public void setLastName(String lastName) { this.lastName = lastName; }

    public int getAge() { return age; }
    public void setAge(int age) { 
        if (age > 0) {
            this.age = age;
        } else {
            throw new IllegalArgumentException("Age must be positive.");
        }
    }

    public String getSmokingStatus() { return smokingStatus; }
    public void setSmokingStatus(String smokingStatus) { 
        if ("smoker".equalsIgnoreCase(smokingStatus) || "non-smoker".equalsIgnoreCase(smokingStatus)) {
            this.smokingStatus = smokingStatus;
        } else {
            throw new IllegalArgumentException("Smoking status must be 'smoker' or 'non-smoker'.");
        }
    }

    public int getHeight() { return height; }
    public void setHeight(int height) { 
        if (height > 0) {
            this.height = height;
        } else {
            throw new IllegalArgumentException("Height must be positive.");
        }
    }

    public int getWeight() { return weight; }
    public void setWeight(int weight) { 
        if (weight > 0) {
            this.weight = weight;
        } else {
            throw new IllegalArgumentException("Weight must be positive.");
        }
    }

    @Override
    public String toString() {
        return String.format("Policyholder: %s %s, Age: %d, Smoking Status: %s, Height: %d inches, Weight: %d pounds",
                firstName, lastName, age, smokingStatus, height, weight);
    }
}

class Policy {
    private static int policyCount = 0;
    private String policyNumber;
    private String providerName;
    private PolicyHolder holder;

    public Policy(String policyNumber, String providerName, PolicyHolder holder) {
        this.policyNumber = policyNumber;
        this.providerName = providerName;
        this.holder = holder;
        policyCount++;
    }

    public String getPolicyNumber() { return policyNumber; }
    public void setPolicyNumber(String policyNumber) { this.policyNumber = policyNumber; }

    public String getProviderName() { return providerName; }
    public void setProviderName(String providerName) { this.providerName = providerName; }

    public PolicyHolder getHolder() { return holder; }
    public void setHolder(PolicyHolder holder) { this.holder = holder; }

    public double calculateBMI() {
        if (holder.getHeight() == 0) {
            throw new ArithmeticException("Height cannot be zero for BMI calculation.");
        }
        return (holder.getWeight() * 703.0) / (holder.getHeight() * holder.getHeight());
    }

    public double calculatePolicyPrice() {
        double baseFee = 600;
        double additionalFee = 0;

        if (holder.getAge() > 50) {
            additionalFee += 75;
        }

        if ("smoker".equalsIgnoreCase(holder.getSmokingStatus())) {
            additionalFee += 100;
        }

        double bmi = calculateBMI();
        if (bmi > 35) {
            additionalFee += (bmi - 35) * 20;
        }

        return baseFee + additionalFee;
    }

    public static int getPolicyCount() { return policyCount; }

    @Override
    public String toString() {
        return String.format("Policy Number: %s, Provider Name: %s, %s, Policy Price: $%.2f",
                policyNumber, providerName, holder.toString(), calculatePolicyPrice());
    }

    public static void main(String[] args) {
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

                PolicyHolder holder = new PolicyHolder(firstName, lastName, age, smokingStatus, height, weight);
                Policy policy = new Policy(policyNumber, providerName, holder);
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

        for (Policy policy : policies) {
            System.out.println(policy);
        }

        System.out.printf("Number of Smokers: %d%n", smokerCount);
        System.out.printf("Number of Non-Smokers: %d%n", nonSmokerCount);
        System.out.printf("Total Policies Created: %d%n", Policy.getPolicyCount());
    }
}
