package org.xyz.cartsvc.dto.external;

import java.util.List;

public record ProductBatchResp(
        Long id,
        String name,
        String description,
        List<String> images,
        String categoryName,
        List<ProductUnitBatchResp> productUnits
) {
}
