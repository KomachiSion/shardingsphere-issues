package komachi.sion;

import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * configuration error，actual-data-nodes should be data-source-names.
 *
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
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        synchronized (IssueMain.class) {
            while (running) {
                try {
                    IssueMain.class.wait();
                } catch (Exception ignored) {
                }
            }
        }
    }
}
