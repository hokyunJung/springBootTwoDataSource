package com.example.demo.primary.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.primary.entity.ShowA;

@Repository
public interface ShowARepository extends JpaRepository<ShowA, Integer> {
	public ShowA findTopByBestShow(String bestShow);
}
