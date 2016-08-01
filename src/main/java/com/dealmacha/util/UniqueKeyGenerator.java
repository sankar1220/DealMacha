/**
 *
 */
package com.dealmacha.util;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.stereotype.Component;

/**
 * @author Jay
 *
 */
@Component
public class UniqueKeyGenerator {

    public String getUniqueKeyGen() {
        String uniqueKey = RandomStringUtils.randomNumeric(10);
        return uniqueKey;
    }

}
