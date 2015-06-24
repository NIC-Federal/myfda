package com.nicusa.converter;

import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.core.convert.converter.Converter;
import org.springframework.hateoas.ResourceSupport;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.util.UriTemplate;

public abstract class ResourceToDomainConverter<T, D> implements Converter<T, D> {

  protected Long extractIdFromLink(Class controllerClass, ResourceSupport resource, String methodName,
                                   Class... args) {
    return Long.valueOf(extractFieldFromLink(controllerClass, resource, "self", methodName, "id", args));
  }

  protected String extractFieldFromLink(Class controllerClass, ResourceSupport resource, String relation,
                                        String methodName, String fieldName, Class... args) {
    UriTemplate uriTemplate = findUriTemplate(controllerClass, methodName, args);
    return uriTemplate.match(resource.getLink(relation).getHref()).get(fieldName);
  }

  protected UriTemplate findUriTemplate(Class controllerClass, String methodName, Class... argumentClasses) {
    RequestMapping requestMapping = findRequestMapping(controllerClass, methodName, argumentClasses);
    return new UriTemplate(requestMapping.value()[0]);
  }

  protected RequestMapping findRequestMapping(Class<?> controller, String methodName, Class<?>... arguments) {
    RequestMapping requestMapping;
    try {
      requestMapping = AnnotationUtils.getAnnotation(
        controller.getMethod(methodName, arguments), RequestMapping.class);
    } catch (NoSuchMethodException | SecurityException e) {
      throw new RuntimeException(e);
    }
    return requestMapping;
  }
}
