package kr.co.gd.command;

import lombok.extern.slf4j.Slf4j;

import java.util.Map;
import java.util.logging.Logger;

/**
 *
 * Server status handler
 *
 * @author a.khettar
 *
 */
@Slf4j
public class StatusHandler implements CommandHandler {

    private final String command;
    private final Logger logger = Logger.getLogger(StatusHandler.class.getName());
    private final String cr = System.getProperty("os.name").matches("(W|w)indows.*") ? "\r\n" : "\n";

    public StatusHandler(final String command) {
        this.command = command;
    }

    /*
     * (non-Javadoc)
     *
     * @see com.akhettar.telnet.command.CommandHandler#handle()
     */
    @Override
    public String handle() {
        StringBuilder builder = new StringBuilder();
        logger.info(">>>> [StatusHandler] handle command, " + command);
//        builder.append("goto dpick");
//        builder.append(cr);

        builder.append("status: DockingState: undocked");
        builder.append(cr);
        builder.append("stateofcharge: 80");
        builder.append(cr);
        builder.append("location: 10 20 30");
        builder.append(cr);
//        builder.append("arrived at DPICK");
//        builder.append("dpick complete");
//        builder.append(cr);

        return builder.toString();
    }

}
