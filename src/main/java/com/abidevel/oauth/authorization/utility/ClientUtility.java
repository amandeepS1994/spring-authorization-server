package com.abidevel.oauth.authorization.utility;

import java.util.stream.Stream;

import com.abidevel.oauth.authorization.model.enumeration.GrantType;
import com.abidevel.oauth.authorization.model.enumeration.ScopeType;

public final class ClientUtility {
    private ClientUtility() {

    }

    public static boolean checkIfScopeTypeExist (String value) {
        return Stream.of(ScopeType.values())
            .anyMatch(scope -> scope.name().equalsIgnoreCase(value));
    }

    public static boolean checkIfGrantTypeExist (String value) {
        return Stream.of(GrantType.values())
            .anyMatch(grant -> grant.name().equalsIgnoreCase(value));
    }
}
