package io.descoped.lds.core.persistence.memory;

import io.descoped.lds.api.persistence.PersistenceInitializer;
import io.descoped.lds.api.persistence.ProviderName;
import io.descoped.lds.api.persistence.TransactionFactory;
import io.descoped.lds.api.persistence.reactivex.RxJsonPersistence;
import io.descoped.lds.api.persistence.reactivex.RxJsonPersistenceBridge;
import io.descoped.lds.core.persistence.foundationdb.FoundationDBDirectory;

import java.util.Map;
import java.util.Set;

import static java.util.Optional.ofNullable;

@ProviderName("mem")
public class MemoryInitializer implements PersistenceInitializer {

    MemoryRxPersistence persistence;
    RxJsonPersistence rxJsonPersistence;

    @Override
    public String persistenceProviderId() {
        return "mem";
    }

    @Override
    public Set<String> configurationKeys() {
        return Set.of(
                "persistence.fragment.capacity"
        );
    }

    public MemoryRxPersistence getPersistence() {
        return persistence;
    }

    public RxJsonPersistence getRxJsonPersistence() {
        return rxJsonPersistence;
    }

    @Override
    public RxJsonPersistence initialize(String defaultNamespace, Map<String, String> configuration, Set<String> managedDomains) {
        int fragmentCapacityBytes = Integer.parseInt(ofNullable(configuration.get("persistence.fragment.capacity")).orElse("8192"));
        boolean cancelTxOnClose = Boolean.parseBoolean(ofNullable(configuration.get("persistence.mem.transaction.cancel-on-close")).orElse("false"));
        TransactionFactory transactionFactory = new MemoryTransactionFactory(cancelTxOnClose);
        FoundationDBDirectory foundationDbDirectory = new MemoryDirectory(2);
        persistence = new MemoryRxPersistence(transactionFactory, foundationDbDirectory);
        rxJsonPersistence = new RxJsonPersistenceBridge(persistence, fragmentCapacityBytes);
        return rxJsonPersistence;
    }
}
