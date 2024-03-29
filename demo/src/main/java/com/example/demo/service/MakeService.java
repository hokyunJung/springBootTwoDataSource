package com.example.demo.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.primary.dao.ShowARepository;
import com.example.demo.primary.entity.ShowA;
import com.example.demo.second.dao.ShowBRepository;
import com.example.demo.second.entity.ShowB;

@Service
public class MakeService {
    @Autowired
    ShowARepository showARepository;
    
    @Autowired
    ShowBRepository showBRepository;
    
    @Transactional
    public void insertData() {
        ShowA showA = new ShowA("dfaf", "dsfafd");

        showARepository.save(showA);
        
        getAll();
    }
    
    public void getAll() {
    	List<ShowA> a = showARepository.findAll();
    	for (ShowA b : a) {
    		System.out.println(b.toString());
    	}
    }
    
    public void insertData2() {
    	ShowB showb = new ShowB("sdaf", "dsfsadf");
    	showBRepository.save(showb);
    }
}
