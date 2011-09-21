package com.controller.binding;

import com.domain.Role;
import com.google.common.base.Function;
import org.springframework.core.convert.converter.Converter;

import java.util.EnumSet;

import static com.google.common.collect.Collections2.transform;
import static java.util.Arrays.asList;
import static java.util.EnumSet.copyOf;

/**
 * @author Stanislav Kurilin
 */
public class RoleConverter implements Converter<String[], EnumSet<Role>> {
    @Override
    public EnumSet<Role> convert(String[] source) {
        return copyOf(transform(asList(source), new Function<String, Role>() {
            @Override
            public Role apply(String input) {
                return Role.valueOf(input);
            }
        }));
    }
}
