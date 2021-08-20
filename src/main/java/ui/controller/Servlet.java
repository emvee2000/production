package ui.controller;

import domain.db.BlueprintsDB;
import domain.db.ItemsDB;
import domain.model.Bpo;
import domain.model.Item;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet({"", "/page"})
public class Servlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ArrayList<Bpo> out = getProductionLineForItem(ItemsDB.getInstance().getItemByName("Ishtar"));
        request.setAttribute("bpoList", out);
        request.setAttribute("MainItemName", out.get(0).getProductName());
        request.getRequestDispatcher("index.jsp").forward(request, response);
    }

    private ArrayList<Bpo> getProductionLineForItem(Item item) {
        Bpo main = BlueprintsDB.getInstance().getBPOforItem(item);
        ArrayList<Bpo> BpoList = new ArrayList<>();
        BpoList.add(main);
        for (Item i :
                main.getMaterials().keySet()) {
            if (BlueprintsDB.getInstance().getBPOforItem(i) != null) {
                BpoList.addAll(getProductionLineForItem(i));
            }
        }
        return BpoList;
    }

}
