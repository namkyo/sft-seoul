package com.sft.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Menu {
	private String menu_id;//메뉴id
	private String menu_nm;//메뉴이름
	private String menu_url;//메뉴url
	private String menu_parent_id;//상위메뉴
	private String menu_yn;//사용여부
}
