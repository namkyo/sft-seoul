package com.sft.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Paging {
	private Integer pageSize;
	private Integer pageOffset;
}
