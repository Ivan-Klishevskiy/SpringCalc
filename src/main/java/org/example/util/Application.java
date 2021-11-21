package org.example.util;

import org.example.entity.User;
import org.example.service.CalcService;
import org.example.service.StorageService;
import org.example.storage.MySession;
import org.springframework.stereotype.Component;

@Component
public class Application {
    private final ConsoleReader reader;
    private final ConsoleWriter writer;
    private final StorageService storage;

    public Application(ConsoleReader reader, ConsoleWriter writer, StorageService storage) {
        this.reader = reader;
        this.writer = writer;
        this.storage = storage;
    }

    private void calculator() {
        writer.write("First value:");
        double first = reader.getNumber();
        writer.write("Sign: ");
        String sign = reader.getSign();
        writer.write("Second value:");
        double second = reader.getNumber();
        double result=CalcService.calculate(first, second, sign);
        System.out.println("Result: "+result);
        storage.addOperation(first,sign,second,result);
    }

    private void authorization() { //////////////////////////////
        writer.write("Username: ");
        String username = reader.getLine();
        writer.write("Password: ");
        String password = reader.getLine();
        if (storage.findByUsername(username) != null) {
            if (storage.findByUsername(username).getPassword().equals(password)) {
                MySession.setSession("user", storage.findByUsername(username).getName());
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
        if (storage.findByUsername(username) == null) {
            storage.saveUser(new User(name, username, password));
        } else {
            writer.write("The user is already registered!");
        }
    }

    private void logout(){
        MySession.deleteAttribute("user");
    }

    private void printHistory(){
        writer.write("\n_______HISTORY________\n");
        storage.getAllHistory().forEach(writer::write);
    }

    public void run() {
        int exit=0;
        while (exit==0) {
            writer.write("\n\n\n\n\n");
            if (MySession.getSession("user") == null) {
                writer.write("1 - Registration\n2 - Authorization\n3- Exit");
                switch ((int) reader.getNumber()) {
                    case 1 -> {
                        registration();
                    }
                    case 2 -> {
                        authorization();
                    }
                    case 3 ->{
                        exit=1;
                    }
                }
            } else {
                writer.write("1 - Calculator\n2 - Logout\n3-Print history operation\n4- Exit");
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
                    case 4 ->{
                        exit=1;
                    }
                }
            }
        }
    }
}
