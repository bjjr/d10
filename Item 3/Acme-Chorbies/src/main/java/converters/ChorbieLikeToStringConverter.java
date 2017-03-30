
package converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import domain.ChorbieLike;

@Component
@Transactional
public class ChorbieLikeToStringConverter implements Converter<ChorbieLike, String> {

	@Override
	public String convert(final ChorbieLike chorbieLike) {
		String res;

		if (chorbieLike == null)
			res = null;
		else
			res = String.valueOf(chorbieLike.getId());

		return res;
	}

}
