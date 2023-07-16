package com.example.ordermanagement.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.ordermanagement.domain.OdList;
import com.example.ordermanagement.domain.request.AddOrder;
import com.example.ordermanagement.domain.request.EditOrder;

import java.util.List;


public interface OdListService extends IService<OdList> {

	List<OdList> selectAll(String belongWho);

	OdList selectOne(int id);

	List<OdList> selectByEntity(String searchColumn,String belongWho);

	int insertOne(AddOrder addOrder);

	void deleteAll(String belongWho);

	int deleteOne(int id);

	OdList updateOne(EditOrder editOrder);
}
