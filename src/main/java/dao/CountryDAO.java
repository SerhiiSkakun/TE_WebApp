package dao;

import entities.Country;
import exceptions.CantFindCountryException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CountryDAO {
    private final Connection connection;

    public CountryDAO(Connection connection) {
        this.connection = connection;
    }

    public List<Country> readAll() throws CantFindCountryException {
        try {
            List<Country> result = new ArrayList<>();
            PreparedStatement ps = connection.prepareStatement("select * from country");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String shortName = rs.getString("short_name");
                result.add(new Country(id, name, shortName));
            }
            return result;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new CantFindCountryException(e);
        }
    }

    public void add(Country country) throws CantFindCountryException {
        try (PreparedStatement ps = connection.prepareStatement
                ("insert into country (name, short_name) values (?,?)")){
            ps.setString(1, country.getName());
            ps.setString(2, country.getShortName());
            ps.execute();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new CantFindCountryException(e);
        }
    }

    public void update(Country country) throws CantFindCountryException {
        try {
            PreparedStatement ps = connection.prepareStatement("update country set short_name = ?, name = ? where id = ?");
            ps.setString(1, country.getShortName());
            ps.setString(2, country.getName());
            ps.setInt(3,country.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new CantFindCountryException(e);
        }
    }

    public Country find(int countryID) throws CantFindCountryException {
        try(PreparedStatement ps = connection.prepareStatement("select * from country where id = ?")){
            ps.setInt(1,countryID);
            ResultSet resultSet = ps.executeQuery();
            if(resultSet.next()) {
                return new Country(
                        resultSet.getInt("id"),
                        resultSet.getString("short_name"),
                        resultSet.getString("name"));
            }
            else {
                return new Country(0,"","");
            }
        } catch (SQLException e){
            e.printStackTrace();
            throw new CantFindCountryException(e);
        }
    }

    public void delete(int countryID) throws CantFindCountryException {
        try(PreparedStatement ps = connection.prepareStatement("delete from country where id = ?")){
            ps.setInt(1,countryID);
            ps.executeUpdate();
        } catch (SQLException e){
            e.printStackTrace();
            throw new CantFindCountryException(e);
        }
    }
}