package kr.co.gd.command;

import java.util.logging.Logger;

/**
 *
 * Server status handler
 *
 * @author a.khettar
 *
 */
public class TempCmdHandler implements CommandHandler {

    private final Logger logger = Logger.getLogger(TempCmdHandler.class.getName());
    private final String cr = System.getProperty("os.name").matches("(W|w)indows.*") ? "\r\n" : "\n";
    private final String omronCmd = "waitstate: 2 3 4 5 6 '\'dpick";
    /*
     * (non-Javadoc)
     *
     * @see com.akhettar.telnet.command.CommandHandler#handle()
     */
    @Override
    public String handle() {
        StringBuilder builder = new StringBuilder();
        logger.info(">>>>>>>> [TempCmdHandler] handle command!");
//                builder.append("going");
//                builder.append(cr);
//                builder.append("going");
//                builder.append(cr);
        builder.append("goto dpick");
        builder.append(cr);
//        builder.append("waitstate: 2 3 4 5 6 '\'dpick");
        builder.append("WaitState: Waiting -1 seconds with status \"dPick location\"");
//        builder.append("WaitState: Waiting -1 seconds with status \"pickup location\"");
        builder.append(cr);

        return builder.toString();
    }

}
