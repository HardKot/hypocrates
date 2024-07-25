package com.hypocrates.hypocrates.controller;

import graphql.language.IntValue;
import graphql.language.StringValue;
import graphql.schema.*;

import java.util.Date;
import java.util.Objects;

public class DateScalar {
    public static final GraphQLScalarType DATE = GraphQLScalarType.newScalar()
            .name("Date")
            .description("A custom scalar that handles dates.")
            .coercing(new Coercing() {
                @Override
                public Object serialize(Object dataFetcherResult) throws CoercingSerializeException {
                    if (dataFetcherResult instanceof Date) {
                        return ((Date) dataFetcherResult).getTime();
                    }
                    return null;
                }

                @Override
                public Object parseValue(Object input) throws CoercingParseValueException {
                    if (input instanceof Long) {
                        return new Date((Long) input);
                    }
                    return null;
                }

                @Override
                public Object parseLiteral(Object input) throws CoercingParseLiteralException {
                    if (input instanceof IntValue) {
                        var time = ((IntValue) input).getValue();
                        return time.longValue();
                    }
                    return null;
                }
            }).build();
}
