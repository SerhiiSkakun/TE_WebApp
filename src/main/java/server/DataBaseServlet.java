package server;


import beans.CountryBean;
import beans.StoreBean;
import beans.VendorBean;
import beans.WatchBean;
import dao.CountryDAO;
import dao.StoreDAO;
import dao.VendorDAO;
import dao.WatchDAO;
import entities.Store;
import entities.Watch;
import exceptions.CantFindCountryException;
import exceptions.CantFindStoreException;
import exceptions.CantFindVendorException;
import exceptions.CantFindWatchException;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

@WebServlet(name = "DataBaseServlet", urlPatterns = {"*.html"})
public class DataBaseServlet extends HttpServlet {

    private CountryDAO countryDAO;
    private VendorDAO vendorDAO;
    private WatchDAO watchDAO;
    private StoreDAO storeDAO;

    @Override
    public void init() throws ServletException {
        super.init();
        try {
            InitialContext ctx = new InitialContext();
            DataSource ds = (DataSource) ctx.lookup("java:comp/env/jdbc/watches");
            Connection connection = ds.getConnection();
            countryDAO = new CountryDAO(connection);
            vendorDAO = new VendorDAO(connection);
            watchDAO = new WatchDAO(connection);
            storeDAO = new StoreDAO(connection);

        } catch (NamingException | SQLException e) {
            e.printStackTrace();
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        if (request.getRequestURI().endsWith("countries.html")){
//            int countryID = Integer.parseInt(request.getParameter("id"));
//            try {
//                countryDAO.delete(countryID);
//                CountryBean countryBean = new CountryBean();
//                countryBean.setCountries(countryDAO.readAll());
//                request.setAttribute("countryBean", countryBean);
//                request.getRequestDispatcher("showcountries.jsp").forward(request, response);
//            } catch (CantFindCountryException e) {
//                e.printStackTrace();
//            }
//        }

        if (request.getRequestURI().endsWith("vendors.html")){
            int countryID = Integer.parseInt(request.getParameter("id"));
            try {
                VendorBean vendorBean = new VendorBean();
                vendorBean.setVendors(vendorDAO.readByCountryId(countryID));
                request.setAttribute("vendorBean", vendorBean);
                request.getRequestDispatcher("showvendors.jsp").forward(request, response);
            } catch (CantFindVendorException e) {
                e.printStackTrace();
            }
        }

        if (request.getRequestURI().endsWith("watches.html")){
            int vendorId = Integer.parseInt(request.getParameter("id"));
            try {
                WatchBean watchBean = new WatchBean();
                List<Watch> list = watchDAO.readByVendorId(vendorId);
                watchBean.setWatches(list);
                request.setAttribute("watchBean", watchBean);
                request.getRequestDispatcher("showwatches.jsp").forward(request, response);
            } catch (CantFindWatchException e) {
                e.printStackTrace();
            }
        }

        if (request.getRequestURI().endsWith("stores.html")){
            int watchId = Integer.parseInt(request.getParameter("id"));
            try {
                StoreBean storeBean = new StoreBean();
                List<Store> list = storeDAO.readByWatchId(watchId);
                storeBean.setStores(list);
                request.setAttribute("storeBean", storeBean);
                request.getRequestDispatcher("showstores.jsp").forward(request, response);
            } catch (CantFindStoreException e) {
                e.printStackTrace();
            }
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            CountryBean countryBean = new CountryBean();
            countryBean.setCountries(countryDAO.readAll());
            request.setAttribute("countryBean", countryBean);
            request.getRequestDispatcher("showcountries.jsp").forward(request, response);
        } catch (CantFindCountryException e) {
            e.printStackTrace();
        }
    }
}
