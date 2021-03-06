
import io.mosip.kernel.core.logger.spi.Logger;
import io.mosip.kernel.core.templatemanager.spi.TemplateManagerBuilder;
import io.mosip.kernel.dataaccess.hibernate.repository.impl.HibernateRepositoryImpl;
import io.mosip.kernel.logger.logback.appender.RollingFileAppender;
import io.mosip.kernel.logger.logback.factory.Logfactory;
import io.mosip.kernel.templatemanager.velocity.builder.TemplateManagerBuilderImpl;
import io.mosip.registration.config.DaoConfig;
import io.mosip.registration.config.PropertiesConfig;
import io.mosip.registration.dao.*;
import io.mosip.registration.jobs.JobProcessListener;
import io.mosip.registration.jobs.JobTriggerListener;
import io.mosip.registration.service.LoginService;
import io.mosip.registration.service.impl.LoginServiceImpl;
import org.mockito.Mock;
import org.quartz.JobListener;
import org.quartz.TriggerListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.*;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.web.client.RestTemplate;

import javax.sql.DataSource;
import java.util.Properties;
import java.util.ResourceBundle;

@Configuration
@EnableAspectJAutoProxy
@Import({ DaoConfig.class, PropertiesConfig.class })
@EnableJpaRepositories(basePackages = "io.mosip.registration", repositoryBaseClass = HibernateRepositoryImpl.class)
@ComponentScan({ "io.mosip.registration", "io.mosip.kernel.core", "io.mosip.kernel.keygenerator",
        "io.mosip.kernel.idvalidator", "io.mosip.kernel.ridgenerator", "io.mosip.kernel.qrcode",
        "io.mosip.kernel.crypto", "io.mosip.kernel.jsonvalidator", "io.mosip.kernel.idgenerator",
        "io.mosip.kernel.virusscanner", "io.mosip.kernel.transliteration", "io.mosip.kernel.applicanttype",
        "io.mosip.kernel.cbeffutil","io.mosip.kernel.core.pdfgenerator.spi","io.mosip.kernel.pdfgenerator.itext.impl" })
@PropertySource(value = { "classpath:spring.properties", "classpath:spring-${spring.profiles.active}.properties" })
public class TestConfig {
//    private static final RollingFileAppender MOSIP_ROLLING_APPENDER = new RollingFileAppender();
//
//    private static final ResourceBundle applicationProperties = ResourceBundle.getBundle("reg_application");

    @Autowired
    @Qualifier("dataSource")
    private DataSource datasource;

    /**
     * Job processor
     */
    @Autowired
    private JobProcessListener jobProcessListener;

    /**
     * Job Trigger
     */
    @Autowired
    private JobTriggerListener commonTriggerListener;

    @Autowired
    private SyncJobConfigDAO syncJobConfigDAO;

//    static {
//        ResourceBundle resourceBundle = ResourceBundle.getBundle("log4j");
//        MOSIP_ROLLING_APPENDER.setAppend(true);
//        MOSIP_ROLLING_APPENDER.setAppenderName(resourceBundle.getString("log4j.appender.Appender"));
//        MOSIP_ROLLING_APPENDER.setFileName(resourceBundle.getString("log4j.appender.Appender.file"));
//        MOSIP_ROLLING_APPENDER.setFileNamePattern(resourceBundle.getString("log4j.appender.Appender.filePattern"));
//        MOSIP_ROLLING_APPENDER.setMaxFileSize(resourceBundle.getString("log4j.appender.Appender.maxFileSize"));
//        MOSIP_ROLLING_APPENDER.setTotalCap(resourceBundle.getString("log4j.appender.Appender.totalCap"));
//        MOSIP_ROLLING_APPENDER
//                .setMaxHistory(Integer.valueOf(resourceBundle.getString("log4j.appender.Appender.maxBackupIndex")));
//        MOSIP_ROLLING_APPENDER.setImmediateFlush(true);
//        MOSIP_ROLLING_APPENDER.setPrudent(true);
//    }

//    public static Logger getLogger(Class<?> className) {
//        return Logfactory.getDefaultRollingFileLogger(MOSIP_ROLLING_APPENDER, className);
//    }
//
//    public static String getApplicationProperty(String property) {
//        return applicationProperties.getString(property);
//    }

    @Bean
    public RestTemplate getRestTemplate() {
        return new RestTemplate();
    }

    @Bean
    public TemplateManagerBuilder getTemplateManagerBuilder() {
        return new TemplateManagerBuilderImpl();
    }

    /**
     * scheduler factory bean used to shedule the batch jobs
     *
     * @return scheduler factory which includes job detail and trigger detail
     */
    @Bean(name = "schedulerFactoryBean")
    public SchedulerFactoryBean getSchedulerFactoryBean() {
        SchedulerFactoryBean schFactoryBean = new SchedulerFactoryBean();
        schFactoryBean.setGlobalTriggerListeners(new TriggerListener[] { commonTriggerListener });
        schFactoryBean.setGlobalJobListeners(new JobListener[] { jobProcessListener });
        Properties quartzProperties = new Properties();
        quartzProperties.put("org.quartz.threadPool.threadCount",
                String.valueOf(syncJobConfigDAO.getActiveJobs().size()));
        schFactoryBean.setQuartzProperties(quartzProperties);
        return schFactoryBean;
    }

}
