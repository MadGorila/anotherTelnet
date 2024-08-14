package kr.co.gd.command;

import lombok.extern.slf4j.Slf4j;

import java.util.logging.Logger;

/**
 *
 * Server status handler
 *
 * @author a.khettar
 *
 */
@Slf4j
public class ExecuteMecroHandler implements CommandHandler {

    private final String command;
    private final Logger logger = Logger.getLogger(ExecuteMecroHandler.class.getName());
    private final String cr = System.getProperty("os.name").matches("(W|w)indows.*") ? "\r\n" : "\n";

    public ExecuteMecroHandler(final String command) {
        this.command = command;
    }

    /*
     * (non-Javadoc)
     *
     * @see com.akhettar.telnet.command.CommandHandler#handle()
     */
    @Override
    public String handle() {
//        logger.info(">>>> [Goto Handler] handle command, " + command);
        // "executemacro dpick start"
        log.info(">>>> [Execute Handler] handle command: {}", command);
        String[] cmdArr = command.split(" ");

        StringBuilder builder = new StringBuilder();
        if (cmdArr.length > 1) {
            builder.append("Executing macro ");
            builder.append(cmdArr[1]);
            builder.append(" start");
            builder.append(cr);
            builder.append("WaitState: Waiting -1 seconds with status \"Waiting\"");
            builder.append(cr);
        }
        return builder.toString();
    }

}
