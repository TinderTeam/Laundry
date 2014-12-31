<?php

class LaundryDaoContext
{
	static function Product()
	{
		return M('product');
	}
	static function ViewProduct()
	{
		return M('view_product');
	}
	static function ProductType()
	{
		return M('product_type');
	}
}

?>