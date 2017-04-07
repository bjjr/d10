
package controllers;

import java.util.Collection;

import org.joda.time.DateTime;
import org.joda.time.Years;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.ChorbiLikeService;
import services.ChorbiService;
import domain.Chorbi;
import forms.ChorbiForm;

@Controller
@RequestMapping("/chorbi")
public class ChorbiController extends AbstractController {

	@Autowired
	private ChorbiService		chorbiService;

	@Autowired
	private ChorbiLikeService	chorbiLikeService;


	// Constructors ---------------------------------

	public ChorbiController() {
		super();
	}

	// List -----------------------------------------

	@RequestMapping(value = "/list")
	public ModelAndView list() {
		ModelAndView res;
		Collection<Chorbi> chorbies;
		final Collection<Chorbi> liked;
		final Chorbi principal;

		chorbies = this.chorbiService.findAll();
		principal = this.chorbiService.findByPrincipal();
		chorbies.remove(principal);
		liked = this.chorbiLikeService.findLiked(principal);
		res = new ModelAndView("chorbi/list");

		res.addObject("chorbies", chorbies);
		res.addObject("requestURI", "chorbi/list.do");
		res.addObject("liked", liked);
		res.addObject("principal", principal);

		return res;
	}

	// Display --------------------------------------

	@RequestMapping(value = "/display")
	public ModelAndView display(@RequestParam final int chorbiId) {
		ModelAndView res;
		Chorbi chorbi;
		String maskedDesc;
		final DateTime now, birthdate;
		int years;

		chorbi = this.chorbiService.findOne(chorbiId);
		res = new ModelAndView("chorbi/display");
		maskedDesc = this.chorbiService.maskSensibleData(chorbi.getDescription());
		now = DateTime.now();
		birthdate = new DateTime(chorbi.getBirthdate());
		years = Years.yearsBetween(birthdate, now).getYears();

		res.addObject("chorbi", chorbi);
		res.addObject("maskedDesc", maskedDesc);
		res.addObject("age", years);

		return res;
	}

	// Registration ---------------------------------

	@RequestMapping(value = "/create")
	public ModelAndView create() {
		ModelAndView res;
		ChorbiForm chorbiForm;

		chorbiForm = new ChorbiForm();
		res = this.createEditModelAndView(chorbiForm);

		return res;
	}

	// Ancillary methods ----------------------------

	protected ModelAndView createEditModelAndView(final ChorbiForm chorbiForm) {
		ModelAndView result;

		result = this.createEditModelAndView(chorbiForm, null);

		return result;
	}

	protected ModelAndView createEditModelAndView(final ChorbiForm chorbiForm, final String message) {
		ModelAndView result;

		result = new ModelAndView("");

		result.addObject("modelAttribute", chorbiForm);
		result.addObject("message", message);
		result.addObject("isEdit", false);

		return result;
	}
}
