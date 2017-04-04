
package services;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.SearchTemplateRepository;
import domain.Chorbi;
import domain.SearchTemplate;

@Service
@Transactional
public class SearchTemplateService {

	@Autowired
	private SearchTemplateRepository	searchTemplateRepository;

	@Autowired
	private ChorbiService				chorbiService;


	public SearchTemplate create() {
		final SearchTemplate searchTemplateResult = new SearchTemplate();

		searchTemplateResult.setAge(null);
		searchTemplateResult.setCoordinates(null);
		searchTemplateResult.setGender(null);
		searchTemplateResult.setKeyword(null);
		searchTemplateResult.setRelationship(null);

		return searchTemplateResult;
	}

	public SearchTemplate save(final SearchTemplate searchTemplate) {
		Assert.notNull(searchTemplate, "SearchTemplateService.save: The search template cannot be null");
		Assert.isTrue(this.isAValidSearchTemplate(searchTemplate), "SearchTemplateService.save: The search template needs to be valid");

		final SearchTemplate searchTemplateSaved = this.searchTemplateRepository.save(searchTemplate);

		return searchTemplateSaved;
	}

	public Collection<SearchTemplate> findAll() {
		return this.searchTemplateRepository.findAll();
	}

	public SearchTemplate findOne(final Integer id) {
		return this.searchTemplateRepository.findOne(id);
	}

	private Boolean isAValidSearchTemplate(final SearchTemplate searchTemplate) {
		Boolean res = true;

		if (searchTemplate.getGender() != null && (!searchTemplate.getGender().toUpperCase().equals("MAN") || !searchTemplate.getGender().toUpperCase().equals("WOMAN")))
			res = false;

		if (searchTemplate.getRelationship() != null
			&& (!searchTemplate.getRelationship().toUpperCase().equals("ACTIVITIES") || !searchTemplate.getRelationship().toUpperCase().equals("FRIENDSHIP") || !searchTemplate.getRelationship().toUpperCase().equals("LOVE")))
			res = false;

		return res;
	}

	// Other business methods

	//TODO: hacer cacheable
	public Collection<Chorbi> search(final SearchTemplate searchTemplate) {

		final Chorbi loggedChorbi = this.chorbiService.findByPrincipal();
		Assert.notNull(loggedChorbi);

		Assert.notNull(loggedChorbi.getCreditCard(), "SearchTemplateService.search: You need a valid creditcard in order to perform a search");

		return this.chorbiService.findChorbiesBySearchTemplate(searchTemplate);
	}

}
