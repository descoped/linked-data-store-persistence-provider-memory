package io.descoped.lds.core.persistence.memory;

import io.descoped.lds.api.persistence.TransactionFactory;
import io.descoped.lds.core.persistence.foundationdb.FoundationDBDirectory;
import io.descoped.lds.core.persistence.foundationdb.FoundationDBRxPersistence;

public class MemoryRxPersistence extends FoundationDBRxPersistence {

    MemoryRxPersistence(TransactionFactory transactionFactory, FoundationDBDirectory foundationDBDirectory) {
        super(transactionFactory, foundationDBDirectory);
    }
}