package com.mamay.controller;

import com.mamay.builder.AbstractFlatwareBuilder;
import com.mamay.entity.Flatware;
import com.mamay.entity.PrimaryFlatware;
import com.mamay.entity.SecondaryFlatware;
import com.mamay.exception.LogicalException;
import com.mamay.factory.FlatwareBuilderFactory;
import com.mamay.type.ParserType;
import com.mamay.utility.Transformer;
import lombok.extern.log4j.Log4j2;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Set;

@WebServlet(urlPatterns = {"/FlatwareServlet"})
@Log4j2
public class FlatwareServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private static final String INPUT_FILE = "WEB-INF/resource/flatware.xml";

    @Override
    public void init() {
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String folder = getServletContext().getRealPath(INPUT_FILE);
        try {
            String parserType = request.getParameter("parser");
            request.setAttribute("parserType", parserType);
            log.info(parserType);
            FlatwareBuilderFactory sFactory = new FlatwareBuilderFactory();
            AbstractFlatwareBuilder builder = sFactory.createFlatwareBuilder(ParserType.valueOf(parserType.toUpperCase()));
            builder.buildFlatwares(folder);
            Set<Flatware> parserSet = builder.getFlatwares();
            Set<PrimaryFlatware> prim = Transformer.extractPrimaryFwares(parserSet);
            Set<SecondaryFlatware> sec = Transformer.extractSecondaryFwares(parserSet);
            request.setAttribute("primary", prim);
            request.setAttribute("secondary", sec);
            request.getRequestDispatcher("/WEB-INF/jsp/flatwares.jsp").forward(request, response);
        } catch (LogicalException e) {
            log.error(e.getMessage());
        }
    }

}
