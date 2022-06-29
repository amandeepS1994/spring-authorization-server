package com.abidevel.oauth.authorization.utility;

import java.util.stream.Stream;

import com.abidevel.oauth.authorization.model.entity.User;
import com.abidevel.oauth.authorization.model.enumeration.AuthorityTypes;

public final class UserUtility {

    private UserUtility(){

    }


    public static boolean checkUserContainsValidAuthority (User user) {
        return Stream.of(AuthorityTypes.values())
            .anyMatch(at -> 
                user.getAuthorities().stream()
                .anyMatch(auth -> at.equals(auth.getAuthorityName())));
    }


    public static boolean checkAuthorityIsValid (AuthorityTypes auth) {
        return Stream.of(AuthorityTypes.values())
            .anyMatch(at -> at.equals(auth));
    }
}
