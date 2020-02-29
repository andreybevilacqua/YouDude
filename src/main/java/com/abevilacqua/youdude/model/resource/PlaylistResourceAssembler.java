package com.abevilacqua.youdude.model.resource;

import com.abevilacqua.youdude.controller.level_3.PlaylistController_Level3;
import com.abevilacqua.youdude.model.Playlist;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;

public class PlaylistResourceAssembler extends RepresentationModelAssemblerSupport<Playlist, PlaylistResource> {

  public PlaylistResourceAssembler() { super(PlaylistController_Level3.class, PlaylistResource.class); }

  @Override // You need to override this instantiateModel() method to be able to set the correct constructor.
  protected PlaylistResource instantiateModel(Playlist entity) {
    return new PlaylistResource(entity);
  }

  @Override
  public PlaylistResource toModel(Playlist playlist) {
    return createModelWithId(playlist.getId(), playlist);
  }

  @Override
  public CollectionModel<PlaylistResource> toCollectionModel(Iterable<? extends Playlist> entities) {
    return super.toCollectionModel(entities);
  }
}
