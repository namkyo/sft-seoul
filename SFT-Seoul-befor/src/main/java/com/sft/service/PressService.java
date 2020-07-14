package com.sft.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sft.dao.PressDAO;
import com.sft.dto.Paging;
import com.sft.dto.Press;

@Service
public class PressService {
	@Autowired
	private PressDAO pressDAO;

	public List<Press> selectList(Paging paing) {
		return pressDAO.selectList(paing);
	}
	
	public int selectTotalCount() {
		return pressDAO.selectTotalCount();
	}

	public Press selectOne(int num) {
		return pressDAO.selectOne(num);
	}

	public int insert(Press press) {
		return pressDAO.insert(press);
	}

	public int update(Press press) {
		return pressDAO.update(press);
	}

	public int delete(Press press) {
		return pressDAO.delete(press);
	}
}
