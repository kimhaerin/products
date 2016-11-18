package org.zerock.persistence;

import org.springframework.stereotype.Repository;
import org.zerock.domain.ProductVO;

@Repository
public class ProductDAOImpl extends AbstractDAO<ProductVO, String> implements ProductDAO {

	public ProductDAOImpl(){
	this.NAME = "org.zerock.dao.ProductMapper";
	}
}
