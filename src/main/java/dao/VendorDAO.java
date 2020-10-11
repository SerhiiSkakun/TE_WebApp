package dao;

import entities.Country;
import entities.Vendor;
import exceptions.CantFindCountryException;
import exceptions.CantFindVendorException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class VendorDAO {
    private Connection connection;
    private CountryDAO countryDAO = new CountryDAO(connection);

    public VendorDAO(Connection connection) {
        this.connection = connection;
    }

    public List<Vendor> readAll() throws CantFindCountryException {
        try {
            List<Vendor> result = new ArrayList<>();
            PreparedStatement ps = connection.prepareStatement("select " +
                    "vendor.id as vendor_id, " +
                    "vendor.name as vendor_name, " +
                    "vendor.country_id as vendor_country_id, " +
                    "country.name as country_name, " +
                    "country.short_name as country_short_name " +
                    "from vendor left join country");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int vendorId = rs.getInt("vendor_id");
                String vendorName = rs.getString("vendor_name");
                int countryId = rs.getInt("vendor_country_id");
                String countryName = rs.getString("country_name");
                String countryShortName = rs.getString("country_short_name");
                Vendor vendor = new Vendor(vendorId, vendorName);
                if (countryId != 0) {
                    Country country = new Country(countryId, countryName, countryShortName);
                    vendor.setCountry(country);
                }
                result.add(vendor);
            }
            return result;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new CantFindCountryException(e);
        }
    }

    public List<Vendor> readByCountryId(int countryID) throws CantFindVendorException {
        List<Vendor> result = new ArrayList<>();
        try (
                PreparedStatement ps = connection.prepareStatement("select " +
                        "vendor.id as vendor_id, " +
                        "vendor.name as vendor_name, " +
                        "country.id as country_id, " +
                        "country.name as country_name, " +
                        "country.short_name as country_short_name " +
                        "from vendor join country " +
                        "on country.id = vendor.country_id " +
                        "where vendor.country_id = ?")){
            ps.setInt(1,countryID);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int vendorId = rs.getInt("vendor_id");
                String vendorName = rs.getString("vendor_name");
                String countryName = rs.getString("country_name");
                String countryShortName = rs.getString("country_short_name");
                Country country = new Country(countryID, countryName, countryShortName);
                result.add(new Vendor(vendorId, vendorName, country));
            }
            return result;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new CantFindVendorException(e);
        }
    }

    public void add(Vendor vendor) throws CantFindVendorException {
        try (
                PreparedStatement ps = connection.prepareStatement
                ("insert into vendor (name, country_id) values (?,?)")){
            ps.setString(1, vendor.getName());
            ps.setInt(2, vendor.getCountry().getId());
            ps.execute();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new CantFindVendorException(e);
        }
    }

    public void update(Vendor vendor) throws CantFindVendorException {
        try (PreparedStatement ps = connection.prepareStatement("update vendor " +
                "set name = ?, " +
                "country_id = ? " +
                "where id = ?")){
            ps.setString(1, vendor.getName());
            ps.setInt(2, vendor.getCountry().getId());
            ps.setInt(3,vendor.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new CantFindVendorException(e);
        }
    }

    public Vendor find(int VendorId) throws CantFindVendorException {
        try(PreparedStatement ps = connection.prepareStatement("select " +
                "vendor.id as vendor_id, " +
                "vendor.name as vendor_name, " +
                "country.id as country_id, " +
                "country.name as country_name, " +
                "country.short_name as country_short_name " +
                "from vendor join country " +
                "on vendor.country_id = country.id " +
                "where id = ?")){
            ps.setInt(1,VendorId);
            ResultSet rs = ps.executeQuery();
            if(rs.next()) {
                return new Vendor(
                        VendorId,
                        rs.getString("vendor_name"),
                        new Country(
                                rs.getInt("country_id"),
                                rs.getString("country_name"),
                                rs.getString("country_short_name")));
            }
            else {
                return new Vendor(0,"",new Country(0,"",""));
            }
        } catch (SQLException e){
            e.printStackTrace();
            throw new CantFindVendorException(e);
        }
    }

    public void delete(int VendorId) throws CantFindVendorException {
        try(PreparedStatement ps = connection.prepareStatement("delete from vendor where id = ?")){
            ps.setInt(1,VendorId);
            ps.executeUpdate();
        } catch (SQLException e){
            e.printStackTrace();
            throw new CantFindVendorException(e);
        }
    }
}
