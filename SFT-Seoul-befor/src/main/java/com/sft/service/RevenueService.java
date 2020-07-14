package com.sft.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sft.dao.RevenueDAO;
import com.sft.domin.SearchCriteria;
import com.sft.dto.Revenue;
import com.sft.dto.RevenueList;
import com.sft.dto.Revenue_files;

@Service
public class RevenueService {
	@Autowired
	private RevenueDAO RevenueDAO;

	public List<RevenueList> selectList(SearchCriteria cri) {
		return RevenueDAO.selectList(cri);
	}

	public Revenue selectOne(int num) {
		return RevenueDAO.selectOne(num);
	}
	

	public int insert(Revenue revenue) {
		return RevenueDAO.insert(revenue);
	}
	

	public int update(Revenue revenue) {
		return RevenueDAO.update(revenue);
	}

	public int delete(Revenue revenue) {
		return RevenueDAO.delete(revenue);
	}
}
