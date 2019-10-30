package org.landslide.data.system;

public class BizDictionaryItem {
	private int bizDicItemId;
	private String dictTypeId;
	private int dictCode;
	private String dictName;
	private int dictOrder;
	private int bizDicItemPid;

	public int getBizDicItemId() {
		return bizDicItemId;
	}

	public void setBizDicItemId(int bizDicItemId) {
		this.bizDicItemId = bizDicItemId;
	}

	public String getDictTypeId() {
		return dictTypeId;
	}

	public void setDictTypeId(String dictTypeId) {
		this.dictTypeId = dictTypeId;
	}

	public int getDictCode() {
		return dictCode;
	}

	public void setDictCode(int dictCode) {
		this.dictCode = dictCode;
	}

	public String getDictName() {
		return dictName;
	}

	public void setDictName(String dictName) {
		this.dictName = dictName;
	}

	public int getDictOrder() {
		return dictOrder;
	}

	public void setDictOrder(int dictOrder) {
		this.dictOrder = dictOrder;
	}

	public int getBizDicItemPid() {
		return bizDicItemPid;
	}

	public void setBizDicItemPid(int bizDicItemPid) {
		this.bizDicItemPid = bizDicItemPid;
	}
}
