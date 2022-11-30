package com.workfusion.validation;

import com.workfusion.repository.ProductDetailsRepository;

public class ProductValidation {
	ProductDetailsRepository prodrepo = new ProductDetailsRepository();
public boolean isValidProductId(int productId) {
	boolean prodstatus=false;
	prodstatus=prodrepo.checkProductExistence(productId);
	return prodstatus;
}
}
