package clds.btcrawling;

import org.springframework.data.domain.Sort;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface ProductsRepository extends ElasticsearchRepository<Products, String> {

    Optional<Products> findById(String id);

    List<Products> findByKorNameContaining(String search, Sort sort);

    List<Products> findByEngNameContaining(String search, Sort sort);
}
