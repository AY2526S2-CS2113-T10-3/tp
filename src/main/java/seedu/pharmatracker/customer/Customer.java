package seedu.pharmatracker.customer;

import java.util.ArrayList;

/**
 * Represents a customer in the PharmaTracker system.
 * Holds personal details and a history of medications dispensed to them.
 */
public class Customer {
    private String customerId;
    private String name;
    private String phone;
    private String address; // Optional field
    private ArrayList<String> dispensingHistory;
    private ArrayList<String> allergies; // Optional field

    /**
     * Constructs a Customer with mandatory and optional details.
     *
     * @param customerId Unique identifier for the customer.
     * @param name       Full name of the customer.
     * @param phone      Contact phone number.
     * @param address    Residential address (can be empty if not provided).
     */
    public Customer(String customerId, String name, String phone, String address) {
        assert customerId != null : "Customer ID cannot be null";
        assert name != null : "Name cannot be null";
        this.customerId = customerId;
        this.name = name;
        this.phone = phone;
        this.address = (address == null) ? "" : address;
        this.dispensingHistory = new ArrayList<>();
        this.allergies = new ArrayList<>();
    }

    public String getCustomerId() {
        return customerId;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = (address == null) ? "" : address;
    }

    public ArrayList<String> getDispensingHistory() {
        return dispensingHistory;
    }

    /**
     * Adds a medication entry to the customer's dispensing history.
     * Called whenever a medication is dispensed to this customer.
     *
     * @param medicationRecord Description of the dispensed medication.
     */
    public void addDispensingHistory(String medicationRecord) {
        assert medicationRecord != null : "Medication record cannot be null";
        this.dispensingHistory.add(medicationRecord);
    }

    public ArrayList<String> getAllergies() {
        return allergies;
    }

    /**
     * Adds a known allergy to the customer's allergy list.
     *
     * @param allergy The allergy keyword to add (e.g. "penicillin").
     */
    public void addAllergy(String allergy) {
        assert allergy != null : "Allergy cannot be null";
        this.allergies.add(allergy.trim().toLowerCase());
    }

    /**
     * Replaces the customer's full allergy list.
     *
     * @param allergies New list of allergy strings.
     */
    public void setAllergies(ArrayList<String> allergies) {
        this.allergies = (allergies == null) ? new ArrayList<>() : allergies;
    }

    /**
     * Returns true if the medication name contains any of the customer's known allergens
     * (case-insensitive substring match).
     *
     * @param medicationName The name of the medication to check.
     * @return true if an allergy match is found.
     */
    public boolean isAllergicTo(String medicationName) {
        if (medicationName == null || allergies.isEmpty()) {
            return false;
        }
        String lowerName = medicationName.toLowerCase();
        for (String allergy : allergies) {
            if (lowerName.contains(allergy)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Returns the first allergen that matches the given medication name, or null if none.
     *
     * @param medicationName The name of the medication to check.
     * @return The first matching allergen keyword, or null.
     */
    public String getMatchedAllergen(String medicationName) {
        if (medicationName == null || allergies.isEmpty()) {
            return null;
        }
        String lowerName = medicationName.toLowerCase();
        for (String allergy : allergies) {
            if (lowerName.contains(allergy)) {
                return allergy;
            }
        }
        return null;
    }

    /**
     * Returns a short summary string for use in list and find displays.
     *
     * @return A formatted string showing customer ID, name, and phone.
     */
    @Override
    public String toString() {
        String result = "[" + customerId + "] " + name + " | Phone: " + phone;
        if (!address.isEmpty()) {
            result += " | Address: " + address;
        }
        if (!allergies.isEmpty()) {
            result += " | Allergies: " + String.join(", ", allergies);
        }
        return result;
    }
}
