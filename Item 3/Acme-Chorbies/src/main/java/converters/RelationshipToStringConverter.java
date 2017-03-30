
package converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import domain.Relationship;

@Component
@Transactional
public class RelationshipToStringConverter implements Converter<Relationship, String> {

	@Override
	public String convert(final Relationship relationship) {
		String res;

		if (relationship == null)
			res = null;
		else
			res = String.valueOf(relationship.getId());

		return res;
	}

}
