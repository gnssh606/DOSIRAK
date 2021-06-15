package kr.ac.hansung.model;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.PositiveOrZero;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class Product { // 제품 정보
	
	private int id;
	
	@NotEmpty
	private String product_name;
	
	@NotEmpty
	@Pattern(regexp = "^[0-9]*$", message = "숫자만 입력할 수 있습니다")
	private String barcode;

	@NotNull
	@PositiveOrZero
	private int price;

	@NotEmpty
	private String category;
}
