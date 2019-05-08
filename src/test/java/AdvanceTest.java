import io.mosip.registration.entity.UserDetail;
import io.mosip.registration.service.LoginService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.context.ApplicationContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import java.io.IOException;
import java.net.URISyntaxException;

@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(classes=TestConfig.class, loader= AnnotationConfigContextLoader.class)
public class AdvanceTest {

    @Before
    public void initialize() throws IOException, URISyntaxException {
        System.out.println("---------------------------");
    }

    @Test
    public void loginServiceTest() {
        String jsonInString = "";
        System.setProperty("file.encoding", "UTF-8");
        System.setProperty("spring.profiles.active", "qa");
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(TestConfig.class);
        LoginService loginService = applicationContext.getBean(LoginService.class);

        UserDetail userDetail = loginService.getUserDetail("110003");

        if(userDetail != null){
            System.out.println(userDetail.getId());
            System.out.println(userDetail.getName());
            System.out.println(userDetail.getEmail());
        }else{
            System.out.println("user not found");
        }
    }
}
