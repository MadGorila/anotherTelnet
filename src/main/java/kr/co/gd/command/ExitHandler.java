package kr.co.gd.command;

/**
 * @author a.khettar
 *
 */
public class ExitHandler implements CommandHandler {

    /*
     * (non-Javadoc)
     *
     * @see com.akhettar.telnet.command.CommandHandler#handle()
     */
    @Override
    public String handle() {

        return "Goodbye...";
    }

}
