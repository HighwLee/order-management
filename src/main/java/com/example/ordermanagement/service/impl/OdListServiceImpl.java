package com.example.ordermanagement.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.ordermanagement.domain.OdList;
import com.example.ordermanagement.domain.request.AddOrder;
import com.example.ordermanagement.domain.request.EditOrder;
import com.example.ordermanagement.mapper.OdListMapper;
import com.example.ordermanagement.service.OdListService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;


@Service
public class OdListServiceImpl extends ServiceImpl<OdListMapper, OdList>
    implements OdListService{

	@Resource
	private OdListMapper odListMapper;

	@Override
	public List<OdList> selectAll(String belongWho) {
		QueryWrapper<OdList> queryWrapper = new QueryWrapper<>();
		queryWrapper.eq("belongWho",belongWho);
		List<OdList> selectAllResult = odListMapper.selectList(queryWrapper);
		return selectAllResult;
	}

	@Override
	public OdList selectOne(int id) {
		OdList selectOneResult = odListMapper.selectById(id);
		return selectOneResult;
	}


	//模糊查询
	@Override
	public List<OdList> selectByEntity(String searchColumn,String belongWho) {
		QueryWrapper<OdList> queryWrapper = new QueryWrapper<>();
		queryWrapper.eq("belongWho",belongWho).and(i -> i.like("ID",searchColumn)
				.or().like("equipmentName",searchColumn)
				.or().like("supplyName",searchColumn)
				.or().like("counts",searchColumn));
		List<OdList> selectAllResult = odListMapper.selectList(queryWrapper);
		return selectAllResult;
	}

	@Override
	public int insertOne(AddOrder addOrder) {
		OdList insertOdList = new OdList();
		insertOdList.setEquipmentName(addOrder.getEquipmentName());
		insertOdList.setSupplyName(addOrder.getSupplyName());
		insertOdList.setCounts(addOrder.getCounts());
		insertOdList.setBelongWho(addOrder.getBelongWho());
		odListMapper.insert(insertOdList);
		return insertOdList.getId();
	}

	@Override
	public void deleteAll(String belongWho) {
		QueryWrapper<OdList> queryWrapper = new QueryWrapper<>();
		queryWrapper.eq("belongWho",belongWho);
		List<OdList> selectAllResult = odListMapper.selectList(queryWrapper);
		for (OdList a : selectAllResult) {
			odListMapper.deleteById(a.getId());
		}
	}

	@Override
	public int deleteOne(int id) {
		odListMapper.deleteById(id);
		return id;
	}

	@Override
	public OdList updateOne(EditOrder editOrder) {
		OdList tempOdList = new OdList();
		tempOdList.setId(editOrder.getId());
		tempOdList.setEquipmentName(editOrder.getEquipmentName());
		tempOdList.setSupplyName(editOrder.getSupplyName());
		tempOdList.setCounts(editOrder.getCounts());
//		tempOdList.setBelongWho(editOrder.getBelongWho());
		odListMapper.updateById(tempOdList);
		return tempOdList;
	}
}




