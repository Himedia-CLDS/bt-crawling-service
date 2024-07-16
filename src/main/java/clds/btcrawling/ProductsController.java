package clds.btcrawling;

import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@Slf4j
@RequestMapping("/v1/api/products")
public class ProductsController {

    private final ProductsService productsService;

    public ProductsController(ProductsService productsService) {
        this.productsService = productsService;
    }
    @GetMapping
    public List<Products> getAllProducts(@RequestParam(defaultValue = "price") String sortBy) {
        Sort sort = Sort.by(sortBy).ascending(); // 기본 정렬을 오름차순으로 설정
        return productsService.findAllProducts(sort);
    }

    @GetMapping("/{id}")
    public Optional<Products> getProductById(@PathVariable String id) {

        return productsService.findProductById(id);
    }

    @GetMapping("/search")
    public List<Products> searchProducts(@RequestParam(name = "search", defaultValue = "") String search,
                                         @RequestParam(defaultValue = "_id") String sortBy
                                        )
    {
        log.info("searched Keyword => {}  " , search);
        Sort sort = Sort.by(sortBy).ascending(); //

        return productsService.searchProducts(search,sort);
    }

}
