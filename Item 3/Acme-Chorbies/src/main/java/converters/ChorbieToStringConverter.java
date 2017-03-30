
package converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import domain.Chorbie;

@Component
@Transactional
public class ChorbieToStringConverter implements Converter<Chorbie, String> {

	@Override
	public String convert(final Chorbie chorbie) {
		String res;

		if (chorbie == null)
			res = null;
		else
			res = String.valueOf(chorbie.getId());

		return res;
	}

}
