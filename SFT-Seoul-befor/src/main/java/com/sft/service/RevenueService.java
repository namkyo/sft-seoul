package com.sft.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sft.dao.RevenueDAO;
import com.sft.dto.Revenue;
import com.sft.dto.RevenueList;
import com.sft.dto.Revenue_files;

@Service
public class RevenueService {
	@Autowired
	private RevenueDAO RevenueDAO;

	public List<RevenueList> selectList() {
		return RevenueDAO.selectList();
	}

	public Revenue selectOne(int num) {
		return RevenueDAO.selectOne(num);
	}
	
	public int seq() {
		return RevenueDAO.seq();
	}
	
	public List<Revenue_files> selectOneFile(int num) {
		return RevenueDAO.selectOneFiles(num);
	}

	public int insert(Revenue revenue) {
		return RevenueDAO.insert(revenue);
	}
	
	public int insert_files(Revenue_files revenue) {
		return RevenueDAO.insert_file(revenue);
	}

	public int update(Revenue revenue) {
		return RevenueDAO.update(revenue);
	}

	public int delete(Revenue revenue) {
		return RevenueDAO.delete(revenue);
	}
	public int delete_file(Revenue_files revenue_files) {
		return RevenueDAO.delete_file(revenue_files);
	}
}
