
package se.kth.iv1350.pos.integration;

import se.kth.iv1350.pos.model.Sale;

/**
 * The class is a placeholder for the external system for the accounting.
 */
public class  AccountingSystem {

   
    /**
     * Creates a new instance of the  AccountingSystem class.
     *
     */
    AccountingSystem(){

     }
      /**
     * Saves the information about the sale in the accountingsystem
     * 
     * @param Sale The completed sale pending addition.
     */

    public void updateAccounting(Sale sale) {
        System.out.println("Transaction completed, accountingsystem updated");
    }
    
}

