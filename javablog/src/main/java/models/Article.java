package models;

import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "articles")
public class Article {

	private int id;
	private String title;
	private String imageLink;
	private String textContent;
	private Author author;
	private List<ArticleSection> articleSections;
	private Date lastUpdated;
	private int numberOfViews;

	public Article(String title, String textContent, Author author) {
		this.title = title;
		this.textContent = textContent;
		this.author = author;
		this.articleSections = new ArrayList<>();
		this.numberOfViews = 0;
		this.imageLink = "";
	}

	public Article() {
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Column(name = "title")
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@Column(name = "image_link")
	public String getImageLink() {
		return imageLink;
	}

	public void setImageLink(String imageLink) {
		this.imageLink = imageLink;
	}

	@Column(name = "text_content")
	public String getTextContent() {
		return textContent;
	}

	public void setTextContent(String textContent) {
		this.textContent = textContent;
	}

	@ManyToOne
	@JoinColumn( name = "author_id", nullable = false)
	public Author getAuthor() {
		return author;
	}

	public void setAuthor(Author author) {
		this.author = author;
	}

	@OneToMany( mappedBy = "section")
	public List<ArticleSection> getArticleSections() {
		return articleSections;
	}

	public void setArticleSections(List<ArticleSection> sections) {
		this.articleSections = sections;
	}

//	public void addSectionToArticle(Section section){
//		this.articleSections.add(section);
//	}

	@Temporal(TemporalType.DATE)
	@Column(name = "last_updated")
	public Date getLastUpdated() {
		return lastUpdated;
	}

	public void setLastUpdated(Date lastUpdated) {
		this.lastUpdated = lastUpdated;
	}

	public void updateArticleDate(){
		Date currentDateTime = new Date();
		this.lastUpdated = currentDateTime;
	}

//	@Transient
//	public List<Integer> getSectionIds(){
//		List<Integer> sectionIds = new ArrayList<>();
//
//		for(Section section : sections){
//			sectionIds.add(section.getId());
//		}
//
//		return sectionIds;
//	}

	public void clearArticleSections(){
		this.articleSections.clear();
	}

	@Column(name = "number_of_views")
	public int getNumberOfViews() {
		return numberOfViews;
	}

	public void setNumberOfViews(int numberOfViews) {
		this.numberOfViews = numberOfViews;
	}

	public void addView(){
		this.numberOfViews++;
	}
}
