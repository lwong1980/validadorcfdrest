package org.common.validador;

import org.restlet.representation.Representation;
import org.restlet.resource.Post;
import org.restlet.resource.ServerResource;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component(value = "/validadorService")
@Scope(value = BeanDefinition.SCOPE_PROTOTYPE)
public class ValidadorResource extends ServerResource {

	@Post
	public Representation accept(Representation entity) {
		Representation Representation = null;
		try {
			System.out.println(entity.getClass() + entity.get);
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	 Js
		return Representation;
	}

}