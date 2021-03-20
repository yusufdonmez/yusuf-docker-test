package yusuf.donmez.main;

import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import java.util.Objects;
import java.util.Random;
import java.util.UUID;

import javax.swing.plaf.basic.BasicBorders.RadioButtonBorder;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DockerTest {

    private final int repeatPeriod;
	private final Date start;
	private final String name;
	private static final Logger LOGGER = LoggerFactory.getLogger(DockerTest.class);

    private DockerTest( int repeatPeriod, Date date, String name ) {
        this.repeatPeriod = Objects.requireNonNull(repeatPeriod);
		this.start = date;
		this.name = name;
    }

    public void run() throws IOException {
    	 
		while (true) {
			try {
				Thread t = Thread.currentThread();
				Date now = Calendar.getInstance().getTime();
				Long diff = now.getTime() - start.getTime();
				
				LOGGER.info("Name:{}, ThreadId:{}, ThreadName:{}, start:{}, UP:{} min ",name, t.getId(), t.getName(), start, diff/60000);
				
				System.out.printf("check date:\t %s \n",Calendar.getInstance().getTime());
				
			} catch (Exception e) {
				LOGGER.error("Exception msg:{}", e.getCause().toString());
			}

			try {
				Thread.sleep( repeatPeriod );  // 1min = 60000
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
    }
	
    public static void main(String args[]) throws Exception {

        int repeatPeriod = Integer.parseInt(args[0]);
		Date start = Calendar.getInstance().getTime();
		String name = UUID.randomUUID().toString().substring(24);

    	
        DockerTest serviceCheck = new DockerTest( repeatPeriod, start, name );
		serviceCheck.run();
    }    
}
