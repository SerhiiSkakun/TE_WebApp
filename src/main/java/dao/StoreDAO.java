package dao;

import entities.Country;
import entities.Vendor;
import entities.Watch;
import entities.Store;
import exceptions.CantFindStoreException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StoreDAO {
    private Connection connection;

    public StoreDAO(Connection connection) {
        this.connection = connection;
    }

    public List<Store> readAll() throws CantFindStoreException {
        List<Store> result = new ArrayList<>();
        try (
                PreparedStatement ps = connection.prepareStatement("select " +
                        "store.id as store_id, " +
                        "store.price as store_price, " +
                        "store.quantity as store_quantity, " +
                        "store.date as store_date, " +
                        "store.actual as store_actual, " +
                        "watch.id as watch_id, " +
                        "watch.mark as watch_mark, " +
                        "watch.type as watch_type, " +
                        "vendor.id as vendor_id, " +
                        "vendor.name as vendor_name, " +
                        "country.id as country_id, " +
                        "country.name as country_name, " +
                        "country.short_name as country_short_name " +
                        "from store left join watch on watch.id = store.watch_id " +
                        "left join vendor on vendor.id = watch.vendor_id " +
                        "left join country on country.id = vendor.country_id")) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int storeId = rs.getInt("store_id");
                int storePrice = rs.getInt("store_price");
                int storeQuantity = rs.getInt("store_quantity");
                String storeDate = rs.getString("store_date");
                boolean storeActual = rs.getBoolean("store_actual");
                int watchId = rs.getInt("watch_id");
                String watchMark = rs.getString("watch_mark");
                String watchType = rs.getString("watch_type");
                int vendorId = rs.getInt("vendor_id");
                String vendorName = rs.getString("vendor_name");
                int countryId = rs.getInt("country_id");
                String countryName = rs.getString("country_name");
                String countryShortName = rs.getString("country_short_name");
                Watch watch = new Watch(watchId, watchMark, watchType);
                if (vendorId != 0) {
                    Vendor vendor = new Vendor(vendorId, vendorName);
                    watch.setVendor(vendor);
                    if (countryId != 0) {
                        Country country = new Country(countryId, countryName, countryShortName);
                        vendor.setCountry(country);
                    }
                }
                Store store = new Store(storeId, storePrice, storeQuantity, storeDate, storeActual, watch);
                result.add(store);
            }
            return result;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new CantFindStoreException(e);
        }
    }

    public List<Store> readByWatchId(int watchId) throws CantFindStoreException {
        List<Store> result = new ArrayList<>();
        try (
                PreparedStatement ps = connection.prepareStatement("select " +
                        "store.id as store_id, " +
                        "store.price as store_price, " +
                        "store.quantity as store_quantity, " +
                        "store.date as store_date, " +
                        "store.actual as store_actual, " +
                        "watch.id as watch_id, " +
                        "watch.mark as watch_mark, " +
                        "watch.type as watch_type, " +
                        "vendor.id as vendor_id, " +
                        "vendor.name as vendor_name, " +
                        "country.id as country_id, " +
                        "country.name as country_name, " +
                        "country.short_name as country_short_name " +
                        "from store left join watch on watch.id = store.watch_id " +
                        "left join vendor on vendor.id = watch.vendor_id " +
                        "left join country on country.id = vendor.country_id " +
                        "where store.watch_id = ?")) {
            ps.setInt(1, watchId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int storeId = rs.getInt("store_id");
                int storePrice = rs.getInt("store_price");
                int storeQuantity = rs.getInt("store_quantity");
                String storeDate = rs.getString("store_date");
                boolean storeActual = rs.getBoolean("store_actual");
                String watchMark = rs.getString("watch_mark");
                String watchType = rs.getString("watch_type");
                int vendorId = rs.getInt("vendor_id");
                String vendorName = rs.getString("vendor_name");
                int countryId = rs.getInt("country_id");
                String countryName = rs.getString("country_name");
                String countryShortName = rs.getString("country_short_name");
                Watch watch = new Watch(watchId, watchMark, watchType);
                if (vendorId != 0) {
                    Vendor vendor = new Vendor(vendorId, vendorName);
                    watch.setVendor(vendor);
                    if (countryId != 0) {
                        Country country = new Country(countryId, countryName, countryShortName);
                        vendor.setCountry(country);
                    }
                }
                Store store = new Store(storeId, storePrice, storeQuantity, storeDate, storeActual, watch);
                result.add(store);
            }
            return result;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new CantFindStoreException(e);
        }
    }
}