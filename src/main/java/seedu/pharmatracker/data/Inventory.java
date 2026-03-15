package seedu.pharmatracker.data;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Comparator;

public class Inventory {
    private ArrayList<Medication> medications;
    private int medicationCount;

    public Inventory() {
        this.medications = new ArrayList<>();
        this.medicationCount = 0;
    }

    public void addMedication(Medication medication) {
        medications.add(medication);
        medicationCount++;
    }

    public ArrayList<Medication> getMedications() {
        return this.medications;
    }

    public Medication getMedication(int index) {
        return this.medications.get(index);
    }

    public void sortByExpiryDate() {
        if (medications.isEmpty()) {
            System.out.println("Inventory is empty.");
            return;
        }
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        medications.sort(Comparator.comparing(med -> {
            try {
                return LocalDate.parse(med.getExpiryDate(), formatter);
            } catch (DateTimeParseException e) {
                return LocalDate.MAX;
            }
        }));
        System.out.println("Medications sorted by expiry date:");
        listMedications();
    }
}
