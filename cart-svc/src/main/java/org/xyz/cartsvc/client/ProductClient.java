package org.xyz.cartsvc.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;
import org.xyz.cartsvc.dto.external.ProductBatchReq;
import org.xyz.cartsvc.dto.external.ProductBatchResp;
import org.xyz.cartsvc.dto.external.ProductResponse;

import java.util.List;
import java.util.Optional;

@FeignClient(name = "product-service", url = "http://localhost:8081")
public interface ProductClient {

    @GetMapping("/api/v1/products/{id}")
    Optional<ProductResponse> getProductById(@PathVariable("id") Long id);

    @GetMapping("/api/v1/products")
    Optional<List<ProductResponse>> getAllProductById(@RequestParam List<Long> ids);

    @PostMapping("/api/v1/products/batch")
    Optional<List<ProductBatchResp>> getAllProductByBatchIds(@RequestBody List<ProductBatchReq> productBatchReqs);

}
