import models.Worker;
import models.WorkerCollection;

import java.util.Properties;
import java.util.Scanner;
import java.util.Vector;

/**
 * Created by kevph on 3/20/2017.
 */
public class Test {
    public static void main(String[] args) {
        int input;
        do {
            String queryParam = "";
            Scanner keyboard = new Scanner(System.in);
            System.out.println("Welcome to the Library System");
            System.out.print("Press...\n'1' to insert Worker.\n" +
                            "'2' to search for book by BannerID.\n" +
                            "Choose entry: ");

            input = keyboard.nextInt();
            keyboard.nextLine();
            switch (input) {
                case 0:
                    System.out.println("Goodbye!");
                    break;
                case 1:
                    insertWorkerIntoDatabase();
                    break;
                case 2:
                    printBooksByBannerId();
                    break;
            }
        } while (input != 0);

    }


    public static void insertWorkerIntoDatabase() {
        String[] workerArray = {"BannerId", "Password", "FirstName", "LastName", "ContactPhone",
                "Email", "Credentials", "DateOfLatestCredentialsStatus", "DateOfHire", "Status"};
        Scanner keyboard = new Scanner(System.in);
        Properties props = new Properties();
        for (int i = 0; i < workerArray.length; i++) {
            System.out.print("Please enter " + workerArray[i] + ": ");
            String workerData = keyboard.nextLine();
            props.put(workerArray[i], workerData);
        }
        Worker worker = new Worker(props);
        worker.update();
        System.out.println("(" + worker.toString() + ") has been added!");
    }

    public static void printBooksByBannerId() {
        Scanner keyboard = new Scanner(System.in);
        System.out.print("Enter BannerID: ");
        String queryParam = keyboard.nextLine();
        WorkerCollection workerCollection = new WorkerCollection();
        Vector workers = workerCollection.findWorkersByBannerId(queryParam);
        for (int index = 0; index < workers.size(); index++) {
            System.out.println(workers.elementAt(index).toString());
        }
    }
}
