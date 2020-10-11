package beans;

import entities.Store;

import java.util.List;

public class StoreBean {
    private List<Store> stores;

    private Store store;

    public List<Store> getStores() {
        return stores;
    }

    public void setStores(List<Store> stores) {
        this.stores = stores;
    }

    public Store getStore() {
        return store;
    }

    public void setStore(Store store) {
        this.store = store;
    }
}
