package cn.e3mall.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.e3mall.common.pojo.EasyUITreeNode;
import cn.e3mall.mapper.TbItemCatMapper;
import cn.e3mall.pojo.TbItemCat;
import cn.e3mall.pojo.TbItemCatExample;
import cn.e3mall.pojo.TbItemCatExample.Criteria;
import cn.e3mall.service.ItemCatService;
/**
 * 商品分类管理
 * @author wking
 *
 */
@Service
public class ItemCatServiceImpl implements ItemCatService {

	@Autowired
	private TbItemCatMapper temCatMapper;
	
	@Override
	public List<EasyUITreeNode> getItemCatList(Long parentId) {
		//根据parentId查询子节点列表
		//创建查询条件对象
		TbItemCatExample example = new TbItemCatExample();
		Criteria criteria = example.createCriteria();
		//设置查询条件
		criteria.andParentIdEqualTo(parentId);
		//执行查询
		List<TbItemCat> list = temCatMapper.selectByExample(example);
		//创建结果List
		List<EasyUITreeNode> resultList = new ArrayList<EasyUITreeNode>();
		//把列表转换成EasyUITreeNode列表
		for (TbItemCat tbItemCat : list) {
			EasyUITreeNode node = new EasyUITreeNode();
			//设置属性
			node.setId(tbItemCat.getId());
			node.setText(tbItemCat.getName());
			node.setState(tbItemCat.getIsParent()?"closed":"open");
			//添加节点到List
			resultList.add(node);
		} 
		//返回结果
		return resultList;
	}

	@Override
	public String getItemCatNameById(Long id) {
		TbItemCat itemCat = temCatMapper.selectByPrimaryKey(id);
		return itemCat.getName();
	}

}
