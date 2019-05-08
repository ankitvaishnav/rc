import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.mosip.registration.audit.AuditFactory;
import io.mosip.registration.audit.AuditFactoryImpl;
import io.mosip.registration.config.AppConfig;
import io.mosip.registration.config.DaoConfig;
import io.mosip.registration.config.PropertiesConfig;
import io.mosip.registration.constants.AuditEvent;
import io.mosip.registration.constants.Components;
import io.mosip.registration.dao.AppAuthenticationDAO;
import io.mosip.registration.dao.RegistrationCenterDAO;
import io.mosip.registration.dao.ScreenAuthorizationDAO;
import io.mosip.registration.dao.UserDetailDAO;
import io.mosip.registration.dao.impl.UserDetailDAOImpl;
import io.mosip.registration.entity.UserDetail;
import io.mosip.registration.repositories.AppAuthenticationRepository;
import io.mosip.registration.repositories.RegistrationCenterRepository;
import io.mosip.registration.repositories.ScreenAuthorizationRepository;
import io.mosip.registration.repositories.UserDetailRepository;
import io.mosip.registration.service.LoginService;
import io.mosip.registration.service.impl.LoginServiceImpl;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Import;
import org.springframework.core.env.Environment;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;
import org.springframework.test.util.ReflectionTestUtils;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes=TestConfig.class, loader= AnnotationConfigContextLoader.class)
public class RunningTest {

    @Autowired
    private LoginService loginService;

    @Before
    public void initialize() throws IOException, URISyntaxException {
        System.out.println("---------------------------");
    }

    @Test
    public void loginServiceTest() {
        UserDetail userDetail = loginService.getUserDetail("1100903");
        if(userDetail != null){
            System.out.println(userDetail.getId());
            System.out.println(userDetail.getName());
            System.out.println(userDetail.getEmail());
        }else{
            System.out.println("user not found");
        }

    }
}
