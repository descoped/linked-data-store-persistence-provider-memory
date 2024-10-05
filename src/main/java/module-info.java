import io.descoped.lds.api.persistence.PersistenceInitializer;
import io.descoped.lds.core.persistence.memory.MemoryInitializer;

module io.descoped.lds.persistence.memory {
    requires io.descoped.lds.persistence.api;
    requires io.descoped.lds.persistence.foundationdb;
    requires fdb.java;

    provides PersistenceInitializer with MemoryInitializer;
}
