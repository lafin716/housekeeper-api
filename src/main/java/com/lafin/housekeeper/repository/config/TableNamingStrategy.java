package com.lafin.housekeeper.repository.config;

import org.hibernate.boot.model.naming.Identifier;
import org.hibernate.boot.model.naming.PhysicalNamingStrategyStandardImpl;
import org.hibernate.engine.jdbc.env.spi.JdbcEnvironment;

public class TableNamingStrategy extends PhysicalNamingStrategyStandardImpl {

    private final String PREFIX = "hk_";

    @Override
    public Identifier toPhysicalTableName(Identifier name, JdbcEnvironment context) {
        Identifier prefixedTableName = new Identifier(PREFIX + name.getText().toLowerCase(), name.isQuoted());

        return super.toPhysicalTableName(prefixedTableName, context);
    }
}
