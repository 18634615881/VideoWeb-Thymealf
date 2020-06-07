package com.example.demo.entity;

public class Image {

	private String location;
	private String p_id;
	private String id;
	private String videolocation;
	private String title;
	private String type;
	private int clicks;
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getP_id() {
		return p_id;
	}

	public void setP_id(String p_id) {
		this.p_id = p_id;
	}

	public String getVideolocation() {
		return videolocation;
	}

	public void setVideolocation(String videolocation) {
		this.videolocation = videolocation;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public int getClicks() {
		return clicks;
	}

	public void setClicks(int clicks) {
		this.clicks = clicks;
	}

	@Override
	public String toString() {
		return "Image [location=" + location + ", p_id=" + p_id + ", id=" + id + ", videolocation=" + videolocation
				+ ", title=" + title + ", type=" + type + "]";
	}










	
	
	
	
	
}
