# Project Portfolio Page: Shamit (ShamitGupta)

## Project Overview: PharmaTracker
PharmaTracker is a Java-based CLI application engineered for pharmacy professionals to manage medication inventory and patient records with high precision. Designed for environments where speed and keyboard-driven workflows are prioritized over GUI navigation, the system allows staff to handle complex tasks—such as tracking stock levels, monitoring expiry dates, and managing customer histories—through rapid command-line inputs.

---

## Technical Contributions Summary

### **Code Contributions**
My full technical contribution history, including logic implementation and bug fixes, can be viewed on the [PharmaTracker Dashboard](https://github.com/ShamitGupta/tp).

### **Functional Enhancements**
* **Customer Infrastructure (Backend)**: Engineered the foundational data layer for the patient tracking system, enabling the application to move beyond simple inventory to a relational patient-management tool.
* **Data Persistence Layer**: Developed the file-based storage logic within the `Storage` class to ensure patient data and dispensing records survive application restarts.
* **Intelligent Stock Monitoring**: Created the `lowstock` logic and configurable threshold engine, allowing users to identify dwindling supplies before they impact patient care.
* **Advanced Command Processing**: Refined the command parser to support complex, multi-parameter inputs required for customer registration and partial record updates.

---

## Detailed Enhancement: Backend Customer Infrastructure

### **Rationale and Objective**
The primary goal of this enhancement was to provide a robust backbone for v2.0 patient-centric features. I designed the `Customer` and `CustomerList` modules to bridge the gap between simple medication tracking and comprehensive pharmacy management. By establishing this backend early, I provided a stable API for my teammates to build specialized features like history tracking and record updates.

### **Technical Implementation**
The system is built upon two core classes that follow clean architectural principles:

1.  **The `Customer` Model**: This represents a single patient and encapsulates data including their `customerId`, `name`, `phone`, and residential `address`. A critical design feature is the internal `ArrayList<String>` designated as `dispensingHistory`, which dynamically grows as medications are dispensed to that individual, creating a permanent clinical audit trail.
2.  **The `CustomerList` Wrapper**: This acts as the primary in-memory data manager. It provides clean APIs for common operations such as `addCustomer()`, `deleteCustomer()`, and keyword-based retrieval via `findByName()`, ensuring that the rest of the application interacts with data in a controlled, validated manner.

### **Design Decisions and Logic Flow**
* **Single Responsibility Principle**: I chose to separate the data model (`Customer`) from the collection logic (`CustomerList`). This decoupling ensured that team members could implement user-facing commands without needing to manage the internal storage list directly.
* **Validation Layer**: By wrapping the internal list, I implemented strict index validation (using `assert` statements) to prevent common runtime errors like `IndexOutOfBoundsException` during rapid command entry.

The following sequence diagram demonstrates the interaction between components during a customer registration:

![Sequence diagram showing the addition of a customer](../images/AddCustomerSequence.png)

### **Evaluative Comparison of Alternatives**
* **Alternative: Integrating Customers into Inventory**: I considered adding customer tracking directly to the existing `Inventory` class. This was rejected to avoid a "God Class" anti-pattern; keeping them separate makes the code more modular and easier to debug.
* **Alternative: Stateless Text File Storage**: Another option was to write records directly to disk without an in-memory model. This was rejected because performance would degrade as the patient database grew, making search operations like `findByName()` inefficient.

---

## Documentation and User Support Contributions

### **User Guide Portfolio**
I took a leadership role in ensuring the User Guide was not just a technical manual, but a practical resource for pharmacy staff. My contributions were focused on making the transition from manual tracking to PharmaTracker as seamless as possible:

* **Command Design Documentation**: I authored the technical instructions for all patient-related commands, including `add-customer`, `listcustomers`, `view-customer`, and `updatecustomer`.
* **Logic Clarification**: For the `lowstock` command, I explicitly documented the strict `<` (less-than) comparison logic to ensure users understood why an item at exactly the threshold is not flagged, preventing confusion during inventory audits.
* **Discrepancy Audit**: I performed a comprehensive cross-referencing audit between the manual and the source code. This led to correcting a critical error where the application-closing command was listed as `bye` in the guide but implemented as `exit` in the code.
* **Quick Reference Tools**: I developed the **Command Summary** table, providing users with a high-density reference guide for all 17 available commands and their respective flag formats.

### **Developer Guide Portfolio**
To support the long-term maintainability of the project, I provided high-level architectural walkthroughs for the following areas:

* **Registration Logic**: I detailed the "Add Customer" flow, explaining how the `Parser` decomposes complex input strings into discrete attributes before constructing the `AddCustomerCommand`.
* **State Analysis**: My contributions include design rationale tables explaining the use of `null` values for absent flags in `UpdateCustomerCommand`, which allows for "partial updates" where users only change the fields they need without overwriting existing data.
* **Visual Documentation**: I created and integrated the PUML sequence diagrams that visualize the interactions between the `Ui`, `Parser`, and `CustomerList` components.