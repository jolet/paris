package tvz.nppjj.paris;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import tvz.nppjj.paris.init.WebNppjjParisApplication;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = WebNppjjParisApplication.class)
@WebAppConfiguration
public class WebNppjjParisApplicationTests {

    @Test
    public void contextLoads() {
    }

}
