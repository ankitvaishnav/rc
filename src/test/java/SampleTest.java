import io.mosip.registration.audit.AuditFactory;
import io.mosip.registration.audit.AuditFactoryImpl;
import io.mosip.registration.config.AppConfig;
import io.mosip.registration.constants.AuditEvent;
import io.mosip.registration.constants.Components;
import io.mosip.registration.dao.UserDetailDAO;
import io.mosip.registration.dto.UserDetailResponseDto;
import io.mosip.registration.entity.UserBiometric;
import io.mosip.registration.entity.UserDetail;
import io.mosip.registration.service.LoginService;
import io.mosip.registration.service.impl.LoginServiceImpl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.List;

import static org.mockito.Mockito.*;


@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {TestConfig.class})
//@ContextConfiguration(classes = AppConfig.class, loader = AnnotationConfigContextLoader.class)
public class SampleTest {

    //DI
//    @Autowired
//    @Qualifier("ls")
//    LoginService ls;
    @Autowired
    ApplicationContext context;

    @MockBean
    private AuditFactory auditFactory;

    @Before
    public void initMocks(){
        auditFactory = new AuditFactoryImpl();
        MockitoAnnotations.initMocks(this);
    }

//    @Test
//    public void test_ls_always_return_true() {
//        UserDetailDAO userDetailDAO = mock(UserDetailDAO.class);
//        UserDetail ud = new UserDetail();
//        ud.setId("10002");
//        when(userDetailDAO.getUserDetail("10002")).thenReturn(ud);
//        Assert.assertNotNull();
//        ls.getUserDetail("10003");
//    }

    @Test
    public void test_user_details(){
//        Assert.assertEquals(null, this.auditFactory);
//        doNothing().when(auditFactory.audit(
//                AuditEvent.LOGIN_AUTHENTICATE_USER_ID,
//                Components.LOGIN,
//                anyString(),
//                anyString()
//            )
//        );
//        AuditFactory auditFactory = mock(AuditFactory.class);
//        Mockito.doNothing().when(this.auditFactory).audit(
//                Mockito.any(AuditEvent.class),
//                Mockito.any(Components.class),
//                Mockito.any(String.class),
//                Mockito.any(String.class)
//        );
//        Mockito.times(1)).audit(
//                Mockito.any(AuditEvent.class),
//                Mockito.any(Components.class),
//                Mockito.any(String.class),
//                Mockito.any(String.class)
//        );

        UserDetailDAO userDetailDAO = mock(UserDetailDAO.class);
        UserDetail ud = new UserDetail();
        ud.setId("10002");
        when(userDetailDAO.getUserDetail(anyString())).thenReturn(ud);
        LoginService ls = new LoginServiceImpl();
        UserDetail ud2 =  ls.getUserDetail("10002");
        Assert.assertNotNull(ud2);
        Assert.assertEquals(ud.getId(),ud2.getId());
    }
}