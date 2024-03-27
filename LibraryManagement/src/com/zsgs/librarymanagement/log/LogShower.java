package com.zsgs.librarymanagement.log;

import java.io.PrintWriter;

/*Optional feature to save logs in a file 
 * Future enhancement - Add option to save logs in a database
*/
public class LogShower {

    public static void saveToFile(Exception exception) {

        LogShower logShower = new LogShower();
        logShower.writeToFile(exception);
    }

    private void writeToFile(Exception exception) {

        try (PrintWriter writer = new PrintWriter("log.txt")) {
            writer.println("Time :" + new java.util.Date() + "\n" + exception.getMessage() + "\n"
                    + exception.getStackTrace() + "\n" + exception.getCause());
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
