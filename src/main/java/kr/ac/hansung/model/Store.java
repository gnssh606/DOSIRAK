package kr.ac.hansung.model;

import javax.validation.constraints.NotEmpty;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class Store { // 매장 정보
	
	private int id;
	
	@NotEmpty
	private String store_name;

	@NotEmpty
	private String location;
}
