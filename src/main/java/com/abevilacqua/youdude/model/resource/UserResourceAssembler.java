package com.abevilacqua.youdude.model.resource;

import com.abevilacqua.youdude.controller.level_3.UserController_Level3;
import com.abevilacqua.youdude.model.User;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;

public class UserResourceAssembler extends RepresentationModelAssemblerSupport<User, UserResource> {

  public UserResourceAssembler() {
    super(UserController_Level3.class, UserResource.class);
  }

  @Override // You need to override this instantiateModel() method to be able to set the correct constructor.
  protected UserResource instantiateModel(User entity) {
    return new UserResource(entity);
  }

  @Override
  public UserResource toModel(User user) {
    return createModelWithId(user.getId(), user);
  }

  @Override
  public CollectionModel<UserResource> toCollectionModel(Iterable<? extends User> entities) {
    return super.toCollectionModel(entities);
  }
}
