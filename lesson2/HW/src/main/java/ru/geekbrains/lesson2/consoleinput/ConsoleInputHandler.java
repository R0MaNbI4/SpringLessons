package ru.geekbrains.lesson2.consoleinput;

import org.springframework.util.StringUtils;

public class ConsoleInputHandler {
    private String[] args;
    private Command command;
    private int id;
    private int count;
    private boolean isValidCommand;
    private boolean isValidId;
    private boolean isValidCount;

    public boolean validate(String input) {
        args = input.split("\\s");
        if (!parseCommand()) {
            isValidCommand = false;
            return false;
        }
        if (command != Command.HELP && command != Command.RESET) {
            if (command == Command.PRINT) {
                if (args.length == 1) {
                    count = -1;
                } else {
                    if (!parseCount(1)) {
                        isValidCount = false;
                        return false;
                    }
                }
            } else {
                if (!parseId(1)) {
                    isValidId = false;
                    return false;
                }
                if (args.length == 2) {
                    count = 1;
                } else {
                    if (args.length > 2) {
                        if (!parseCount(2)) {
                            isValidCount = false;
                            return false;
                        }
                    }
                }
            }
        }
        return true;
    }

    private boolean parseCommand() {
        try {
            command = Command.valueOf(args[0].toUpperCase());
        } catch (IllegalArgumentException | ArrayIndexOutOfBoundsException e) {
            return false;
        }
        return true;
    }

    private boolean parseId(int argIdx) {
        int value = parseInt(argIdx);
        if (value != -1) {
            id = value;
        }
        return value != -1;
    }

    private boolean parseCount(int argIdx) {
        int value = parseInt(argIdx);
        if (value != -1) {
            count = value;
        }
        return value != -1;
    }

    private int parseInt(int argIdx) {
        int value;
        try {
            value = Integer.parseInt(args[argIdx]);
        } catch (NumberFormatException e) {
            return -1;
        }
        return value;
    }

    public boolean isValidCommand() {
        return isValidCommand;
    }

    public boolean isValidId() {
        return isValidId;
    }

    public boolean isValidCount() {
        return isValidCount;
    }

    public Command getCommand() {
        return command;
    }

    public int getId() {
        return id;
    }

    public int getCount() {
        return count;
    }
}