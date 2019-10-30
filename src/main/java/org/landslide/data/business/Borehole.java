package org.landslide.data.business;

import org.apache.commons.codec.binary.Base64;


public class Borehole {
	private int bhId;
	private int nkId;
	private String bhName;
	private String dvCode;
	private String dvName;
	private String dvSimCode;
	private double bhIntCoordDe;
	private double bhIntCoordDn;
	private double bhIntCoordDu;
	private String bhContact;
	private String bhTel;
	private String bhNote;
	private double bhLg;
	private double bhLt;
	private int bhDisplaceRange;
	//image
	private byte[] bhPhoto;
	private String bhPhotoBase64;
	private int bhPhotoHeight;
	private int bhPhotoWidth;
	
	public int getBhId() {
		return bhId;
	}
	public void setBhId(int bhId) {
		this.bhId = bhId;
	}
	public int getNkId() {
		return nkId;
	}
	public void setNkId(int nkId) {
		this.nkId = nkId;
	}
	public String getBhName() {
		return bhName;
	}
	public void setBhName(String bhName) {
		this.bhName = bhName;
	}

	public String getDvCode() {
		return dvCode;
	}
	public void setDvCode(String dvCode) {
		this.dvCode = dvCode;
	}
	public String getDvName() {
		return dvName;
	}
	public void setDvName(String dvName) {
		this.dvName = dvName;
	}
	public String getDvSimCode() {
		return dvSimCode;
	}
	public void setDvSimCode(String dvSimCode) {
		this.dvSimCode = dvSimCode;
	}
	public double getBhIntCoordDe() {
		return bhIntCoordDe;
	}
	public void setBhIntCoordDe(double bhIntCoordDe) {
		this.bhIntCoordDe = bhIntCoordDe;
	}
	public double getBhIntCoordDn() {
		return bhIntCoordDn;
	}
	public void setBhIntCoordDn(double bhIntCoordDn) {
		this.bhIntCoordDn = bhIntCoordDn;
	}
	public double getBhIntCoordDu() {
		return bhIntCoordDu;
	}
	public void setBhIntCoordDu(double bhIntCoordDu) {
		this.bhIntCoordDu = bhIntCoordDu;
	}
	public String getBhContact() {
		return bhContact;
	}
	public void setBhContact(String bhContact) {
		this.bhContact = bhContact;
	}
	public String getBhTel() {
		return bhTel;
	}
	public void setBhTel(String bhTel) {
		this.bhTel = bhTel;
	}
	public String getBhNote() {
		return bhNote;
	}
	public void setBhNote(String bhNote) {
		this.bhNote = bhNote;
	}
	public double getBhLg() {
		return bhLg;
	}
	public void setBhLg(double bhLg) {
		this.bhLg = bhLg;
	}
	public double getBhLt() {
		return bhLt;
	}
	public void setBhLt(double bhLt) {
		this.bhLt = bhLt;
	}
	public int getBhDisplaceRange() {
		return bhDisplaceRange;
	}
	public void setBhDisplaceRange(int bhDisplaceRange) {
		this.bhDisplaceRange = bhDisplaceRange;
	}
	public byte[] getBhPhoto() {
		return bhPhoto;
	}
	public void setBhPhoto(byte[] bhPhoto) {
		this.bhPhoto = bhPhoto;
	}
	public String getBhPhotoBase64() {
		return bhPhotoBase64;
	}
	public void setBhPhotoBase64(String bhPhotoBase64) {
		this.bhPhotoBase64 = bhPhotoBase64;
	}
	public void transformBhPhoto(){
		//ת��
		byte[] encoded=Base64.encodeBase64(bhPhoto);
		this.bhPhotoBase64 = new String(encoded);
	}
	public int getBhPhotoHeight() {
		return bhPhotoHeight;
	}
	public void setBhPhotoHeight(int bhPhotoHeight) {
		this.bhPhotoHeight = bhPhotoHeight;
	}
	public int getBhPhotoWidth() {
		return bhPhotoWidth;
	}
	public void setBhPhotoWidth(int bhPhotoWidth) {
		this.bhPhotoWidth = bhPhotoWidth;
	}
}
