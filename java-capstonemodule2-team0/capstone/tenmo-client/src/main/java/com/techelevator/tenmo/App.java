package com.techelevator.tenmo;

import com.techelevator.tenmo.model.*;
import com.techelevator.tenmo.services.ApiService;
import com.techelevator.tenmo.services.AuthenticationService;
import com.techelevator.tenmo.services.ConsoleService;
import com.techelevator.tenmo.services.TransferService;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.security.Principal;
import java.util.List;
import java.util.Scanner;


public class App {
    Scanner scanner = new Scanner(System.in);
    private static final String API_BASE_URL = "http://localhost:8080";
    private ConsoleService consoleService = new ConsoleService();
    private AuthenticationService authenticationService = new AuthenticationService(API_BASE_URL);
    private Transfer transfer = new Transfer();
    private Account account = new Account();
    private User user = new User();
    private AuthenticatedUser currentUser;
    private RestTemplate restTemplate = new RestTemplate();
    private ConsoleService console = new ConsoleService();
    private ApiService apiService = new ApiService(API_BASE_URL,currentUser);
    private TransferService transferService = new TransferService(API_BASE_URL);


    public static void main(String[] args) {
        App app = new App();
        app.run();
    }


    private void run() {
        consoleService.printGreeting();
        loginMenu();
        if (currentUser != null) {
            mainMenu();
        }
    }

    private void loginMenu() {
        int menuSelection = -1;
        while (menuSelection != 0 && currentUser == null) {
            consoleService.printLoginMenu();
            menuSelection = consoleService.promptForMenuSelection("Please choose an option: ");
            if (menuSelection == 1) {
                handleRegister();
            } else if (menuSelection == 2) {
                handleLogin();
            } else if (menuSelection == 3) {
                viewCurrentBalance();
            } else if (menuSelection != 0) {
                System.out.println("Invalid Selection");
                consoleService.pause();
            }
        }
    }

    private void handleRegister() {
        System.out.println("Please register a new user account");
        UserCredentials credentials = consoleService.promptForCredentials();
        if (authenticationService.register(credentials)) {
            System.out.println("Registration successful. You can now login.");
        } else {
            consoleService.printErrorMessage();
        }
    }

    private void handleLogin() {
        UserCredentials credentials = consoleService.promptForCredentials();
        currentUser = authenticationService.login(credentials);
        if (currentUser == null) {
            consoleService.printErrorMessage();
        }
    }

    private void mainMenu() {
        int menuSelection = -1;
        while (menuSelection != 0) {
            consoleService.printMainMenu();
            menuSelection = consoleService.promptForMenuSelection("Please choose an option: ");
            if (menuSelection == 1) {
                viewCurrentBalance();
            } else if (menuSelection == 2) {
                viewTransferHistory();
            } else if (menuSelection == 3) {
                viewPendingRequests();
            } else if (menuSelection == 4) {
                sendBucks();
            } else if (menuSelection == 5) {

            } else if (menuSelection == 0) {
                continue;
            } else {
                System.out.println("Invalid Selection");
            }
            consoleService.pause();
        }
    }

    private void viewCurrentBalance() {
        ApiService apiService = new ApiService(API_BASE_URL, currentUser);
        try {

        System.out.println("Your current account balance is:  $" +  apiService.getBalance());
        } catch (NullPointerException e) {
            System.out.println("No balance");
        }
    }

    private void viewTransferHistory() {
        // TODO Auto-generated method stub
        Transfer[] arraylist = new Transfer[]{};
        String username = currentUser.getUser().getUsername();
        try{
            arraylist = transferService.viewMyTransfer(currentUser.getUser().getId());
            for (Transfer transfer: arraylist){
                System.out.println("From:" + "   " + "To:" + "   " + "Amount:");

                    System.out.println(username + "   " + transfer.getAccountTo() + "   " + transfer.getAmount());
            }
        }catch (RestClientException rce){
            System.out.println("Bad path");
        }
    }

    private void viewPendingRequests() {
        // TODO Auto-generated method stub

    }

    private void sendBucks() {
        TransferService transferService = new TransferService(API_BASE_URL);
        showAllUsers();
        System.out.println("Enter account ID to send funds to (0 to cancel: ");
        int input = scanner.nextInt();
        if (input == currentUser.getUser().getId()) {
            System.out.println("Invalid transfer. Can't send money to self.");
            console.printMainMenu();
        }
        System.out.println("Enter amount to transfer $: ");
        double response = scanner.nextDouble();
        try {
            Transfer transfer = new Transfer();
            transfer.setAccountFrom(currentUser.getUser().getId());
            transfer.setAccountTo(input);
            transfer.setAmount(response);
            transfer.setTransferStatusDesc("Approved");

            transferService.sendBucks(currentUser,transfer);
            System.out.println("Transfer completed.");
        } catch(RestClientException e) {
            e.getMessage();
        }

    }
    private User[] showAllUsers() {
        TransferService transferService = new TransferService(API_BASE_URL);
        User[] users = transferService.getAllUsers();
        System.out.println("Username | ID");
        System.out.println("-------------");
        for (User u : users) {
                System.out.println(u.getUsername() + " " + u.getId());
            }
        return users;
    }


    }



//    private void printUsers(User[] users) {
//
//        System.out.println("-------------------------------");
//        System.out.println("Users");
//        System.out.println("ID          Name");
//        System.out.println("-------------------------------");
//        for(User user : users){
//            System.out.println(user.toString());
//        }
//        System.out.println();
 //   }

