package kr.co.gd.command;

import java.util.logging.Logger;

/**
 * Factory generating command handlers
 *
 * @author a.khettar
 *
 */
public class CommandHandlerFactory {
    private final Logger logger = Logger.getLogger(StatusHandler.class.getName());
    private static CommandHandlerFactory factory;

    /**
     * Factory method.
     *
     * @return
     */
    public static CommandHandlerFactory getInstance() {
        if (factory != null) {
            return factory;
        }
        return new CommandHandlerFactory();
    }

    /**
     * Get handler for given command.
     *
     * @param command
     * @param workingDir
     * @return
     */
    public CommandHandler getHandler(final String command, final String workingDir) {
        logger.info(">>>>> [CommandHandler] command?: " + command);

        if (command.matches("^cd .*")) {
            return new CDHandler(command, workingDir);
        } else if (command.matches("^ls.*")) {
            return new LSHandler(command, workingDir);
        } else if (command.matches("^pwd.*$")) {
            return new PWDHandler(command, workingDir);
        } else if (command.matches("^mkdir .*")) {
            return new MKDIRHandler(command, workingDir);
        } else if (command.equalsIgnoreCase("status")) {
            return new StatusHandler(command);
        } else if (command.equalsIgnoreCase("exit")) {
            return new ExitHandler();
        } else if (command.startsWith("goto")) {
            // command: goto 목적지
            return new GotoHandler(command);
        } else if (command.startsWith("execute")) {
            // command: execute macro 매크로명
            return new ExecuteMecroHandler(command);
        } else if (command.equalsIgnoreCase("")) {
            return new TempCmdHandler();
        } else {
            return new UnknownCommandHandler(command);
        }

    }

}
