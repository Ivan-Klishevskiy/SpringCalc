package org.example.console;

import org.example.entity.User;
import org.example.service.CalcService;
import org.example.service.HistoryService;
import org.example.service.UserService;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class Application {
    private final ConsoleReader reader;
    private final ConsoleWriter writer;
    private final UserService userService;
    private final HistoryService historyService;
    private final CalcService calcService;
    private final MySession session;

    public Application(ConsoleReader reader, ConsoleWriter writer, UserService userService, HistoryService historyService, CalcService calcService, MySession session) {
        this.reader = reader;
        this.writer = writer;
        this.userService = userService;
        this.historyService = historyService;
        this.calcService = calcService;
        this.session = session;
    }

    private void calculator() {
        writer.write("First value:");
        double first = reader.getNumber();
        writer.write("Sign: ");
        String sign = reader.getSign();
        writer.write("Second value:");
        double second = reader.getNumber();
        double result = calcService.calculate(session.getSession("user"), first, second, sign);
        writer.write("Result: " + result);
    }

    private void authorization() {
        writer.write("Username: ");
        String username = reader.getLine();
        writer.write("Password: ");
        String password = reader.getLine();
        if (userService.findByUsername(username) != null) {
            if (userService.findByUsername(username).getPassword().equals(password)) {
                session.setSession("user", username);
            } else {
                writer.write("The password is incorrect");
            }
        } else {
            writer.write("User not found!");
        }
    }

    private void registration() {
        writer.write("Name: ");
        String name = reader.getLine();
        writer.write("Username: ");
        String username = reader.getLine();
        writer.write("Password: ");
        String password = reader.getLine();
        if (userService.findByUsername(username) == null) {
            userService.saveUser(new User(name, username, password));
        } else {
            writer.write("The user is already registered!");
        }
    }

    private void logout() {
        session.deleteAttribute("user");
    }

    private void printHistory() {
        writer.write("\n_______HISTORY________\n");
        historyService.getAllHistory(session.getSession("user")).forEach(writer::write);
    }


    private void deleteUserbyAdmin() {
        userService.getAllUsers()
                .forEach(i ->
                {
                    if (i.getUsername() != null) {
                        writer.write(i.getUsername());
                    }
                });
        writer.write("Enter a username:");
        userService.deleteUser(reader.getLine());
    }

    private void printHistoryByAdmin() {
        writer.write("_______HISTORY_BY_ADMIN_______");
        for (User user : userService.getAllUsers()) {
            if (user.getUsername() != null) {
                writer.write(user.getUsername()+": ");
                List<String> list = historyService.getAllHistory(user.getUsername());
                if(list!=null){
                    for (String s : list) {
                        writer.write(s);
                    }
                }
            }
        }

    }

    private int userMenu() {
        writer.write("1 - Calculator\n2 - Logout\n3 - Print history operation\n4- Exit");
        switch ((int) reader.getNumber()) {
            case 1 -> {
                calculator();
            }
            case 2 -> {
                logout();
            }
            case 3 -> {
                printHistory();
            }
            case 4 -> {
                return 1;
            }
        }
        return 0;
    }

    private int adminMenu() {
        writer.write("1 - Calculator\n2 - Logout\n3 - Print history operation\n4 - Delete user\n5 - print the history operation of all users \n6 - Exit");
        switch ((int) reader.getNumber()) {
            case 1 -> {
                calculator();
            }
            case 2 -> {
                logout();
            }
            case 3 -> {
                printHistory();
            }
            case 4 -> {
                deleteUserbyAdmin();
            }
            case 5 -> {
                printHistoryByAdmin();
            }
            case 6 -> {
                return 1;
            }
        }
        return 0;
    }

    public void run() {
        int exit = 0;
        while (exit == 0) {
            writer.write("\n\n\n\n\n");
            if (session.getSession("user") == null) {
                writer.write("1 - Registration\n2 - Authorization\n3- Exit");
                switch ((int) reader.getNumber()) {
                    case 1 -> {
                        registration();
                    }
                    case 2 -> {
                        authorization();
                    }
                    case 3 -> {
                        exit = 1;
                    }
                }
            } else if (userService.findByUsername(session.getSession("user")).getRole().equals("admin")) {
                exit = adminMenu();
            } else {
                exit = userMenu();
            }
        }
    }
}

