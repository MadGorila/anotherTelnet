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
public class GotoHandler implements CommandHandler {

    private final String command;
    private final Logger logger = Logger.getLogger(GotoHandler.class.getName());
    private final String cr = System.getProperty("os.name").matches("(W|w)indows.*") ? "\r\n" : "\n";

    public GotoHandler(final String command) {
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
        // command: goto 목적지
        log.info(">>>> [Goto Handler] handle command: {}", command);
        String[] cmdArr = command.split(" ");

        StringBuilder builder = new StringBuilder();
        if (cmdArr.length > 1) {
//            builder.append("WaitState: Waiting -1 seconds with status \"");
//            builder.append(cmdArr[1]);
//            builder.append(" location\"");
//            builder.append(cr);

//            builder.append("arrived at ");
//            builder.append(cmdArr[1]);
//            builder.append(cr);

            builder.append("Going to ");
            builder.append(cmdArr[1]);
        }

//        builder.append("goto dpick");
//        builder.append(cr);
//        builder.append("status: DockingState: undocked");
//        builder.append(cr);
//        builder.append("stateofcharge: 80");
//        builder.append(cr);
//        builder.append("location: 10 20 30");
//        builder.append(cr);
//        builder.append("dpick complete");
//        builder.append(cr);

        return builder.toString();
    }
}
