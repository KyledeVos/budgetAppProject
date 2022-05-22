package budgetApp.Controllers;

import budgetApp.Data.PopulateLinkedLists;

//Controller Class for Entire App
public class AppClass {

    public static void main(String[] args) {

//        AddDataToDataBase addDataToDataBase = new AddDataToDataBase();
//        addDataToDataBase.addIncome(3, "salary", 9000, "2022-04-01", "monthly", "");

        LinkedListsClass linkedListsClass = new LinkedListsClass();
        System.out.println("First Print:\n");
        linkedListsClass.printOutDatabase();
        PopulateLinkedLists populateLinkedLists = new PopulateLinkedLists();
        populateLinkedLists.initializeFromDatabase(linkedListsClass);
        System.out.println("\nsecond Print:\n");
        linkedListsClass.printOutDatabase();
    }

}
