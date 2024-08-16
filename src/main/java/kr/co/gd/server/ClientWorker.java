package kr.co.gd.server;

import kr.co.gd.command.CDHandler;
import kr.co.gd.command.CommandHandler;
import kr.co.gd.command.CommandHandlerFactory;
import kr.co.gd.command.ExitHandler;
import lombok.extern.slf4j.Slf4j;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * Client Worker handling request of a client.
 *
 * @author a.khettar
 *
 */
@Slf4j
public class ClientWorker implements Runnable {

    private final Socket socket;
    private String workingDir;
    private final Logger logger = Logger.getLogger(ClientWorker.class.getName());

    /**
     * @param socket
     */
    public ClientWorker(final Socket socket, String homeDir) {
        this.socket = socket;
        this.workingDir = homeDir;
    }

    /*
     * (non-Javadoc)
     *
     * @see java.lang.Runnable#run()
     */
    @Override
    public void run() {
        try {
            ExecutorService es = Executors.newCachedThreadPool();
            final BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            final PrintWriter out = new PrintWriter(socket.getOutputStream(), true);

            // display welcome screen
            out.println(this.omronTelnetSimulator());
//            out.println(Util.buildWelcomeScreen());

            boolean cancel = false;
            logger.info("cancel: " + cancel);
            CommandHandlerFactory fac = CommandHandlerFactory.getInstance();

            while (!cancel) {
//                logger.info("in while");
                final String command = reader.readLine();
                logger.info("[ClientWorker] in while command: " + command);
                if (command == null) {
//                    logger.info("if in");
//                    out.println(this.omronTelnetSimulator());
                    continue;
                }

                //handle the command
                final CommandHandler handler = fac.getHandler(command, workingDir);
                String response = handler.handle();

                // setting the working directory
                if (handler instanceof CDHandler) {
                    workingDir = (response.contains("No such file or directory") || response
                            .contains("You must supply directory name")) ? workingDir : response;
                    logger.info("Working directory set to: " + workingDir);
                }
                out.println(response);
                
                CompletableFuture<Void> future = new CompletableFuture<>();
                es.submit(() -> future.complete(responseSuccessMsg(command)));

                // command issuing an exit.
                if (handler instanceof ExitHandler) {
                    cancel = true;
                }
            }
        } catch (IOException e) {
            logger.log(Level.SEVERE, e.getMessage(), e);
        } finally {
            try {
                socket.close();
            } catch (IOException e) {
                logger.log(Level.SEVERE, "Failed to close the socket", e);
            }
        }
    }

    private String omronTelnetSimulator() {
        String cr = System.getProperty("os.name").matches("(W|w)indows.*") ? "\r\n" : "\n";
        StringBuilder builder = new StringBuilder();
//        builder.append("status: DockingState: undocked");
//        builder.append(cr);
//        builder.append("stateofcharge: 80");
//        builder.append(cr);
//        builder.append("location: 10 20 30");
//        builder.append(cr);
//        builder.append("waitstate: 2 3 4 5 6 '\'dpick");
//        builder.append(cr);
//        builder.append("dserv");
//        builder.append(cr);
//        builder.append("arrived");
        builder.append(cr);
        builder.append("end ");
//        builder.append(cr);
//        builder.append("dockingstate: undocked");

        return builder.toString();
    }

    public Void responseSuccessMsg(String cmd) {
        StringBuilder sb = new StringBuilder();

        String[] cmdArr = cmd.split(" ");
        boolean isFire = false;

        switch (cmdArr[0].toLowerCase()) {
            case "goto": {
                isFire = true;
                sb.append("Arrived at ");
                sb.append(cmdArr[1]);
                break;
            }
            case "executemacro": {
                isFire = true;
                sb.append(cmdArr[1]);
                sb.append(" COMPLETE");
                break;
            }
        }

        if (isFire) {
            try {
                log.info(">>>>>>> Fire after 10 sec, {}", sb.toString());
                final PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
                Thread.sleep(10000);
                out.println(sb.toString());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }
}
