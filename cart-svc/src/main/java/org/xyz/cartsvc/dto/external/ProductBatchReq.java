package org.xyz.cartsvc.dto.external;

import java.util.List;

public record ProductBatchReq(
        Long productId,
        List<Long> productUnitIds
) {
}
