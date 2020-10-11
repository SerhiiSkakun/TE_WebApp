package dao;

import entities.Country;
import entities.Vendor;
import entities.Watch;
import exceptions.CantFindWatchException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class WatchDAO {
    private Connection connection;

    public WatchDAO(Connection connection) {
        this.connection = connection;
    }

    public List<Watch> readAll() throws CantFindWatchException {
        List<Watch> result = new ArrayList<>();
        try (
                PreparedStatement ps = connection.prepareStatement("select " +
                        "watch.id as watch_id, " +
                        "watch.mark as watch_mark, " +
                        "watch.type as watch_type, " +
                        "vendor.id as vendor_id, " +
                        "vendor.name as vendor_name, " +
                        "country.id as country_id, " +
                        "country.name as country_name, " +
                        "country.short_name as country_short_name " +
                        "from watch left join vendor on vendor.id = watch.vendor_id " +
                        "left join country on country.id = vendor.country_id")) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
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
                result.add(watch);
            }
            return result;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new CantFindWatchException(e);
        }
    }

    public List<Watch> readByVendorId(int vendorId) throws CantFindWatchException {
        List<Watch> result = new ArrayList<>();
        try (
                PreparedStatement ps = connection.prepareStatement("select " +
                        "watch.id as watch_id, " +
                        "watch.mark as watch_mark, " +
                        "watch.type as watch_type, " +
                        "vendor.id as vendor_id, " +
                        "vendor.name as vendor_name, " +
                        "country.id as country_id, " +
                        "country.name as country_name, " +
                        "country.short_name as country_short_name " +
                        "from watch left join vendor on vendor.id = watch.vendor_id " +
                        "left join country on country.id = vendor.country_id " +
                        "where watch.vendor_id = ?")) {
            ps.setInt(1, vendorId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int watchId = rs.getInt("watch_id");
                String watchMark = rs.getString("watch_mark");
                String watchType = rs.getString("watch_type");
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
                result.add(watch);
            }
            return result;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new CantFindWatchException(e);
        }
    }
}
