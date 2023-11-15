package com.task.demo.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "detail")
public class Product  extends HelperEntity{

	@Id
	private int id;
	private String P_Name;
	private String P_Type;

	private String uuid=HelperEntity.getUUild();
	private Long Epoc_Version=HelperEntity.getEpoc();
	private String Iso_creadtedOn=HelperEntity.getIso();

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getP_Name() {
		return P_Name;
	}

	public void setP_Name(String p_Name) {
		P_Name = p_Name;
	}

	public String getP_Type() {
		return P_Type;
	}

	public void setP_Type(String p_Type) {
		P_Type = p_Type;
	}

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public Long getEpoc_Version() {
		return Epoc_Version;
	}

	public void setEpoc_Version(Long epoc_Version) {
		Epoc_Version = epoc_Version;
	}

	public String getIso_creadtedOn() {
		return Iso_creadtedOn;
	}

	public void setIso_creadtedOn(String iso_creadtedOn) {
		Iso_creadtedOn = iso_creadtedOn;
	}

	public Product() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Product(int id, String p_Name, String p_Type) {
		
		this.id = id;
		P_Name = p_Name;
		P_Type = p_Type;
	}

	@Override
	public String toString() {
		return "Product [id=" + id + ", P_Name=" + P_Name + ", P_Type=" + P_Type + ", uuid=" + uuid + ", Epoc_Version="
				+ Epoc_Version + ", Iso_creadtedOn=" + Iso_creadtedOn + "]";
	}

}
