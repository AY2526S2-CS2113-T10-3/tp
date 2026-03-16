package seedu.pharmatracker.command;

import java.util.ArrayList;

import seedu.pharmatracker.data.Inventory;
import seedu.pharmatracker.data.Medication;

public class ListCommand extends Command {
    private static final int LOW_STOCK_THRESHOLD = 10;
    private static final String DIVIDER = "------------------------------------------------------";

    @Override
    public void execute(Inventory inventory) {
        ArrayList<Medication> medicationList = inventory.getMedications();
        if (medicationList.isEmpty()) {
            System.out.println("Inventory is empty.");
            return;
        }
        System.out.println("PharmaTracker Inventory:");
        for (int i = 0; i < medicationList.size(); i++) {
            Medication med = medicationList.get(i);
            String lowStock = med.getQuantity() <= LOW_STOCK_THRESHOLD ? " [LOW STOCK]" : "";
            System.out.println((i + 1) + ". " + med.toString() + lowStock);
        }
        System.out.println(DIVIDER);
        System.out.println("Total Medications: " + medicationList.size());
    }
}
