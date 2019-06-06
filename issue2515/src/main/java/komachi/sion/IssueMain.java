package komachi.sion;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import komachi.sion.services.OrderService;

/**
 * duplicate with #1567.
 *
 * @author yangyi
 */
public class IssueMain {
    
    private static volatile boolean running = true;
    
    public static void main(String[] args) {
        try {
            final ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("classpath*:spring-context.xml");
        
            Runtime.getRuntime().addShutdownHook(new Thread() {
            
                @Override
                public void run() {
                    try {
                        context.stop();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    synchronized (IssueMain.class) {
                        running = false;
                        IssueMain.class.notify();
                    }
                }
            });
            context.start();
            
            OrderService service = context.getBean(OrderService.class);
            service.cleanEnvironment();
            service.createEnvironment();
            service.insertOneData();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
