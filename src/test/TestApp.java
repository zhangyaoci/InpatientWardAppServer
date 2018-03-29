package test;

import domain.Patient;
import domain.User;
import domain.UserPatient;
import org.apache.commons.codec.digest.Md5Crypt;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import service.UserService;
import org.hibernate.cfg.Configuration;
import util.MD5Util;

import javax.swing.*;
import java.sql.Date;


public class TestApp {

    @Test
    public void  demo01(){
        User user = new User();
        user.setName("jack");
        user.setPassword("123456");
        user.setUserId(11111);
        String xmlPath = "spring.cfg.xml";

        ApplicationContext applicationContext = new ClassPathXmlApplicationContext(xmlPath);

        UserService userService = applicationContext.getBean("userService",UserService.class);
        userService.saveUser(user);
    }

    @Test
    public void insertTest(){
        Configuration configuration = new Configuration().configure();
        SessionFactory sessionFactory = configuration.buildSessionFactory();

        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        User user = new User();
        user.setName("jack");
        user.setPassword(MD5Util.generate("123456"));

        System.out.println("密码校验："+MD5Util.verify("123456",user.getPassword()));

        user.setPhone("15909314794");

        Patient patient = new Patient();
        patient.setName("liu");

        patient.setSex((byte)0);
        patient.setPhone("3232324443");
        patient.setDateOfBirth(new Date(3));
        patient.setAddress("重庆理工大学");

        UserPatient userPatient = new UserPatient();

        userPatient.setRelation("监护人444");
        userPatient.setUser(user);
        userPatient.setPatient(patient);

        user.getUserPatients().add(userPatient);
        patient.getUserPatients().add(userPatient);

        session.save(user);


        transaction.commit();
        session.close();
        sessionFactory.close();
    }
}
