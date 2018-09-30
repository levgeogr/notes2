package ru.note.controllers;

import ru.note.dao.NotesDAO;
import ru.note.entities.Note;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet(name = "MainController", urlPatterns = "/")
public class MainController extends HttpServlet {

    private NotesDAO notesDAO;

    public void init() {
        String jdbcURL = getServletContext().getInitParameter("jdbcURL");
        String jdbcUsername = getServletContext().getInitParameter("jdbcUsername");
        String jdbcPassword = getServletContext().getInitParameter("jdbcPassword");

        notesDAO = new NotesDAO(jdbcUsername, jdbcPassword, jdbcURL);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String action = req.getServletPath();
        System.out.println("ACTION: " + action);
        try {
            switch (action) {

                case "/delete":
                    deleteNote(req, resp);
                    break;

                case "/shownote":
                    showNote(req, resp);
                    break;

                case "/addnew":
                    req.getRequestDispatcher("/views/add_note_form.jsp").forward(req, resp);
                    break;

                default:
                    System.out.println("Action undefined: " + action);
                    showAll(req, resp);
                    break;
            }
        } catch (SQLException e) {
            throw new ServletException(e);
        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String action = req.getServletPath();
        try {
            switch (action) {
                case "/add":
                    addNote(req, resp);
                    break;

                case "/search":
                    search(req, resp);
                    break;

                default:
                    System.out.println("Action undefined: " + action);
                    showAll(req, resp);
                    break;
            }
        } catch (SQLException e) {
            System.out.println(e.getErrorCode());
        }
    }

    private void search(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, SQLException {

        String match = req.getParameter("match");
        req.setAttribute("notes", notesDAO.getNotesByStringMatch(match));
        req.getRequestDispatcher("/views/notes_main.jsp").forward(req, resp);
    }

    private void showAll(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, SQLException {

        List<Note> notes = notesDAO.getNotes();
        req.setAttribute("notes", notes);
        req.getRequestDispatcher("/views/notes_main.jsp").forward(req, resp);
    }

    private void deleteNote(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, SQLException {

        int id = Integer.parseInt(req.getParameter("id"));

        Note note = new Note(id);
        boolean isDeletedSuccessfully = notesDAO.deleteNote(note);
        if (isDeletedSuccessfully) {
            resp.sendRedirect("/notes2");
        } else {
            error("incorrect deleting", req, resp);
        }

    }

    private void showNote(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, SQLException {

        int id = Integer.parseInt(req.getParameter("id"));

        Note note = notesDAO.find(id);

        if (note == null) {
            error("Note not found", req, resp);
        } else {
            req.setAttribute("note", note);
            req.getRequestDispatcher("/views/note_body.jsp").forward(req, resp);
        }
    }

    private void addNote(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, SQLException {
        String title = req.getParameter("title");
        String textbody = req.getParameter("textbody");

        if (title.equals("") && textbody.equals("")) {
            error("Note can not be empty", req, resp);
        } else {
            notesDAO.insertNote(new Note(0, title, textbody));
            resp.sendRedirect("/notes2");
        }
    }

    private void error(String message, HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("message", message);
        req.getRequestDispatcher("/views/error_page.jsp").forward(req, resp);
    }

}
