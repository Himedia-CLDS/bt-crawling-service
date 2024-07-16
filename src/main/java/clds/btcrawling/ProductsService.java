package clds.btcrawling;

import org.springframework.data.domain.Sort;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductsService {

    private final ProductsRepository productsRepository;


    public ProductsService(ProductsRepository productsRepository) {
        this.productsRepository = productsRepository;

    }


    public List<Products> findAllProducts(Sort sort) {
        List<Products> productsList = (List)productsRepository.findAll(sort);

        return productsList;
    }


    public Optional<Products> findProductById(String id) {
        return productsRepository.findById(id);
    }


    public List<Products> searchProducts(String search,Sort sort) {
        if(isKorean(search)){
            return productsRepository.findByKorNameContaining(search,sort);

        }else{
            return productsRepository.findByEngNameContaining(search,sort);

        }

    }

    private boolean isKorean(String search) {
        return search.codePoints().anyMatch(ch -> (ch >= 0x1100 && ch <= 0x11FF) || (ch >= 0x3130 && ch <= 0x318F) || (ch >= 0xAC00 && ch <= 0xD7A3));
    }
}
