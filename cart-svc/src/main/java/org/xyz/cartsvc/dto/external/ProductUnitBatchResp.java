package org.xyz.cartsvc.dto.external;

import java.math.BigDecimal;

public record ProductUnitBatchResp(
        Long id,
        String unitType,
        BigDecimal price,
        int stock,
        String  imageUrl
) {
}
