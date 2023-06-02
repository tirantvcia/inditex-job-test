package com.inditex.test.springboot.app.data;

public enum Brand {
	ZARA(1, "Zara");
	
	private int code;
	private String description;
	
	private Brand(int code, String description) {
		this.code = code;
		this.description = description;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	public static Brand valueOfCode(int code) {
        for(Brand b: values()) {
            if(b.code == code) {
                return b;
            }
        }
        return null;
	}
	
	
	
}
