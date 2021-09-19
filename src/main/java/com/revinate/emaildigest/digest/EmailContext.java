package com.revinate.emaildigest.digest;

import com.revinate.emaildigest.deal.model.Deal;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * @author mahmood
 * @since 9/18/21
 */
@Data
@Accessors(chain = true)
class EmailContext {
    private String contactFirstName;
    private List<Deal> deals;
}
