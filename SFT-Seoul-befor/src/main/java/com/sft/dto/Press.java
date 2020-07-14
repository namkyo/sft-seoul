package com.sft.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Press {
	private int num;
	private String title;
	private String txusrnm;
	private String reg_date;
	private String content;
}
