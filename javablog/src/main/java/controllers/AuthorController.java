package controllers;

import db.DBHelper;
import models.Author;
import spark.ModelAndView;
import spark.template.velocity.VelocityTemplateEngine;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static spark.Spark.get;
import static spark.Spark.post;

public class AuthorController {

	public AuthorController(){
		setupEndPoints();
	}

	private static void setupEndPoints(){

		VelocityTemplateEngine velocityTemplateEngine = new VelocityTemplateEngine();

		get("/authors/new", (req, res) ->{
			Map<String, Object> model = new HashMap<>();
			model.put("template", "templates/authors/new.vtl");
			return new ModelAndView(model, "templates/layout.vtl");
		}, velocityTemplateEngine
		);


		get("/authors", (req, res) ->{
			Map<String, Object> model = new HashMap<>();
			List<Author> authors = DBHelper.findAll(Author.class);
			model.put("template", "templates/authors/index.vtl");
			model.put("authors", authors);
			return new ModelAndView(model, "templates/layout.vtl");
			}, velocityTemplateEngine
		);

		post("/authors", (req, res) -> {
			String name = req.queryParams("name");
			Author author = new Author(name);
			DBHelper.save(author);
			res.redirect("/authors");
			return null;
		}, velocityTemplateEngine);

		get("authors/:id", (req, res) -> {
			String strId = req.params(":id");
			Integer intId = Integer.parseInt(strId);
			Author author = DBHelper.findById(Author.class, intId);

			Map<String, Object> model = new HashMap<>();

			model.put("author", author);
			model.put("template", "templates/authors/show.vtl");

			return new ModelAndView(model, "templates/layout.vtl");
		}, velocityTemplateEngine);


	}
}
