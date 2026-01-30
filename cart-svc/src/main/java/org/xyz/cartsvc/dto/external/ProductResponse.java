package org.xyz.cartsvc.dto.external;

import java.math.BigDecimal;
import java.util.List;

public record ProductResponse(
        Long id,
        String name,
        int stock,
        List<String> images,
        BigDecimal price,
        List<ProductUnitResponse> productUnits
) {
}
