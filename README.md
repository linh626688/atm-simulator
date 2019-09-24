## ATM-simulator
- Base on old project atm-simulator
- Update use some function of Java 8 with stream function for collection
- Restructure and refactor code


###Spring-boot 
* Init Database file dump: `\resources\atm-simulator.sql`
    * Can auto init Data from CSV file by uncomment this code
    ```    @Override
           public void run(String... args) throws Exception {
       //        init account to DB
               accountRepository.save(Utils.importAccountFromCSV());
       
       //        init transaction log to DB
               transactionRepository.save(Utils.getTransactionFromCSV());
           }
  ```
* Run app: Main `SpringBootWebApplication.java`
* Test: `ATMSimulatorTests.java`