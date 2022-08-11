package br.com.compass.site.repository;

import br.com.compass.site.model.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemRepository extends JpaRepository<Item, Long> {

    boolean existsBySkuid(String skuId);

    Item findBySkuid(String skuId);

    boolean existsByEstoque(Item item);


}
