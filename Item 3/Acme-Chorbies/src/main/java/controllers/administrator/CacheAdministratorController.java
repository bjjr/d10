
package controllers.administrator;

import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.config.CacheConfiguration;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import controllers.AbstractController;
import forms.CacheForm;

@Controller
@RequestMapping("/cache/administrator")
public class CacheAdministratorController extends AbstractController {

	@RequestMapping(value = "/display")
	public ModelAndView display() {
		ModelAndView res;

		res = new ModelAndView("cache/display");
		res.addObject("timeout", this.getCurrentTimeout());

		return res;
	}

	@RequestMapping(value = "/edit")
	public ModelAndView create() {
		ModelAndView res;
		final CacheForm cacheForm = new CacheForm(this.getCurrentTimeout());

		res = this.createEditModelAndView(cacheForm);

		return res;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView save(final CacheForm cacheForm) {
		ModelAndView res;

		if (cacheForm.getTimeout() == 0)
			res = this.createEditModelAndView(cacheForm, "cache.invalid");
		else
			try {
				this.setTimeout(cacheForm.getTimeout());
				res = new ModelAndView("redirect:display.do");
			} catch (final Throwable th) {
				res = this.createEditModelAndView(cacheForm, "misc.commit.error");
			}

		return res;
	}
	private long getCurrentTimeout() {
		return this.getCacheConfiguration().getTimeToIdleSeconds();
	}

	private void setTimeout(final long timeout) {
		final CacheConfiguration config = this.getCacheConfiguration();
		config.setTimeToIdleSeconds(timeout);
		config.setTimeToLiveSeconds(timeout);
	}

	private CacheConfiguration getCacheConfiguration() {
		final CacheManager manager = CacheManager.newInstance();
		final Cache cache = manager.getCache("chorbiesPerSearchTemplate");
		return cache.getCacheConfiguration();
	}

	protected ModelAndView createEditModelAndView(final CacheForm cacheForm) {
		ModelAndView result;

		result = this.createEditModelAndView(cacheForm, null);

		return result;
	}

	protected ModelAndView createEditModelAndView(final CacheForm cacheForm, final String message) {
		ModelAndView result;

		result = new ModelAndView("cache/edit");

		result.addObject("cacheForm", cacheForm);
		result.addObject("message", message);

		return result;
	}

}
