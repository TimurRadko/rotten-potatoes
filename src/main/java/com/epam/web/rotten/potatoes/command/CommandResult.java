package com.epam.web.rotten.potatoes.command;

public class CommandResult {
    private final String command;
    private final boolean isRedirect;

    private CommandResult(String command, boolean isRedirect) {
        this.command = command;
        this.isRedirect = isRedirect;
    }

    public static CommandResult forward(String page) {
        return new CommandResult(page, false);
    }

    public static CommandResult redirect(String page) {
        return new CommandResult(page, true);
    }

    public String getCommand() {
        return command;
    }

    public boolean isRedirect() {
        return isRedirect;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof CommandResult)) {
            return false;
        }

        CommandResult that = (CommandResult) o;

        if (isRedirect() != that.isRedirect()) {
            return false;
        }
        return getCommand() != null ? getCommand().equals(that.getCommand()) : that.getCommand() == null;
    }

    @Override
    public int hashCode() {
        int result = getCommand() != null ? getCommand().hashCode() : 0;
        result = 31 * result + (isRedirect() ? 1 : 0);
        return result;
    }
}