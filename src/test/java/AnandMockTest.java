import io.mosip.registration.audit.AuditFactory;
import io.mosip.registration.audit.AuditFactoryImpl;
import io.mosip.registration.config.AppConfig;
import io.mosip.registration.constants.AuditEvent;
import io.mosip.registration.constants.Components;
import io.mosip.registration.dao.*;
import io.mosip.registration.dao.impl.UserDetailDAOImpl;
import io.mosip.registration.dto.AuthorizationDTO;
import io.mosip.registration.dto.RegistrationCenterDetailDTO;
import io.mosip.registration.dto.UserDetailResponseDto;
import io.mosip.registration.entity.RegistrationCenter;
import io.mosip.registration.entity.UserBiometric;
import io.mosip.registration.entity.UserDetail;
import io.mosip.registration.repositories.AppAuthenticationRepository;
import io.mosip.registration.repositories.RegistrationCenterRepository;
import io.mosip.registration.repositories.ScreenAuthorizationRepository;
import io.mosip.registration.repositories.UserDetailRepository;
import io.mosip.registration.service.LoginService;
import io.mosip.registration.service.impl.LoginServiceImpl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.io.IOException;
import java.net.URISyntaxException;
import java.sql.Timestamp;
import java.util.*;
import java.util.stream.Collectors;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.*;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes=TestConfig.class, loader= AnnotationConfigContextLoader.class)
public class AnandMockTest {

    @Test
    public void getUserDetailTest() {

        String testUserId = "00080";

        AuditFactory auditFactory = mock(AuditFactoryImpl.class);
//        UserDetailDAO userDetailDAO = mock( UserDetailDAOImpl.class);

        UserDetail user1 = new UserDetail();
        user1.setId(testUserId);
//        when(userDetailDAO.getUserDetail(anyString())).thenReturn(user1);

        LoginService ls = new LoginServiceImpl();
        ReflectionTestUtils.setField(ls, "auditFactory", auditFactory);
//        ReflectionTestUtils.setField(ls, "userDetailDAO", userDetailDAO);

        UserDetail res = ls.getUserDetail(testUserId);
        System.out.println(testUserId);
        System.out.println(res.getId());
        assertEquals(testUserId, res.getId());
    }

}