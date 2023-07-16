package com.example.ordermanagement.service.impl;


import com.example.ordermanagement.domain.OdList;
import com.example.ordermanagement.mapper.OdListMapper;
import com.example.ordermanagement.service.OdListService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.List;


@SpringBootTest
class OdListServiceImplTest {

	@Resource
	private OdListMapper odListMapper;

	@Resource
	private OdListService odListService;

	@Test
	void test(){
/*
		List<OdList> res = odListService.selectAll();
		System.out.println(res);

		odListService.deleteAll();
*/
//		List<OdList> res = odListService.selectByEntity("3445354");
//		System.out.println(res);


/*
		String equipmentName = "test";
		String supplyName = "test";
		int counts= 344;
		AddOrder addOrder = new AddOrder();
		addOrder.setEquipmentName(equipmentName);
		addOrder.setSupplyName(supplyName);
		addOrder.setCounts(counts);

		int result = odListService.insertOne(addOrder);
		System.out.println(result);

		odListService.deleteAll();
		int id =1;
		AddOrder addOrder = new AddOrder();
		addOrder.setEquipmentName("ceshi");
		addOrder.setSupplyName("ceshi");
		addOrder.setCounts(345);
		OdList tempOdList = new OdList();
		tempOdList.setId(id);
		tempOdList.setEquipmentName(addOrder.getEquipmentName());
		tempOdList.setSupplyName(addOrder.getSupplyName());
		tempOdList.setCounts(addOrder.getCounts());
		odListService.updateOne(id,addOrder);
		System.out.println(addOrder);
*/

//		int id = 9;
//		odListService.deleteOne(id);
		String belongWho = "test1111";
		List<OdList> selectAllResult = odListService.selectAll(belongWho);
		System.out.println(selectAllResult);


	}

}