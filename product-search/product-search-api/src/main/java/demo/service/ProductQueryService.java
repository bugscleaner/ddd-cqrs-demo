package demo.service;

import com.cqrs.demo.dto.ProductDTO;
import demo.dto.PageList;
import demo.dto.ProductQueryDTO;

import java.util.List;

/**
 * @author : kenny
 * @since : 2022/1/23
 **/
public interface ProductQueryService {
    PageList<List<ProductDTO>> pageList(ProductQueryDTO queryParam);
}
