<?php

class LaundryDaoContext
{
	const LAUNDRYDB = 'mysql://root:root@localhost:3306/laundry';
	static function Product()
	{
		return M('product',NULL,LaundryDaoContext::LAUNDRYDB);
	}
	static function ViewProduct()
	{
		return M('view_product',NULL,LaundryDaoContext::LAUNDRYDB);
	}
	static function ProductType()
	{
		return M('product_type',NULL,LaundryDaoContext::LAUNDRYDB);
	}
	static function Customer()
	{
		return M('customer',NULL,LaundryDaoContext::LAUNDRYDB);
	}
	static function ViewCustomer()
	{
		return M('view_customer',NULL,LaundryDaoContext::LAUNDRYDB);
	}
	static function Order()
	{
		return M('order',NULL,LaundryDaoContext::LAUNDRYDB);
	}
	static function OrderDetail()
	{
		return M('order_detail',NULL,LaundryDaoContext::LAUNDRYDB);
	}
	static function ViewOrder()
	{
		return M('view_order',NULL,LaundryDaoContext::LAUNDRYDB);
	}
}

?>