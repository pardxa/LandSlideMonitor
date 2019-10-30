package org.landslide.service.system;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.landslide.data.system.BizDictionary;
import org.landslide.data.system.BizDictionaryItem;
import org.landslide.data.system.IBizDictionaryDao;
import org.landslide.data.system.JsonBizDictionaryItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SystemBizDictionaryService {
	@Autowired
	private IBizDictionaryDao bizDictionaryDao;
	/**
	 * 初始化时获取全局字典放入内存
	 * @return
	 */
	public Map<String,Map<Integer,String>> getBizDictionary(){
		Map<String,Map<Integer,String>> bizDicMap=new HashMap<String,Map<Integer,String>>();
		List<BizDictionary> dicList=bizDictionaryDao.queryBizDictionary();
		List<BizDictionaryItem> itemList=bizDictionaryDao.queryBizDictionaryItem();
		for(BizDictionary bizDic:dicList){
			Map<Integer,String> itemMap=new HashMap<Integer,String>();
			for(BizDictionaryItem item:itemList){
				if(item.getDictTypeId().trim().equals(bizDic.getDictTypeId().trim())){
					itemMap.put(item.getDictCode(), item.getDictName());
				}
			}
			bizDicMap.put(bizDic.getDictTypeId(), itemMap);
		}
		return bizDicMap;
	}
	/**
	 * 送入字典类型获取业务字典条目列表
	 * @param bizDicMap
	 * @param dictTypeId
	 * @return
	 */
	public List<JsonBizDictionaryItem> getBizDictionaryItems(Map<String,Map<Integer,String>> bizDicMap,
			String dictTypeId){
		List<JsonBizDictionaryItem> itemList=new ArrayList<JsonBizDictionaryItem>();
		Map<Integer,String> itemMap=bizDicMap.get(dictTypeId);
		if(itemMap!=null){
			Iterator<Integer> itemMapItor=itemMap.keySet().iterator();
			while(itemMapItor.hasNext()){
				Integer key=(Integer)itemMapItor.next();
				JsonBizDictionaryItem item=new JsonBizDictionaryItem();
				item.setDictCode(key);
				item.setDictName(itemMap.get(key));
				itemList.add(item);
			}
		}
		return itemList;
	}
	/**
	 * 送入字典类型,父PID，获取业务字典条目列表
	 * @param dictTypeId
	 * @param bizDicItemPid
	 * @return
	 */
	public List<JsonBizDictionaryItem> queryBizDictionaryItemTree(String dictTypeId,
			int bizDicItemPid){
		List<JsonBizDictionaryItem> itemList=new ArrayList<JsonBizDictionaryItem>();
		Map<String,String> params=new HashMap<String,String>();
		params.put("dictTypeId", dictTypeId);
		params.put("bizDicItemPid", Integer.toString(bizDicItemPid));
		List<org.landslide.data.system.BizDictionaryItem> rslt=bizDictionaryDao.queryBizDictionaryItemTree(params);
		for(org.landslide.data.system.BizDictionaryItem bitem:rslt){
			JsonBizDictionaryItem item=new JsonBizDictionaryItem();
			item.setDictCode(bitem.getDictCode());
			item.setDictName(bitem.getDictName());
			itemList.add(item);	
		}
		return itemList;
	}
}
