package org.xyz.cartsvc.dto.external;

import java.math.BigDecimal;

public record ProductUnitResponse(
        Long id,
        String productUnitType,
        BigDecimal price,
        int stock
) {
}
