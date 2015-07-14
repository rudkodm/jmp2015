package by.rudko.workout.controller;

import org.springframework.data.rest.webmvc.RepositoryLinksResource;
import org.springframework.hateoas.ResourceProcessor;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/test")
public class TestController
        implements ResourceProcessor<RepositoryLinksResource>
{

    @RequestMapping(method = RequestMethod.GET, value = "")
    public String helloWorld(){
        return string(", and this is method signature change");
    }

    private String string(String str){
        return "This string generated in method" + str;
    }
    @Override
    public RepositoryLinksResource process(RepositoryLinksResource resource) {
        resource.add(ControllerLinkBuilder.linkTo(TestController.class).withRel("test"));
        return resource;
    }
}
