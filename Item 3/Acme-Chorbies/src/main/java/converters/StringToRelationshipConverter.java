
package converters;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import repositories.RelationshipRepository;
import domain.Relationship;

@Component
@Transactional
public class StringToRelationshipConverter implements Converter<String, Relationship> {

	@Autowired
	RelationshipRepository	relationshipRepository;


	@Override
	public Relationship convert(final String text) {
		Relationship res;
		int id;

		try {
			if (StringUtils.isEmpty(text))
				res = null;
			else {
				id = Integer.valueOf(text);
				res = this.relationshipRepository.findOne(id);
			}
		} catch (final Throwable th) {
			throw new IllegalArgumentException(th);
		}

		return res;
	}

}
