package com.example.demo;

import java.util.List;

import javax.transaction.Transactional;

import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.demo.primary.dao.ShowARepository;
import com.example.demo.primary.entity.ShowA;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RepositoryTests {

    @Autowired
    ShowARepository showARepository;

    @Test
    @Transactional
    public void testCustomerRepository(){
        ShowA showA = new ShowA();
        showA.setThisKey(23434);
        showA.setBestShow("sdfasdf");
        showA.setWorstShow("sdfsadfv");
        showARepository.save(showA);

        List<ShowA> customerList = showARepository.findAll();

        ShowA chris = customerList.get(0);
        assertThat(chris.getBestShow(), is("sdfasdf"));
        assertThat(chris.getWorstShow(), is("sdfsadfv"));
        
        ShowA a = showARepository.findTopByBestShow("sdfasdf");
        System.out.println(a.getThisKey());
    }

    //@After
    //public void deleteAll() {
    	//showARepository.deleteAll();
    //}

}
