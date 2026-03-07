package org.example.Repository;

import org.example.Model.Product;
import org.springframework.stereotype.Repository;
import java.util.List;

import java.util.ArrayList;
import java.util.Optional;



@Repository
public class ProductRepository {
    private List<Product> productList = new ArrayList<>();
    private Long idCounter = 1L;

    public List<Product> findAll(){
        return productList;
    }
    public void save(Product product){
        if(product.getId() == null){
            product.setId(idCounter++);
            productList.add(product);
        }else{
            update(product);
        }
    }
    public Optional<Product> findById(Long id){
        return productList.stream().filter(p -> p.getId().equals(id))
                .findFirst();
    }
    public void deleteById(Long id){
        productList.removeIf(p -> p.getId().equals(id));
    }

    private void update(Product updateProduct){
        for(int i =0;i<productList.size(); i++){
            if(productList.get(i).getId().equals(updateProduct.getId())){
                productList.set(i,updateProduct);
                break;
            }
        }
    }

}