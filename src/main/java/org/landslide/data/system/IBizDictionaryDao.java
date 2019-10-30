package org.landslide.data.system;

import java.util.List;
import java.util.Map;

public interface IBizDictionaryDao {
	public List<org.landslide.data.system.BizDictionary> queryBizDictionary();
	public List<org.landslide.data.system.BizDictionaryItem> queryBizDictionaryItem();
	public List<org.landslide.data.system.BizDictionaryItem> queryBizDictionaryItemTree(Map<String,String> params);
}
