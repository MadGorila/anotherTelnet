package kr.co.gd;

import kr.co.gd.server.TelnetServer;
import kr.co.gd.service.OmronService;
import org.slf4j.bridge.SLF4JBridgeHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.ApplicationPidFileWriter;
import org.springframework.scheduling.annotation.EnableAsync;

@EnableAsync
@SpringBootApplication
public class Application {
    @Autowired
    private OmronService omronService;

    public static void main(String[] args) {
        System.out.println("Hello world by Spring boot!!");
        // launch the server
        SLF4JBridgeHandler.removeHandlersForRootLogger();
        SLF4JBridgeHandler.install();
        SpringApplication springApplication = new SpringApplication(Application.class);
        springApplication.addListeners(new ApplicationPidFileWriter("app.pid"));
        springApplication.run(args);
        new TelnetServer(args.length == 0 ? null : args[0]).run();
    }

    public void init() {
        omronService.bulkInsert();
    }
}
