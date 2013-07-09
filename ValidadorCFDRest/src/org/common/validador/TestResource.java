package org.common.validador;

import org.restlet.resource.Get;
import org.restlet.resource.ServerResource;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;



@Component(value = "/testService")
@Scope(value = BeanDefinition.SCOPE_PROTOTYPE  )
public class TestResource extends ServerResource {

  @Get
  public String getResource()  {
    return "TestService!";
  }
}
