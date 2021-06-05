package comparator;

import java.util.Comparator;

import kr.ac.hansung.model.Product;

public class ProductComparator implements Comparator<Product> {

	@Override
	public int compare(Product p1, Product p2) {
		if (Integer.parseInt(p1.getBarcode()) > Integer.parseInt(p2.getBarcode())) return 1;
		if (Integer.parseInt(p1.getBarcode()) < Integer.parseInt(p2.getBarcode())) return -1;
		return 0;
	}

}
