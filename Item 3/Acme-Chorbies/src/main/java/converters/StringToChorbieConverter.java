
package converters;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import repositories.ChorbieRepository;
import domain.Chorbie;

@Component
@Transactional
public class StringToChorbieConverter implements Converter<String, Chorbie> {

	@Autowired
	ChorbieRepository	chorbieRepository;


	@Override
	public Chorbie convert(final String text) {
		Chorbie res;
		int id;

		try {
			if (StringUtils.isEmpty(text))
				res = null;
			else {
				id = Integer.valueOf(text);
				res = this.chorbieRepository.findOne(id);
			}
		} catch (final Throwable th) {
			throw new IllegalArgumentException(th);
		}

		return res;
	}

}
